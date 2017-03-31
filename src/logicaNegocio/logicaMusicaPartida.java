package logicaNegocio;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

public class logicaMusicaPartida implements ControllerListener{
	URL urlArchivoMusica;
	Player reproductor;
	ArrayList<String> canciones = new ArrayList<String>();
	
	//valor usado para saber que cancion esta sonando
	int numeroCancion=0;
	
	public  logicaMusicaPartida(){
		
		llenarLista();
		System.out.println(canciones.get(0));
		try {
			urlArchivoMusica= (new java.io.File("archivos\\musica\\fondos\\"+canciones.get(0)+".wav")).toURI().toURL();
			
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
	
	public void llenarLista(){
		canciones.add("fondo1");
		canciones.add("fondo2");
		canciones.add("fondo3");
		canciones.add("fondo4");
		canciones.add("fondo5");
		canciones.add("fondo6");
		canciones.add("fondo7");
		canciones.add("fondo8");
		long seed = System.nanoTime();
		Collections.shuffle(canciones, new Random(seed));
		
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
			reproductor.addControllerListener(this);
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
			numeroCancion+=1;
			System.out.println(numeroCancion);
			if(numeroCancion<=7){
				cargarCancion("archivos\\musica\\fondos\\"+canciones.get(numeroCancion)+".wav");
				reproducirCancion();

			}else{
				numeroCancion=0;
				cargarCancion("archivos\\musica\\fondos\\"+canciones.get(numeroCancion)+".wav");
				reproducirCancion();
			}
			
		}
	}

}
