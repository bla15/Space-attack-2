package logicaNegocio;

public class logicaMiNave {
	
//Esta clase se utilizara para dar las funciones al juego

//creamos valorres numericos para cada una de las variables de mi nave
private double miVelocidad;
private double miDireccionActual;
private double posX;
private double posY;


	/**
	 * Costructor de la clase
	 */
	public logicaMiNave(){
		miVelocidad = 0.0;
		miDireccionActual = 0.0;
		posX = 623;
		posY = 500;	
	}

	//Añadimos los set y get
	public double getMiVelocidad() {
		return miVelocidad;
	}
	public void setMiVelocidad(double miVelocidad) {
		this.miVelocidad = miVelocidad;
	}
	public double getMiDireccionActual() {
		return miDireccionActual;
	}
	public void setMiDireccionActual(double dir) {
		if (dir < 0) dir = 360 + dir;
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	
	public void setPosicion(double posX,double posY){
		setPosY(posX);
		setPosX(posY);
	}
	
	/**
	 * Metodo auto generado toString para vr que cotienen las variables
	 */
	@Override
	public String toString() {
		return " en posicion " + miDireccionActual+" y direccion "+ posX + " en las x, " + posY +" en las Y; con una velocidad de "+ miVelocidad;
	}
	
	/**
	 * Metodo encargado de gestionar la aceleracion de la nave
	 */
	public void acelera(double aceleracion){
		miVelocidad += aceleracion;
	}
	
	/**
	 * Metodo encargado de gestionar el movimiento por segundo
	 */
	public void mueve( double tiempoDeMovimiento) {
		setPosX( posX + miVelocidad * Math.cos(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );
	}
	


}




