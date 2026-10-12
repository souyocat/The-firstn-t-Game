package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Red red;
	Jugadores jugador,jugador2;
	Pelota pelota;
	
	boolean mirandoDerecha=true;
	boolean subiendo=false;

	int puntos1=0;
	int puntos2=0;



	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "First n´t Game", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		red = new Red(400, 300, 4,600);
		jugador = new Jugadores (0,300,20,80);
		jugador2 = new Jugadores (800, 300,20,80);
		pelota = new Pelota (jugador.getAncho()+10, jugador.getY(), 20,20);
		// ...


		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */



	public void tick()
	{
		// Procesamiento de un instante de tiempo
		entorno.cambiarFont("Comic Sans MS ", 10, Color.white,entorno.NEGRITA);
		entorno.escribirTexto(" ", 1, 599);
		entorno.cambiarFont("Consolas", 30, Color.white);
		entorno.escribirTexto(" " + puntos1, 200, 100);
		entorno.escribirTexto(" " + puntos2, 600, 100);
		

		// ...


		if (red != null) {
			red.dibujar(entorno);
		}

		if (jugador != null) {
			jugador.dibujar(entorno);
		}

		if (jugador2 != null) {
			jugador2.dibujar(entorno);
		}

		if (pelota !=null){
			pelota.dibujar(entorno);
		}




		if (entorno.estaPresionada('d') && jugador.bordeDerecho() < entorno.ancho()/2) {
			jugador.moverDerecha();
		}
		if (entorno.estaPresionada('a') && jugador.bordeIzquierdo() > 0) {
			jugador.moverIzquierda(); 
		}
		if (entorno.estaPresionada('w') && jugador.bordeSuperior() > 0) {
			jugador.moverArriba();
		}
		if (entorno.estaPresionada('s') && jugador.bordeInferior() < entorno.alto()) {
			jugador.moverAbajo();
		}




		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && jugador2.bordeDerecho() < entorno.ancho() ){
			jugador2.moverDerecha();
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && jugador2.bordeIzquierdo() > entorno.ancho()/2) {
			jugador2.moverIzquierda(); 
		}
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && jugador2.bordeSuperior() > 0) {
			jugador2.moverArriba();
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO) && jugador2.bordeInferior() < entorno.alto()) {
			jugador2.moverAbajo();
		}



		if (pelota.colisionaConJugador(jugador)) {
			mirandoDerecha = !mirandoDerecha;
			pelota.moverDerecha();

		} else {
			if (mirandoDerecha) {
				pelota.moverDerecha();
				if (pelota.bordeDerecho() >= entorno.ancho()) {
					mirandoDerecha = false;}
				} else {
					pelota.moverIzquierda();
					if (pelota.bordeIzquierdo() <= 0) {
						mirandoDerecha = true;
					}
				}
			}



	if (pelota.colisionaConJugador(jugador2)) {
			mirandoDerecha = !mirandoDerecha;
			pelota.moverIzquierda();

		} else {
			if (mirandoDerecha) {
				pelota.moverDerecha();
				if (pelota.bordeDerecho() >= entorno.ancho()) {
					mirandoDerecha = false;}
				} else {
					pelota.moverIzquierda();
					if (pelota.bordeIzquierdo() <= 0) {
						mirandoDerecha = true;
					}
				}
			}

	/*if (pelota.colisionaConJugador(jugador) || pelota.colisionaConJugador(jugador2)){
		pelota.moverArriba();
		
	}else{
		pelota.moverAbajo();
		if(pelota.bordeSuperior()>entorno.alto()){
			pelota.setX(100);
			pelota.setY(100);
		}
	
	}
*/
	if (pelota.colisionaConJugador(jugador) || pelota.colisionaConJugador(jugador2)) {
		subiendo = true;
	}
	if (subiendo) {
		pelota.moverArriba();
	} else {
		pelota.moverAbajo();
			if(pelota.bordeSuperior()>entorno.alto()){
				pelota.setX(400);
				pelota.setY(0);
		}
	}
	if (pelota.bordeSuperior() <= 0) {
		subiendo = false;
	}



	//jugador 1
	if (pelota.getY() == entorno.alto() && pelota.getX() > 400) {
		puntos1++;
	}

	//jugador 2
	if (pelota.getY() == entorno.alto() && pelota.getX() < 400) {
		puntos2++;
	}


	

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
