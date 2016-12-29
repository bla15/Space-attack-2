  package logicaNegocio;



import java.awt.EventQueue;
import java.awt.color.CMMException;

import logicaNegocio.logicaEnemigos.enemigoDos;
import logicaNegocio.logicaEnemigos.enemigoUno;
import ventanas.ventanaEntreBatallas;
import ventanas.ventanaMenu;
import ventanas.ventanaPrincipal;
import ventanasEspeciales.transiciones.saltoEspacial1;
import ventanasEspeciales.transiciones.saltoEstandar;

public class logicaNivel {

	int nivel;
	private String[] mapas = {"fondo1", "fondo2"};
	public static String arma ="/archivos/armas/bala.png";
	public static logicaCambio lc;
	public static boolean cambioMapa = true;
	String fondo;	 
	
	//Creamos los diferentes tipos de enmigos
	enemigoUno enemigosFirst;
	enemigoDos enemigoSecond;

	
	/**
	 *Constructor de clase
	 */
	public logicaNivel(int nivel) {
		// TODO Auto-generated constructor stub
		this.nivel=nivel;
		crearPartida();	
	}
	
	/**
	 *Metodo encargado de crear la partida
	 */
	public void crearPartida(){
		
		fondo=mapas[nivel-1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal.window = new ventanaPrincipal("/archivos/mapas/"+fondo+".jpg");
					ventanaPrincipal.window.frame.setVisible(true);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//creamos las ventanas intermedias
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaEntreBatallas.window = new ventanaEntreBatallas();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//creamos las ventanas intermedias
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							saltoEstandar.window = new saltoEstandar("/archivos/mapas/fondo2.jpg");	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		
		//empieza el contador para el cambio de mapa
		lc = new logicaCambio();
	}
	/**
	 * Metodo para identificar en que nivel se empieza
	 */
	public void identificarNivel(){
		if(nivel==1){
			//enemigosFirst = new enemigoUno();
			enemigoSecond = new enemigoDos();
		}else if(nivel==2){
			enemigosFirst = new enemigoUno();
		}else if(nivel==3){

		}else if(nivel==4){
			
		}else if(nivel==5){
			
		}else{
			
		}
	}
	/**
	 * Metodo encaegado de realizar el cambio
	 */
	public void cambio(){
		nivel++;
		cambioMapa = false;
		lc.setPausa2(false);
	}
	public void salto(){
		if(nivel==2){
			saltoEstandar.window.frame.setVisible(true);
			saltoEstandar.window.comenzar();
		}
		if(nivel==3){
			saltoEstandar.window.reutilizar("/archivos/mapas/salto2.jpg", "/archivos/mapas/fondo3.jpg");
			saltoEstandar.window.frame.setVisible(true);	
		}
	}
	public void mapaNuevo(){
		System.out.println(nivel);
		if(nivel==2){
			cambioMapa = true;
			lc.tiempoAlEmpezar=(int) System.currentTimeMillis();
			ventanaPrincipal.fondoJuego.setCambio("/archivos/mapas/fondo2.jpg");
			ventanaPrincipal.window.frame.setVisible(true);
			lc.setPausa2(true);
			identificarNivel();
		}else if(nivel==3){
			cambioMapa = true;
			lc.tiempoAlEmpezar=(int) System.currentTimeMillis();
			ventanaPrincipal.fondoJuego.setCambio("/archivos/mapas/fondo3.jpg");
			ventanaPrincipal.window.frame.setVisible(true);
			lc.setPausa2(true);
			identificarNivel();
		}else if(nivel==4){

		}else if(nivel==5){
			
		}else if(nivel==6){
			
		}else{
			
		}
	}

	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public static void main(String[] args) {
		logicaNivel ln = new logicaNivel(1);
	}
}
