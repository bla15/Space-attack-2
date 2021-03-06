package logicaNegocio;

import java.awt.Color;

import pruebas.ventanaPruebas;
import ventanas.ventanaPrincipal;
import logicaVentanas.logicaFondos;

public class logicaCorazones {
	// vidas que me quedan
	int vidas;
	//los corazones
	logicaFondos corazon1;
	logicaFondos corazon2;
	logicaFondos corazon3;
	logicaFondos corazon4;

	/**
	 * Constructor de la clase
	 * @param vida: es un valor numerico que decrece al perder vidas
	 */
	public logicaCorazones(int vida){
		
		//igualamos vidas
		this.vidas=vida;
		
		//creamos todos los corazones
		corazon1 = new logicaFondos("/archivos/escudos/completo.png");
		corazon1.setSize(39, 45);
		corazon1.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.05),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon1.getWidth(), corazon1.getHeight());
		ventanaPrincipal.panelCorazones.add(corazon1);

		corazon2 = new logicaFondos("/archivos/escudos/completo.png");
		corazon2.setSize(39, 45);
		corazon2.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.28),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon2.getWidth(), corazon2.getHeight());
		ventanaPrincipal.panelCorazones.add(corazon2);

		corazon3 = new logicaFondos("/archivos/escudos/completo.png");
		corazon3.setSize(39, 45);
		corazon3.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.51),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon3.getWidth(), corazon3.getHeight());
		ventanaPrincipal.panelCorazones.add(corazon3);

		corazon4 = new logicaFondos("/archivos/escudos/completo.png");
		corazon4.setSize(39, 45);
		corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
		ventanaPrincipal.panelCorazones.add(corazon4);
		
		
		//pintamos los corazones reales despues de la carga
		pintarCorazones();
		
		ventanaPrincipal.fondoControles.repaint();
	}
	/**
	 * Metodo encargado de pintar los corazones
	 */
	public void pintarCorazones(){
		if((vidas==6)||(vidas==5)){
			
			corazon4.setCambio("/archivos/escudos/roto.png");
			corazon4.setSize(39, 45);
			corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon4);
			
		}else if((vidas==4)||(vidas==3)){
			ventanaPrincipal.panelCorazones.remove(corazon4);
			corazon4 = new logicaFondos("/archivos/escudos/roto.png");
			corazon4.setSize(39, 45);
			corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon4);
			
			ventanaPrincipal.panelCorazones.remove(corazon3);
			corazon3 = new logicaFondos("/archivos/escudos/roto.png");
			corazon3.setSize(39, 45);
			corazon3.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.51),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon3.getWidth(), corazon3.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon3);
			
		}else if((vidas==2)||(vidas==1)){
			ventanaPrincipal.panelCorazones.remove(corazon4);
			corazon4 = new logicaFondos("/archivos/escudos/roto.png");
			corazon4.setSize(39, 45);
			corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon4);
			
			ventanaPrincipal.panelCorazones.remove(corazon3);
			corazon3 = new logicaFondos("/archivos/escudos/roto.png");
			corazon3.setSize(39, 45);
			corazon3.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.51),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon3.getWidth(), corazon3.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon3);
			
			ventanaPrincipal.panelCorazones.remove(corazon2);
			corazon2 = new logicaFondos("/archivos/escudos/roto.png");
			corazon2.setSize(39, 45);
			corazon2.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.28),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon2.getWidth(), corazon2.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon2);
		}
		
		
	}
	
	/**
	 * Metodo encargado de eliminar los corazones
	 */
	public void pares(){
		if(vidas==6){
			ventanaPrincipal.panelCorazones.remove(corazon4);
			corazon4 = new logicaFondos("/archivos/escudos/roto.png");
			corazon4.setSize(39, 45);
			corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon4);
			ventanaPrincipal.fondoControles.repaint();
		}else if(vidas==4){
			ventanaPrincipal.panelCorazones.remove(corazon3);
			corazon3 = new logicaFondos("/archivos/escudos/roto.png");
			corazon3.setSize(39, 45);
			corazon3.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.51),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon3.getWidth(), corazon3.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon3);
			ventanaPrincipal.fondoControles.repaint();
		}else if(vidas==2){
			ventanaPrincipal.panelCorazones.remove(corazon2);
			corazon2 = new logicaFondos("/archivos/escudos/roto.png");
			corazon2.setSize(39, 45);
			corazon2.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.28),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon2.getWidth(), corazon2.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon2);
			ventanaPrincipal.fondoControles.repaint();
		}else if(vidas==0){
			ventanaPrincipal.panelCorazones.remove(corazon1);
			corazon1 = new logicaFondos("/archivos/escudos/roto.png");
			corazon1.setSize(39, 45);
			corazon1.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.05),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon1.getWidth(), corazon1.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon1);
			ventanaPrincipal.fondoControles.repaint();
		}
	}
	
	/**
	 * Metodo encargado de quitar medio corazon
	 */
	public void impares(){
		if(vidas==7){
			ventanaPrincipal.panelCorazones.remove(corazon4);
			corazon4 = new logicaFondos("/archivos/escudos/medio.png");
			corazon4.setSize(39, 45);
			corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon4);
			ventanaPrincipal.fondoControles.repaint();
		}
		if(vidas==5){
			ventanaPrincipal.panelCorazones.remove(corazon3);
			corazon3 = new logicaFondos("/archivos/escudos/medio.png");
			corazon3.setSize(39, 45);
			corazon3.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.51),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon3.getWidth(), corazon3.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon3);
			ventanaPrincipal.fondoControles.repaint();
		}
		if(vidas==3){
			ventanaPrincipal.panelCorazones.remove(corazon2);
			corazon2 = new logicaFondos("/archivos/escudos/medio.png");
			corazon2.setSize(39, 45);
			corazon2.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.28),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon2.getWidth(), corazon2.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon2);
			ventanaPrincipal.fondoControles.repaint();
		}
		if(vidas==1){
			ventanaPrincipal.panelCorazones.remove(corazon1);
			corazon1 = new logicaFondos("/archivos/escudos/medio.png");
			corazon1.setSize(39, 45);
			corazon1.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.05),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon1.getWidth(), corazon1.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon1);
			ventanaPrincipal.fondoControles.repaint();
		}
	}
	
	/**
	 * Metodo encargado de eliminar los corazones de manera especial
	 */
	public void eliminar(){
		if(vidas>=4){
			ventanaPrincipal.especialVida=4;
			ventanaPrincipal.vida=6;
			ventanaPrincipal.panelCorazones.remove(corazon4);
			corazon4 = new logicaFondos("/archivos/escudos/roto.png");
			corazon4.setSize(39, 45);
			corazon4.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.74),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon4.getWidth(), corazon4.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon4);
			ventanaPrincipal.fondoControles.repaint();
		}else if(vidas==3){
			ventanaPrincipal.vida=4;
			ventanaPrincipal.panelCorazones.remove(corazon3);
			corazon3 = new logicaFondos("/archivos/escudos/roto.png");
			corazon3.setSize(39, 45);
			corazon3.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.51),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon3.getWidth(), corazon3.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon3);
			ventanaPrincipal.fondoControles.repaint();
		}else if(vidas==2){
			ventanaPrincipal.vida=2;
			ventanaPrincipal.panelCorazones.remove(corazon2);
			corazon2 = new logicaFondos("/archivos/escudos/roto.png");
			corazon2.setSize(39, 45);
			corazon2.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.28),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon2.getWidth(), corazon2.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon2);
			ventanaPrincipal.fondoControles.repaint();
		}else if(vidas==1){
			ventanaPrincipal.vida=0;
			ventanaPrincipal.panelCorazones.remove(corazon1);
			corazon1 = new logicaFondos("/archivos/escudos/roto.png");
			corazon1.setSize(39, 45);
			corazon1.setBounds((int) Math.floor(ventanaPrincipal.panelCorazones.getWidth() * 0.05),(int) Math.floor(ventanaPrincipal.panelCorazones.getHeight() * 0.15), corazon1.getWidth(), corazon1.getHeight());
			ventanaPrincipal.panelCorazones.add(corazon1);
			ventanaPrincipal.fondoControles.repaint();
		}
	}
	public int getVidas() {
		return vidas;
		}

		public void setVidas(int vidas) {
			this.vidas = vidas;
		}
	}
