package ventanasEspeciales.transiciones;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import logicaVentanas.logicaFondos;
import ventanas.ventanaCampaña;
import ventanas.ventanaMenu;
import ventanas.ventanaVideoPresent.hiloVideo;

public class saltoEspacial1 {

	public JFrame frame;
	public static saltoEspacial1 window;
	
	private logicaFondos panelFondo;
	private JLabel lblTitulo;
	
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new saltoEspacial1();
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
	public saltoEspacial1() {
		initialize();
		
		// hilos
		hiloVideo miVideo = new hiloVideo();
		miVideo.start();
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
		
		panelFondo= new logicaFondos("/archivos/gif/saltoEspacial.gif");
		//panelFondo.setBorder(new LineBorder(Color.BLUE));
		//panelFondo.setSize(159,113);
		//panelFondo.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		lblTitulo= new JLabel("Modo Campaña");
		lblTitulo.setSize(540,100);
		lblTitulo.setBounds(ancho/2-lblTitulo.getWidth()/2, (int) Math.floor(alto*0.20), 540,100);
		lblTitulo.setForeground(Color.blue);
		lblTitulo.setFont(new Font("Snap ITC", Font.PLAIN, 45));
		lblTitulo.setHorizontalAlignment(lblTitulo.CENTER);
		lblTitulo.setBorder(new LineBorder(Color.BLUE, 3));
		
	}
	
	public class hiloVideo extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("SUII");
				
				panelFondo.add(lblTitulo);
				
			}
		}
	}

}
