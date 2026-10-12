package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;

	boolean jugando;
	Image imagenPortada;
	int contador = 0;

	Red red;
	Jugadores jugador, jugador2;
	Pelota pelota;
	
	boolean mirandoDerecha = true;
	boolean subiendo = false;

	int puntos1 = 0;
	int puntos2 = 0;

	Juego()
	{

		this.entorno = new Entorno(this, "First n't Game", 800, 600);
		this.jugando = false;
		
		this.imagenPortada = Herramientas.cargarImagen("titulo.png");
		
		// Inicializar lo que haga falta para el juego
		
		red = new Red(400, 300, 4, 600);
		jugador = new Jugadores(0, 300, 20, 80);
		jugador2 = new Jugadores(800, 300, 20, 80);
		pelota = new Pelota(jugador.getAncho() + 10, jugador.getY(), 20, 20);

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase.
	 */
	public void tick()
	{
		if (!jugando) {
			dibujarPantallaTitulo();

			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				this.jugando = true;
			}
		} else {
		
			entorno.cambiarFont("Comic Sans MS", 10, Color.white, entorno.NEGRITA);
			entorno.escribirTexto(" ", 1, 599);
			entorno.cambiarFont("Consolas", 30, Color.white);
			entorno.escribirTexto(" " + puntos1, 200, 100);
			entorno.escribirTexto(" " + puntos2, 600, 100);

	
			if (red != null) {
				red.dibujar(entorno);
			}

			if (jugador != null) {
				jugador.dibujar(entorno);
			}

			if (jugador2 != null) {
				jugador2.dibujar(entorno);
			}

			if (pelota != null) {
				pelota.dibujar(entorno);
			}

			//controles
			if (entorno.estaPresionada('d') && jugador.bordeDerecho() < entorno.ancho() / 2) {
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

			if (entorno.estaPresionada(entorno.TECLA_DERECHA) && jugador2.bordeDerecho() < entorno.ancho()) {
				jugador2.moverDerecha();
			}
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && jugador2.bordeIzquierdo() > entorno.ancho() / 2) {
				jugador2.moverIzquierda(); 
			}
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && jugador2.bordeSuperior() > 0) {
				jugador2.moverArriba();
			}
			if (entorno.estaPresionada(entorno.TECLA_ABAJO) && jugador2.bordeInferior() < entorno.alto()) {
				jugador2.moverAbajo();
			}

		// Colisiones
			if (pelota.colisionaConJugador(jugador)) {
				try { 
					Herramientas.play("contacto.wav");
				} catch (Exception e) {
					System.out.println("No se pudo reproducir el sonido: " + e.getMessage());
				}
				mirandoDerecha = !mirandoDerecha;
				pelota.moverDerecha();
			} else {
				if (mirandoDerecha) {
					pelota.moverDerecha();
					if (pelota.bordeDerecho() >= entorno.ancho()) {
						mirandoDerecha = false;
					}
				} else {
					pelota.moverIzquierda();
					if (pelota.bordeIzquierdo() <= 0) {
						mirandoDerecha = true;
					}
				}
			}

			
			if (pelota.colisionaConJugador(jugador2)) {
				try { 
					Herramientas.play("contacto.wav");
				} catch (Exception e) {
					System.out.println("No se pudo reproducir el sonido: " + e.getMessage());
				}
				mirandoDerecha = !mirandoDerecha;
				pelota.moverIzquierda();
			} else {
				if (mirandoDerecha) {
					pelota.moverDerecha();
					if (pelota.bordeDerecho() >= entorno.ancho()) {
						mirandoDerecha = false;
					}
				} else {
					pelota.moverIzquierda();
					if (pelota.bordeIzquierdo() <= 0) {
						mirandoDerecha = true;
					}
				}
			}

			if (pelota.colisionaConJugador(jugador) || pelota.colisionaConJugador(jugador2)) {
				subiendo = true;
			}
			
			if (subiendo) {
				pelota.moverArriba();
			} else {
				pelota.moverAbajo();
				if (pelota.bordeSuperior() > entorno.alto()) {
					try { 
						Herramientas.play("caer.wav");
					} catch (Exception e) {
						System.out.println("No se pudo reproducir el sonido: " + e.getMessage());
					}
					pelota.setX(400);
					pelota.setY(0);
				}
			}

			if (pelota.bordeSuperior() <= 0) {
				subiendo = false;
			}

		
			// Puntos
			if (pelota.getY() == entorno.alto() && pelota.getX() > entorno.ancho() / 2) {
				puntos1++;
			}
			if (pelota.getY() == entorno.alto() && pelota.getX() < entorno.ancho() / 2) {
				puntos2++;
			}

			if (entorno.sePresiono(entorno.TECLA_ESCAPE)) {
				this.jugando = false;
			}
		}
	}


	private void dibujarPantallaTitulo()
	{
		entorno.colorFondo(Color.BLACK);


		if (imagenPortada != null) {
			entorno.dibujarImagen(imagenPortada, entorno.ancho() / 2.0, entorno.alto() / 2.0 -100, 0);
		}


		entorno.cambiarFont("Consolas", 10, Color.WHITE);
		entorno.escribirTexto(" Genesis-Action 52's  51st game",300,290);
		entorno.cambiarFont("Consolas", 22, Color.LIGHT_GRAY);
		entorno.escribirTexto("Presiona            para Empezar", 200, 400);
		contador++;
		if ((contador / 45) % 2 == 0){
			entorno.cambiarFont("Consolas", 30, Color.WHITE);
			entorno.escribirTexto("ENTER", 330,404);
		}

		entorno.cambiarFont("Arial", 14, Color.GRAY);
		entorno.escribirTexto("Jugador 1: W A S D | Jugador 2: Flechas de dirección", 220, 570);
		entorno.escribirTexto("Presiona ESCAPE durante el juego para volver al menú", 220, 590);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}