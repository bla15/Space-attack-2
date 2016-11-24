package logicaNegocio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

public class logicaMusica implements ControllerListener{
	URL urlArchivoMusica;
	Player reproductor;
	
	public logicaMusica (){
		try {
			urlArchivoMusica= (new java.io.File("archivos\\musica\\musicaLogging.wav")).toURI().toURL();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			reproductor = Manager.createPlayer(urlArchivoMusica);
		} catch (NoPlayerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		reproductor.addControllerListener(this);
		reproductor.start();
	}
	
	public void cargarCancion(String RutaCancion){
		try {
			urlArchivoMusica= (new java.io.File(RutaCancion)).toURI().toURL();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			reproductor = Manager.createPlayer(urlArchivoMusica);
		} catch (NoPlayerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public void reproducirCancion(){
		reproductor.start();
	}
	public void pausarCancion(){
		reproductor.stop();
	}
	
	public void acabarCancion(){
		reproductor.stop();
		reproductor.close();
	}
	@Override
	public void controllerUpdate(ControllerEvent e) {
		// TODO Auto-generated method stub
		
		if(e instanceof EndOfMediaEvent){
			System.out.println("Se acaba la cancion");
			reproductor.setMediaTime(new Time(0));
			reproductor.start();
		}
	}

}

