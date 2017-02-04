package BD;

import gestorDeNegocio.FaltanDatosEnLaBaseDeDatos;
import gestorDeNegocio.gestorUsuario;
import gestorDeNegocio.usuario;

public class pruebasBD {

	public static void main(String[] args) {
		usuario usu;
		// TODO Auto-generated method stub
		gestorUsuario gestor = new gestorUsuario();
		try {
		
			System.out.println(gestor.comprobar("fenris", "fenris"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
