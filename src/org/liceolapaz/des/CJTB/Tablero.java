package org.liceolapaz.des.CJTB;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Tablero extends JLabel {
	private Ventana ventana;
	private int filas;
	private int columnas;
	private int turno;
	private Boton[][] botones;
	private Boton ultimoPulasado;
	private int intentos;
	private int parejas;
	private int resultados = 0;

	public Tablero(Ventana ventana, int filas, int columnas) {
		super();
		this.filas = filas;
		this.columnas = columnas;
		this.ventana = ventana;

		setLayout(new GridLayout(filas, columnas));
		parejas = (filas * columnas) / 2;

		crearBotones();

	}

	public Tablero(Ventana ventana2, int fils, int colms, Boton[][] botones2, int parejs, int inten) {
		super();
		this.ventana = ventana2;
		this.filas = fils;
		this.columnas = colms;
		this.botones = botones2;
		this.parejas = parejs;
		this.intentos = inten;
		setLayout(new GridLayout(filas, columnas));
		crearNuevosBotones(botones2);
		setBorder(new LineBorder(Color.RED, 5));
		setVisible(true);
	}

	private void crearNuevosBotones(Boton[][] botones) {
		botones = new Boton[filas][columnas];
		for (int filas = 0; filas < this.filas; filas++) {
			for (int columnas = 0; columnas < this.columnas; columnas++) {
				botones[filas][columnas] = botones[filas][columnas];
				this.botones[filas][columnas].setTablero(this);
				add(this.botones[filas][columnas]);
				System.out.println(filas);
				System.out.println(columnas);
			}
		}

	}

	public void comprobarFin() {
		// ventana.parejasEncontradas.setText(Integer.toString(parejas));
		if (parejas == 0) {
			ventana.getTimer().cancel();
			String mensaje = "Ganaste en " + intentos + " intentos";
			JOptionPane.showMessageDialog(null, mensaje);
			int repetir = JOptionPane.showConfirmDialog(null, "Quiere volver a jugar", "Fin de partida",
					JOptionPane.YES_NO_OPTION);
			if (resultados == 1) {
				ventana.guardarResultados();
			}
			if (repetir == JOptionPane.YES_OPTION) {
				ventana.nuevaPartida();
			} else {
				System.exit(0);
			}
		}
	}

	private void generarCasillas() {
		Random random = new Random();
		int contador = 1;
		while (contador <= (filas * columnas) / 2) {
			int fila = random.nextInt(filas);
			int columna = random.nextInt(columnas);
			if (botones[fila][columna].getValor() == 0) {
				this.botones[fila][columna].setValor(contador);
				contador++;

			}
		}

	}

	private void crearBotones() {
		botones = new Boton[filas][columnas];
		for (int filas = 0; filas < this.filas; filas++) {
			for (int columnas = 0; columnas < this.columnas; columnas++) {
				botones[filas][columnas] = new Boton(this, filas, columnas, 0);
				add(this.botones[filas][columnas]);
			}
		}
		generarCasillas();
		generarCasillas();

	}

	public void pulsarBoton(Boton boton) {
		if (this.ultimoPulasado != null) {
			if (ultimoPulasado.getValor() != boton.getValor()) {
				String mensaje = "No son pareja";
				intentos++;
				ventana.intentosContador.setText("" + Integer.toString(intentos));
				JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.OK_OPTION);

				ultimoPulasado.cambiarEstadoNegro();
				boton.cambiarEstadoNegro();
				ultimoPulasado.revalidate();
				boton.revalidate();
				ultimoPulasado = null;
				boton = null;

			} else if (ultimoPulasado.getValor() == boton.getValor()) {
				String mensaje = "Son pareja";
				intentos++;
				ventana.intentosContador.setText("" + Integer.toString(intentos));
				parejas--;
				// System.out.println(parejas);
				ventana.parejasEncontradas.setText("" + Integer.toString(parejas));
				JOptionPane.showMessageDialog(null, mensaje);
				boton.cambiarEstadonBlanco();
				ultimoPulasado.cambiarEstadonBlanco();
				boton = null;
				ultimoPulasado = null;
				comprobarFin();
			}
		}

		this.ultimoPulasado = boton;
		cambiarTurno();
	}

	private void cambiarTurno() {

		if (this.turno == 0) {
			this.turno = 1;

		} else if (this.turno == 1) {

			this.turno = 0;

		}

	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public void setParejas(int parejas) {
		this.parejas = parejas;
	}

	public int getIntentos() {
		return intentos;
	}

	public int getParejas() {
		return parejas;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public Boton[][] getBotones() {
		return botones;
	}

	public void setBotones(Boton[][] botones) {
		this.botones = botones;
	}

	public int getResultados() {
		return resultados;
	}

	public void setResultados(int resultados) {
		this.resultados = resultados;
	}

}
