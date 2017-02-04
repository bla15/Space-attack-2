package gestorDeNegocio;

public class FaltanDatosEnLaBaseDeDatos extends Exception {

	/**
	 * Excepcion que se le lanza al usuario cuando intenta hacer referencia a
	 * informacion que ya no esta en la base de datos.
	 */
	private static final long serialVersionUID = 1L;

	private String falta;

	/**
	 * Obtener el mesaje que se le presentara al usuario cuando esto se
	 * produzca.
	 * 
	 * @return Mensaje en String.
	 */
	public String getFalta() {
		return falta;
	}

	/**
	 * Introducir el mensaje que le mostrara al usuario cuando se de la
	 * excepcio.
	 * 
	 * @param falta
	 *            -> String explicativo de las causas del fallo.
	 */
	public void setFalta(String falta) {
		this.falta = falta;
	}

}
