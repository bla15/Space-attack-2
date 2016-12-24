package logicaNegocio.logicaArmas;


import ventanas.ventanaPrincipal;


public class logicaMovimientoArma {
	//valores de la nave
	boolean teclasMovimientoNave [] = new boolean[3];

	//Elementoss de los hilos
	protected int i;
	
	//creacion del hilo
	hiloArma miHiloArma = new hiloArma(); 
	
	/**
	 * Metodo get de teclasMovimiento
	 */
	public boolean[] getTeclasMovimientoNave() {
		return teclasMovimientoNave;
	}
	/**
	 * Metodo set de teclasMovimiento
	 */
	public void setTeclasMovimientoNave(int i,boolean b){
		this.teclasMovimientoNave[i]=b;
	}
	
	/*
	 * Constructor de la clase
	 */
	public void movimentoBaseArma(){
		//hilos
		
		miHiloArma.start();
	}
	
	/*
	 * Hilo para la gestion de los proyectiles
	 */
	public class hiloArma extends Thread{
		public void run(){

			while(ventanaPrincipal.funcionar==true){
				//System.out.println(ventanaPrincipal.pausar);
				//usamos boton pausar
			System.out.println(ventanaPrincipal.pausar);
				while(ventanaPrincipal.pausar){
					if(ventanaPrincipal.disparo==true){
						ventanaPrincipal.unProyectil= new armaConjunto();
						ventanaPrincipal.unProyectil.setPosX(ventanaPrincipal.naveConjunta.getPosX()+13);
						ventanaPrincipal.unProyectil.setPosY(ventanaPrincipal.naveConjunta.getPosY());
						ventanaPrincipal.unProyectil.setMiVelocidad(300);
						ventanaPrincipal.fondoJuego.add(ventanaPrincipal.unProyectil.getFotoArma());
						
						//el giro del laser
						if(ventanaPrincipal.naveConjunta.getMiVelocidad()>100){
							ventanaPrincipal.unProyectil.gira(20);	
						}

						else if(ventanaPrincipal.naveConjunta.getMiVelocidad()<-100){
							ventanaPrincipal.unProyectil.gira(-20);
						}
						//lo añadimos al arrayList de lasers
						ventanaPrincipal.misProyectiles.add(ventanaPrincipal.unProyectil);
						//Ponemos la variable a falso
						ventanaPrincipal.disparo = false;
					}
					//gestionamos el movimiento de todos los laseres
					for(i=0;i<ventanaPrincipal.misProyectiles.size();i++){
						ventanaPrincipal.misProyectiles.get(i).mueve(0.040);
						if (ventanaPrincipal.misProyectiles.get(i).getPosY() < -logicaFotoArma.TAMAÑOX/2 || ventanaPrincipal.misProyectiles.get(i).getPosY()>ventanaPrincipal.fondoJuego.getHeight()-logicaFotoArma.TAMAÑOX/2 ) {
							ventanaPrincipal.fondoJuego.remove(ventanaPrincipal.misProyectiles.get(i).getFotoArma());
							ventanaPrincipal.misProyectiles.remove(i);
						}
					}
					try {
						hiloArma.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}}
