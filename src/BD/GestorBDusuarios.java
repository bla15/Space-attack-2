package BD;

import gestorDeNegocio.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;


public class GestorBDusuarios {
	
	String url ="jdbc:mysql://localhost:3306/";
	String user = "root";
	String password = "root";
	public Connection con;
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	//conectamos con la BD pasando el usuario y la contraseña
	public void connect() {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			Statement stt = con.createStatement();

			// create and select DB
			stt.execute("CREATE DATABASE IF NOT EXISTS tfg_db");
			stt.execute("USE tfg_db");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entramos a la BD");
	}
	
	//desconectamos de la BD
	public void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("salimos de la BD");
	}
	
	//cargamos todos los elementos de la tabla users
	public HashSet<usuario> listarUsuario() {
		final HashSet<usuario> usuarios = new HashSet<usuario>();
		
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from users");
			while (rs.next()) {
				final usuario usu = new usuario();
				usu.setId_u(rs.getInt("id"));
				usu.setNick(rs.getString("nick"));
				usu.setPassword(rs.getString("password"));
				usuarios.add(usu);
				//System.out.println(usu.getNick());
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}

}
