package gestorDeNegocio;

import java.util.HashSet;

import BD.GestorBDusuarios;

public class gestorPartida {
	
	protected HashSet<partida> listaPartidas;
	final GestorBDusuarios objConversor = new GestorBDusuarios();
	
	
	/**
	 * Obtenemos la lista de Vehiculos de la base de datos
	 * @throws ClassNotFoundException En caso de que no nos podamos conectar correctamente a la base de datos.
	 */
	
	public void cargarDatos() throws ClassNotFoundException 
	{
		objConversor.connect();
		listaPartidas = objConversor.listaPartidas();
		objConversor.disconnect();
	}
	/**
	 * Añadimos la nueva partida a la base de datos siempre y cuando este no se encuntre ya en ella.
	 * @param o La partida que deseamos agregar tiene que tener todos los atributos completados excepto el de id.
	 * @throws excepAnadirDupli En caso de que la partida a insertar en la base de datos ya exista
	 * @throws ClassNotFoundException En caso de que no nos podamos conectar correctamente a la base de datos.
	 */
	public void addPartida(partida obj) throws  ClassNotFoundException {
		cargarDatos();
		objConversor.connect();
		objConversor.addPartida(obj);
		objConversor.disconnect();
	}

}
