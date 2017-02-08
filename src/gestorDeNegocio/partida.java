package gestorDeNegocio;

import java.sql.Date;

public class partida {
	private int id_u, id_partida;
	private String nombrePiloto, raza, ultimoPlaneta;
	private int disparos, deads, score, life;
	private boolean status;
	private Date created_at, updated_at;
	public int getId_u() {
		return id_u;
	}
	public void setId_u(int id_u) {
		this.id_u = id_u;
	}
	public int getId_partida() {
		return id_partida;
	}
	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}
	public String getNombrePiloto() {
		return nombrePiloto;
	}
	public void setNombrePiloto(String nombrePiloto) {
		this.nombrePiloto = nombrePiloto;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getUltimoPlaneta() {
		return ultimoPlaneta;
	}
	public void setUltimoPlaneta(String ultimoPlaneta) {
		this.ultimoPlaneta = ultimoPlaneta;
	}
	public int getDisparos() {
		return disparos;
	}
	public void setDisparos(int disparos) {
		this.disparos = disparos;
	}
	public int getDeads() {
		return deads;
	}
	public void setDeads(int deads) {
		this.deads = deads;
	}
	public int getScore() {
		return score;
	}
	public void setScore() {
		if(disparos==0){
			score=0;
		}else{
			this.score = calcularScore();
		}
		
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public int calcularScore(){
		int score;
		
		score= (deads*100)/disparos ;
		return score;
	}
	

}
