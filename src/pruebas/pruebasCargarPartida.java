package pruebas;

import java.awt.EventQueue;

import ventanas.ventanaCampaņa;
import ventanas.ventanaMenu;
import logicaNegocio.logicaNivel;
import logicaNegocio.partidasLN;

public class pruebasCargarPartida {
	partidasLN miPartida = new partidasLN();
	
	public pruebasCargarPartida(){
		miPartida.setRaza("Amarr");
		miPartida.setPlaneta(3);
		miPartida.setDaņorecibido(3);
		
		ventanaMenu.ln = new logicaNivel(miPartida.getPlaneta(),miPartida.getDaņorecibido());
	}
	
}
