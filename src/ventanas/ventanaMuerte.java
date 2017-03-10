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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;

import javax.swing.JLabel;

import ventanasEspeciales.transiciones.ventanaGuardado;

public class ventanaMuerte implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaMuerte window;
	public static logicaMusica musica;
	
	private logicaFondos panelFondo;
	
	private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    //botones
    private JButton btnSalir;
    
    //Valores
    private JLabel lblNombrePilotoDato;
    private JLabel lblRazaDato;
    private JLabel lblPlanetaDato;
    private JLabel lblDisparosDato;
    private JLabel lblEnemigosDatos;
    private JLabel lblScoreDato;
   
  
	
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
		lblTexto.setForeground(Color.GREEN);
		lblTexto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblTexto.setSize(500,100);
		lblTexto.setBounds((int) Math.floor(ancho*0.5)- lblTexto.getWidth()/2, (int) Math.floor(alto*0.8), lblTexto.getWidth(), lblTexto.getHeight());
		panelFondo.add(lblTexto);
		
		logicaFondos panelPuntuaciones = new logicaFondos("");
		panelPuntuaciones.setBorder(new LineBorder(Color.GREEN, 5));
		panelPuntuaciones.setSize(800,500);
		panelPuntuaciones.setBounds((int) Math.floor(ancho*0.5)- panelPuntuaciones.getWidth()/2, (int) Math.floor(alto*0.4) - panelPuntuaciones.getHeight()/2, panelPuntuaciones.getWidth(),panelPuntuaciones.getHeight());
		panelFondo.add(panelPuntuaciones);
		panelPuntuaciones.setLayout(null);

		//ponemos el titulo
		JLabel titulo = new JLabel("PUNTUACION");
		titulo.setSize(227,43);
		titulo.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.5)- titulo.getWidth()/2, (int) Math.floor(panelPuntuaciones.getHeight()*0.1) - titulo.getHeight(), titulo.getWidth(), titulo.getHeight());
		titulo.setForeground(Color.GREEN);
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 30));
		titulo.setHorizontalAlignment(titulo.CENTER);
		panelPuntuaciones.add(titulo);
		
		//ponemos los datos
		JLabel lblNombrePiloto = new JLabel("Piloto:");
		lblNombrePiloto.setSize(90,70);
		lblNombrePiloto.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.1)- lblNombrePiloto.getWidth()/2, (int) Math.floor(panelPuntuaciones.getWidth()*0.1)+lblNombrePiloto.getHeight()/2, lblNombrePiloto.getWidth(), lblNombrePiloto.getHeight());
		lblNombrePiloto.setForeground(Color.GREEN);
		lblNombrePiloto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePiloto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblNombrePiloto);
		
		lblNombrePilotoDato = new JLabel(ventanaCampaña.miPartida.getNombrePiloto());
		lblNombrePilotoDato.setSize(130,70);
		lblNombrePilotoDato.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.15) , (int) Math.floor(panelPuntuaciones.getWidth()*0.1)+lblNombrePilotoDato.getHeight()/2, lblNombrePilotoDato.getWidth(), lblNombrePilotoDato.getHeight());
		lblNombrePilotoDato.setForeground(Color.WHITE);
		lblNombrePilotoDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePilotoDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblNombrePilotoDato);
		
		JLabel lblRaza = new JLabel("Raza:");
		lblRaza.setSize(90,70);
		lblRaza.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.1)- lblRaza.getWidth()/2, (int) Math.floor(panelPuntuaciones.getWidth()*0.25)+lblRaza.getHeight()/2, lblRaza.getWidth(), lblRaza.getHeight());
		lblRaza.setForeground(Color.GREEN);
		lblRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaza.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblRaza);
		
		lblRazaDato = new JLabel(ventanaCampaña.miPartida.getRaza());
		lblRazaDato.setSize(130,70);
		lblRazaDato.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.15), (int) Math.floor(panelPuntuaciones.getWidth()*0.25)+lblRazaDato.getHeight()/2, lblRazaDato.getWidth(), lblRazaDato.getHeight());
		lblRazaDato.setForeground(Color.WHITE);
		lblRazaDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblRazaDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblRazaDato);
		
		JLabel lblPlaneta = new JLabel("Ultimo Planeta:");
		lblPlaneta.setSize(170,70);
		lblPlaneta.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.15)- lblPlaneta.getWidth()/2, (int) Math.floor(panelPuntuaciones.getWidth()*0.40)+lblPlaneta.getHeight()/2, lblPlaneta.getWidth(), lblPlaneta.getHeight());
		lblPlaneta.setForeground(Color.GREEN);
		lblPlaneta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaneta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblPlaneta);
		
		lblPlanetaDato = new JLabel(ventanaCampaña.miPartida.getUltimoPlaneta());
		lblPlanetaDato.setSize(130,70);
		lblPlanetaDato.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.26), (int) Math.floor(panelPuntuaciones.getWidth()*0.40)+lblPlanetaDato.getHeight()/2, lblPlanetaDato.getWidth(), lblPlanetaDato.getHeight());
		lblPlanetaDato.setForeground(Color.BLUE);
		lblPlanetaDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanetaDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblPlanetaDato);
		
		JLabel lblDisparos = new JLabel("Disparos:");
		lblDisparos.setSize(110,70);
		lblDisparos.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.6)- lblDisparos.getWidth()/2, (int) Math.floor(panelPuntuaciones.getWidth()*0.1)+lblDisparos.getHeight()/2, lblDisparos.getWidth(), lblDisparos.getHeight());
		lblDisparos.setForeground(Color.GREEN);
		lblDisparos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisparos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblDisparos);
	
		lblDisparosDato = new JLabel(Integer.toString(ventanaCampaña.miPartida.getDisparos()));
		lblDisparosDato.setSize(90,70);
		lblDisparosDato.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.67) , (int) Math.floor(panelPuntuaciones.getWidth()*0.1)+lblDisparosDato.getHeight()/2, lblDisparosDato.getWidth(), lblDisparosDato.getHeight());
		lblDisparosDato.setForeground(Color.WHITE);
		lblDisparosDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisparosDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblDisparosDato);
		
		JLabel lblEnemigos = new JLabel("Enemigos abatidos:");
		lblEnemigos.setSize(210,70);
		lblEnemigos.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.65)- lblEnemigos.getWidth()/2, (int) Math.floor(panelPuntuaciones.getWidth()*0.25)+lblEnemigos.getHeight()/2, lblEnemigos.getWidth(), lblEnemigos.getHeight());
		lblEnemigos.setForeground(Color.GREEN);
		lblEnemigos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemigos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblEnemigos);
		
		lblEnemigosDatos = new JLabel(Integer.toString(ventanaCampaña.miPartida.getDeads()));
		lblEnemigosDatos.setSize(90,70);
		lblEnemigosDatos.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.78), (int) Math.floor(panelPuntuaciones.getWidth()*0.25)+lblEnemigosDatos.getHeight()/2, lblEnemigosDatos.getWidth(), lblEnemigosDatos.getHeight());
		lblEnemigosDatos.setForeground(Color.WHITE);
		lblEnemigosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemigosDatos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblEnemigosDatos);
		
		JLabel lblScore = new JLabel("Puntiacion:");
		lblScore.setSize(130,70);
		lblScore.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.6)- lblScore.getWidth()/2, (int) Math.floor(panelPuntuaciones.getWidth()*0.40)+lblScore.getHeight()/2, lblScore.getWidth(), lblScore.getHeight());
		lblScore.setForeground(Color.GREEN);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblScore);
		
		lblScoreDato = new JLabel(Integer.toString(ventanaCampaña.miPartida.getScore()));
		lblScoreDato.setSize(130,70);
		lblScoreDato.setBounds((int) Math.floor(panelPuntuaciones.getWidth()*0.65), (int) Math.floor(panelPuntuaciones.getWidth()*0.40)+lblScoreDato.getHeight()/2, lblScoreDato.getWidth(), lblScoreDato.getHeight());
		lblScoreDato.setForeground(Color.BLUE);
		lblScoreDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelPuntuaciones.add(lblScoreDato);

		//botones
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(ventanaLogging.class.getResource("/archivos/botones/exit.png")));
		btnSalir.setSize(60, 55);
		btnSalir.setBounds((int)Math.floor(ancho*0.9),((int)Math.floor(alto*0.9)), btnSalir.getWidth(), btnSalir.getHeight());
		btnSalir.addActionListener(this);
		panelFondo.add(btnSalir);
	}
	
	/**
	 * Metodo usado para actualizar los valores
	 */
	public void actualizar(){
		lblNombrePilotoDato.setText(ventanaCampaña.miPartida.getNombrePiloto());
		lblRazaDato.setText(ventanaCampaña.miPartida.getRaza());
		lblPlanetaDato.setText(ventanaCampaña.miPartida.getUltimoPlaneta());
		lblDisparosDato.setText(String.valueOf(ventanaCampaña.miPartida.getDisparos()));
		lblEnemigosDatos.setText(String.valueOf(ventanaCampaña.miPartida.getDeads()));
		lblScoreDato.setText(String.valueOf(ventanaCampaña.miPartida.getScore()));
	}
	
	/**
	 * Metodo usado para actualizar los valores
	 */
	public void musicaMuerte(){
		ventanaLogging.musica.cargarCancion("archivos\\musica\\gameOver.wav");
		ventanaLogging.musica.reproducirCancion();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnSalir){
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
