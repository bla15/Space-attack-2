package ventanasEspeciales.transiciones;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logicaVentanas.logicaFondos;
import ventanas.ventanaCampaña;
import ventanas.ventanaEmergencia;
import ventanas.ventanaLogging;
import ventanas.ventanaMenu;
import ventanas.ventanaVideoPresent.hiloVideo;

public class saltoEspacial1 implements KeyListener, ActionListener{

	public JFrame frame;
	public static saltoEspacial1 window;
	
	private logicaFondos panelFondo1;
	private logicaFondos panelFondo2;
	private logicaFondos panelFondo3;
	private JPanel panelLlegada;
	private JLabel lblTitulo;
	
	private int hilosSegundos =0;
	private int cuentaAtras =11;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    private JButton btnContinuar;

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
		
		panelFondo1= new logicaFondos("/archivos/imagenes/cabinaSalto.jpg");
		panelFondo1.setLayout(null);
		frame.getContentPane().add(panelFondo1);
		
		lblTitulo= new JLabel("");
		lblTitulo.setSize(540,100);
		lblTitulo.setBounds(ancho/2-lblTitulo.getWidth()/2, (int) Math.floor(alto*0.20), 540,100);
		lblTitulo.setForeground(Color.blue);
		lblTitulo.setFont(new Font("Snap ITC", Font.PLAIN, 45));
		lblTitulo.setHorizontalAlignment(lblTitulo.CENTER);
		lblTitulo.setBorder(new LineBorder(Color.BLUE, 3));
		
	}
	public void salto(){
		panelFondo2= new logicaFondos("/archivos/gif/salto1.gif");
		panelFondo2.setLayout(null);
		frame.getContentPane().add(panelFondo2);
		
		panelFondo3= new logicaFondos("/archivos/imagenes/saltoCabina.png");
		panelFondo3.setBounds(0,0,ancho-1,alto-1);
		panelFondo3.setLayout(null);
		panelFondo2.add(panelFondo3);
		
		panelFondo1.setVisible(false);	
	}
	public void llegada(){
		panelFondo2.setCambio("/archivos/imagenes/fondo1.jpg");
		
		btnContinuar = new JButton("continuar");
		btnContinuar.setSize(260,50);
		btnContinuar.setBackground(Color.GREEN);
		btnContinuar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnContinuar.setBounds(ancho / 2+btnContinuar.getWidth(), (int) Math.floor(alto * 0.90),btnContinuar.getWidth(), btnContinuar.getHeight());
		btnContinuar.addActionListener(this);
		panelFondo3.add(btnContinuar);
		
		frame.invalidate();
		frame.validate();
		frame.repaint();	
	}
	
	public class hiloVideo extends Thread{
		public void run(){
			lblTitulo.setText(String.valueOf(cuentaAtras));
			panelFondo1.add(lblTitulo);
			while(hilosSegundos<19){
				hilosSegundos++;
				
				if(cuentaAtras==11){
					//ventanaLogging.musica.cargarCancion("archivos\\musica\\cuentaAtras.wav");
					//ventanaLogging.musica.reproducirCancion();
					lblTitulo.setText(String.valueOf(cuentaAtras));
				}else if(cuentaAtras==0){
					lblTitulo.setVisible(false);
					salto();
				}else if(cuentaAtras==-2){
					System.out.println(cuentaAtras);
					llegada();
				}
				cuentaAtras--;	
				try {
					lblTitulo.setText(String.valueOf(cuentaAtras));
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//panelFondo.add(lblTitulo);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnContinuar){
			window.frame.dispose();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaEmergencia.window = new ventanaEmergencia();
						ventanaEmergencia.window.frame.setVisible(true);		
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
