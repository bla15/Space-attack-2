package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;

import javax.swing.JLabel;

public class ventanaMuerte {

	public JFrame frame;
	public static ventanaMuerte window;
	public static logicaMusica musica;
	
	private logicaFondos panelFondo;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
   
	
	private  JTextArea texto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaMuerte();
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
	public ventanaMuerte() {
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

		panelFondo= new logicaFondos("/archivos/mapas/muerte.jpg");
		panelFondo.setBorder(new LineBorder(Color.RED, 4));
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JLabel lblTexto = new JLabel("<html><body>Upssss has muerto, <br>te lo digo por si no te has dado cuenta, <br> dejas mucho que desear.</body></html>");
		lblTexto.setForeground(Color.RED);
		lblTexto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblTexto.setSize(500,100);
		lblTexto.setBounds((int) Math.floor(ancho*0.5)- lblTexto.getWidth()/2, (int) Math.floor(alto*0.7), lblTexto.getWidth(), lblTexto.getHeight());
		panelFondo.add(lblTexto);

		
	}
}
