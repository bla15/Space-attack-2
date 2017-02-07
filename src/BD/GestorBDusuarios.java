package BD;

import gestorDeNegocio.partida;
import gestorDeNegocio.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	//conectamos con la BD pasando el usuario y la contrase�a
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
	
	/*
	 * metodo encargado de a�adir una partidaNueva
	 */
	public void addPartida(final partida nuevaPartida) {
		try {
			PreparedStatement stat = con.prepareStatement(
					"INSERT INTO partidas values (null,?,?,?,?,?,?,?,?,?,?,?)");

			stat.setInt(1, nuevaPartida.getId_u());
			stat.setString(2, nuevaPartida.getNombrePiloto());
			stat.setString(3, nuevaPartida.getRaza());
			stat.setString(4, nuevaPartida.getUltimoPlaneta());
			stat.setInt(5, nuevaPartida.getDisparos());
			stat.setInt(6, nuevaPartida.getDeads());
			stat.setInt(7, nuevaPartida.getScore());
			stat.setInt(8, nuevaPartida.getLife());
			stat.setBoolean(9, nuevaPartida.isStatus());
			stat.setDate(10, nuevaPartida.getCreated_at());
			stat.setDate(11, nuevaPartida.getUpdated_at());
			
			stat.executeUpdate();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problema al crear la partida: " + e);
		}
	}
	/**
	 * Se devuelve la lista de vehiculos que existen en la base de datos
	 * 
	 * @return HashSet<Vehiculo> Con objetos Vehiculo.java
	 */
	public HashSet<partida> listaPartidas() {
		final HashSet<partida> vehiculos = new HashSet<partida>();
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from partidas");

			while (rs.next()) {
				final partida obj = new partida();
				obj.setId_partida(rs.getInt("id"));
				obj.setId_u(rs.getInt("user_id"));
				obj.setNombrePiloto(rs.getString("nombrePiloto"));
				obj.setRaza(rs.getString("raza"));
				obj.setUltimoPlaneta(rs.getString("ultimoPlaneta"));
				obj.setDisparos(rs.getInt("disparos"));
				obj.setDeads(rs.getInt("deads"));
				obj.setScore(rs.getInt("deads"),rs.getInt("disparos"));
				obj.setLife(rs.getInt("life"));
				obj.setStatus(rs.getBoolean("status"));
				obj.setCreated_at(rs.getDate("created_at"));
				obj.setUpdated_at(rs.getDate("updated_at"));
				
				vehiculos.add(obj);
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
		}
		return vehiculos;
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
