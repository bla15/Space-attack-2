package gestorDeNegocio;

import java.util.HashSet;

import contrase�as.conversorContrase�as;
import BD.GestorBDusuarios;

public class gestorUsuario {
	
	protected HashSet<usuario> listaUsuario;
	final GestorBDusuarios objConversor = new GestorBDusuarios();
	conversorContrase�as cc = new conversorContrase�as();
	
	//etodo que llama a el gestro BD  para que carge todos los elementos de la talba users
	public void cargarDatos() throws ClassNotFoundException {
		objConversor.connect();
		listaUsuario = objConversor.listarUsuario();
		objConversor.disconnect();
	}
	
	//metodo para hacer el loging
	public boolean comprobar(String usuario, String passWord) throws ClassNotFoundException {
		String contrase�aModificada;
		cargarDatos();
		boolean dev = false;
		for (usuario a : listaUsuario) {
			if (a.getNick().equals(usuario)) {//miramos a ver si hay algun usuario en la BD con ese nick, si es asi entra
				contrase�aModificada=cc.convertir(a.getPassword());//recuperamos la contrase�a de la BD referente a ese usuario y la tratamos para aplicar el hash
				dev=cc.checkPassword(passWord, contrase�aModificada); //revertimos la contrase�a de la BD y la comparamos con la contrase�a introducida
				//si coinciden dev == true y si no dev == false;
			}
		}
		
		return dev;
	}
	
	//metodo que devuelve un usuario que corresponda con la ID que le pasamos
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
}
