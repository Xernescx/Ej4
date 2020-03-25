package org.liceolapaz.des.CJTB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Boton extends JButton {
	private Tablero tablero;
	private int fila;
	private int columna;
	private int valor;
	private boolean pulsado;
	private int turno;

	public Boton(Tablero tablero, int fila, int columna, int valor) {
		super();
		this.tablero = tablero;
		this.columna = columna;
		this.fila = fila;
		this.pulsado = false;
		cambiarEstadoNegro();
		setValor(valor);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Boton.this.pulsado) {

					pulsar();
					// tablero.comprobarFin();
					// System.out.println(turno);

				}

			}

		});

	}

	public Boton(Tablero tablero, int fil, int col, int val, boolean est) {
		this.tablero = tablero;
		this.fila = fil;
		this.columna = col;
		this.valor = val;
		this.pulsado = est;
		setValor(val);
		setPulsado(est);
		if (pulsado == false) {
			cambiarEstadoNegro();
		} else {
			cambiarEstadonBlanco();
		}
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Boton.this.pulsado) {

					pulsar();
					// tablero.comprobarFin();
					// System.out.println(turno);

				}

			}

		});
	}

	private void pulsar() {
		cambiarEstadonBlanco();
		this.pulsado = true;
		this.tablero.pulsarBoton(this);
		turno = this.tablero.getTurno();

	}

	public void cambiarEstadonBlanco() {

		setBackground(Color.WHITE);
		setText("" + getValor());
		setEnabled(false);

	}

	public void cambiarEstadoNegro() {
		pulsado = false;
		setBackground(Color.BLACK);
		setText("");
		setEnabled(true);

	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;

	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean isPulsado() {
		return pulsado;
	}

	public void setPulsado(boolean pulsado) {
		this.pulsado = pulsado;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

}
