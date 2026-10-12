package juego;
import java.awt.Color;
import entorno.Entorno;

public class Red {
    
    private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Red (int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
    public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0 , Color.white);
	}

}
