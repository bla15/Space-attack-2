package logicaNegocio.logicaEnemigos;

import java.awt.geom.Area;
import java.util.ArrayList;

import logicaNegocio.logicaFotoMiNave;
import logicaNegocio.movimiento.logicaEsfera;
import ventanas.ventanaEntreBatallas;
import ventanas.ventanaMenu;
import ventanas.ventanaPrincipal;

public class enemigoCinco {
	//limites
	int limiteDerecho=ventanaPrincipal.fondoJuego.WIDTH;
	int limiteIzquierdo=ventanaPrincipal.ancho-50;

	//logicas
	public  logicaEnemigosConjunta unEnemigo;
	ArrayList<logicaEnemigosConjunta> misEnemigos = new ArrayList<logicaEnemigosConjunta>();
	//los enemigos abatidos
	public  logicaEnemigosConjunta muerte;
	ArrayList<logicaEnemigosConjunta> misMuertes = new ArrayList<logicaEnemigosConjunta>();	

	//Variables de dificultad
	String tipoEnemigo= "/archivos/enemigos/cinco.png";
	int velocidadEstandar=-50;
	int tiempoCreacion=2000;

	//bandera de los hilos
	public static boolean funcionar=true;

	//hilos
	hiloCreacionEnemigos creacion;
	
	//esfera
	logicaEsfera esfera;

	/**
	 * Constructo de clase
	 */
	public enemigoCinco(){
		//lave de los hilos
		funcionar=true;

		velocidadEstandar=-50;
		tiempoCreacion=2000;
		
		//esfera
		esfera= new logicaEsfera();
		esfera.setVisible(true);
		esfera.setLocation(600, 480);
		ventanaPrincipal.fondoJuego.add(esfera);

		//lanzams hilo de creacion de enemigos
		creacion = new hiloCreacionEnemigos(); 
		creacion.start();
		
		//lanzamos el hilo de movimiento de los enemigos
		hiloMovimiento mover = new hiloMovimiento(); 
		mover.start();

		//lanzamos el hilo de choques
		hiloChoques choqueConLaser = new hiloChoques(); 
		choqueConLaser.start();

		//lanzamos el hilo de las muertes 
		hiloMovimientoMuertes moverMuerte = new hiloMovimientoMuertes(); 
		moverMuerte.start();

	}

	/**
	 * Metodo que me devuelve el tamamño del array de enemigo
	 * @return enemigos en juego
	 */
	public int getTamañoArray(){
		return misEnemigos.size();
	}
	public class hiloCreacionEnemigos extends Thread{
		public void run() {
			while(funcionar&&ventanaPrincipal.vida>0&& ventanaMenu.ln.cambioMapa){
				//usamos el boton pausar
				if(ventanaPrincipal.pausar==true){

					unEnemigo= new logicaEnemigosConjunta(tipoEnemigo);
					//posicon aleatoria en el eje de las x (sin que toque los bordes para que se vea bien la imagen
					unEnemigo.setPosX((int)(Math.random()*((limiteIzquierdo)-limiteDerecho+1)+limiteDerecho));
					//la posicion de las y es el alto del panel
					unEnemigo.setPosY(ventanaPrincipal.fondoJuego.HEIGHT);
					//le damos un giro hacia la derecha o izquierda
					int numero = (int) (Math.random() * 2);
					if(numero==0){
						unEnemigo.setGiro(270);
					}else{
						unEnemigo.setGiro(90);
					}

					//lo metemos en el array de enmigos
					misEnemigos.add(unEnemigo);

					//lo sacmos en el panel de juego
					if(unEnemigo!=null){

						ventanaPrincipal.fondoJuego.add(unEnemigo.getFotoEnemigo());
						ventanaPrincipal.fondoJuego.repaint();
						//cada nuevo enemigo sale mas rapido
						velocidadEstandar-=5;

					}else{
						System.out.println("no hay aun enemigo");
					}
					if(tiempoCreacion>=1500){
						//reducimos tiempo de creacion
						tiempoCreacion-=25;
					}
				}
				try {
					//cada cuanto tiempo los va creando
					Thread.sleep(tiempoCreacion);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
		}
	}
	
	/*
	 * hilo que gestiona el movimiento de los enemigos
	 * y la retira de vidas si pasan la frontera
	 */
	public class hiloMovimiento extends Thread{
		int i;
		public void run(){
			while(ventanaPrincipal.vida>0&&(funcionar)){
				//usamos el boton pausar
				if(ventanaPrincipal.pausar==true && ventanaPrincipal.corazon!=null){
					esfera.setLocation((int)ventanaPrincipal.naveConjunta.getPosX()-23,500);
					//les damos movimiento
					for(i=0;i<misEnemigos.size();i++){
						//misEnemigos.get(i).gira(10);
						misEnemigos.get(i).setSuVelocidad(velocidadEstandar);
						//Ultimo modificado con giro = 90 como giro base (el movimiento enemigo es ya conocido)
						misEnemigos.get(i).mueve(0.040, misEnemigos.get(i).getGiro());
						ventanaPrincipal.fondoJuego.repaint();
					}
					
					//miramos si sobrepasan las frontera
					for(i=0;i<misEnemigos.size();i++){
						if(misEnemigos.get(i).getPosY()>ventanaPrincipal.fondoJuego.getHeight()-50){
							ventanaPrincipal.fondoJuego.remove(misEnemigos.get(i).getFotoEnemigo());
							misEnemigos.remove(i);
							ventanaPrincipal.vida-=1;
							//ventanaStart.contenedor.setEnemigoPasa1(ventanaStart.contenedor.getEnemigoPasa1()+1);
							//vemos que hacer con los corazones
							if(ventanaPrincipal.vida<=0){
								ventanaPrincipal.corazon.setVidas(ventanaPrincipal.vida);
								ventanaPrincipal.corazon.pares();
								
								//LLAMAR METODO CAMBIO PANTALLA MUERTE
								ventanaMenu.ln.muerte(ventanaMenu.ln.getNivel());
								//paramos los hilos
								ventanaPrincipal.funcionar=false;
							}else if((ventanaPrincipal.vida % 2) != 0) {
								ventanaPrincipal.corazon.setVidas(ventanaPrincipal.vida);
								ventanaPrincipal.corazon.impares();
							}else if ((ventanaPrincipal.vida % 2) == 0) {
								ventanaPrincipal.corazon.setVidas(ventanaPrincipal.vida);
								ventanaPrincipal.corazon.pares();
							}
						}
						
						//miramos los rebotes en los limites latetrales
						//primero si existen elementos
						if(misEnemigos.size()!=0){
							if(misEnemigos.get(i).getPosX() < limiteDerecho){
								misEnemigos.get(i).setGiro(270);
							} else if (misEnemigos.get(i).getPosX() > limiteIzquierdo){
								misEnemigos.get(i).setGiro(90);
							}
						}
					}	
				}
				try {
					hiloMovimiento.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * hilo que gestiona los choques de los enemigos
	 */
	public class hiloChoques extends Thread{
		int i;
		int z;
		public void run(){
			while(ventanaPrincipal.vida>0&&(funcionar)){
				//usamos el boton pausar
				if(ventanaPrincipal.pausar==true){
					for(i=0;i<misEnemigos.size();i++){
						Area areaEnemigo = new Area(misEnemigos.get(i).getFoto().miArea);
						Area areaEsfera = new Area(esfera.miArea);
						if(areaEnemigo.intersects(areaEsfera.getBounds2D())){
							System.out.println("SUIIIIIIIIIIII");
							ventanaPrincipal.fondoJuego.remove(misEnemigos.get(i).getFotoEnemigo());

							muerte= new logicaEnemigosConjunta("/archivos/enemigos/muerte.png");
							muerte.setPosX(misEnemigos.get(i).getPosX());
							muerte.setPosY(misEnemigos.get(i).getPosY());
							misMuertes.add(muerte);
							ventanaPrincipal.fondoJuego.add(muerte.getFotoEnemigo());

							misEnemigos.remove(i);
						}
					}
						
					}
				if((ventanaMenu.ln.cambioMapa==false)&& misEnemigos.size()==0 && misMuertes.size()==0){
					try {
						hiloChoques.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ventanaEntreBatallas.window.reutilizar();
					ventanaEntreBatallas.window.frame.setVisible(true);	
					ventanaPrincipal.naveConjunta.setPosX((int) Math.floor(ventanaPrincipal.ancho * 0.5));
					ventanaPrincipal.naveConjunta.setPosY((int) Math.floor(ventanaPrincipal.alto * 0.80)-logicaFotoMiNave.getTAMAÑO());
					ventanaPrincipal.naveConjunta.setMiVelocidad(0);
					ventanaPrincipal.naveConjunta.acelera(0);
					esfera.setVisible(false);
					
					ventanaPrincipal.window.frame.dispose();
					
					System.out.println("enemigo uno parado");
					funcionar=false;
				
				}
			
				try {
					hiloChoques.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Hilo que gestiona el movimiento de las muertes
	 */
	public class hiloMovimientoMuertes extends Thread{
		int i;
		public void run(){
			while(ventanaPrincipal.vida>0&&(funcionar)){
				if(ventanaPrincipal.pausar==true && ventanaPrincipal.corazon!=null){
					//les damos movimiento
					for(i=0;i<misMuertes.size();i++){
						//	misEnemigos.get(i).gira(10);
						misMuertes.get(i).setSuVelocidad(-1*(velocidadEstandar-20));
						misMuertes.get(i).mueve(0.040, misMuertes.get(i).getGiro());
						ventanaPrincipal.fondoJuego.repaint();
					}
					
					//miramos si pasan la frontera superior
					for(i=0;i<misMuertes.size();i++){
						if(misMuertes.get(i).getPosY()<0){
							ventanaPrincipal.fondoJuego.remove(misMuertes.get(i).getFotoEnemigo());
							misMuertes.remove(i);
						}
					}
				}
				try {
					hiloMovimientoMuertes.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
