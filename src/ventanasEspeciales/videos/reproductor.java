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

import javafx.application.Platform;

import javax.swing.JButton;
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
import ventanas.ventanaCampaña;
import ventanas.ventanaLogging;

public class reproductor implements KeyListener, ActionListener {

	public JFrame frame;
	public static reproductor window;
	private JPanel panelVideo;

	private Canvas canvas;
	private CanvasVideoSurface videoSurface;

	private String ruta;
	private File file;
	private int camino;
	private EmbeddedMediaPlayerComponent player;
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
	public reproductor(String ruta, int camino) {
		this.camino=camino;
		this.ruta=ruta;
		
		
		initialize();
		reproductor(this.ruta);

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
	
	}
	public void reproductor(String ruta){
		file = new File(ruta);
		player = new EmbeddedMediaPlayerComponent();
		mediaPlayerFactory = new MediaPlayerFactory();
		mediaPlayer= mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas));
        mediaPlayer.playMedia(file.getAbsolutePath());      
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
							ventanaCampaña.window = new ventanaCampaña();
							ventanaCampaña.window.frame
									.setVisible(true);
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

}
