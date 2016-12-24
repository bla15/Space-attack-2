package logicaNegocio.logicaArmas;


//esta clase pondar en comun los metodos de la foto y de la logica del laser
public class armaConjunto extends logicaArma {
	//creamos la foto del arma
	logicaFotoArma fotoArma;
	
	/**
	 * Constructor de la clase
	 */
	public armaConjunto (){
		//Instanciamos la foto del arma
		fotoArma= new logicaFotoArma();

	}
	
	/**
	 * Metodo encargado de cambiar la imagen del proyectil
	 */
	public logicaFotoArma getFotoArma() {
		return fotoArma;
	}
	
	/**
	 * Metodo encargado de cambiar el eje X del proyectil
	 */
	public void setPosX(double posX) {
		//para el metodo padre
		super.setPosX(posX);
		//para que afecte a la foto del arma
		fotoArma.setLocation((int)posX, fotoArma.getY());
		
	}
	/**
	 * Metodo encargado de cambiar el eje Y del proyectil
	 */
	public void setPosY(double posY) {
		super.setPosY(posY);	
		fotoArma.setLocation(fotoArma.getX(),(int)posY);
	}
	
	/**
	 * MMetodo encargado de movel el proyectil
	 */
	public void mueve(double tiempoDeMovimiento) {
		// TODO Auto-generated method stub
		super.mueve(tiempoDeMovimiento);
	}
	
	/**
	 * MMetodo encargado de devolver el proyectil
	 */
	public armaConjunto esteArma( ) {
		return this;
	}
}
