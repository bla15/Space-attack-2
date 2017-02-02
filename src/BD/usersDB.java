package BD;

import java.sql.*;

import javax.swing.JOptionPane;

public class usersDB {
	// TODO Auto-generated method stub
	//ponemos los valores de la BD
			String url ="jdbc:mysql://localhost:3306/";
			String user = "root";
			String password = "root";
			public Connection con;
	
			//conectamos con la BD pasando el usuario y la contraseña
	public void conectarBD(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			//enlazamos con la BD
			Statement stt = con.createStatement();
			stt.execute("USE tfg_db");
			
			//add some entries
			//stt.execute("INSERT INTO zzz (nick, date) VALUES"+"('ressss', '2016-10-01 00:00:00') ");
			//add some entries
			//stt.execute("INSERT INTO users (nick, email, password, role, pais, created_at, updated_at) VALUES"+ "('Juan','juan', 'aaaaa', 'administrador', 'Spain','2016-10-01 00:00:00', '2016-10-01 00:00:00' ) ");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("conecto");
	}
	public boolean loggingBD(String nick, String pass){
		String cap="";
		String sql = "SELECT * FROM users WHERE nick='"+nick+"' && password='"+pass+"'";
		try {
			Statement stt = con.createStatement();
			ResultSet res = stt.executeQuery(sql);
			while(res.next()){
				cap = res.getString("role");
			}
			if(!cap.equals("ROLE_USER")&&(!cap.equals("ROLE_ADMIN"))){
				JOptionPane.showMessageDialog(null, "ERROR");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	public void desconectarDB(){
		
	}
}
