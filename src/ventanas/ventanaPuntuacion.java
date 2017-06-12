package ventanas;

import gestorDeNegocio.gestorPartida;
import gestorDeNegocio.partida;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ventanasEspeciales.transiciones.ventanaGuardado;
import logicaNegocio.logicaMusica;
import logicaVentanas.logicaFondos;

import javax.swing.JPanel;

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

    //Valores
    private JLabel lblNombrePilotoDato;
    private JLabel lblRazaDato;
    private JLabel lblPlanetaDato;
    private JLabel lblDisparosDato;
    private JLabel lblEnemigosDatos;
    private JLabel lblScoreDato;
    
    private JLabel lblMiNombrePilotoDato;
    private JLabel lblMiRazaDato;
    private JLabel lblMiPlanetaDato;
    private JLabel lblMiDisparosDato;
    private JLabel lblMiEnemigosDatos;
    private JLabel lblMiScoreDato;

    //elementos necesarios para cargar la partida
    private gestorPartida GP = new gestorPartida();
    private HashSet<partida> mp;
    private partida mejorPartida = new partida();
    
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
		mp = GP.obtnerMejorPartida();
		int puntacion=0;
		for (partida p : mp){
			if(p.getScore()>puntacion){
				puntacion=p.getScore();
				mejorPartida.setNombrePiloto(p.getNombrePiloto());
				mejorPartida.setRaza(p.getRaza());
				mejorPartida.setUltimoPlaneta(p.getUltimoPlaneta());
				mejorPartida.setDisparos(p.getDisparos());
				mejorPartida.setDeads(p.getDeads());
				mejorPartida.setScore();p.getScore();
			}
		
		}
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
		
		panelFondo= new logicaFondos("/archivos/mapas/luna.jpg");
		panelFondo.setLayout(null);
		frame.getContentPane().add(panelFondo);
		
		//ponemos el titulo
		JLabel titulo = new JLabel("Tan cerca pero tan lejos");
		titulo.setSize((int) Math.floor(ancho*0.3),40);
		titulo.setBounds((int) Math.floor(ancho*0.5)-titulo.getWidth()/2, (int) Math.floor(alto*0.01), titulo.getWidth(), titulo.getHeight());
		titulo.setOpaque(false);
		titulo.setForeground(Color.white);
		titulo.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 50));
		titulo.setHorizontalAlignment(titulo.CENTER);
		panelFondo.add(titulo);
	
		//ponemos los datos del mejor piloto
		logicaFondos panelmejorPiloto = new logicaFondos("");
		panelmejorPiloto.setBorder(new LineBorder(Color.YELLOW, 4));
		panelmejorPiloto.setSize(ancho/2-100,400);
		panelmejorPiloto.setBounds(50, titulo.getHeight()*3, panelmejorPiloto.getWidth(), panelmejorPiloto.getHeight());
		panelFondo.add(panelmejorPiloto);
		panelmejorPiloto.setLayout(null);

		//ponemos el titulo
		JLabel tituloMejorPiloto = new JLabel("Mejor piloto del universo");
		tituloMejorPiloto.setSize(450,43);
		tituloMejorPiloto.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.5)- tituloMejorPiloto.getWidth()/2, (int) Math.floor(panelmejorPiloto.getHeight()*0.1) - tituloMejorPiloto.getHeight()+10, tituloMejorPiloto.getWidth(), tituloMejorPiloto.getHeight());
		tituloMejorPiloto.setForeground(Color.YELLOW);
		tituloMejorPiloto.setFont(new Font("Arial Black", Font.PLAIN, 30));
		tituloMejorPiloto.setHorizontalAlignment(titulo.CENTER);
		panelmejorPiloto.add(tituloMejorPiloto);

		JLabel lblNombrePiloto = new JLabel("Piloto:");
		lblNombrePiloto.setSize(90,70);
		lblNombrePiloto.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.1)- lblNombrePiloto.getWidth()/2, (int) Math.floor(panelmejorPiloto.getWidth()*0.1)+lblNombrePiloto.getHeight()/2, lblNombrePiloto.getWidth(), lblNombrePiloto.getHeight());
		lblNombrePiloto.setForeground(Color.YELLOW);
		lblNombrePiloto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePiloto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblNombrePiloto);

		lblNombrePilotoDato = new JLabel(mejorPartida.getNombrePiloto());
		lblNombrePilotoDato.setSize(130,70);
		lblNombrePilotoDato.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.15) , (int) Math.floor(panelmejorPiloto.getWidth()*0.1)+lblNombrePilotoDato.getHeight()/2, lblNombrePilotoDato.getWidth(), lblNombrePilotoDato.getHeight());
		lblNombrePilotoDato.setForeground(Color.WHITE);
		lblNombrePilotoDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePilotoDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblNombrePilotoDato);

		JLabel lblRaza = new JLabel("Raza:");
		lblRaza.setSize(90,70);
		lblRaza.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.1)- lblRaza.getWidth()/2, (int) Math.floor(panelmejorPiloto.getWidth()*0.25)+lblRaza.getHeight()/2, lblRaza.getWidth(), lblRaza.getHeight());
		lblRaza.setForeground(Color.YELLOW);
		lblRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaza.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblRaza);

		lblRazaDato = new JLabel(mejorPartida.getRaza());
		lblRazaDato.setSize(130,70);
		lblRazaDato.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.15), (int) Math.floor(panelmejorPiloto.getWidth()*0.25)+lblRazaDato.getHeight()/2, lblRazaDato.getWidth(), lblRazaDato.getHeight());
		lblRazaDato.setForeground(Color.WHITE);
		lblRazaDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblRazaDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblRazaDato);

		JLabel lblPlaneta = new JLabel("Ultimo Planeta:");
		lblPlaneta.setSize(170,70);
		lblPlaneta.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.15)- lblPlaneta.getWidth()/2, (int) Math.floor(panelmejorPiloto.getWidth()*0.40)+lblPlaneta.getHeight()/2, lblPlaneta.getWidth(), lblPlaneta.getHeight());
		lblPlaneta.setForeground(Color.YELLOW);
		lblPlaneta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaneta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblPlaneta);

		lblPlanetaDato = new JLabel(mejorPartida.getUltimoPlaneta());
		lblPlanetaDato.setSize(130,70);
		lblPlanetaDato.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.26), (int) Math.floor(panelmejorPiloto.getWidth()*0.40)+lblPlanetaDato.getHeight()/2, lblPlanetaDato.getWidth(), lblPlanetaDato.getHeight());
		lblPlanetaDato.setForeground(Color.WHITE);
		lblPlanetaDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanetaDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblPlanetaDato);

		JLabel lblDisparos = new JLabel("Disparos:");
		lblDisparos.setSize(110,70);
		lblDisparos.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.6)- lblDisparos.getWidth()/2, (int) Math.floor(panelmejorPiloto.getWidth()*0.1)+lblDisparos.getHeight()/2, lblDisparos.getWidth(), lblDisparos.getHeight());
		lblDisparos.setForeground(Color.YELLOW);
		lblDisparos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisparos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblDisparos);

		lblDisparosDato = new JLabel(Integer.toString(mejorPartida.getDisparos()));
		lblDisparosDato.setSize(90,70);
		lblDisparosDato.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.67) , (int) Math.floor(panelmejorPiloto.getWidth()*0.1)+lblDisparosDato.getHeight()/2, lblDisparosDato.getWidth(), lblDisparosDato.getHeight());
		lblDisparosDato.setForeground(Color.WHITE);
		lblDisparosDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisparosDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblDisparosDato);

		JLabel lblEnemigos = new JLabel("Enemigos:");
		lblEnemigos.setSize(180,70);
		lblEnemigos.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.65)- lblEnemigos.getWidth()/2, (int) Math.floor(panelmejorPiloto.getWidth()*0.25)+lblEnemigos.getHeight()/2, lblEnemigos.getWidth(), lblEnemigos.getHeight());
		lblEnemigos.setForeground(Color.YELLOW);
		lblEnemigos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemigos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblEnemigos);

		lblEnemigosDatos = new JLabel(Integer.toString(mejorPartida.getDeads()));
		lblEnemigosDatos.setSize(90,70);
		lblEnemigosDatos.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.72), (int) Math.floor(panelmejorPiloto.getWidth()*0.25)+lblEnemigosDatos.getHeight()/2, lblEnemigosDatos.getWidth(), lblEnemigosDatos.getHeight());
		lblEnemigosDatos.setForeground(Color.WHITE);
		lblEnemigosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemigosDatos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblEnemigosDatos);

		JLabel lblScore = new JLabel("Puntiacion:");
		lblScore.setSize(130,70);
		lblScore.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.6)- lblScore.getWidth()/2, (int) Math.floor(panelmejorPiloto.getWidth()*0.40)+lblScore.getHeight()/2, lblScore.getWidth(), lblScore.getHeight());
		lblScore.setForeground(Color.YELLOW);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblScore);

		lblScoreDato = new JLabel(Integer.toString(mejorPartida.getScore()));
		lblScoreDato.setSize(130,70);
		lblScoreDato.setBounds((int) Math.floor(panelmejorPiloto.getWidth()*0.65), (int) Math.floor(panelmejorPiloto.getWidth()*0.40)+lblScoreDato.getHeight()/2, lblScoreDato.getWidth(), lblScoreDato.getHeight());
		lblScoreDato.setForeground(Color.WHITE);
		lblScoreDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelmejorPiloto.add(lblScoreDato);

		//ponemos los datos de mi partida
		logicaFondos panelMiPartida = new logicaFondos("");
		panelMiPartida.setBorder(new LineBorder(Color.BLUE, 4));
		panelMiPartida.setSize(ancho/2-100,400);
		panelMiPartida.setBounds(ancho/2+50, titulo.getHeight()*3, panelMiPartida.getWidth(), panelMiPartida.getHeight());
		panelFondo.add(panelMiPartida);
		panelMiPartida.setLayout(null);

		//ponemos el titulo
		JLabel tituloMiPartida= new JLabel("Mi partida");
		tituloMiPartida.setSize(250,43);
		tituloMiPartida.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.5)- tituloMiPartida.getWidth()/2, (int) Math.floor(panelMiPartida.getHeight()*0.1) - tituloMiPartida.getHeight()+10, tituloMiPartida.getWidth(), tituloMiPartida.getHeight());
		tituloMiPartida.setForeground(Color.BLUE);
		tituloMiPartida.setFont(new Font("Arial Black", Font.PLAIN, 30));
		tituloMiPartida.setHorizontalAlignment(titulo.CENTER);
		panelMiPartida.add(tituloMiPartida);
		
		JLabel lblMiNombrePiloto = new JLabel("Piloto:");
		lblMiNombrePiloto.setSize(90,70);
		lblMiNombrePiloto.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.1)- lblMiNombrePiloto.getWidth()/2, (int) Math.floor(panelMiPartida.getWidth()*0.1)+lblMiNombrePiloto.getHeight()/2, lblMiNombrePiloto.getWidth(), lblMiNombrePiloto.getHeight());
		lblMiNombrePiloto.setForeground(Color.RED);
		lblMiNombrePiloto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiNombrePiloto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiNombrePiloto);

		lblMiNombrePilotoDato = new JLabel("bbb");
		lblMiNombrePilotoDato.setSize(130,70);
		lblMiNombrePilotoDato.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.15) , (int) Math.floor(panelMiPartida.getWidth()*0.1)+lblMiNombrePilotoDato.getHeight()/2, lblMiNombrePilotoDato.getWidth(), lblMiNombrePilotoDato.getHeight());
		lblMiNombrePilotoDato.setForeground(Color.WHITE);
		lblMiNombrePilotoDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiNombrePilotoDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiNombrePilotoDato);

		JLabel lblMiRaza = new JLabel("Raza:");
		lblMiRaza.setSize(90,70);
		lblMiRaza.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.1)- lblRaza.getWidth()/2, (int) Math.floor(panelMiPartida.getWidth()*0.25)+lblMiRaza.getHeight()/2, lblMiRaza.getWidth(), lblMiRaza.getHeight());
		lblMiRaza.setForeground(Color.RED);
		lblMiRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiRaza.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiRaza);

		lblMiRazaDato = new JLabel("bbb");
		lblMiRazaDato.setSize(130,70);
		lblMiRazaDato.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.15), (int) Math.floor(panelMiPartida.getWidth()*0.25)+lblMiRazaDato.getHeight()/2, lblMiRazaDato.getWidth(), lblMiRazaDato.getHeight());
		lblMiRazaDato.setForeground(Color.WHITE);
		lblMiRazaDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiRazaDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiRazaDato);

		JLabel lblMiPlaneta = new JLabel("Ultimo Planeta:");
		lblMiPlaneta.setSize(170,70);
		lblMiPlaneta.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.15)- lblPlaneta.getWidth()/2, (int) Math.floor(panelMiPartida.getWidth()*0.40)+lblMiPlaneta.getHeight()/2, lblMiPlaneta.getWidth(), lblMiPlaneta.getHeight());
		lblMiPlaneta.setForeground(Color.RED);
		lblMiPlaneta.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiPlaneta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiPlaneta);

		lblMiPlanetaDato = new JLabel("bbb");
		lblMiPlanetaDato.setSize(130,70);
		lblMiPlanetaDato.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.26), (int) Math.floor(panelMiPartida.getWidth()*0.40)+lblMiPlanetaDato.getHeight()/2, lblMiPlanetaDato.getWidth(), lblMiPlanetaDato.getHeight());
		lblMiPlanetaDato.setForeground(Color.WHITE);
		lblMiPlanetaDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiPlanetaDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiPlanetaDato);

		JLabel lblMisDisparos = new JLabel("Disparos:");
		lblMisDisparos.setSize(110,70);
		lblMisDisparos.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.6)- lblDisparos.getWidth()/2, (int) Math.floor(panelMiPartida.getWidth()*0.1)+lblMisDisparos.getHeight()/2, lblMisDisparos.getWidth(), lblMisDisparos.getHeight());
		lblMisDisparos.setForeground(Color.RED);
		lblMisDisparos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisDisparos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMisDisparos);

		lblMiDisparosDato = new JLabel("bbb");
		lblMiDisparosDato.setSize(90,70);
		lblMiDisparosDato.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.67) , (int) Math.floor(panelMiPartida.getWidth()*0.1)+lblMiDisparosDato.getHeight()/2, lblMiDisparosDato.getWidth(), lblMiDisparosDato.getHeight());
		lblMiDisparosDato.setForeground(Color.WHITE);
		lblMiDisparosDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiDisparosDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiDisparosDato);

		JLabel lblMisEnemigos = new JLabel("Enemigos:");
		lblMisEnemigos.setSize(180,70);
		lblMisEnemigos.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.72)- lblMisEnemigos.getWidth()/2, (int) Math.floor(panelMiPartida.getWidth()*0.25)+lblMisEnemigos.getHeight()/2, lblMisEnemigos.getWidth(), lblMisEnemigos.getHeight());
		lblMisEnemigos.setForeground(Color.RED);
		lblMisEnemigos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisEnemigos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMisEnemigos);

		lblMiEnemigosDatos = new JLabel("bbb");
		lblMiEnemigosDatos.setSize(90,70);
		lblMiEnemigosDatos.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.78), (int) Math.floor(panelMiPartida.getWidth()*0.25)+lblMiEnemigosDatos.getHeight()/2, lblMiEnemigosDatos.getWidth(), lblMiEnemigosDatos.getHeight());
		lblMiEnemigosDatos.setForeground(Color.WHITE);
		lblMiEnemigosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiEnemigosDatos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiEnemigosDatos);

		JLabel lblMiScore = new JLabel("Puntiacion:");
		lblMiScore.setSize(130,70);
		lblMiScore.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.6)- lblMiScore.getWidth()/2, (int) Math.floor(panelMiPartida.getWidth()*0.40)+lblMiScore.getHeight()/2, lblMiScore.getWidth(), lblMiScore.getHeight());
		lblMiScore.setForeground(Color.RED);
		lblMiScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiScore.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiScore);

		lblMiScoreDato = new JLabel("bbb");
		lblMiScoreDato.setSize(130,70);
		lblMiScoreDato.setBounds((int) Math.floor(panelMiPartida.getWidth()*0.65), (int) Math.floor(panelMiPartida.getWidth()*0.40)+lblMiScoreDato.getHeight()/2, lblMiScoreDato.getWidth(), lblMiScoreDato.getHeight());
		lblMiScoreDato.setForeground(Color.WHITE);
		lblMiScoreDato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiScoreDato.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelMiPartida.add(lblMiScoreDato);

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
		ventanaCampaña.miPartida.setScore();
		lblMiNombrePilotoDato.setText(ventanaCampaña.miPartida.getNombrePiloto());
		lblMiRazaDato.setText(ventanaCampaña.miPartida.getRaza());
		lblMiPlanetaDato.setText(ventanaCampaña.miPartida.getUltimoPlaneta());
		lblMiDisparosDato.setText(String.valueOf(ventanaCampaña.miPartida.getDisparos()));
		lblMiEnemigosDatos.setText(String.valueOf(ventanaCampaña.miPartida.getDeads()));
		lblMiScoreDato.setText(String.valueOf(ventanaCampaña.miPartida.getScore()));
	}
	
	/**
	 * Metodo usado para actualizar los valores
	 */
	public void musicaVictoria(){
		ventanaLogging.musica.cargarCancion("archivos\\musica\\win.wav");
		ventanaLogging.musica.reproducirCancion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnSalir){
			ventanaGuardado.window.frame.setVisible(true);

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
