package logicaNegocio.movimiento;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class logicaEsfera extends JLabel{
	//tama�o del cohete
	public static int tama�oX = 120;
	public static int tama�oY = 120;
	
	public Area miArea = new Area();
	//constructor
	public logicaEsfera(){
		//le damos el tama�o antes definido
		setSize(tama�oX, tama�oY);

		//CReamos una BufferedImage de nombre "imgagenPrimitiva" , que lee el archivo donde esta la imagen
		BufferedImage imgagenPrimitiva = null;//CReamos una BufferedImage de nombre "img" , con la cual se lee el archivo donde esta la imagen
		//Hacemos el try cathc por si no en cuentra el fichero
		try {
			imgagenPrimitiva = ImageIO.read(getClass().getResource("/archivos/armas/esfera.png"));
		} catch (IOException e) {
			System.err.print("No se ha podido cargar bien la imagen");
			e.printStackTrace();
		}
		/*Utilizo el metodo getScaledInstance el cual pertenenece a la clase BufferedImage y nos da una Image
		 *redimensionada 
		 */
		setIcon(new ImageIcon(imgagenPrimitiva.getScaledInstance(tama�oX, tama�oY, Image.SCALE_SMOOTH)));
	}
	
	/**
	 * Metodo encargado de acceder a las posiciones X e Y del proyectil
	 */
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		super.setLocation(x, y);
		Rectangle r = new Rectangle(x,y,25,tama�oY);
		miArea = new Area(r);
	}
	protected void paintComponent(Graphics g) {
		Image imagenRedimensionada= ((ImageIcon)getIcon()).getImage();
		//El Graphics realmente es Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		// Prepara rotaci�n (siguientes operaciones se rotar�n)
		//g2.rotate( miGiro, 50, 50 );
		// Prepara rotaci�n (siguientes operaciones se rotar�n)
		g2.drawImage( imagenRedimensionada, 0, 0, tama�oX, tama�oY, null );
	}


}

