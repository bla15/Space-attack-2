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

import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;

public class ventanaFinal implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaFinal window;
	public static logicaMusica musica;
	private logicaFondos panelFondo;
	private logicaFondos panelEstadoNave;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    private  JTextArea texto;
    
    //botones
    private JButton btnSaltar;
	private JButton btnContinuar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaFinal();
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
	public ventanaFinal() {
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
		
		panelFondo= new logicaFondos("/archivos/mapas/ventanaFinal.jpg");
		panelFondo.setBorder(new LineBorder(Color.RED, 4));
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JPanel panelTexto = new JPanel ();
		panelTexto.setBorder(new LineBorder(Color.RED, 3));
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
		texto.setText("Capitan tengo malas noticias...."
				+ " debido a los continuos combates que hemos sufrido en los seis saltos realizados, "
				+ "queda muy poca energia en el motor de salto estelar. En la sala de mantenimiento nos dicen"
				+ "que no queda suficiente energia para realizar el ultimo salto y que lo mas seguro es que si lo "
				+ "hacemos, nos quedemos a la mitad de camino. No podemos volver hacia atras y avanzar resulta peligroso."
				+" Ademas en este sistema solar solo hay ese planeta y resulta hostil.");
		texto.setEditable ( false ); // set textArea non-editable
		JScrollPane scroll = new JScrollPane ( texto );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panelTexto.add ( scroll );
		
		btnContinuar = new JButton("continuar");
		btnContinuar.setSize(260,50);
		btnContinuar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnContinuar.setBounds(ancho / 2+btnContinuar.getWidth(), (int) Math.floor(alto * 0.90),btnContinuar.getWidth(), btnContinuar.getHeight());
		btnContinuar.addActionListener(this);
		panelFondo.add(btnContinuar);
		
		//elementos que aun no estan visibles
		btnSaltar = new JButton("Saltar");
		btnSaltar.setSize(260,50);
		btnSaltar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnSaltar.setBorder(new LineBorder(Color.GREEN, 3));
		btnSaltar.setBounds((int) Math.floor(ancho*0.05), (int) Math.floor(alto*0.90), btnSaltar.getWidth(), btnSaltar.getHeight());
		btnSaltar.addActionListener(this);
		
		panelEstadoNave= new logicaFondos("/archivos/imagenes/estadoNave.jpg");
		panelEstadoNave.setLayout(null);
		panelEstadoNave.setSize((int) Math.floor(ancho*0.3), (int) Math.floor(alto*0.2));
		panelEstadoNave.setBounds((int) Math.floor(ancho*0.5), (int) Math.floor(alto*0.48), panelEstadoNave.getWidth(), panelEstadoNave.getHeight());
		panelEstadoNave.setVisible(true);
		panelFondo.setBorder(new LineBorder(Color.RED, 4));
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnContinuar){
			btnContinuar.setVisible(false);
			texto.setText(" Recomiendo hacer el salto y cruzar los dedos"
					+ "igual acabamos cerca de la estacion del planeta que se conocia como marte"
					+" y los terricolas nos pueden rescatar siempre y cuando sigan vivos. Cuando usted diga ");
			
			panelFondo.add(panelEstadoNave);
			panelFondo.add(btnSaltar);
			frame.repaint();
		}else if( e.getSource() == btnSaltar){
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
