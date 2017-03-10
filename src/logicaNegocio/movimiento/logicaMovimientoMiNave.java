package logicaNegocio.movimiento;

import ventanas.ventanaPrincipal;
import logicaNegocio.logicaConjuntaMiNave;
import logicaNegocio.logicaFotoMiNave;

public class logicaMovimientoMiNave {
	//valores de la nave
	boolean teclasMovimientoNave [] = new boolean[3];

	//Elementoss de los hilos
	protected int i;
	
	/**
	 * Metodo get de teclasMovimiento
	 */
	public boolean[] getTeclasMovimientoNave() {
		return teclasMovimientoNave;
	}
	/**
	 * Metodo set de teclasMovimiento
	 */
	public void setTeclasMovimientoNave(int i,boolean b){
		this.teclasMovimientoNave[i]=b;
	}
	
	/*
	 * Constructor de la clase
	 */
	public void movimientoBase(){
		//hilos
		hiloMiNave miHiloNave = new hiloMiNave(); 
		miHiloNave.start();
	}
	

	/**
	 * Hilo para gestionar la nave
	 *  @throws InterruptedException Error al dormir el hiloMiNave
	 */
	public class hiloMiNave extends Thread{
		long tiempoEsfera;

		public void run(){
			while(ventanaPrincipal.funcionar==true){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(ventanaPrincipal.pausar){
					ventanaPrincipal.naveConjunta.mueve(0.040);
					//movimiento de la nave hacia la izq
					if(teclasMovimientoNave[0]==true){
						//si la nave esta parada acelera mas rapido
						if(ventanaPrincipal.naveConjunta.getMiVelocidad()==0){
							ventanaPrincipal.naveConjunta.acelera(-100);
							//si a alcanzado el maximo de la velocidad
						}else if(ventanaPrincipal.naveConjunta.getMiVelocidad()<-400){
							ventanaPrincipal.naveConjunta.acelera(0);
							//si lleva movimiento contrario
						}else if(ventanaPrincipal.naveConjunta.getMiVelocidad()>0){
							ventanaPrincipal.naveConjunta.acelera(-50);
						}else{
							ventanaPrincipal.naveConjunta.acelera(-35);
							//ponemos los cohetes en posicion
							ventanaPrincipal.coheteDerecho.setVisible(true);
						}
					}else{
						ventanaPrincipal.coheteDerecho.setVisible(false);
					}
					//movimiento de la nava hacia la dcha
					if(teclasMovimientoNave[1]==true){
						//si la nave esta parada hay acelera mas rapida
						if(ventanaPrincipal.naveConjunta.getMiVelocidad()==0){
							ventanaPrincipal.naveConjunta.acelera(100);
						}//si la nave a alcanzado la velocidad maxima
						else if(ventanaPrincipal.naveConjunta.getMiVelocidad()>400){
							ventanaPrincipal.naveConjunta.acelera(0);
						}//si lleva movimiento contrario
						else if(ventanaPrincipal.naveConjunta.getMiVelocidad()<0){
							ventanaPrincipal.naveConjunta.acelera(100);
						}
						else{
							ventanaPrincipal.naveConjunta.acelera(35);
							//ponemos los cohetes en posicion
							ventanaPrincipal.coheteIzquierdo.setVisible(true);
						}



					}else{
						ventanaPrincipal.coheteIzquierdo.setVisible(false);
					}
					
					if (ventanaPrincipal.naveConjunta.getPosX() < ventanaPrincipal.fondoJuego.WIDTH -logicaFotoMiNave.TAMAÑO/2 ) {
						ventanaPrincipal.naveConjunta.setPosX(ventanaPrincipal.naveConjunta.getPosX()+10);
						ventanaPrincipal.naveConjunta.setMiVelocidad(-ventanaPrincipal.naveConjunta.getMiVelocidad());
						tiempoEsfera=System.currentTimeMillis();
						tiempoEsfera=System.currentTimeMillis();
						
					}
					if (ventanaPrincipal.naveConjunta.getPosX()>ventanaPrincipal.fondoJuego.getWidth()-logicaFotoMiNave.TAMAÑO/2  ) {
						ventanaPrincipal.naveConjunta.setPosX(ventanaPrincipal.naveConjunta.getPosX()-10);
						ventanaPrincipal.naveConjunta.setMiVelocidad(-ventanaPrincipal.naveConjunta.getMiVelocidad());
						tiempoEsfera=System.currentTimeMillis();
					}
					
					//las posiciones de los componentes sigen la nave
					ventanaPrincipal.coheteIzquierdo.setLocation((int)ventanaPrincipal.naveConjunta.getPosX()-53, (int)ventanaPrincipal.naveConjunta.getPosY()+60);
					ventanaPrincipal.coheteDerecho.setLocation((int)ventanaPrincipal.naveConjunta.getPosX()+57, (int)ventanaPrincipal.naveConjunta.getPosY()+60);
					//esfera.setLocation((int)naveConjunta.getPosX()-23,480);

					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
