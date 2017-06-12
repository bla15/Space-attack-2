package ventanasEspeciales.transiciones;

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
import ventanas.ventanaLogging;
import ventanas.ventanaMenu;
import ventanasEspeciales.transiciones.saltoEspacial1.hiloVideo;
import logicaVentanas.logicaFondos;

public class saltoEstandar implements KeyListener, ActionListener{

	public JFrame frame;
	public static saltoEstandar window;
	
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
    
    //ruta fondo destino
    private String rutaDestino;
    
    //bolean que controla el hilo
    boolean funcionar = true;
    
    //Ruta llegada
    String ruta2;
    
    //booleano que controla si se trata de una partida cargada
    boolean carga;
    
    // hilos
 		hiloVideo miVideo = new hiloVideo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new saltoEstandar("/archivos/mapas/fondo2.jpg", false);
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
	public saltoEstandar( String ruta2, boolean carga) {
		this.ruta2=ruta2;
		this.carga=carga;
		initialize();

	}

	public void cambioFondoPrimero(){
		
	}
	
	public String getRutaDestino() {
		return rutaDestino;
	}

	public void setRutaDestino(String rutaDestino) {
		this.rutaDestino = rutaDestino;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(new ImageIcon(getClass().getResource("/archivos/imagenes/SpaceAttack.png")).getImage());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		panelFondo1= new logicaFondos("/archivos/mapas/salto1.jpg");
		panelFondo1.setLayout(null);
		frame.getContentPane().add(panelFondo1);
		
		lblTitulo= new JLabel("");
		lblTitulo.setSize(540,100);
		lblTitulo.setBounds(ancho/2-lblTitulo.getWidth()/2, (int) Math.floor(alto*0.20), 540,100);
		lblTitulo.setForeground(Color.blue);
		lblTitulo.setFont(new Font("Snap ITC", Font.PLAIN, 45));
		lblTitulo.setHorizontalAlignment(lblTitulo.CENTER);
		lblTitulo.setBorder(new LineBorder(Color.BLUE, 3));
		
		
		panelFondo2= new logicaFondos("/archivos/gif/salto1.gif");
		panelFondo2.setVisible(false);
		panelFondo3= new logicaFondos("/archivos/imagenes/saltoCabina.png");
		panelFondo3.setBounds(0,0,ancho-1,alto-1);
		panelFondo3.setVisible(false);
		
		btnContinuar = new JButton("continuar");
		btnContinuar.setSize(260,50);
		btnContinuar.setBackground(Color.GREEN);
		btnContinuar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnContinuar.setBounds(ancho / 2+btnContinuar.getWidth(), (int) Math.floor(alto * 0.90),btnContinuar.getWidth(), btnContinuar.getHeight());
		btnContinuar.addActionListener(this);
	}
	
	public void comenzar(){
		funcionar=true;
		hilosSegundos =0;
		cuentaAtras =11;
		miVideo.start();
	}
	
	public void reutilizar(String ruta1, String ruta2){
		if(carga==true){
			System.out.println("partida cargada");
			panelFondo1.setCambio(ruta1);
			this.ruta2=ruta2;
			funcionar=true;
			
			hilosSegundos =0;
			cuentaAtras =11;
			miVideo.start();
			carga=false;
		}else{
			hilosSegundos =0;
			cuentaAtras =11;
			panelFondo1.setCambio(ruta1);
			panelFondo1.setVisible(true);
			panelFondo2.setCambio("/archivos/gif/salto1.gif");
			panelFondo2.setVisible(false);
			panelFondo3.setVisible(false);
			btnContinuar.setVisible(false);
			funcionar=true;
			hilosSegundos =0;
			cuentaAtras =11;
			this.ruta2=ruta2;
			lblTitulo.setVisible(true);
		}
	}
	
	public void salto(){
		panelFondo2.setLayout(null);
		panelFondo2.setVisible(true);
		frame.getContentPane().add(panelFondo2);
		
		panelFondo3.setVisible(true);
		panelFondo3.setLayout(null);
		panelFondo2.add(panelFondo3);
		
		panelFondo1.setVisible(false);	
	}
	public void llegada(String ruta2){
		panelFondo2.setCambio(ruta2);
		
		panelFondo3.add(btnContinuar);
		btnContinuar.setVisible(true);
		
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnContinuar){
			window.frame.dispose();
			System.out.println("Entramos");
			ventanaMenu.ln.mapaNuevo();
			funcionar=false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public class hiloVideo extends Thread{
		public void run(){
			lblTitulo.setText(String.valueOf(cuentaAtras));
			panelFondo1.add(lblTitulo);
			while(true){
				try {
					lblTitulo.setText(String.valueOf(cuentaAtras));
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(funcionar==true){
					try {
						lblTitulo.setText(String.valueOf(cuentaAtras));
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					while(hilosSegundos<19){
						System.out.println(hilosSegundos);
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
							llegada(ruta2);
						}
						cuentaAtras--;	
						try {
							lblTitulo.setText(String.valueOf(cuentaAtras));
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}

		}
	}
}
