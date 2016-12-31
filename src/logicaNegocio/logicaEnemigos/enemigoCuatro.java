package logicaNegocio.logicaEnemigos;

import java.awt.geom.Area;
import java.util.ArrayList;

import com.mysql.jdbc.EscapeTokenizer;

import logicaNegocio.logicaFotoMiNave;
import logicaNegocio.logicaEnemigos.enemigosTres.hiloChoques;
import logicaNegocio.logicaEnemigos.enemigosTres.hiloCreacionEnemigos;
import logicaNegocio.logicaEnemigos.enemigosTres.hiloMovimiento;
import logicaNegocio.logicaEnemigos.enemigosTres.hiloMovimientoMuertes;
import ventanas.ventanaEntreBatallas;
import ventanas.ventanaMenu;
import ventanas.ventanaPrincipal;

public class enemigoCuatro {
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
	String tipoEnemigo= "/archivos/enemigos/cuatro.png";
	int velocidadEstandar=-100;
	int tiempoCreacion=1500;

	//bandera de los hilos
	public static boolean funcionar=true;
	
	//hilos
	hiloCreacionEnemigos creacion;
	
	/**
	 * Constructo de clase
	 */
	public enemigoCuatro(){
		
		//lave de los hilos
		funcionar=true;

		velocidadEstandar=-50;
		tiempoCreacion=2000;

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
				try {
					hiloMovimiento.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//usamos el boton pausar
				if(ventanaPrincipal.pausar==true && ventanaPrincipal.corazon!=null){
					//les damos movimiento
					for(i=0;i<misEnemigos.size();i++){
						//misEnemigos.get(i).gira(10);
						misEnemigos.get(i).setSuVelocidad(velocidadEstandar);
						misEnemigos.get(i).mueve(0.040, misEnemigos.get(i).getGiro());
						ventanaPrincipal.fondoJuego.repaint();
					}
					
					//miramos si sobrepasan las frontera
					for(i=0;i<misEnemigos.size();i++){
						if(misEnemigos.get(i).getPosY()>ventanaPrincipal.fondoJuego.getHeight()-50){
							ventanaPrincipal.fondoJuego.remove(misEnemigos.get(i).getFotoEnemigo());
							misEnemigos.remove(i);
							//ventanaStart.contenedor.setEnemigoPasa1(ventanaStart.contenedor.getEnemigoPasa1()+1);
							//vemos que hacer con los corazones
							if(ventanaPrincipal.especialVida<=0){
								ventanaPrincipal.corazon.setVidas(ventanaPrincipal.especialVida);
								ventanaPrincipal.corazon.pares();
								
								//LLAMAR METODO CAMBIO PANTALLA MUERTE
								ventanaMenu.ln.muerte(ventanaMenu.ln.getNivel());
							
								//paramos los hilos
								ventanaPrincipal.funcionar=false;
							}else if(ventanaPrincipal.especialVida>0){
								System.out.println(ventanaPrincipal.especialVida);
								ventanaPrincipal.corazon.setVidas(ventanaPrincipal.especialVida);
								ventanaPrincipal.corazon.eliminar();
							}
							
							ventanaPrincipal.especialVida-=1;
						}
					}	
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
					for(z=0;z<ventanaPrincipal.misProyectiles.size();z++){
						for(i=0;i<misEnemigos.size();i++){
							Area areaEnemigo = new Area(misEnemigos.get(i).getFoto().miArea);
							Area areaArma = new Area(ventanaPrincipal.misProyectiles.get(z).getFotoArma().miArea );
							if(areaEnemigo.intersects(areaArma.getBounds2D())){
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

