package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import logicaNegocio.logicaConjuntaMiNave;
import logicaNegocio.logicaFotoMiNave;
import logicaVentanas.logicaFondos;

import javax.swing.border.LineBorder;

import java.awt.Color;


public class ventanaPrincipal  implements KeyListener, ActionListener {

	public static JFrame frame;
	public static ventanaPrincipal window;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static logicaFondos fondoJuego;
    private String rutaFondo;
    public static logicaFondos fondoControles;
    
    private JButton bSalir;
	private JButton bPause;
	
	//valores de la nave
	static logicaConjuntaMiNave naveConjunta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal window = new ventanaPrincipal("/archivos/imagenes/fondo1.jpg");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventanaPrincipal(String rutaFondo) {
		this.rutaFondo=rutaFondo;
		
		//instanciamos mi nave y la colocamos en la posicion inicial
		naveConjunta = new logicaConjuntaMiNave();
		naveConjunta.setPosX(623);
		naveConjunta.getFotoNave();
		naveConjunta.setPosY((int) Math.floor(alto * 0.80)-logicaFotoMiNave.getTAMAÑO());
		
		initialize();
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
		
		fondoJuego= new logicaFondos(rutaFondo);
		fondoJuego.setLayout(null);
		frame.getContentPane().add(fondoJuego);
		
		fondoControles  = new logicaFondos("/archivos/imagenes/controles1.jpg");
		fondoControles.setBorder(new LineBorder(Color.BLUE, 3));
		fondoControles.setSize(ancho, (int) Math.floor(alto * 0.2));
		fondoControles.setBounds(0, (int) Math.floor(alto * 0.80), fondoControles.getWidth(), fondoControles.getHeight());
		fondoJuego.add(fondoControles);
		fondoJuego.setLayout(null);
		fondoJuego.requestFocus();
		
		//ponemos la imagen de la nave
		fondoJuego.add(naveConjunta.getFotoNave());
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
