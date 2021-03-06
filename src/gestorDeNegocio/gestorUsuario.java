package gestorDeNegocio;

import java.util.HashSet;

import contraseņas.conversorContraseņas;
import BD.GestorBDusuarios;

public class gestorUsuario {
	
	protected HashSet<usuario> listaUsuario;
	final GestorBDusuarios objConversor = new GestorBDusuarios();
	conversorContraseņas cc = new conversorContraseņas();
	

	/**
	 * Metodo que llama a el gestro BD  para que carge todos los elementos de la talba users
	 * @throws ClassNotFoundException En caso de que no nos podamos conectar correctamente a la base de datos.
	 */
	public void cargarDatos() throws ClassNotFoundException {
		objConversor.connect();
		listaUsuario = objConversor.listarUsuario();
		objConversor.disconnect();
	}
	
	/**
	 * Metodo para hacer el logging
	 */
	public boolean comprobar(String usuario, String passWord) throws ClassNotFoundException {
		String contraseņaModificada;
		cargarDatos();
		boolean dev = false;
		for (usuario a : listaUsuario) {
			if (a.getNick().equals(usuario)) {//miramos a ver si hay algun usuario en la BD con ese nick, si es asi entra
				contraseņaModificada=cc.convertir(a.getPassword());//recuperamos la contraseņa de la BD referente a ese usuario y la tratamos para aplicar el hash
				dev=cc.checkPassword(passWord, contraseņaModificada); //revertimos la contraseņa de la BD y la comparamos con la contraseņa introducida
				//si coinciden dev == true y si no dev == false;
			}
		}
		
		return dev;
	}
	
	/**
	 *Metodo que develve un usuario dependiendo el id quele pasamos
	 */
	public usuario getUsuario(int id) throws FaltanDatosEnLaBaseDeDatos, ClassNotFoundException {

		usuario usu = null;
		
		cargarDatos();
		
		for (usuario u : listaUsuario) {
			if (u.getId_u() == id) {
				usu = u;
			}
		}
		
		if (usu == null) {
		
			FaltanDatosEnLaBaseDeDatos f = new FaltanDatosEnLaBaseDeDatos();
			f.setFalta("No existe el usuario en la base de datos");
			throw f;
		}
		
		return usu;
	}
	
	/**
	 *Metodo encargado de saber que usario es el registrado
	 */
	public usuario usuarioActual(String nick, String passWord)throws ClassNotFoundException {
		usuario usar = null;
		cargarDatos();
		System.out.println("el nick es: "+ nick+" el pass es: "+passWord);
		for (usuario a : listaUsuario) {

			if (a.getNick().equals(nick)) {

					usar=a;
			}

		}
		return usar;
	}
}
