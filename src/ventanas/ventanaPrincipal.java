package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logicaNegocio.logicaConjuntaMiNave;
import logicaNegocio.logicaCorazones;
import logicaNegocio.logicaFotoMiNave;
import logicaNegocio.logicaArmas.armaConjunto;
import logicaNegocio.logicaArmas.logicaMovimientoArma;
import logicaNegocio.movimiento.logicaCoheteDerecho;
import logicaNegocio.movimiento.logicaCoheteIzquierdo;
import logicaNegocio.movimiento.logicaMovimientoMiNave;
import logicaVentanas.logicaFondos;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import pruebas.ventanaPruebas;
import ventanasEspeciales.transiciones.ventanaGuardado;


public class ventanaPrincipal  implements KeyListener, ActionListener {

	public JFrame frame;
	public static ventanaPrincipal window;
	
	public static int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static logicaFondos fondoJuego;
    private String rutaFondo;
    public static logicaFondos fondoControles;
    public static JPanel panelCorazones;
    
    private JButton bSalir;
	private JButton bPause;
	
	//valores de la nave
	public static logicaConjuntaMiNave naveConjunta;
	boolean teclasMovimientoNave [] = new boolean[3];
	public static armaConjunto unProyectil;
	//cohetes
	public static logicaCoheteIzquierdo coheteIzquierdo;
	public static logicaCoheteDerecho coheteDerecho;
	
	//mis cosas de lo laser
	//arrayList de los lasers que voy disparando
	public static ArrayList<armaConjunto> misProyectiles = new ArrayList<armaConjunto>();
		

	//elemento hilos
	logicaMovimientoMiNave movimiento = new logicaMovimientoMiNave();
	logicaMovimientoArma movimientoArma = new logicaMovimientoArma();
	
	//boolean para que funcionen los hilos
	public static boolean funcionar= true;
	//Logica para pausar los hilos
	public static boolean pausar=true;
	//Logica Hilos del Arma
	public static boolean disparo = false;
	
	//Boolean para el boton pausa
	boolean contador;
	
	//gestion de los corazones
	public static logicaCorazones corazon;
	public static int vida;
	public static int especialVida ;

	/**
	 * Create the application.
	 */
	public ventanaPrincipal(String rutaFondo, int vidas) {
		
		this.rutaFondo=rutaFondo;
		if(vidas>=7){
			especialVida=4;
		}else if((vidas<7)&&(vidas>4)){
			especialVida=3;
		}else if((vidas<5)&&(vidas>2)){
			especialVida=2;
		}else{
			especialVida=1;
		}
		this.vida=vidas;
		//instanciamos mi nave y la colocamos en la posicion inicial
		naveConjunta = new logicaConjuntaMiNave();
		naveConjunta.setPosX((int) Math.floor(ancho * 0.5));
		naveConjunta.getFotoNave();
		naveConjunta.setPosY((int) Math.floor(alto * 0.80)-logicaFotoMiNave.getTAMAÑO());
		
		//damos formato al fondo del juego
		fondoJuego= new logicaFondos(rutaFondo);
		fondoJuego.setLayout(null);

		//cohetes
		coheteDerecho = new logicaCoheteDerecho();
		coheteDerecho.setLocation((int) Math.floor(ancho * 0.5)+34, (int) Math.floor(alto * 0.755));

		coheteIzquierdo= new logicaCoheteIzquierdo();
		coheteIzquierdo.setLocation((int) Math.floor(ancho * 0.5)-coheteIzquierdo.getWidth()-34, (int) Math.floor(alto * 0.755));
		
		//hilos
		movimiento.movimientoBase();
		movimientoArma.movimentoBaseArma();

		initialize();
		
		//metemos los corazones
		//logicaFondos corazon1 = new logicaFondos("/archivos/escudos/completo.png");
		corazon= new logicaCorazones(vida);
		
		ventanaMenu.ln.identificarNivel();
		//metodos para recuperar el foco y que las teclas funcionan
		fondoJuego.setFocusable(true);
		fondoJuego.requestFocus();
		fondoJuego.addKeyListener(this);
		fondoJuego.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				fondoJuego.requestFocus();
			}
		});

		//iniciamos las variables del boton pausa
		pausar=true;
		contador=true;
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		fondoJuego.setLayout(null);
		fondoJuego.requestFocus();
		frame.getContentPane().add(fondoJuego);
		
		fondoControles  = new logicaFondos("/archivos/imagenes/controles1.jpg");
		fondoControles.setBorder(new LineBorder(Color.BLUE, 3));
		fondoControles.setSize(ancho, (int) Math.floor(alto * 0.2));
		fondoControles.setBounds(0, (int) Math.floor(alto * 0.80), fondoControles.getWidth(), fondoControles.getHeight());
		fondoControles.setLayout(null);
		fondoJuego.add(fondoControles);
		
		
		//ponemos la imagen de la nave
		fondoJuego.add(naveConjunta.getFotoNave());
		//ponemos los cohetes
		fondoJuego.add(coheteDerecho);
		fondoJuego.add(coheteIzquierdo);
		
		panelCorazones = new JPanel();
		panelCorazones.setLayout(null);
		panelCorazones.setBorder(new LineBorder(Color.BLUE, 2));
		panelCorazones.setSize(190,60);
		panelCorazones.setBounds( (int) Math.floor(fondoControles.getWidth() * 0.1), (int) Math.floor(fondoControles.getHeight()* 0.55), panelCorazones.getWidth(), panelCorazones.getHeight());
		panelCorazones.setOpaque( false );
		fondoControles.add(panelCorazones);
	
		//Ponemos los botones
		bSalir = new JButton("");
		bSalir.setSize(62,65);
		bSalir.setBounds((int) Math.floor(fondoControles.getWidth() * 0.9)-bSalir.getWidth(), (int) Math.floor(fondoControles.getHeight() * 0.3), bSalir.getWidth(), bSalir.getHeight());
		bSalir.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/archivos/iconos/exit.png")));
		bSalir.addActionListener(this);
		fondoControles.add(bSalir);
		
		bPause = new JButton("");
		bPause.setSize(62,65);
		bPause.setBounds((int) Math.floor(fondoControles.getWidth() * 0.9)+bPause.getWidth(), (int) Math.floor(fondoControles.getHeight() * 0.3), bSalir.getWidth(), bSalir.getHeight());
		bPause.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/archivos/iconos/pause.png")));
		bPause.addActionListener(this);
		fondoControles.add(bPause);
		
	}

	/**
	 * Metodo para escuchar eventos en la ventana
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bSalir){
			String ultimoPlaneta=ventanaMenu.ln.miNivel();
			ventanaCampaña.miPartida.setUltimoPlaneta(ultimoPlaneta);

			System.out.println("SALIR");
			window.frame.dispose();
			funcionar=false;
			ventanaGuardado.window.frame.setVisible(true);
		}else if(e.getSource()==bPause){
			
			if(contador==true){//pausamos hilos
				fondoJuego.setBorder(new LineBorder(Color.RED, 4));
				contador=false;
				pausar=false;
			}else{
				fondoJuego.setBorder(null);
				contador=true;
				pausar=true;//despausamos los hilos
				
			}
		}
	}

	/**
	 * Metodo para pulsaciones de teclado
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			movimiento.setTeclasMovimientoNave(0,true);
		}
			
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			movimiento.setTeclasMovimientoNave(1,true);
		}
			
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			movimientoArma.setTeclasMovimientoNave(2,true);
		}
		
	}

	/**
	 * Metodo para escuchar despulsaciones de tecla
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			movimiento.setTeclasMovimientoNave(0,false);
		}

		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			movimiento.setTeclasMovimientoNave(1,false);
		}

		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			movimientoArma.setTeclasMovimientoNave(2,false);
			disparo=true;
		}
			
	}

	/**
	 * Metodo para escuchar pulsaciones de teclas especiales
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

