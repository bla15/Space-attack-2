package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import logicaNegocio.logicaNivel;
import logicaVentanas.logicaFondos;

public class ventanaEntreBatallas implements KeyListener, ActionListener {

	public JFrame frame;
	public static ventanaEntreBatallas window;
	
	private logicaFondos panelFondo;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    private  JTextArea texto;
    
	private JButton btnDefensa;
	private JButton btnContinuar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaEntreBatallas();
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
	public ventanaEntreBatallas() {
		initialize();
	}
	
	public void reutilizar (){
		texto.setText("ATENCION, ATENCION............ (se enciende la megafonia)   ");
		btnContinuar.setVisible(true);
		btnDefensa.setVisible(false);
		frame.invalidate();
		frame.validate();
		frame.repaint();
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
		    
		panelFondo= new logicaFondos("/archivos/imagenes/entreBatallas.jpg");
		panelFondo.setLayout(null);
		frame.getContentPane().add(panelFondo);
		
		JPanel panelTexto = new JPanel ();
		panelTexto.setBorder(new LineBorder(Color.BLUE, 3));
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
		texto.setText("ATENCION, ATENCION............ (se enciende la megafonia)   ");
		texto.setEditable ( false ); // set textArea non-editable
		JScrollPane scroll = new JScrollPane ( texto );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panelTexto.add ( scroll );
		
		btnDefensa = new JButton("Defender");
		btnDefensa.setSize(260,50);
		btnDefensa.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnDefensa.setBorder(new LineBorder(Color.GREEN, 3));
		btnDefensa.setBounds((int) Math.floor(ancho*0.05), (int) Math.floor(alto*0.90), btnDefensa.getWidth(), btnDefensa.getHeight());
		btnDefensa.addActionListener(this);
		btnDefensa.setVisible(false);
		panelFondo.add(btnDefensa);
			
			
		btnContinuar = new JButton("continuar");
		btnContinuar.setSize(260,50);
		btnContinuar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnContinuar.setBounds(ancho / 2+btnContinuar.getWidth(), (int) Math.floor(alto * 0.90),btnContinuar.getWidth(), btnContinuar.getHeight());
		btnContinuar.addActionListener(this);
		panelFondo.add(btnContinuar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnContinuar){
			texto.setText("Que la tripulacion se prepare para realizar el siguiente hiper salto");
			btnDefensa.setVisible(true);
			btnContinuar.setVisible(false);
			frame.invalidate();
			frame.validate();
			frame.repaint();
		}else if( e.getSource() == btnDefensa){
			window.frame.dispose();
			ventanaMenu.ln.salto();
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
