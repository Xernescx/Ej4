package org.liceolapaz.des.CJTB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame {

	private Tablero tablero;
	JLabel intentosContador;
	JLabel parejasEncontradas;
	private Timer timer;
	private JPanel panel;
	private int segundos;
	private File archivo = null;
	private Dialogo dialogo;
	private int fila = 0;
	private int columna = 0;
	private String dificultad;

	public Ventana() {

		setTitle("Buscar Parejas - Carlos Jose Torres Baietti");
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		URL url = getClass().getResource("/icono1.Jpg");
		setIconImage(new ImageIcon(url).getImage());
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		pantallaPrincipal();

	}

	private void pantallaPrincipal() {
		JFrame ventanaFrame = new JFrame();
		Font fuente = new Font("Calibri", 3, 40);
		JLabel ibTitulo = new JLabel("Buscar Parejas");
		ibTitulo.setBounds(325, 5, 500, 60);
		ibTitulo.setFont(fuente);
		//////////////////////////////////////
		Font fuente2 = new Font("Calibri", 3, 20);
		JLabel ibtuto = new JLabel("Pulse la imagen para continuar");
		ibtuto.setBounds(325, 20, 500, 800);
		ibtuto.setFont(fuente2);
		//////////////////////////////////////
		Font fuente3 = new Font("Calibri", 3, 20);
		JLabel ibAutor = new JLabel("Autor: Carlos Jose Torres Baietti");
		ibAutor.setBounds(320, 20, 500, 900);
		ibAutor.setFont(fuente3);
		//////////////////////////////////////
		JPanel borde = new JPanel();
		borde.setBorder(new LineBorder(Color.BLACK, 10));
		borde.setBounds(0, 0, 886, 562);
		//////////////////////////////////////
		JButton botonP = new JButton();
		botonP.setIcon(new ImageIcon(getClass().getResource("/icono2.jpg")));
		botonP.setBounds(305, 100, 300, 300);
		botonP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(ibTitulo);
				remove(ibtuto);
				remove(borde);
				remove(ibAutor);
				remove(botonP);
				setResizable(true);
				fila = 3;
				columna = 4;
				dificultad = "facil";
				crearMenu();
				crearTablero();
				crearBarra();
				revalidate();
			}

		});
		add(ibTitulo);
		add(ibtuto);
		add(ibAutor);
		add(botonP);
		add(borde);
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		//////////////////////////////////////
		JMenu menuPartida = new JMenu("Partida");
		menuPartida.setMnemonic(KeyEvent.VK_P);
		//////////////////////////////////////
		JMenuItem nuevo = new JMenuItem("Nueva partida");
		nuevo.setIcon(new ImageIcon(getClass().getResource("/propiedades.png")));
		nuevo.setMnemonic(KeyEvent.VK_N);
		nuevo.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		nuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nuevaPartida();

			}

		});
		//////////////////////////////////////
		JMenuItem guarda = new JMenuItem("Guardar partida");
		guarda.setIcon(new ImageIcon(getClass().getResource("/Guardar.png")));
		guarda.setMnemonic(KeyEvent.VK_G);
		guarda.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		guarda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser guardar = new JFileChooser("");
				guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				guardar.showSaveDialog(null);
				if (guardar.getSelectedFile() != null) {
					archivo = guardar.getSelectedFile();
					guardarBarra();
					guardarBotones();

				}
			}

			private void guardarBarra() {
				int filas = tablero.getFilas();
				int columnas = tablero.getColumnas();
				int pareja = tablero.getParejas();
				int intentos = tablero.getIntentos();
				int tiempo = segundos;
				String d = dificultad;
				String linea = "";
				linea = filas + ";" + columnas + ";" + intentos + ";" + pareja + ";" + tiempo+ ";" + d;
				// System.out.println(linea);
				guardarFichero(linea, archivo, false);

			}

			private void guardarBotones() {
				int filas = tablero.getFilas();
				int columnas = tablero.getColumnas();
				Boton[][] botones = tablero.getBotones();
				String linea = "";
				for (int fila = 0; fila < filas; fila++) {
					for (int columna = 0; columna < columnas; columna++) {
						linea = botones[fila][columna].getFila() + ";" + botones[fila][columna].getColumna() + ";"
								+ botones[fila][columna].getValor() + ";" + botones[fila][columna].isPulsado();
						// System.out.println(linea);
						guardarFichero(linea, archivo, true);
					}
				}

			}

			public void guardarFichero(String cadena, File archivo, boolean append) {
				try {
					FileWriter fw = new FileWriter(archivo, append);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					pw.println(cadena);
					pw.close();
					
					pw.flush(); // cuando no lee el archivo por ser muy grande se usa esto....
					

				} catch (java.io.IOException ioex) {
				}
			}

		});
		//////////////////////////////////////
		JMenuItem abrir = new JMenuItem("Cargar partida");
		abrir.setIcon(new ImageIcon(getClass().getResource("/Abrir.png")));
		abrir.setMnemonic(KeyEvent.VK_C);
		abrir.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		abrir.addActionListener(new ActionListener() {
			String filas;
			String columnas;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				AbriCosas();
				crearBarra();
				// revalidate();

				repaint();

			}

			private void AbriCosas() {
				JFileChooser abrir = new JFileChooser("");
				abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
				abrir.setMultiSelectionEnabled(false);
				abrir.showOpenDialog(null);
				if (abrir.getSelectedFile() != null) {
					archivo = abrir.getSelectedFile();
					remove(tablero);
					remove(panel);
					timer.cancel();
				try {

						FileReader fr = new FileReader(archivo);
						BufferedReader br = new BufferedReader(fr);
						String s = br.readLine();

						String[] barra = s.split(";");
						filas = barra[0];
						columnas = barra[1];
						String intentos = barra[2];
						String parejas = barra[3];
						String tiempo = barra[4];
						String dificl = barra[5];
						// System.out.println(filas + " " + columnas + " " + intentos + " " + parejas +
						// " " + tiempo);
						int colms = Integer.parseInt(columnas);
						int fils = Integer.parseInt(filas);
						int inten = Integer.parseInt(intentos);
						int parejs = Integer.parseInt(parejas);
						tablero.setFilas(fils);
						tablero.setColumnas(colms);
						tablero.setIntentos(inten);
						tablero.setParejas(parejs);
						setDificultad(dificl);
						segundos = Integer.parseInt(tiempo);

						Boton[][] botones = new Boton[fils][colms];

						for (int fila = 0; fila < fils; fila++) {
							for (int columna = 0; columna < colms; columna++) {

								s = br.readLine();
								/////////////////////////
								String[] lineaBotones = s.split(";");
								String filas1 = lineaBotones[0];
								String columnas2 = lineaBotones[1];
								String valores = lineaBotones[2];
								String estados = lineaBotones[3];
								int fil = Integer.parseInt(filas1);
								int col = Integer.parseInt(columnas2);
								int val = Integer.parseInt(valores);
								boolean est = Boolean.parseBoolean(estados);
								botones[fil][col] = new Boton(tablero, fil, col, val, est);
								// System.out.println(filas1 + columnas2 + valores + estados);
							}
						}
						tablero = new Tablero(Ventana.this, fils, colms, botones, parejs, inten);

						Ventana.this.add(tablero);

						// System.out.println(filas1 + " " + columnas2 + " " + valores + " " + estados
						// );

						br.close();

					} catch (java.io.FileNotFoundException fnfex) {

					} catch (java.io.IOException ioex) {
					}
				}
			}
		});
		//////////////////////////////////////
		JMenuItem salir = new JMenuItem("Salir");
		salir.setIcon(new ImageIcon(getClass().getResource("/Nombre.png")));
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//////////////////////////////////////
		JMenu menuOpciones = new JMenu("Opciones");
		menuPartida.setMnemonic(KeyEvent.VK_O);
		//////////////////////////////////////
		JCheckBoxMenuItem resultados = new JCheckBoxMenuItem("Almacenar Resultados");
		resultados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablero.getResultados() == 0) {
					tablero.setResultados(1);
				} else {
					tablero.setResultados(0);
				}
				//System.out.println(tablero.getResultados());
			}
		});
		//////////////////////////////////////
		JMenuItem nivel = new JMenuItem("Nivel de dificultad");
		nivel.setMnemonic(KeyEvent.VK_D);
		nivel.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		nivel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogo = new Dialogo(Ventana.this);

			}
		});
		//////////////////////////////////////
		menuPartida.add(nuevo);
		menuPartida.add(guarda);
		menuPartida.add(abrir);
		menuPartida.add(salir);
		//////////////////////////////////////
		menuOpciones.add(resultados);
		menuOpciones.add(nivel);
		//////////////////////////////////////
		menuBar.add(menuPartida);
		menuBar.add(menuOpciones);
		setJMenuBar(menuBar);
		menuBar.setVisible(true);
	}

	protected void guardarResultados() {
			String nombre = JOptionPane.showInputDialog("Introduzca nombre de usuario");
			String linea = "";
			Calendar fecha = new GregorianCalendar();
			int año = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			int hora = fecha.get(Calendar.HOUR);
			int minutos = fecha.get(Calendar.MINUTE);
			linea = "Usuario: " + nombre + " N.filas: " + fila + " N.columnas: " + columna + " Dificultad " + dificultad
					+ " Tiempo: " + segundos + " segundos" + " Fecha: " + año + "/" + mes + "/" + dia + " Hora: " + hora
					+ ":" + minutos +"Intentos: " + tablero.getIntentos();
			
			guardarFicheroResultados(linea, true);

		
	}

	private void guardarFicheroResultados(String cadena, boolean append) {
		try {
			FileWriter fw = new FileWriter("resultados.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(cadena);
			pw.close();

		} catch (java.io.IOException ioex) {

		}

	}

	protected void pararCronometro() {
		timer.cancel();

	}

	protected void nuevaPartida() {
		remove(tablero);
		remove(panel);
		tablero.setIntentos(0);
		tablero.setParejas((tablero.getColumnas() * tablero.getFilas()) / 2);
		pararCronometro();
		crearBarra();
		crearTablero();
		revalidate();

	}

	private void crearTablero() {
		if (this.tablero != null) {
			remove(this.tablero);
		}
		this.tablero = new Tablero(this, fila, columna);
		this.tablero.setBorder(new LineBorder(Color.RED, 5));

		add(this.tablero, BorderLayout.CENTER);

	}

	private void crearBarra() {
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//////////////////////////////////////
		Font fuente = new Font("Calibri", 1, 20);
		//////////////////////////////////////
		JLabel intentos = new JLabel("Intentos", SwingConstants.CENTER);
		intentos.setBorder(new LineBorder(Color.BLACK, 5));
		intentos.setPreferredSize(new Dimension(120, 50));

		intentos.setFont(fuente);
		//////////////////////////////////////
		intentosContador = new JLabel("" + tablero.getIntentos(), SwingConstants.CENTER);
		intentosContador.setBorder(new LineBorder(Color.BLUE, 5));
		intentosContador.setPreferredSize(new Dimension(120, 50));
		intentosContador.setFont(fuente);
		///////////////////////////////////
		JLabel parejas = new JLabel("     Parejas");
		parejas.setBorder(new LineBorder(Color.BLACK, 5));
		parejas.setPreferredSize(new Dimension(120, 50));
		parejas.setFont(fuente);
		///////////////////////////////////
		parejasEncontradas = new JLabel("" + tablero.getParejas(), SwingConstants.CENTER);
		parejasEncontradas.setBorder(new LineBorder(Color.BLUE, 5));
		parejasEncontradas.setPreferredSize(new Dimension(120, 50));
		parejasEncontradas.setFont(fuente);
		///////////////////////////////////
		JLabel tiempo = new JLabel();
		tiempo.setIcon(new ImageIcon(getClass().getResource("/tiempo.png")));
		///////////////////////////////////
		JLabel segundo = new JLabel();
		segundo.setBorder(new LineBorder(Color.ORANGE, 5));
		segundo.setPreferredSize(new Dimension(120, 50));
		TimerTask tiempo1 = new TimerTask() {

			@Override
			public void run() {
				segundos++;
				if (segundos < 10) {
					segundo.setText("         00" + Integer.toString(segundos));
				} else if (segundos < 100) {
					segundo.setText("         0" + Integer.toString(segundos));
				} else {
					segundo.setText("         " + Integer.toString(segundos));
				}
			}

		};
		timer = new Timer();
		timer.schedule(tiempo1, 0, 1000);
		segundo.setFont(fuente);
		panel.add(intentos);
		panel.add(intentosContador);
		panel.add(parejas);
		panel.add(parejasEncontradas);
		panel.add(tiempo);
		panel.add(segundo);
		panel.setVisible(true);
		add(panel, BorderLayout.SOUTH);

	}

	public Timer getTimer() {
		return timer;
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

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

}
