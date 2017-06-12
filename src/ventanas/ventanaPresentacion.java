package ventanas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;

import javax.swing.JTextArea;
import javax.swing.BoxLayout;

import ventanasEspeciales.transiciones.saltoEspacial1;
import ventanasEspeciales.videos.reproductor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ventanaPresentacion implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaPresentacion window;
	public static logicaMusica musica;
	
	private logicaFondos panelFondo;
	
	private JButton btnLanzar;
	private JButton btnContinuar;
	private JButton btnAtras;
	
	private  JTextArea texto;
	
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaPresentacion();
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
	public ventanaPresentacion() {
		initialize();
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
		    
		    panelFondo= new logicaFondos("/archivos/imagenes/puentePresentacion.png");
		    frame.getContentPane().add(panelFondo);
		    panelFondo.setLayout(null);
		    
		    JPanel panelTexto = new JPanel ();
			panelTexto.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTexto.setSize(600, 150);
			panelTexto.setBounds(ancho / 2, (int) Math.floor(alto * 0.70),panelTexto.getWidth(), panelTexto.getHeight());
			panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.X_AXIS));
			panelFondo.add(panelTexto);

		    // create the middle panel components
			texto = new JTextArea ( 12,0 );
		    texto.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
		    texto.setLineWrap(true); 
		    texto.setWrapStyleWord(true); 
		    texto.setCaretPosition(0);
		    texto.setText("Bienvenido piloto, en su informe pone que se llama: , está presente para llevar "
		    		+ "la misión mas importante hasta la fecha para nuestra raza, los: . La misión no es otra "
		    		+ "que encontrar la tierra. Como ya sabe desde que se cerró el agujero de gusano no hemos "
		    		+ "vuelto a saber nada de nuestro hogar. Creemos que actualmente es posible llegar al sistema solar, "
		    		+ "no sin recorrer una gran distancia y peligro. Tendrás que dirigirte a al otro rincón de la galaxia "
		    		+ "para encontrar tu objetivo. La idea es hacer un gran salto espacial que nos deje lo mas cerca posible del sistema solar. "
		    		+ "Te entregamos la nave 'Zapador', la mas rapida de la flota, la cual tiene integrado un motor con el nuevo mecanismo"
		    		+ " de salto estelar");
		    texto.setEditable ( false ); // set textArea non-editable
		    JScrollPane scroll = new JScrollPane ( texto );
		    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		    panelTexto.add ( scroll );
		    
			btnLanzar = new JButton("Lanzamiento");
			btnLanzar.setSize(260,50);
			btnLanzar.setFont(new Font("Arial Black", Font.PLAIN, 30));
			btnLanzar.setBorder(new LineBorder(Color.GREEN, 3));
			btnLanzar.setBounds((int) Math.floor(ancho*0.05), (int) Math.floor(alto*0.90), btnLanzar.getWidth(), btnLanzar.getHeight());
			btnLanzar.addActionListener(this);
			
			
			btnContinuar = new JButton("continuar");
			btnContinuar.setSize(260,50);
			btnContinuar.setFont(new Font("Arial Black", Font.PLAIN, 30));
			btnContinuar.setBounds(ancho / 2+btnContinuar.getWidth(), (int) Math.floor(alto * 0.90),btnContinuar.getWidth(), btnContinuar.getHeight());
			btnContinuar.addActionListener(this);
			panelFondo.add(btnContinuar);
			
			btnAtras = new JButton("Atras");
			btnAtras.setSize(200,50);
			btnAtras.setFont(new Font("Arial Black", Font.PLAIN, 30));
			btnAtras.setBounds((int) Math.floor(ancho*0.40)-btnAtras.getWidth(), (int) Math.floor(alto*0.90), btnAtras.getWidth(), btnAtras.getHeight());
			btnAtras.addActionListener(this);
			panelFondo.add(btnAtras);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnContinuar){
		
			texto.setText("Hemos identificado multiples civilizaciones ostiles de cerca del sistema solar, parece que se enfrentan entre ellas, "
		    		+ "de la tierra no hemos recibido ninguna señal. "
		    		+ "Tengo que informarte que el  motor estelar de ultima generacion de esta nave aun no se ha probado asi que el"
		    		+ "salto puede ser un poco brusco y hay riesgo de implosion o sobre calentamiento del nucleo."
		    		+ "Suerte piloto. "
		    		+ "Cuando estes listo presinona el boton verde para sentarte en el asiento y comenzar tu aventura. ");
			panelFondo.add(btnLanzar);
			btnContinuar.setVisible(false);
			frame.invalidate();
			frame.validate();
			frame.repaint();
		}else if( e.getSource() == btnLanzar){
			window.frame.dispose();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						saltoEspacial1.window = new saltoEspacial1();
						saltoEspacial1.window.frame.setVisible(true);		
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}else if( e.getSource() == btnAtras){
			window.frame.dispose();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaCampaña.window.frame.setVisible(true);		
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
