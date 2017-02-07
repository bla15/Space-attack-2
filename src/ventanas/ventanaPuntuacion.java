package ventanas;

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

import ventanasEspeciales.transiciones.ventanaGuardado;
import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;

public class ventanaPuntuacion implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaPuntuacion window;
	public static logicaMusica musica;
	private logicaFondos panelFondo;

	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    //botones
    private JButton btnSalir;
    private JButton btnVolver;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaPuntuacion();
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
	public ventanaPuntuacion() {
		initialize();
		ventanaCampaña.miPartida.setStatus(true);
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
		
		panelFondo= new logicaFondos("/archivos/mapas/luna.jpg");
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		//ponemos el titulo
		JLabel titulo = new JLabel("Tan cerca pero tan lejos");
		titulo.setSize((int) Math.floor(ancho*0.3),40);
		titulo.setBounds((int) Math.floor(ancho*0.5)-titulo.getWidth()/2, (int) Math.floor(alto*0.01), titulo.getWidth(), titulo.getHeight());
		titulo.setOpaque(false);
		titulo.setForeground(Color.white);
		titulo.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 50));
		titulo.setHorizontalAlignment(titulo.CENTER);
		panelFondo.add(titulo);
		
		//botones
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(ventanaLogging.class.getResource("/archivos/botones/exit.png")));
		btnSalir.setSize(60, 55);
		btnSalir.setBounds((int)Math.floor(ancho*0.9),((int)Math.floor(alto*0.9)), btnSalir.getWidth(), btnSalir.getHeight());
		btnSalir.addActionListener(this);
		panelFondo.add(btnSalir);
		
		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(ventanaLogging.class.getResource("/archivos/botones/volver.png")));
		btnVolver.setSize(60, 55);
		btnVolver.setBounds((int)Math.floor(ancho*0.85),((int)Math.floor(alto*0.9)), btnVolver.getWidth(), btnVolver.getHeight());
		btnVolver.addActionListener(this);
		panelFondo.add(btnVolver);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnSalir){
			ventanaGuardado.window.frame.setVisible(true);
			
		}else if( e.getSource() == btnVolver){
			window.frame.dispose();
			ventanaMenu.window.frame.setVisible(true);
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
