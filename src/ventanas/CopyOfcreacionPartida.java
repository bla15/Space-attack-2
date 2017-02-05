package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logicaNegocio.partidasLN;
import logicaVentanas.logicaFondos;

import javax.swing.BoxLayout;

import ventanasEspeciales.videos.reproductor;

import java.awt.FlowLayout;

public class CopyOfcreacionPartida implements KeyListener, ActionListener{

	public JFrame frame;
	public static CopyOfcreacionPartida window;
	private logicaFondos panelFondo;

	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

	private JTextField textNombre;
	
	private logicaFondos panelAmarr;
	private JButton btnAmarr;
	private logicaFondos panelGallente;
	private JButton btnGallente;
	private logicaFondos panelCaldari;
	private JButton btnCaldari;
	private logicaFondos panelMinmatar;
	private JButton btnMinmatar;
	
	private JButton btnContinuar;
	private JButton btnAtras;
	
	public static partidasLN miPartida = new partidasLN();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new CopyOfcreacionPartida();
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
	public CopyOfcreacionPartida() {
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
		
		panelFondo= new logicaFondos("/archivos/gif/planeta2.gif");
		//panelFondo.setBorder(new LineBorder(Color.BLUE));
		//panelFondo.setSize(159,113);
		//panelFondo.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Crea tu piloto");
		lblTitulo.setSize(400,80);
		lblTitulo.setBounds(ancho/2-lblTitulo.getWidth()/2, (int) Math.floor(alto*0.20), 400,80);
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		lblTitulo.setHorizontalAlignment(lblTitulo.CENTER);
		lblTitulo.setBorder(new LineBorder(Color.red, 3));
		panelFondo.add(lblTitulo);
		
		JPanel panelCentral = new JPanel();
		//panelCentral.setBorder(new LineBorder(Color.WHITE, 4));
		panelCentral.setSize((int) Math.floor(ancho*0.40),(int) Math.floor(alto*0.15));
		panelCentral.setBounds(ancho/2-panelFondo.getWidth()/2-panelCentral.getWidth()/2,(int) Math.floor(alto*0.40)-panelCentral.getHeight()/2, panelCentral.getWidth(),panelCentral.getHeight());
		panelCentral.setOpaque(false);
		panelCentral.setLayout(null);
		panelFondo.add(panelCentral);
		
		JLabel lblNombre = new JLabel("Nombre piloto:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNombre.setSize(200,34);
		lblNombre.setBounds(panelCentral.getWidth()/2-lblNombre.getWidth()-10,panelCentral.getHeight()/2-lblNombre.getHeight()/2,lblNombre.getWidth(),lblNombre.getHeight());
		panelCentral.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		textNombre.setSize(150,34);
		textNombre.setBounds(panelCentral.getWidth()/2+10,panelCentral.getHeight()/2-textNombre.getHeight()/2,textNombre.getWidth(),textNombre.getHeight());
		panelCentral.add(textNombre);
		
		panelAmarr= new logicaFondos("/archivos/imagenes/escudoAmarr.png");
		panelAmarr.setSize(300,263);
		panelAmarr.setBounds((int) Math.floor(ancho*0.23)-panelAmarr.getWidth(), (int) Math.floor(alto*0.50), panelAmarr.getWidth(), panelAmarr.getHeight());
		panelFondo.add(panelAmarr);
		panelAmarr.setLayout(null);
	
		btnAmarr = new JButton("");
		btnAmarr.setSize(300, 263);
		btnAmarr.setBounds(0,0,btnAmarr.getWidth(),btnAmarr.getHeight());
		btnAmarr.setOpaque(false);
		btnAmarr.setContentAreaFilled(false);
		//btnAmarr.setBorderPainted(false);
		btnAmarr.addActionListener(this);
		panelAmarr.add(btnAmarr);
		
		panelCaldari= new logicaFondos("/archivos/imagenes/escudoCaldari.png");
		panelCaldari.setSize(300,263);
		panelCaldari.setBounds((int) Math.floor(ancho*0.48)-panelCaldari.getWidth(), (int) Math.floor(alto*0.50), panelAmarr.getWidth(), panelAmarr.getHeight());
		panelFondo.add(panelCaldari);
		panelCaldari.setLayout(null);
		
		btnCaldari = new JButton("");
		btnCaldari.setSize(300, 263);
		btnCaldari.setBounds(0,0,btnCaldari.getWidth(),btnCaldari.getHeight());
		btnCaldari.setOpaque(false);
		btnCaldari.setContentAreaFilled(false);
		btnAmarr.setBorderPainted(false);
		btnCaldari.addActionListener(this);
		panelCaldari.add(btnCaldari);
		
		panelGallente= new logicaFondos("/archivos/imagenes/escudoGallente.png");
		panelGallente.setSize(300,263);
		panelGallente.setBounds((int) Math.floor(ancho*0.73)-panelGallente.getWidth(), (int) Math.floor(alto*0.50), panelAmarr.getWidth(), panelAmarr.getHeight());
		panelFondo.add(panelGallente);
		panelGallente.setLayout(null);
		
		btnGallente = new JButton("");
		btnGallente.setSize(300, 263);
		btnGallente.setBounds(0,0,btnGallente.getWidth(),btnGallente.getHeight());
		btnGallente.setOpaque(false);
		btnGallente.setContentAreaFilled(false);
		//btnAmarr.setBorderPainted(false);
		btnGallente.addActionListener(this);
		panelGallente.add(btnGallente);
		
		panelMinmatar= new logicaFondos("/archivos/imagenes/escudoMinmatar.jpg");
		panelMinmatar.setSize(300,263);
		panelMinmatar.setBounds((int) Math.floor(ancho*0.98)-panelGallente.getWidth(), (int) Math.floor(alto*0.50), panelAmarr.getWidth(), panelAmarr.getHeight());
		panelFondo.add(panelMinmatar);
		panelMinmatar.setLayout(null);
		
		btnMinmatar = new JButton("");
		btnMinmatar.setSize(300, 263);
		btnMinmatar.setBounds(0,0,btnMinmatar.getWidth(),btnMinmatar.getHeight());
		btnMinmatar.setOpaque(false);
		btnMinmatar.setContentAreaFilled(false);
		//btnAmarr.setBorderPainted(false);
		btnMinmatar.addActionListener(this);
		panelMinmatar.add(btnMinmatar);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setSize(200,50);
		btnContinuar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnContinuar.setBounds((int) Math.floor(ancho*0.60), (int) Math.floor(alto*0.90), btnContinuar.getWidth(), btnContinuar.getHeight());
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
		if(e.getSource()==btnAmarr) {
			panelAmarr.setBorder(new LineBorder(Color.BLUE, 4));
			panelCaldari.setBorder(null);
			panelGallente.setBorder(null);
			panelMinmatar.setBorder(null);
			miPartida.setRaza("Amarr");

		} else if (e.getSource() == btnCaldari) {
			panelAmarr.setBorder(null);
			panelCaldari.setBorder(new LineBorder(Color.BLUE, 4));
			panelGallente.setBorder(null);
			panelMinmatar.setBorder(null);
			miPartida.setRaza("Caldari");

		} else if (e.getSource() == btnGallente) {
			panelAmarr.setBorder(null);
			panelCaldari.setBorder(null);
			panelGallente.setBorder(new LineBorder(Color.BLUE, 4));
			panelMinmatar.setBorder(null);
			miPartida.setRaza("Gallente");

		} else if (e.getSource() == btnMinmatar) {
			panelAmarr.setBorder(null);
			panelCaldari.setBorder(null);
			panelGallente.setBorder(null);
			panelMinmatar.setBorder(new LineBorder(Color.BLUE, 4));
			miPartida.setRaza("Minmatar");

		} else if( e.getSource() == btnContinuar){
			if((textNombre.getText().length() != 0) && (miPartida.getRaza() !=  null)){
				miPartida.setNombreComandante(textNombre.getText());
				System.out.println("SUIIII");
				window.frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaPresentacion.window = new ventanaPresentacion();
							ventanaPresentacion.window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}else{
				if(textNombre.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "ERROR de nombre de piloto");
				}else{
					JOptionPane.showMessageDialog(null, "ERROR selecciona una raza");
				}
			}
		}else if( e.getSource() == btnAtras){
			window.frame.dispose();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaCampaña.window = new ventanaCampaña();
						ventanaCampaña.window.frame.setVisible(true);		
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub	
	}

}
