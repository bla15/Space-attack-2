package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import logicaNegocio.logicaMusica;
import logicaNegocio.logicaNivel;
import logicaVentanas.logicaFondos;
import ventanasEspeciales.transiciones.saltoEspacial1;

public class ventanaEmergencia implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaEmergencia window;
	public static logicaMusica musica;
	
	private logicaFondos panelFondo;
	
	private JButton btnDefensa;
	private JButton btnContinuar;
	
	private logicaFondos planAnterior;
	private logicaFondos nuevoPlan;
	
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
					window = new ventanaEmergencia();
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
	public ventanaEmergencia() {
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
		    
		    panelFondo= new logicaFondos("/archivos/imagenes/puentePresentacion.png");
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
		    texto.setText("ATENCION, ATENCION............    "
		    		+ "HA HABIDO UN FALLO EN EL PROPULSOR DE ..... SALTO ESPACIAL, FALLO GRAVEEEE "
		    		+ "RECALENTAMIENTO EXTREMO, SE ACONSEJA .. RATE HINWEIS MIT EINEM 10TH hat dieses Projekt......"
		    		+ "SE NECESITAN UNOS MINUTOS PARA VOLVER A PONER ......  EN FUNCIONAMIENTO EL REACTOR "
		    		+ "DESCONOCEMOS COMO SOLU...CIONAR EL FALLO");
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
		
			planAnterior= new logicaFondos("/archivos/imagenes/plan1.jpg");
			planAnterior.setSize(300,200);
			planAnterior.setBounds((int) Math.floor(ancho*0.6)-planAnterior.getWidth(),(int) Math.floor(alto*0.1),planAnterior.getWidth(),planAnterior.getHeight());
			planAnterior.setLayout(null);
			panelFondo.add(planAnterior);
			
			//ponemos el titulo
			JLabel titulo = new JLabel("Plan original");
			titulo.setSize(450,40);
			titulo.setBounds(planAnterior.getWidth()/2-titulo.getWidth()/2, 10, 450, 40);
			titulo.setOpaque(true);
			titulo.setForeground(Color.blue);
			titulo.setBackground(Color.WHITE);
			titulo.setFont(new Font("Arial Black", Font.PLAIN, 30));
			titulo.setHorizontalAlignment(titulo.CENTER);
			titulo.setBorder(new LineBorder(Color.BLUE, 3));
			planAnterior.add(titulo);
			
			nuevoPlan= new logicaFondos("/archivos/imagenes/plan2.jpg");
			nuevoPlan.setSize(300,200);
			nuevoPlan.setBounds((int) Math.floor(ancho*0.6)+ 10,(int) Math.floor(alto*0.1),nuevoPlan.getWidth(),planAnterior.getHeight());
			nuevoPlan.setLayout(null);
			panelFondo.add(nuevoPlan);
			
			//ponemos el titulo
			JLabel titulo2 = new JLabel("Nuevo Plan");
			titulo2.setSize(450,40);
			titulo2.setBounds(nuevoPlan.getWidth()/2-titulo2.getWidth()/2, 10, 450, 40);
			titulo2.setOpaque(true);
			titulo2.setForeground(Color.blue);
			titulo2.setBackground(Color.WHITE);
			titulo2.setFont(new Font("Arial Black", Font.PLAIN, 30));
			titulo2.setHorizontalAlignment(titulo2.CENTER);
			titulo2.setBorder(new LineBorder(Color.BLUE, 3));
			nuevoPlan.add(titulo2);
			
			texto.setText("Ya ha escuchado la megafonia, el propulsor experimental a fallado, no se puede hacer saltos de largos distancia. "
		    		+ "Apartir de ahora tendremos que hacer pequeños saltos espaciales hasta llegar al sistema solar, calculamos que se "
		    		+ "necesitaran unos 6 saltos para llegar al destino. Esta parte de la galxia esta llena de enenmigos, por lo que tendremos que "
		    		+ "defendernos hasta poder volver saltar al siguiente punto, en el cual repitiremos el proceso."
		    		+ "Pondremos musica para hacer mas amena la defensa. Suerte una vez mas. ");
			panelFondo.add(btnDefensa);
			btnContinuar.setVisible(false);
			frame.invalidate();
			frame.validate();
			frame.repaint();
		}else if( e.getSource() == btnDefensa){
			window.frame.dispose();
			
			ventanaMenu.ln = new logicaNivel(1);
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
