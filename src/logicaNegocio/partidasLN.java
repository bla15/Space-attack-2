package logicaNegocio;

import java.util.Date;

public class partidasLN {
	int idPartida, planeta, disparosTotales, enemigosTotales, puntuacion, tiempoTotal, dañorecibido;
	String NombreComandante, Raza;
	Date fechaGuardado;
	public int getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	
	public int getPlaneta() {
		return planeta;
	}
	public void setPlaneta(int planeta) {
		this.planeta = planeta;
	}
	public int getDisparosTotales() {
		return disparosTotales;
	}
	public void setDisparosTotales(int disparosTotales) {
		this.disparosTotales = disparosTotales;
	}
	public int getEnemigosTotales() {
		return enemigosTotales;
	}
	public void setEnemigosTotales(int enemigosTotales) {
		this.enemigosTotales = enemigosTotales;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public int getDañorecibido() {
		return dañorecibido;
	}
	public void setDañorecibido(int dañorecibido) {
		this.dañorecibido = dañorecibido;
	}
	public String getNombreComandante() {
		return NombreComandante;
	}
	public void setNombreComandante(String nombreComandante) {
		NombreComandante = nombreComandante;
	}
	public String getRaza() {
		return Raza;
	}
	public void setRaza(String raza) {
		Raza = raza;
	}
	
	

}
