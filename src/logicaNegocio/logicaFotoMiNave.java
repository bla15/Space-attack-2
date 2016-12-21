package logicaNegocio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class logicaFotoMiNave extends JLabel {
	
	//El tama�o de la nave
	public static int TAMA�O = 80;
	double miVelocidad;
	
	/**
	 * Constructor de la clase
	 * @throws IOException En caso de que no encuentre la image de la nave
	 */
		public logicaFotoMiNave() {
			//le damos el tama�o al JLabel de la nave
			setSize(TAMA�O, TAMA�O);
			
			//CReamos una BufferedImage de nombre "imgagenPrimitiva" , que lee el archivo donde esta la imagen
			BufferedImage imgagenPrimitiva = null;//CReamos una BufferedImage de nombre "img" , con la cual se lee el archivo donde esta la imagen
			//Hacemos el try cathc por si no en cuentra el fichero
			try {
				imgagenPrimitiva = ImageIO.read(getClass().getResource("/archivos/imagenes/nave1.png"));
		
			} catch (IOException e) {
				System.err.print("No se ha podido cargar bien la imagen");
			    e.printStackTrace();
			}
			
			/*Utilizo el metodo getScaledInstance el cual pertenenece a la clase BufferedImage y nos da una Image
			 *redimensionada 
			 */
			setIcon(new ImageIcon(imgagenPrimitiva.getScaledInstance(TAMA�O, TAMA�O, Image.SCALE_SMOOTH)));
		}
		
		/**
		 * Metodo encargado de mover la imagen
		 */
		public void calcVelocidadConAceleracion(long tiempo, double aceleracion) {
			int posX=getX();
			miVelocidad=aceleracion*tiempo;
			System.out.println("posicion:  "+ getX()+"velocida:   "+miVelocidad);
			setLocation(posX+=miVelocidad, getY());
		}
		
		/**
		 * Metodo encargado de redibujar la imagen
		 */
		protected void paintComponent(Graphics g) {
			Image imagenRedimensionada= ((ImageIcon)getIcon()).getImage();
			 //El Graphics realmente es Graphics2D
			Graphics2D g2 = (Graphics2D) g;
			// Prepara rotaci�n (siguientes operaciones se rotar�n)
	        //g2.rotate( miGiro, 50, 50 );
			// Prepara rotaci�n (siguientes operaciones se rotar�n)
			 g2.drawImage( imagenRedimensionada, 0, 0, TAMA�O, TAMA�O, null );
		}
		
		/**
		 * Metodo get de el tama�o
		 */
		public static int getTAMA�O() {
			return TAMA�O;
		}
		/**
		 * Metodo set de el tama�o
		 */
		public static void setTAMA�O(int tAMA�O) {
			TAMA�O = tAMA�O;
		}

}
