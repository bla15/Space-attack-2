package pruebas;

import gestorDeNegocio.partida;

import java.awt.EventQueue;

import ventanas.ventanaCampa�a;
import ventanas.ventanaMenu;
import logicaNegocio.logicaNivel;

public class pruebasCargarPartida {
	partida miPartida = new partida();
	
	public pruebasCargarPartida(){
		miPartida.setRaza("Amarr");
		miPartida.setUltimoPlaneta("z");
		miPartida.setLife(3);
		
		//ventanaMenu.ln = new logicaNivel(5,miPartida.getLife(),true);
	}
	
}
