package pruebas;

import java.awt.EventQueue;

import ventanas.ventanaCampa�a;
import ventanas.ventanaMenu;
import logicaNegocio.logicaNivel;
import logicaNegocio.partidasLN;

public class pruebasCargarPartida {
	partidasLN miPartida = new partidasLN();
	
	public pruebasCargarPartida(){
		miPartida.setRaza("Amarr");
		miPartida.setPlaneta(3);
		miPartida.setDa�orecibido(3);
		
		ventanaMenu.ln = new logicaNivel(miPartida.getPlaneta(),miPartida.getDa�orecibido());
	}
	
}
