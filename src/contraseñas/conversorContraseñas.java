package contraseñas;

import org.mindrot.jbcrypt.BCrypt;


public class conversorContraseñas {
	String pn="";
	int u = 2;
	String l = "a";

	public String convertir(String pv){
		for (int i = 0; i < pv.length(); i++){ //de p a l a b r a 
			if (i == u){ // 0 1 2 3 4 5 6 
				pn = pn.concat(l); 

			}else{ 
				pn = pn.concat(String.valueOf(pv.charAt(i))); 
			} 
		}
		return pn;
		//System.out.println(pv); //la contraseña vieja
		//System.out.println(pn); //la contraseña nueva
	}
	
	/**
	 * This method can be used to verify a computed hash from a plaintext (e.g. during a login
	 * request) with that of a stored hash from a database. The password hash from the database
	 * must be passed as the second variable.
	 * @param password_plaintext The account's plaintext password, as provided during a login request
	 * @param stored_hash The account's stored password hash, retrieved from the authorization database
	 * @return boolean - true if the password matches the password of the stored hash, false otherwise
	 */
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}

}
