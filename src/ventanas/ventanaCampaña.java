package ventanas;

import gestorDeNegocio.partida;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pruebas.pruebasCargarPartida;
import ventanasEspeciales.videos.reproductor;
import logicaVentanas.logicaFondos;

public class ventanaCampaña implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaCampaña window;
	
	private logicaFondos panelFondo;
	private logicaFondos asteroide1;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private JButton btnNuevaCampaña;
    private JButton btnCargarCampaña;
    private JButton btnAtras;
    
    static String[] argumentos;
    
    //objeto partida
    public static partida miPartida;
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		argumentos = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaCampaña();
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
	public ventanaCampaña() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setIconImage(new ImageIcon(getClass().getResource("/archivos/imagenes/SpaceAttack.png")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		panelFondo= new logicaFondos("/archivos/gif/planeta.gif");
		//panelFondo.setBorder(new LineBorder(Color.BLUE));
		//panelFondo.setSize(159,113);
		//panelFondo.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modo Campaña");
		lblTitulo.setSize(540,100);
		lblTitulo.setBounds(ancho/2-lblTitulo.getWidth()/2, (int) Math.floor(alto*0.20), 540,100);
		lblTitulo.setForeground(Color.blue);
		lblTitulo.setFont(new Font("Snap ITC", Font.PLAIN, 45));
		lblTitulo.setHorizontalAlignment(lblTitulo.CENTER);
		lblTitulo.setBorder(new LineBorder(Color.BLUE, 3));
		panelFondo.add(lblTitulo);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setSize((int) Math.floor(ancho*0.25),(int) Math.floor(alto*0.50));
		panelCentral.setBounds(ancho/2-panelFondo.getWidth()/2-panelCentral.getWidth()/2,(int) Math.floor(alto*0.60)-panelCentral.getHeight()/2, (int) Math.floor(ancho*0.25),(int) Math.floor(alto*0.50));
		panelCentral.setOpaque(false);
		panelFondo.add(panelCentral);
		
		btnNuevaCampaña = new JButton("Nueva Campaña");
		btnNuevaCampaña.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnNuevaCampaña.setSize(350, 65);
		btnNuevaCampaña.setBounds(panelCentral.getWidth()/2-btnNuevaCampaña.getWidth()/2,(int) Math.floor(panelCentral.getWidth()*0.2),350,50);
		btnNuevaCampaña.addActionListener(this);
		panelCentral.setLayout(null);
		panelCentral.add(btnNuevaCampaña);
		
		btnCargarCampaña = new JButton("Cargar campaña");
		btnCargarCampaña.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnCargarCampaña.setBounds(panelCentral.getWidth()/2-btnNuevaCampaña.getWidth()/2,(int) Math.floor(panelCentral.getWidth()*0.5),350,50);
		btnCargarCampaña.addActionListener(this);
		panelCentral.add(btnCargarCampaña);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnAtras.setSize(200,50);
		btnAtras.setBounds(panelCentral.getWidth()/2-btnAtras.getWidth()/2,(int) Math.floor(panelCentral.getWidth()*0.8),200,50);
		btnAtras.addActionListener(this);
		panelCentral.add(btnAtras);
		
		asteroide1= new logicaFondos("/archivos/gif/sun.gif");
		asteroide1.setSize(300,250);
		asteroide1.setBounds(0,0, 300,250);
		panelFondo.add(asteroide1);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNuevaCampaña) {
			//creamos el objeto partida
			miPartida=new partida();
			miPartida.setDisparos(0);
			miPartida.setDeads(0);
			miPartida.setLife(8);
			miPartida.setStatus(false);
			//la ventan se oculta
			window.frame.dispose();
			
			//acabamos con la musica
			ventanaLogging.musica.acabarCancion();
			String ruta = "archivos\\videos\\starCampaign.mp4";
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						reproductor.window.frame.setVisible(true);
						reproductor.window.reproductor(ruta,2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}else if(e.getSource()==btnCargarCampaña) {
			miPartida=new partida();
			window.frame.dispose();
			//ventanaLogging.musica.acabarCancion();
			//pruebasCargarPartida mp = new pruebasCargarPartida();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaCargaPartida.window = new ventanaCargaPartida();
						ventanaCargaPartida.window.frame.setVisible(true);	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			
		}else if(e.getSource()==btnAtras) {
			window.frame.dispose();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaMenu.window = new ventanaMenu();
						ventanaMenu.window.frame.setVisible(true);		
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
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
