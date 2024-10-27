package liz_parejas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Liz
 */
public class Ventanaprincipal {

    JFrame frame = new JFrame();                                                //frame (ventana)
    JPanel panel = new JPanel();                                                //panel (caja de la ventana)

    JLabel titulo = new JLabel();                                               //JLabel para el título del juego

    JButton btnjugar = new JButton();                                           //boton para empezar a jugar (abrir ventana emergente)
    JButton btnsalir = new JButton();                                           //botón para salir completamente del juego

    JLabel fondo = new JLabel();                                                //JLabel para implementar foto de fondo

    public Ventanaprincipal() {

        frame.setVisible(true);
        frame.setTitle("JUEGO DE CARTAS EMPAREJADAS");

        frame.setBounds(200, 200, 700, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        panel.setSize(frame.getWidth(), frame.getHeight());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.setLocation(600, 500);

        Panel();
        Textos();
        Buscar();
        Salir();
        FondoPantalla();

        frame.add(panel);
        panel.updateUI();
    }

    public void Panel() {
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        frame.add(panel);
    }

    public void Textos() {
        titulo.setText("Bienvenido al juego de cartas");
        titulo.setForeground(Color.ORANGE);
        titulo.setBackground(Color.BLACK);
        titulo.setOpaque(true);
        titulo.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
        titulo.setBounds(130, 50, 400, 50);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo);
    }

    public void Buscar() {
        btnjugar.setBounds(180, 220, 300, 80);
        btnjugar.setText("PULSA PARA INICIAR EL JUEGO");
        btnjugar.setForeground(Color.ORANGE);
        btnjugar.setBackground(Color.BLACK);
        btnjugar.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        panel.add(btnjugar);

        ActionListener inicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventanaemergente ventana = new Ventanaemergente();
            }
        };
        btnjugar.addActionListener(inicio);
        panel.add(btnjugar);
    }

    public void Salir() {
        btnsalir.setBounds(580, 390, 80, 50);
        btnsalir.setText("SALIR");
        btnsalir.setForeground(Color.ORANGE);
        btnsalir.setBackground(Color.BLACK);
        btnsalir.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        panel.add(btnsalir);

        ActionListener inicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);                                            //Para cerrar la ventana por completo
            }
        };
        btnsalir.addActionListener(inicio);
        panel.add(btnsalir);
    }

    public void FondoPantalla() {
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondo.setOpaque(true);
        ImageIcon imagen = new ImageIcon("Imagenes/fondojuego.jpg");
        fondo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_DEFAULT)));
        panel.add(fondo);
    }
}
