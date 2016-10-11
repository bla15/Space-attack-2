package BD;

import java.sql.*;

public class usersDB {
	// TODO Auto-generated method stub
			String url ="jdbc:mysql://localhost:3306/";
			String user = "root";
			String password = "root";
			
	public void conectarBD(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con= DriverManager.getConnection(url, user, password);
			
			//enlazamos con la BD
			Statement stt = con.createStatement();
			stt.execute("USE tfg_db");
			
			
			//add some entries
			stt.execute("INSERT INTO zzz (nick, date) VALUES"+
					"('ressss', '2016-10-01 00:00:00') ");
			//add some entries
			stt.execute("INSERT INTO users (nick, email, password, role, pais, created_at, updated_at) VALUES"+
					"('prueba','reees', 'aaaaa', 'aaaaa', 'Spain','2016-10-01 00:00:00', '2016-10-01 00:00:00' ) ");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("conecto");
	}
	
	public void desconectarDB(){
		
	}
}
