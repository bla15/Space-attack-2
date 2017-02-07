  package logicaNegocio;



import java.awt.EventQueue;
import java.awt.color.CMMException;

import pruebas.ventanaPruebas;
import logicaNegocio.logicaEnemigos.enemigoCinco;
import logicaNegocio.logicaEnemigos.enemigoCuatro;
import logicaNegocio.logicaEnemigos.enemigoDos;
import logicaNegocio.logicaEnemigos.enemigoSeis;
import logicaNegocio.logicaEnemigos.enemigoUno;
import logicaNegocio.logicaEnemigos.enemigosTres;
import logicaNegocio.logicaEnemigos.enemigoUno.hiloChoques;
import ventanas.ventanaCampaña;
import ventanas.ventanaEntreBatallas;
import ventanas.ventanaFinal;
import ventanas.ventanaMenu;
import ventanas.ventanaMuerte;
import ventanas.ventanaPrincipal;
import ventanas.ventanaPuntuacion;
import ventanasEspeciales.transiciones.saltoEspacial1;
import ventanasEspeciales.transiciones.saltoEstandar;
import ventanasEspeciales.transiciones.ventanaGuardado;

public class logicaNivel {

	int nivel;
	private String[] mapas = {"fondo1", "fondo2", "fondo3", "fondo4", "fondo5", "fondo6"};
	public static String arma ="/archivos/armas/bala.png";
	public static logicaCambio lc;
	public static boolean cambioMapa = true;
	String fondo;	 
	
	//Creamos los diferentes tipos de enmigos
	enemigoUno enemigosFirst;
	enemigoDos enemigoSecond;
	enemigosTres enemigoThird;
	enemigoCuatro enemigoFourd;
	enemigoCinco enemigoFive;
	enemigoSeis enemigoSix;
	
	//booleano dee partida cargada
	boolean carga = false;
	//int que nos dice el numero de vidas
	int vidas;
	int vidasEspeciales;
	
	
	/**
	 *Constructor de clase
	 */
	public logicaNivel(int nivel, int vidas) {
		// TODO Auto-generated constructor stub
		this.nivel=nivel;
		this.vidas=8;
		if(nivel!=1){
			carga=true;
			this.vidas=vidas;
		}
		
		crearPartida();
	}
	
	/**
	 *Metodo encargado de crear la partida
	 */
	public void crearPartida(){

		fondo=mapas[nivel-1];
		
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
							ventanaFinal.window = new ventanaFinal();	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				//creamos las ventanas intermedias
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							saltoEstandar.window = new saltoEstandar("/archivos/mapas/fondo2.jpg", carga);	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				//creamos las ventanas de muerte
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaMuerte.window = new ventanaMuerte();	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				//creamos las ventanas intermedias
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaPuntuacion.window = new ventanaPuntuacion();	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				//creamos las ventanas intermedias
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaGuardado.window = new ventanaGuardado();	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ventanaPrincipal.window = new ventanaPrincipal("/archivos/mapas/"+fondo+".jpg",vidas);
							ventanaPrincipal.window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		//empieza el contador para el cambio de mapa
		lc = new logicaCambio();
	}
	
	public boolean isCarga() {
		return carga;
	}

	public void setCarga(boolean carga) {
		this.carga = carga;
	}

	/**
	 * Metodo para identificar en que nivel se empieza
	 */
	public void identificarNivel(){
		if(nivel==1){
			enemigosFirst = new enemigoUno();
		}else if(nivel==2){
			enemigoFourd = new enemigoCuatro();
			
		}else if(nivel==3){
			enemigoFive = new enemigoCinco();
		}else if(nivel==4){
			enemigoThird = new enemigosTres();
		}else if(nivel==5){
			enemigoSecond = new enemigoDos();
		}else{
			enemigoSix = new enemigoSeis();
		}
	}
	/**
	 * Metodo para identificar en que planeta estamos
	 */
	public String  miNivel(){
		String planeta;
		if(nivel==1){
			planeta="Tumbr";
		}else if(nivel==2){
			planeta="Axion";
		}else if(nivel==3){
			planeta="Blor";
		}else if(nivel==4){
			planeta="Hell";
		}else if(nivel==5){
			planeta="Shadow";
		}else{
			planeta="Logic";
		}
		return planeta;
	}
	/**
	 * Metodo encaegado de realizar el cambio
	 */
	public void cambio(){
		nivel++;
		cambioMapa = false;
		lc.setPausa2(false);
	}
	
	/**
	 * Metodo encaegado de realizar el salto de cambio de mapa
	 */
	public void salto(){
		
		if(nivel==2){
			saltoEstandar.window.frame.setVisible(true);
			saltoEstandar.window.comenzar();
		}else if(nivel==3){
			saltoEstandar.window.reutilizar("/archivos/mapas/salto2.jpg", "/archivos/mapas/fondo3.jpg");
			saltoEstandar.window.frame.setVisible(true);
		}else if(nivel==4){
			saltoEstandar.window.reutilizar("/archivos/mapas/salto3.jpg", "/archivos/mapas/fondo4.jpg");
			saltoEstandar.window.frame.setVisible(true);	
		}else if(nivel==5){
			saltoEstandar.window.reutilizar("/archivos/mapas/salto4.jpg", "/archivos/mapas/fondo5.jpg");
			saltoEstandar.window.frame.setVisible(true);	
		}else if(nivel==6){
			saltoEstandar.window.reutilizar("/archivos/mapas/salto5.jpg", "/archivos/mapas/fondo6.jpg");
			saltoEstandar.window.frame.setVisible(true);	
		} else{
			saltoEstandar.window.reutilizar("/archivos/mapas/salto6.jpg", "/archivos/mapas/lunaEspacio.jpg");
			saltoEstandar.window.frame.setVisible(true);
		}
	
	}
	

	/**
	 * Metodo encargado de cargar el nuevo mapa y poner la locic+a de cambio de mapa a cero
	 */
	public void mapaNuevo(){
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
			cambioMapa = true;
			lc.tiempoAlEmpezar=(int) System.currentTimeMillis();
			ventanaPrincipal.fondoJuego.setCambio("/archivos/mapas/fondo4.jpg");
			ventanaPrincipal.window.frame.setVisible(true);
			lc.setPausa2(true);
			identificarNivel();
		}else if(nivel==5){
			cambioMapa = true;
			lc.tiempoAlEmpezar=(int) System.currentTimeMillis();
			ventanaPrincipal.fondoJuego.setCambio("/archivos/mapas/fondo5.jpg");
			ventanaPrincipal.window.frame.setVisible(true);
			lc.setPausa2(true);
			identificarNivel();
		}else if(nivel==6){
			cambioMapa = true;
			lc.tiempoAlEmpezar=(int) System.currentTimeMillis();
			ventanaPrincipal.fondoJuego.setCambio("/archivos/mapas/fondo6.jpg");
			ventanaPrincipal.window.frame.setVisible(true);
			lc.setPausa2(true);
			identificarNivel();
		}else{
			cambioMapa = true;
			ventanaPuntuacion.window.frame.setVisible(true);
			ventanaPrincipal.funcionar=false;
		}
	}
	/**
	 * Metodo encargado de cargar el nuevo mapa y poner la locic+a de cambio de mapa a cero
	 * @param nivel, le pasamo el nivel en el que estamos
	 */
	public void muerte(int nivel){
		ventanaPrincipal.funcionar=false;
		ventanaPrincipal.window.frame.dispose();
		ventanaMuerte.window.frame.setVisible(true);	
		
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public static void main(String[] args) {
		logicaNivel ln = new logicaNivel(1,8);
	}
}
