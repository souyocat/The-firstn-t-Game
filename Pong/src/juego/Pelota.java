package juego;
import java.awt.Color;
import entorno.Entorno;

public class Pelota {
    private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Pelota(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0 , Color.white);
	}


    public void moverDerecha() {
		this.x=this.x+4;
	}
	public void moverIzquierda() {
		this.x=this.x-4;
	}
	public void moverArriba() {
		this.y=this.y-3;
	}
	public void moverAbajo() {
		this.y=this.y+3;
	}

    public boolean colisionaConJugador(Jugadores j) {
		if(this.bordeIzquierdo()>j.bordeDerecho()) {
			return false;
		}
		if(this.bordeDerecho()<j.bordeIzquierdo()) {
			return false;
		}
		if(this.bordeSuperior()>j.bordeInferior()) {
			return false;
		}
		if(this.bordeInferior()<j.bordeSuperior()) {
			return false;
		}
		return true;
    }
	
























































































    public int bordeDerecho() {
		return this.x+this.ancho/2;
	}
	public int bordeIzquierdo() {
		return this.x-this.ancho/2;
	}
	public int bordeSuperior() {
		return this.y-this.alto/2;
	}
	public int bordeInferior() {
		return this.y+this.alto/2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}



}
