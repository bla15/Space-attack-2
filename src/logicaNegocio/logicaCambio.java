package logicaNegocio;


import java.awt.EventQueue;

import ventanas.ventanaEntreBatallas;
import ventanas.ventanaMenu;
import ventanas.ventanaPrincipal;

public class logicaCambio {
	//varaible que usaremos para determinar el tiempo pasado desde que iniciamos el juego
	boolean pausa2 = true;
	long tiempoAlEmpezar;
	long tiempoMapa2;
	public static boolean funcionar= true;
	public static boolean pausar= true;
	
	/*
	 * Constructor de la clase 
	 */
	public logicaCambio(){
		
		tiempoAlEmpezar=(int) System.currentTimeMillis();
		
		//control de los hilos
		hiloCambios miHiloCambios = new hiloCambios(); 
		miHiloCambios.start();
	}
	
	public boolean isPausa2() {
		return pausa2;
	}

	public void setPausa2(boolean pausa2) {
		this.pausa2 = pausa2;
	}

	public class hiloCambios extends Thread{
		public void run(){
			while(funcionar){
				try {
			        Thread.sleep(10); // for 100 FPS
			    } catch (InterruptedException ignore) {
			    }
				while(pausar && pausa2 ){
					//si pasa 30 segundos hace el primer cambio de mapa
					if(((int)System.currentTimeMillis()-tiempoAlEmpezar>4000)){
						tiempoAlEmpezar=(int) System.currentTimeMillis();
						System.out.println("CAMBIO");
						//ventanaPrincipal.window.frame.dispose();
						ventanaMenu.ln.cambio();
					}
				}
			}
		}
	}
}
