package ventanas;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.border.LineBorder;

import logicaNegocio.logicaMusica;
import logicaVentanas.JPanelGradient;
import logicaVentanas.logicaFondos;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import ventanasEspeciales.videos.reproductor;
import BD.usersDB;


public class ventanaLogging implements KeyListener, ActionListener {
	
	private usersDB conectarDBUsers = new usersDB();
	public JFrame frame;
	public static ventanaLogging window;
	public static logicaMusica musica;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    private JTextField textNick;
    private JPasswordField textPass;
    
    private JButton btnEntrar;
    private JButton btnRegistrarse; 
    private JButton btnSalir;
    
    static String[] argumentos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		argumentos = args;
		System.out.println(args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaLogging();
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
	public ventanaLogging() {
		conectarDBUsers.conectarBD();
		initialize();
		musica = new logicaMusica();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Personalizamos el frame
		 */
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((JComponent) frame.getContentPane()).setBorder(new LineBorder(Color.BLUE, 3));
		frame.setSize(700,450); 
		frame.setBounds( (ancho/2)-frame.getWidth()/2, (alto/2)-frame.getHeight()/2, 700,450);
		frame.setResizable(false); 
		
		logicaFondos fotoLogging = new logicaFondos("/imagenes/logg.jpg");
		fotoLogging.setBorder(new LineBorder(Color.BLUE));
		fotoLogging.setSize(159,113);
		fotoLogging.setBounds((int)Math.floor(frame.getWidth()*0.9)-fotoLogging.getWidth(), (int)Math.floor(frame.getHeight()*0.1), 159, 113);
		frame.getContentPane().add(fotoLogging);
		
		logicaFondos fotoSolLogging = new logicaFondos("/imagenes/sol.png");
		fotoSolLogging.setSize(200,150);
		fotoSolLogging.setBounds(3, 3, 200, 150);
		frame.getContentPane().add(fotoSolLogging);
		
		JPanelGradient panelCentral = new  JPanelGradient();
		panelCentral.setSize(393,147);
		panelCentral.setBounds(frame.getWidth()/2-panelCentral.getWidth()/2, ((int)Math.floor(frame.getHeight()*0.6))-panelCentral.getHeight()/2,393,147);
		frame.getContentPane().add(panelCentral);
		panelCentral.setLayout(null);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setSize(46,24);
		lblNick.setBounds(((int)Math.floor(panelCentral.getWidth()*0.1)), ((int)Math.floor(panelCentral.getHeight()*0.25)), 46, 24);
		lblNick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNick.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNick.setForeground(Color.WHITE);
		panelCentral.add(lblNick);
		
		JLabel lblPass = new JLabel("Password:");
		lblPass.setSize(46,24);
		lblPass.setBounds(((int)Math.floor(panelCentral.getWidth()*0.1)), ((int)Math.floor(panelCentral.getHeight()*0.55)), 87, 24);
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblPass.setForeground(Color.WHITE);
		panelCentral.add(lblPass);
		
		textNick = new JTextField();
		textNick.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textNick.setSize(125,20);
		textNick.setBounds(176, 36, 125, 25);
		panelCentral.add(textNick);
		textNick.setColumns(10);
		
		textPass = new JPasswordField();
		textPass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textPass.setColumns(10);
		textPass.setSize(125,20);
		textPass.setBounds(176, 85, 125, 20);
		panelCentral.add(textPass);
		
		btnEntrar= new JButton("");
		btnEntrar.setIcon(new ImageIcon(ventanaLogging.class.getResource("/imagenes/botones/btnAdelante.png")));
		btnEntrar.setBounds(410, 376, 100, 23);
		btnEntrar.addActionListener(this);
		frame.getContentPane().add(btnEntrar);
		
		btnRegistrarse= new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
		btnRegistrarse.setBounds(185, 376, 134, 23);
		btnRegistrarse.addActionListener(this);
		frame.getContentPane().add(btnRegistrarse);
		
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(ventanaLogging.class.getResource("/imagenes/botones/btnSalir.png")));
		btnSalir.setSize(48, 47);
		btnSalir.setBounds(10,((int)Math.floor(frame.getHeight()*0.98)-btnSalir.getHeight()), 48, 47);
		btnSalir.addActionListener(this);
		frame.getContentPane().add(btnSalir);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnEntrar) {
			String nick = textNick.getText();
			String password = new String(textPass.getPassword());
			if(conectarDBUsers.loggingBD(nick, password)){
				window.frame.dispose();
				String ruta = "archivos\\videos\\videoPresentacion.mp4";
				musica.acabarCancion();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							reproductor.window = new reproductor(ruta);
							reproductor.window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}else if(e.getSource()==btnRegistrarse){
			 try {

		            Desktop.getDesktop().browse(new URI("http://space.attack.2/app_dev.php/"));

		        } catch (URISyntaxException ex) {

		            System.out.println(ex);

		        }catch(IOException e1){

		            System.out.println(e1);

		        }
		      
			
		}else if(e.getSource()==btnSalir){
			musica.acabarCancion();
			System.exit(0);
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
