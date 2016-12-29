package logicaNegocio.logicaEnemigos;

public class logicaEnemigosConjunta extends logicaEnemigos{
elecionFotoEnemigo fotoEnemigo;

	/**
	 * Metodo heredado de la clase padre logicaEnemigos 
	 * @return devuelve la foto del enemigo
	 */
	public elecionFotoEnemigo getFoto() {
		return fotoEnemigo;
	}
	
	/**
	 * Constructor de la clase
	 * @param tipoEnemigo, recibe la ruta de la foto a usar
	 */
	public logicaEnemigosConjunta(String tipoEnemigo){
		fotoEnemigo= new elecionFotoEnemigo(tipoEnemigo);
	}
	
	/**
	 * devuelve la foto del enemigo
	 * @return
	 */
	public elecionFotoEnemigo getFotoEnemigo() {
		return fotoEnemigo;
	}
	
	/**
	 * Metodo para cambiar el eje X tanto de la logica como de la foto del enemigo
	 */
	public void setPosX(double posX) {
		//para el metodo padre
		super.setPosX(posX);
		//para que afecte a la foto del enemigo
		fotoEnemigo.setLocation((int)posX, fotoEnemigo.getY());

	}
	
	/**
	 * Metodo para cambiar el eje Y tanto de la logica como de la foto del enemigo
	 */
	public void setPosY(double posY) {
		super.setPosY(posY);	
		fotoEnemigo.setLocation(fotoEnemigo.getX(),(int)posY);
	}
	/**
	 * Metodo encargado de mover tanto la logica como la foto de los enemigos
	 */
	public void mueve(double tiempoDeMovimiento, double giro) {
		// TODO Auto-generated method stub
		super.mueve(tiempoDeMovimiento,giro);
	}
	public logicaEnemigosConjunta esteEnemigo() {
		return this;
	}
	public void setGiro(int giro){
		super.setGiro(giro);
	}
}
