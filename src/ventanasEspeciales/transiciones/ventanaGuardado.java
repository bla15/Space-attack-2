package ventanasEspeciales.transiciones;

import gestorDeNegocio.gestorPartida;
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
import javax.swing.border.LineBorder;

import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;
import ventanas.ventanaCampaña;
import ventanas.ventanaEmergencia;
import ventanas.ventanaLogging;
import ventanas.ventanaPrincipal;

public class ventanaGuardado implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaGuardado window;
	
	private logicaFondos panelFondo;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    //botones
    private JButton btnGuardar;
	private JButton btnNoGuardar;
	private JButton btnAtras;
	
	//elementos necesarios para poder guardar
	private gestorPartida GP = new gestorPartida();
	private boolean carga;

	/**
	 * Create the application.
	 */
	public ventanaGuardado(boolean carga) {
		initialize();
		this.carga=carga;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(750,500);
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		panelFondo= new logicaFondos("/archivos/imagenes/guardar.jpg");
		panelFondo.setBorder(new LineBorder(Color.WHITE, 4));
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);

		//ponemos el titulo
		JLabel titulo = new JLabel("Vas a abandonar la partida");
		titulo.setSize((int) Math.floor(frame.getWidth()*0.6),40);
		titulo.setBounds((int) Math.floor(frame.getWidth()*0.5)-titulo.getWidth()/2, (int) Math.floor(frame.getHeight()*0.1), titulo.getWidth(), titulo.getHeight());
		titulo.setOpaque(false);
		titulo.setBorder(new LineBorder(Color.WHITE, 4));
		titulo.setForeground(Color.white);
		titulo.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 50));
		titulo.setHorizontalAlignment(titulo.CENTER);
		panelFondo.add(titulo);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setSize(260,50);
		btnGuardar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnGuardar.setBorder(new LineBorder(Color.GREEN, 3));
		btnGuardar.setBounds((int) Math.floor(frame.getWidth()*0.6)-frame.getWidth()/2, (int) Math.floor(frame.getHeight()*0.5)+btnGuardar.getHeight()/2, btnGuardar.getWidth(), btnGuardar.getHeight());
		btnGuardar.addActionListener(this);
		panelFondo.add(btnGuardar);	
			
		btnNoGuardar = new JButton("No guardar");
		btnNoGuardar.setSize(260,50);
		btnNoGuardar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnNoGuardar.setBounds((int) Math.floor(frame.getWidth()*0.6), (int) Math.floor(frame.getHeight()*0.5)+btnNoGuardar.getHeight()/2, btnNoGuardar.getWidth(), btnNoGuardar.getHeight());
		btnNoGuardar.addActionListener(this);
		panelFondo.add(btnNoGuardar);
		
		btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(ventanaLogging.class.getResource("/archivos/botones/exit.png")));
		btnAtras.setSize(60, 55);
		btnAtras.setBounds((int) Math.floor(frame.getWidth()*0.9)-btnAtras.getWidth(), (int) Math.floor(frame.getHeight()*0.9)-btnAtras.getHeight()/2, btnAtras.getWidth(), btnAtras.getHeight());
		btnAtras.addActionListener(this);
		panelFondo.add(btnAtras);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnGuardar){
			if(carga==true){
				//guardamos los valores en la variable usuario
				ventanaCampaña.miPartida.setId_u(ventanaLogging.usuarioActual.getId_u());
				//ventanaCampaña.miPartida.setNombrePiloto("prueba");
				//ventanaCampaña.miPartida.setRaza("prueba");
				ventanaCampaña.miPartida.setDisparos(100);
				ventanaCampaña.miPartida.setDeads(50);
				ventanaCampaña.miPartida.setScore();
				ventanaCampaña.miPartida.setLife(ventanaPrincipal.vida);
				//ventanaCampaña.miPartida.setStatus(false);
				java.util.Date utilDate = new java.util.Date();
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				ventanaCampaña.miPartida.setCreated_at(sqlDate);
				ventanaCampaña.miPartida.setUpdated_at(sqlDate);
			}else if(carga==false){
				
				//guardamos los valores en la variable usuario
				ventanaCampaña.miPartida.setId_u(ventanaLogging.usuarioActual.getId_u());
				//ventanaCampaña.miPartida.setNombrePiloto("prueba");
				//ventanaCampaña.miPartida.setRaza("prueba");
				//ventanaCampaña.miPartida.setDisparos(100);
				//ventanaCampaña.miPartida.setDeads(50);
				ventanaCampaña.miPartida.setScore();
				ventanaCampaña.miPartida.setLife(ventanaPrincipal.vida);
				//ventanaCampaña.miPartida.setStatus(flase);
				java.util.Date utilDate = new java.util.Date();
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				ventanaCampaña.miPartida.setCreated_at(sqlDate);
				ventanaCampaña.miPartida.setUpdated_at(sqlDate);
			}
			
			
			//guardamos la partida en la BD
			try {
				GP.addPartida(ventanaCampaña.miPartida);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("ADIOS");
			System.exit(0);
		}else if( e.getSource() == btnNoGuardar){
			System.exit(0);
		}else if( e.getSource() == btnAtras){
			window.frame.dispose();
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
