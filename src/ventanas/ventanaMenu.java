package ventanas;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import logicaNegocio.logicaNivel;
import logicaVentanas.logicaFondos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;


public class ventanaMenu implements KeyListener, ActionListener{

	public static logicaNivel ln;
	public JFrame frame;
	public static ventanaMenu window;
	
	private logicaFondos panelFondo;
	private logicaFondos asteroide1;
	
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private JButton btnCampaña;
    private JButton btnOpciones;
    private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaMenu();
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
	public ventanaMenu() {
		initialize();
		//ventanaLogging.musica.cargarCancion("archivos\\musica\\musicaMenu.wav");
		//ventanaLogging.musica.reproducirCancion();
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
		
		panelFondo= new logicaFondos("/archivos/gif/estrellas.gif");
		//panelFondo.setBorder(new LineBorder(Color.BLUE));
		//panelFondo.setSize(159,113);
		//panelFondo.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Space Attack 2");
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
		
		btnCampaña = new JButton("Campaña");
		btnCampaña.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnCampaña.setSize(200, 65);
		btnCampaña.setBounds(panelCentral.getWidth()/2-btnCampaña.getWidth()/2,(int) Math.floor(panelCentral.getWidth()*0.2),200,50);
		btnCampaña.addActionListener(this);
		panelCentral.setLayout(null);
		panelCentral.add(btnCampaña);
		
		btnOpciones = new JButton("Opciones");
		btnOpciones.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnOpciones.setBounds(panelCentral.getWidth()/2-btnCampaña.getWidth()/2,(int) Math.floor(panelCentral.getWidth()*0.5),200,50);
		btnOpciones.addActionListener(this);
		panelCentral.add(btnOpciones);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnSalir.setBounds(panelCentral.getWidth()/2-btnCampaña.getWidth()/2,(int) Math.floor(panelCentral.getWidth()*0.8),200,50);
		btnSalir.addActionListener(this);
		panelCentral.add(btnSalir);
		
		asteroide1= new logicaFondos("/archivos/gif/ast1.gif");
		asteroide1.setSize(200,150);
		asteroide1.setBounds((int) Math.floor(ancho*0.10), (int) Math.floor(alto*0.20), 200,150);
		panelFondo.add(asteroide1);
		
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnCampaña) {
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
			
		}else if(e.getSource()==btnSalir) {
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
