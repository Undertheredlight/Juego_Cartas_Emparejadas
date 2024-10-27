package liz_parejas;

/**
 *
 * @author Liz
 */
public class Carta {

    int id;         //id de la imagen
    String reverso; //ruta para encontrar la imagen
    String cara;    //ruta para encontrar la imagen

    public Carta() {
    }

    public Carta(int id, String reverso, String cara) {
        this.id = id;
        this.reverso = reverso;
        this.cara = cara;
    }
}
