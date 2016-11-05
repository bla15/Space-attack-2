package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

public class ventanaVideoPresent implements KeyListener, ActionListener{

	public JFrame frame;
	public static ventanaVideoPresent window;
	private final JFXPanel jfxPanel = new JFXPanel();  
	private MediaPlayer oracleVid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ventanaVideoPresent();
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
	public ventanaVideoPresent() {
		initialize();
		createScene();
		//gestion de teclas
		frame.setFocusable(true);
		frame.requestFocus();
		frame.addKeyListener(this);
		frame.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				frame.requestFocus();
			}
		});
		//hilos
		hiloVideo miVideo = new hiloVideo(); 
		miVideo.start();
	}
	/**
	 *control del video
	 */
	private void createScene(){
        Platform.runLater(new Runnable() {
             @Override
             public void run() {   
            	 //File rutaVideo = new File ("/archivos/r.mp4");
            	 //System.out.println("La ruta del fichero es: " + getClass().getResource("/archivos/r.mp4"));
            	 //System.out.println(System.getProperty("user.dir"));
                //File filestring  = new File("C:\\proyectos Java\\TFG-ing\\space.attack.2\\src\\archivos\\z.mp4");  
                Media file = new Media(new File("src\\archivos\\videos\\videoPresentacion.mp4").toURI().toString());    
                oracleVid = new MediaPlayer(file);
                oracleVid.onReadyProperty();
                  
                    //se añade video al jfxPanel
                    jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));  
                    oracleVid.setVolume(0.7);//volumen
                    oracleVid.setCycleCount(MediaPlayer.INDEFINITE);//repite video
                    oracleVid.play();//play video
      
                 
             }
        });
    }  

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		((JComponent) frame.getContentPane()).setBorder(new LineBorder(Color.WHITE, 3));
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		frame.setSize(1200,500); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(false); 

		
		jfxPanel.setBackground(Color.BLACK);
		frame.getContentPane().add(jfxPanel);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			
			window.frame.dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ventanaMenu.window = new ventanaMenu();
						ventanaMenu.window.frame.setVisible(true);		
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});	
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public class hiloVideo extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("SUII");
				
				window.frame.dispose();
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaMenu.window = new ventanaMenu();
							ventanaMenu.window.frame.setVisible(true);		
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		}
	}
}
