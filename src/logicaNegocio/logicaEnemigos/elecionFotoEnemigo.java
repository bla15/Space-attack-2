package logicaNegocio.logicaEnemigos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class elecionFotoEnemigo extends JLabel {

	public static int TAMAÑO = 50;
	double suVelocidad;

	String rutafoto;
	public Area miArea = new Area();

	/**
	 * Constructor de la clase
	 * @param tipoEnemigo
	 */
	public elecionFotoEnemigo(String tipoEnemigo){
		//le damos el tamaño al JLabel de la nave enemiga
		setSize(TAMAÑO, TAMAÑO);

		//CReamos una BufferedImage de nombre "imgagenPrimitiva" , que lee el archivo donde esta la imagen
		BufferedImage imgagenPrimitiva = null;//CReamos una BufferedImage de nombre "img" , con la cual se lee el archivo donde esta la imagen
		//Hacemos el try cathc por si no en cuentra el fichero
		try {

			imgagenPrimitiva =  ImageIO.read(getClass().getResource(tipoEnemigo));
		} catch (IOException e) {
			System.err.print("No se ha podido cargar bien la imagen");
			e.printStackTrace();
		}
		
		/*Utilizo el metodo getScaledInstance el cual pertenenece a la clase BufferedImage y nos da una Image
		 *redimensionada 
		 */
		setIcon(new ImageIcon(imgagenPrimitiva.getScaledInstance(TAMAÑO, TAMAÑO, Image.SCALE_SMOOTH)));
	}
	
	/**
	 * Metodo encagado de mover las posicones de la foto
	 * @param posicionX
	 * @param posicionY
	 */
	public void setPosiciones(int posicionX, int posicionY){
		int posX= getX() + posicionX;
		setLocation(posX, posicionY);
	}
	
	/**
	 * Metodo encargado de calcular la velocidad junto con la aceleracion del enemigo
	 * @param tiempo que dura la aceleracion
	 * @param aceleracion de la foto
	 */
	public void calcVelocidadConAceleracion(long tiempo, double aceleracion) {
		int posX=getX();
		suVelocidad=aceleracion*tiempo;
		System.out.println("posicion:  "+ getX()+"velocida:   "+suVelocidad);
		setLocation(posX+=suVelocidad, getY());
	}

	/**
	 * Metodo encargado de cambiar los ejes X e Y de la foto
	 */
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		super.setLocation(x, y);
		Rectangle r = new Rectangle(x,y,TAMAÑO,TAMAÑO);
		miArea = new Area(r);
	}

	/**
	 * Metodo encargado de modificar los eje X e Y
	 */
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		setLocation(p.x,p.y);
	}
	
	/**
	 * Metodo encargado de redibujar la imagen
	 */
	protected void paintComponent(Graphics g) {
		Image imagenRedimensionada= ((ImageIcon)getIcon()).getImage();
		 //El Graphics realmente es Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		// Prepara rotación (siguientes operaciones se rotarán)
        //g2.rotate( miGiro, 50, 50 );
		// Prepara rotación (siguientes operaciones se rotarán)
		 g2.drawImage( imagenRedimensionada, 0, 0, TAMAÑO, TAMAÑO, null );
	}
		
}
