package williamjss.model.image;

import javax.swing.ImageIcon;

public class ImageBlock {

    private static ImageIcon blocoAgua;
    private static ImageIcon blocoAreia;
    private static ImageIcon blocoBasquete;
    private static ImageIcon blocoEspinho;
    private static ImageIcon blocoGelo;
    private static ImageIcon blocoGrama;
    private static ImageIcon blocoGrama1;
    private static ImageIcon blocoGrama2;
    private static ImageIcon blocoLava;
    private static ImageIcon blocoNeve;
    private static ImageIcon blocoPedra;

    private static ImageIcon getResource(String name) {
        return new ImageIcon("src/main/resources/img/bloco/bloco-" + name + ".png");
    }

    public static ImageIcon getBlocoAgua() {
        if (blocoAgua == null) {
            blocoAgua = getResource("agua");
        }
        return blocoAgua;
    }

    public static ImageIcon getBlocoAreia() {
        if (blocoAreia == null) {
            blocoAreia = getResource("areia");
        }
        return blocoAreia;
    }

    public static ImageIcon getBlocoBasquete() {
        if (blocoBasquete == null) {
            blocoBasquete = getResource("basquete");
        }
        return blocoBasquete;
    }

    public static ImageIcon getBlocoEspinho() {
        if (blocoEspinho == null) {
            blocoEspinho = getResource("espinho");
        }
        return blocoEspinho;
    }

    public static ImageIcon getBlocoGelo() {
        if (blocoGelo == null) {
            blocoGelo = getResource("gelo");
        }
        return blocoGelo;
    }

    public static ImageIcon getBlocoGrama() {
        if (blocoGrama == null) {
            blocoGrama = getResource("grama");
        }
        return blocoGrama;
    }

    public static ImageIcon getBlocoGrama1() {
        if (blocoGrama1 == null) {
            blocoGrama1 = getResource("grama1");
        }
        return blocoGrama1;
    }

    public static ImageIcon getBlocoGrama2() {
        if (blocoGrama2 == null) {
            blocoGrama2 = getResource("grama2");
        }
        return blocoGrama2;
    }

    public static ImageIcon getBlocoLava() {
        if (blocoLava == null) {
            blocoLava = getResource("lava");
        }
        return blocoLava;
    }

    public static ImageIcon getBlocoNeve() {
        if (blocoNeve == null) {
            blocoNeve = getResource("neve");
        }
        return blocoNeve;
    }

    public static ImageIcon getBlocoPedra() {
        if (blocoPedra == null) {
            blocoPedra = getResource("pedra");
        }
        return blocoPedra;
    }

}
