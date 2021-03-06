package ventanasEspeciales.videos;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import ventanas.creacionPartida;
import ventanas.ventanaCampaņa;
import ventanas.ventanaLogging;
import ventanas.ventanaMenu;

public class reproductor implements KeyListener, ActionListener {

	public JFrame frame;
	public static reproductor window;
	private JPanel panelVideo;

	private Canvas canvas;
	private CanvasVideoSurface videoSurface;

	private String ruta;
	private File file;
	private int tiempoTotalVideo;
	private int camino;
	private EmbeddedMediaPlayerComponent player;
	
	boolean semaforo;
	
	MediaPlayerFactory mediaPlayerFactory ;
	EmbeddedMediaPlayer mediaPlayer ;
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"C:\\Program Files\\VideoLAN\\VLC\\");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new reproductor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public reproductor(String ruta) {
		this.ruta=ruta;
		
		initialize();

		// metodos para recuperar el foco y que las teclas funcionan
		canvas.setFocusable(true);
		canvas.requestFocus();
		canvas.addKeyListener(this);
		canvas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				canvas.requestFocus();
			}
		});
	
		reproductor(this.ruta, 1);
		// hilos
		hiloParaVideo  hiloParaVideo = new hiloParaVideo();
		hiloParaVideo.start();
		
	
	}
	public void reproductor(String ruta, int camino){
		this.camino=camino;
		file = new File(ruta);
		player = new EmbeddedMediaPlayerComponent();
		mediaPlayerFactory = new MediaPlayerFactory();
		mediaPlayer= mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas));
        mediaPlayer.playMedia(file.getAbsolutePath());  
        mediaPlayer.parseMedia();
        tiempoTotalVideo = (int)(mediaPlayer.getLength());
        semaforo = true;
        System.out.println(tiempoTotalVideo);
        
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
		frame.setVisible(true);
			
		panelVideo = new JPanel();
        panelVideo.setLayout(new BorderLayout());
        frame.getContentPane().add(panelVideo, BorderLayout.CENTER);
        
        canvas= new Canvas();
        canvas.setBackground(Color.black);
        panelVideo.add(canvas, BorderLayout.CENTER);
      
	}

	@Override
	public void actionPerformed(ActionEvent  b) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			System.out.println("El video se para");
			mediaPlayer.stop();
			
			this.window.frame.dispose();
			if (camino==1){
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaMenu.window = new ventanaMenu();
							ventanaMenu.window.frame
									.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}else if (camino == 2) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							creacionPartida.window = new creacionPartida();
							creacionPartida.window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
		}	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// hilo que gestiona mi nave
	public class hiloParaVideo extends Thread {
		

		public void run() {

			/*
			  while (semaforo) {
			 
				try {

					Thread.sleep(tiempoTotalVideo + 500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				semaforo = false;
				System.out.println("El video se para");
				mediaPlayer.stop();

				reproductor.window.frame.dispose();
				if (camino == 1) {
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
				}else if (camino == 2) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								creacionPartida.window = new creacionPartida();
								creacionPartida.window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
			*/

		}

	}
}
