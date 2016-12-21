package logicaNegocio;


public class logicaConjuntaMiNave extends logicaMiNave {
	logicaFotoMiNave miNave;
	
	/**
	 * Constructor de la clase
	 */
	public logicaConjuntaMiNave (){
		//Instanciamos el JLabel de nombre miNave para que exista
		miNave = new logicaFotoMiNave();
	}
	
	/**
	 * Metodo encargado de cambiar la imagen de la nave
	 */
	public void setFotoNave(logicaFotoMiNave miNave) {
		this.miNave = miNave;
	}
	/**
	 * MMetodo encargado de devolver la nave
	 */
	public logicaFotoMiNave getFotoNave() {
		return miNave;
	}
	
	/**
	 * MMetodo encargado de cambiar el eje de las X de la nave
	 */
	public void setPosX(double posX) {
		//Al poner super.setPos indicamos que es el metodo del padre al cual hay que pasarle una variable de tipo Double
		super.setPosX(posX);
		//Esa variable alterara la posicion del eje de las x sobre el cual esta el JLabel
		miNave.setLocation((int)posX, miNave.getY());
	}
	/**
	 * MMetodo encargado de cambiar el eje de las Y de la nave
	 */
	public void setPosY(double posY) {
		super.setPosY(posY);	
		miNave.setLocation(miNave.getX(),(int)posY);
	}
	
	/**
	 * MMetodo encargado del movimiento de la nave a lo largo del tiempo
	 */
	public void mueve(double tiempoDeMovimiento) {
		// TODO Auto-generated method stub
		super.mueve(tiempoDeMovimiento);
	}
}
