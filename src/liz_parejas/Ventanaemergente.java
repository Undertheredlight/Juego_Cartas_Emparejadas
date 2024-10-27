package liz_parejas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ventanaemergente {

    JFrame frame = new JFrame();
    JPanel panelventanaemergente = new JPanel();
    JLabel fondo = new JLabel();

    //Creamos cada carta (6)
    JLabel carta1 = new JLabel();
    JLabel carta2 = new JLabel();
    JLabel carta3 = new JLabel();
    JLabel carta4 = new JLabel();
    JLabel carta5 = new JLabel();
    JLabel carta6 = new JLabel();

    //Controlador para saber 
    Carta primeraCartaVuelta = null;
    Carta segundaCartaVuelta = null;
    JLabel primeraCartaLabel = null;
    JLabel segundaCartaLabel = null;
    boolean bloquearCartas = false;

    int contador = 0;

    ArrayList<Carta> baraja = new ArrayList<>();

    public Ventanaemergente() {
        frame.setVisible(true);
        frame.setTitle("A JUGAR!!");
        frame.setBounds(300, 60, 870, 650);
        frame.setResizable(false);
        panelventanaemergente.setSize(frame.getWidth(), frame.getHeight());
        panelventanaemergente.setBackground(Color.white);
        panelventanaemergente.setLayout(null);

        Panelemergente();
        CrearBaraja();
        FondoPantalla();

        frame.add(panelventanaemergente);
        panelventanaemergente.updateUI();
    }

    public void Panelemergente() {
        panelventanaemergente.setLayout(null);
        frame.add(panelventanaemergente);
    }

    public void FondoPantalla() {
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondo.setOpaque(true);
        ImageIcon imagen = new ImageIcon("Imagenes/fondojuego.jpg");
        fondo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_DEFAULT)));
        panelventanaemergente.add(fondo);
    }

    public void CrearBaraja() {
        //Limpiamos y volvemos a crear la baraja con pares de cartas
        baraja.clear();
        for (int i = 0; i < 6; i++) {
            if (i < 2) {
                baraja.add(new Carta(i, "Imagenes/reverso.png", "Imagenes/aa.png"));
            } else if (i < 4) {
                baraja.add(new Carta(i, "Imagenes/reverso.png", "Imagenes/ba.png"));
            } else {
                baraja.add(new Carta(i, "Imagenes/reverso.png", "Imagenes/ca.png"));
            }
        }

        //Generamos y colocamos posiciones para las cartas en el panel
        ArrayList<Point> posiciones = new ArrayList<>();
        posiciones.add(new Point(50, 70));
        posiciones.add(new Point(50, 330));
        posiciones.add(new Point(330, 70));
        posiciones.add(new Point(330, 330));
        posiciones.add(new Point(610, 70));
        posiciones.add(new Point(610, 330));
        Collections.shuffle(posiciones); //Mezclamos las posiciones

        ArrayList<Integer> posCartas = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            posCartas.add(i);
        }
        Collections.shuffle(posCartas); //Mezclamos el orden de las cartas

        ImageIcon imgReverso = new ImageIcon(baraja.get(0).reverso);

        //Asignamos cartas y posiciones aleatorias al panel
        configurarCarta(carta1, posiciones.get(0).x, posiciones.get(0).y, posCartas.get(0), imgReverso);
        configurarCarta(carta2, posiciones.get(1).x, posiciones.get(1).y, posCartas.get(1), imgReverso);
        configurarCarta(carta3, posiciones.get(2).x, posiciones.get(2).y, posCartas.get(2), imgReverso);
        configurarCarta(carta4, posiciones.get(3).x, posiciones.get(3).y, posCartas.get(3), imgReverso);
        configurarCarta(carta5, posiciones.get(4).x, posiciones.get(4).y, posCartas.get(4), imgReverso);
        configurarCarta(carta6, posiciones.get(5).x, posiciones.get(5).y, posCartas.get(5), imgReverso);

        //Añadimos cartas al panel
        panelventanaemergente.add(carta1);
        panelventanaemergente.add(carta2);
        panelventanaemergente.add(carta3);
        panelventanaemergente.add(carta4);
        panelventanaemergente.add(carta5);
        panelventanaemergente.add(carta6);
    }

    public void configurarCarta(JLabel carta, int x, int y, int num, ImageIcon imgReverso) {
        carta.setBounds(x, y, 220, 220);
        carta.setIcon(new ImageIcon(imgReverso.getImage().getScaledInstance(carta.getWidth(), carta.getHeight(), Image.SCALE_DEFAULT)));
        carta.setOpaque(false);

        carta.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (bloquearCartas || carta == primeraCartaLabel) {
                    return;
                }

                //Voltear la carta seleccionada
                ImageIcon imagenCara = new ImageIcon(baraja.get(num).cara);
                carta.setIcon(new ImageIcon(imagenCara.getImage().getScaledInstance(carta.getWidth(), carta.getHeight(), Image.SCALE_DEFAULT)));

                if (primeraCartaVuelta == null) {
                    //Primera carta seleccionada
                    primeraCartaVuelta = baraja.get(num);
                    primeraCartaLabel = carta;
                } else {
                    //Segunda carta seleccionada
                    segundaCartaVuelta = baraja.get(num);
                    segundaCartaLabel = carta;
                    bloquearCartas = true;
                    compararCartas();
                }
            }
        });
    }

    public void compararCartas() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);  //Tiempo de 1 segundo para mostrar ambas cartas

                if (primeraCartaVuelta.cara.equals(segundaCartaVuelta.cara)) {
                    contador++;
                    if (contador == 3) {
                        mostrarMensajeVictoria();
                    }
                    primeraCartaVuelta = null;
                    segundaCartaVuelta = null;
                } else {
                    //Ambas cartas a reverso si no coinciden
                    primeraCartaLabel.setIcon(new ImageIcon(new ImageIcon(primeraCartaVuelta.reverso).getImage().getScaledInstance(primeraCartaLabel.getWidth(), primeraCartaLabel.getHeight(), Image.SCALE_DEFAULT)));
                    segundaCartaLabel.setIcon(new ImageIcon(new ImageIcon(segundaCartaVuelta.reverso).getImage().getScaledInstance(segundaCartaLabel.getWidth(), segundaCartaLabel.getHeight(), Image.SCALE_DEFAULT)));

                    primeraCartaVuelta = null;
                    segundaCartaVuelta = null;
                }
                bloquearCartas = false;  //Desbloquear para próxima jugada
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void mostrarMensajeVictoria() {
        int respuesta = JOptionPane.showOptionDialog(
                frame,
                "¡Felicidades! Has ganado el juego. ¿Qué deseas hacer?",
                "Juego completado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Reiniciar", "Salir"},
                "Reiniciar"
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            reiniciarJuego();
        } else {
            frame.dispose(); //Salimos
        }
    }

    private void reiniciarJuego() {
        //Reseteamos todas las variables de control
        contador = 0;
        primeraCartaVuelta = null;
        segundaCartaVuelta = null;
        primeraCartaLabel = null;
        segundaCartaLabel = null;
        bloquearCartas = false;

        //Limpiamos el panel, reiniciar la baraja y actualizar el panel
        panelventanaemergente.removeAll();
        CrearBaraja();
        FondoPantalla();
        panelventanaemergente.revalidate();
        panelventanaemergente.repaint();
    }
}
