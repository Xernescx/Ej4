package org.liceolapaz.des.CJTB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Dialogo extends JDialog {
	private int filaValor = 0;
	private int columnaValor = 0;
	private JTextField filas;
	private JTextField columnas;
	private Ventana ventana;
	private String dificultades;

	public Dialogo(Ventana ventana) {
		this.ventana = ventana;
		setTitle("Nivel de dificultad");
		setSize(450, 300);

		URL url = getClass().getResource("/icono1.Jpg");
		setIconImage(new ImageIcon(url).getImage());
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
		////////////////////////////////////////////
		JLabel lbFilas = new JLabel("Filas");
		lbFilas.setBounds(30, 70, 50, 50);
		add(lbFilas);
		filas = new JTextField();
		filas.setBounds(250, 80, 150, 30);
		add(filas);
		///////////////////////////////////////////
		JLabel lbColumnas = new JLabel("Columnas");
		lbColumnas.setBounds(30, 130, 80, 50);
		add(lbColumnas);
		columnas = new JTextField();
		columnas.setBounds(250, 140, 150, 30);
		add(columnas);
		////////////////////////////////////////////
		JRadioButton facil = new JRadioButton("Facil");
		facil.setSelected(true);
		if (facil.isSelected()) {
			filaValor = 3;
			filas.setText(Integer.toString(filaValor));
			filas.setEditable(false);
			columnaValor = 4;
			columnas.setText(Integer.toString(columnaValor));
			columnas.setEditable(false);
			dificultades = "facil";
		}
		facil.setBounds(10, 20, 80, 40);
		facil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filaValor = 3;
				filas.setText(Integer.toString(filaValor));
				filas.setEditable(false);
				columnaValor = 4;
				columnas.setText(Integer.toString(columnaValor));
				columnas.setEditable(false);
				dificultades = "facil";

			}
		});
		add(facil);
		////////////////////////////////////////////
		JRadioButton medio = new JRadioButton("Medio");
		medio.setBounds(100, 20, 80, 40);
		medio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filaValor = 4;
				filas.setText(Integer.toString(filaValor));
				filas.setEditable(false);
				columnaValor = 5;
				columnas.setText(Integer.toString(columnaValor));
				columnas.setEditable(false);
				dificultades = "media";

			}
		});
		add(medio);
		////////////////////////////////////////////
		JRadioButton dificil = new JRadioButton("Dificil");
		dificil.setBounds(200, 20, 80, 40);
		dificil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filaValor = 6;
				filas.setText(Integer.toString(filaValor));
				filas.setEditable(false);
				columnaValor = 6;
				columnas.setText(Integer.toString(columnaValor));
				columnas.setEditable(false);
				dificultades = "dificil";

			}
		});
		add(dificil);
		////////////////////////////////////////////
		JRadioButton personalizado = new JRadioButton("Personalizado");
		personalizado.setBounds(300, 20, 110, 40);
		personalizado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filas.setEditable(true);
				columnas.setEditable(true);
				filas.setText("0");
				columnas.setText("0");
				columnaValor = 0;
				filaValor = 0;
				dificultades = "personalizado";

			}
		});
		add(personalizado);
		////////////////////////////////////////////
		ButtonGroup dificultad = new ButtonGroup();
		dificultad.add(facil);
		dificultad.add(medio);
		dificultad.add(dificil);
		dificultad.add(personalizado);
		///////////////////////////////////////////

		///////////////////////////////////////////
		JButton btAceptar = new JButton("Aceptar");
		btAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(columnaValor == 0 && filaValor == 0) {
					String filatx = "";
					filatx = filas.getText();
					String columnatx = "";
					columnatx = columnas.getText();
					columnaValor =Integer.parseInt(columnatx);
					filaValor = Integer.parseInt(filatx);
					if (columnaValor == 0 || filaValor == 0) {
						columnaValor = 4;
						filaValor = 3;
						dificultades = "facil";
					}
					ventana.setFila(filaValor);
					ventana.setColumna(columnaValor);
					ventana.nuevaPartida();
					ventana.setDificultad(dificultades);
					setVisible(false);
					
				}else {
				//System.out.println(filaValor);
				//System.out.println(columnaValor);
				ventana.setFila(filaValor);
				ventana.setColumna(columnaValor);
				ventana.nuevaPartida();
				ventana.setDificultad(dificultades);
				setVisible(false);
			}}
		});
		btAceptar.setBounds(100, 200, 80, 30);
		add(btAceptar);
		///////////////////////////////////////////
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btCancelar.setBounds(220, 200, 90, 30);
		add(btCancelar);
	}

	public int getFilaValor() {
		return filaValor;
	}

	public void setFilaValor(int filaValor) {
		this.filaValor = filaValor;
	}

	public int getColumnaValor() {
		return columnaValor;
	}

	public void setColumnaValor(int columnaValor) {
		this.columnaValor = columnaValor;
	}

}
