package logicaNegocio.logicaArmas;

public class logicaArma {


	//creamos valorres numericos para cada una de las variables que tendra el laser
	private double miVelocidad;
	private double miDireccionActual;
	private double posX;
	private double posY;

	/**
	 * Costructor de la clase
	 */
	public logicaArma(){
		//le damos una velocidad inicial al laser
		miVelocidad = 50;
		miDireccionActual = 0.0;
		posX = 623 ;
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
	 * Metodo encargado de gestionar la aceleracion del poryectil
	 */
	public void acelera(double aceleracion){
		miVelocidad += aceleracion;
	}
	
	/**
	 * Metodo encargado de gestionar el giro del proyectil
	 */
	public void gira( double giro ) {
		setMiDireccionActual( miDireccionActual + giro );
	}

	/**
	 * Metodo encargado de gestionar el movimiento por segundo
	 */
	public void mueve( double tiempoDeMovimiento) {
		setPosX( posX + miVelocidad * Math.sin(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );
		// el negativo es porque en pantalla la Y crece hacia abajo y no hacia arriba
		setPosY( posY + miVelocidad * -Math.cos(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );

	}
}
