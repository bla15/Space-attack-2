package logicaNegocio.logicaEnemigos;

import java.util.Random;

public class logicaEnemigos {
	//creamos valorres numericos para cada una de las variables que tendran los enemigos
	private double suVelocidad;
	private double suDireccionActual;
	private double posX;
	private double posY;
	private int giro = 0;

	/**
	 * Constructor de la clase
	 */
	public logicaEnemigos(){
		//le damos una velocidad inicial al enemigo
		suVelocidad = 50;
		suDireccionActual = 0.0;
		posX = 500 ;
		posY = 10;
	}
	
	public double getSuVelocidad() {
		return suVelocidad;
	}
	public void setSuVelocidad(double suVelocidad) {
		this.suVelocidad = suVelocidad;
	}
	public double getSuDireccionActual() {
		return suDireccionActual;
	}
	public void setSuDireccionActual(double dir) {
		if (dir < 0) dir = 360 + dir;
		if (dir > 360) dir = dir - 360;
		suDireccionActual = dir;
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
	public int getGiro(){
		return giro;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setPosicion(double posX,double posY){
		setPosY(posX);
		setPosX(posY);
	}
	
	/**
	 * Metodo encargado de cambiar el giro a los enemigos
	 * @param giro2 recibe un valor int
	 */
	public void setGiro(int giro2){
		//hacer lo esto con giro set y get
		this.giro=giro2;
	}

	/**
	 * Metodo encargado de acelerar a los enemigos
	 * @param aceleracion
	 */
	public void acelera(double aceleracion){
		suVelocidad += aceleracion;
	}
	/**
	 * Metodo encargado de girar los enemigos
	 * @param giro
	 */
	public void gira( double giro ) {
		setSuDireccionActual( suDireccionActual + giro );
	}

	/**
	 * Metodo encargado de mover a los enemigos
	 * @param tiempoDeMovimiento
	 * @param giro
	 */
	public void mueve( double tiempoDeMovimiento, double giro) {
		if (giro > 0){
			setPosX( posX + (suVelocidad*4) * Math.sin(suDireccionActual+giro/180.0*Math.PI) * tiempoDeMovimiento );
		} else {
			setPosX( posX + suVelocidad * Math.sin(suDireccionActual+giro/180.0*Math.PI) * tiempoDeMovimiento );
		}
		setPosY( posY + suVelocidad * -Math.cos(suDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );

		// el negativo es porque en pantalla la Y crece hacia abajo y no hacia arriba
	}

	/**
	 * Metodo que se encarga de mover de manera aleatoria el destino del enemigo
	 */
	public void randomDestino(){
		Random aleatorio = new Random();
		int numero = aleatorio.nextInt(2);
		if (numero == 0)
			setGiro(90);
		else 
			setGiro(270);
	}
}
