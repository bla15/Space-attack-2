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
import java.sql.Date;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import logicaNegocio.logicaNivel;
import logicaVentanas.logicaFondos;

public class ventanaCargaPartida implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaCargaPartida window;
	
	public static int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static logicaFondos panelFondo;
    
    //elementos de la tabla
    DefaultTableModel modelo;
    private JTable tabla;
    private String[] columnas = {"Piloto", "Raza", "Destino", "Disparos", "Muertes", "Puntuacion", "Vidas", "Guardado"};
    private int fila;
    
    //elementos necesarios para cargar la partida
    private gestorPartida GP = new gestorPartida();
	private HashSet<partida> pa;
	private partida P = new partida();
	private int vida=2;
    
    //botones
    private JButton btnCargar;
	private JButton btnAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaCargaPartida();
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
	public ventanaCargaPartida() {
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
		
		panelFondo= new logicaFondos("/archivos/imagenes/cargar.jpg");
		panelFondo.setBorder(new LineBorder(Color.WHITE, 4));
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		//ponemos el titulo
		JLabel titulo = new JLabel("Se procede a cargar una mision");
		titulo.setSize((int) Math.floor(ancho*0.6),80);
		titulo.setBounds((int) Math.floor(ancho*0.5)-titulo.getWidth()/2, (int) Math.floor(frame.getHeight()*0.1), titulo.getWidth(), titulo.getHeight());
		titulo.setOpaque(false);
		titulo.setBorder(new LineBorder(Color.WHITE, 4));
		titulo.setForeground(Color.white);
		titulo.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 80));
		titulo.setHorizontalAlignment(titulo.CENTER);
		panelFondo.add(titulo);
		
		//creamos el JScrollPaneque soportara la tabla
		JScrollPane desplazamiento = new JScrollPane();
		desplazamiento.setSize(979, (int) Math.floor(alto*0.4));
		desplazamiento.setBounds(ancho/2-desplazamiento.getWidth()/2, alto/2-desplazamiento.getHeight()/2, desplazamiento.getWidth(),  desplazamiento.getHeight());
		panelFondo.add(desplazamiento);
		
		//creamos el modelo necesario para la tabla
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(columnas);
		

		//creamos la tabla y la introducimos en el jScroolPanel;
		tabla = new JTable(modelo);
		tabla.setBorder(new LineBorder(Color.BLUE, 2));
		JTableHeader header = tabla.getTableHeader();
		header.setFont( header.getFont().deriveFont(16) );
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		tabla.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		
		pa=GP.obtenerPartidasUsuario(ventanaLogging.usuarioActual.getId_u());
		for( partida p :pa) {
			//solo mostramos aquellas partidas que no han sido finalizadas
			if(p.isStatus()==false){
				String vida="";
				if(p.getLife()<=2){
					vida="Un escudo";
				}else if((p.getLife()>2)&&(p.getLife()<=4)){
					vida="Dos escudos";
				}else if((p.getLife()>4)&&(p.getLife()<=6)){
					vida="Tres escudos";
				}else if((p.getLife()>6)&&(p.getLife()<=8)){
					vida="Cuatro escudos";
				}
				   modelo.addRow( new Object[] {p.getNombrePiloto(), p.getRaza(), p.getUltimoPlaneta(), p.getDisparos(),p.getDeads(), p.getScore(), vida, p.getUpdated_at()});   
	               modelo.fireTableDataChanged();
			}
		}
		desplazamiento.setViewportView(tabla);
		
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				String Svida;
				
				// TODO Auto-generated method stub
				  // do some actions here, for example
	            // print first column value from selected row
	        	//{"Color", "Traccion", "Numero Bastidor", "Fecha Venta", "Precio venta", "Coste", "Potencia", "ID"};
	        	fila=tabla.getSelectedRow();
	        	
	        	P.setNombrePiloto(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
	        	P.setRaza(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
	        	P.setUltimoPlaneta(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
	        	P.setDisparos((Integer) tabla.getValueAt(tabla.getSelectedRow(), 3));
	        	P.setDeads((Integer) tabla.getValueAt(tabla.getSelectedRow(), 4));
	        	P.setScore();
	        	Svida=tabla.getValueAt(tabla.getSelectedRow(), 6).toString();
	        	if(Svida.equals("Un escudo")){
	        		vida=2;
	        	}else if(Svida.equals("Dos escudos")){
	        		vida=4;
	        	}else if(Svida.equals("Tres escudos")){
	        		vida=6;
	        	}else if(Svida.equals("Cuatro escudos")){
	        		vida=8;
	        	}
	        	P.setLife((Integer)vida);
	        	P.setUpdated_at((Date) tabla.getValueAt(tabla.getSelectedRow(), 7));
			}
			
		  });
		// Barras de desplazamiento
        desplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        desplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Propiedades de la tabla
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        tabla.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 16));
        tabla.setFillsViewportHeight(true);  


		btnCargar = new JButton("Cargar");
		btnCargar.setSize(260,50);
		btnCargar.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnCargar.setBorder(new LineBorder(Color.GREEN, 3));
		btnCargar.setBounds((int) Math.floor(ancho*0.6), (int) Math.floor(alto*0.8)+btnCargar.getHeight()/2, btnCargar.getWidth(), btnCargar.getHeight());
		btnCargar.addActionListener(this);
		panelFondo.add(btnCargar);
		
		btnAtras = new JButton("Atras");
		btnAtras.setSize(260,50);
		btnAtras.setFont(new Font("Arial Black", Font.PLAIN, 30));
		btnAtras.setBorder(new LineBorder(Color.BLUE, 3));
		btnAtras.setBounds((int) Math.floor(ancho*0.3)-btnAtras.getWidth()/2, (int) Math.floor(alto*0.8)+btnCargar.getHeight()/2, btnCargar.getWidth(), btnCargar.getHeight());
		btnAtras.addActionListener(this);
		panelFondo.add(btnAtras);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == btnCargar){
			int planeta=6;
			if(P.getUltimoPlaneta().equals("Tumbr")){
				planeta=1;
			}else if(P.getUltimoPlaneta().equals("Axion")){
				planeta=2;
			}else if(P.getUltimoPlaneta().equals("Blor")){
				planeta=3;
			}else if(P.getUltimoPlaneta().equals("Hell")){
				planeta=4;
			}else if(P.getUltimoPlaneta().equals("Shadow")){
				planeta=5;
			}else if(P.getUltimoPlaneta().equals("Logic")){
				planeta=6;
			}
			window.frame.dispose();
			ventanaMenu.ln = new logicaNivel(5,vida);
		}else if(e.getSource() == btnAtras){
			this.window.frame.dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaCampaña.window.frame.setVisible(true);	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});	
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
