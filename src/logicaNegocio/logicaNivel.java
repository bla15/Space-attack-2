  package logicaNegocio;

import java.awt.EventQueue;

import ventanas.ventanaPrincipal;

public class logicaNivel {

	int nivel;
	private String[] mapas = {"fondo1", "fondo2"};
	public static String arma ="/archivos/armas/bala.png";
	String fondo;	 
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
	}
	public void verNivel(int i){
		if(i==1){
			nivelUno();
		}else if(i==2){
			
		}else if(i==3){
			
		}else if(i==4){
			
		}else if(i==5){
			
		}else if(i==6){
			
		}
	}
	public void nivelUno(){
		
	}

	public void nivelDos(){

	}

	public void nivelTres(){

	}

	public void nivelCuatro(){

	}

	public void nivelCinco(){

	}

	public void nivelSeis(){

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
