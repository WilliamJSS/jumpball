package williamjss.model.image;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ImageObject {

    private static ImageIcon imgBandeira;
    private static ImageIcon imgCadeado;
    private static ArrayList<ImageIcon> contagemRegressiva;
    private static ImageIcon imgEstrela;
    private static ImageIcon imgEstrelaCinzaGrande;
    private static ImageIcon imgEstrelaCinza;
    private static ImageIcon imgEstrelaGrande;
    private static ImageIcon imgEstrelaVermelha;
    private static Image iconeJumpBall;
    private static ImageIcon logoJumpBall;
    private static ArrayList<ImageIcon> spriteMoeda;
    private static ImageIcon pinAzul;
    private static ImageIcon pinBranco;
    private static ImageIcon setasTeclado;

    private static ImageIcon getResource(String name) {
        return new ImageIcon("src/main/resources/img/icone/" + name + ".png");
    }

    public static ImageIcon getImgBandeira() {
        if (imgBandeira == null) {
            imgBandeira = getResource("bandeira");
        }
        return imgBandeira;
    }

    public static ImageIcon getImgCadeado() {
        if (imgCadeado == null) {
            imgCadeado = getResource("cadeado");
        }
        return imgCadeado;
    }

    public static ArrayList<ImageIcon> getContagemRegressiva() {
        if (contagemRegressiva == null) {
            contagemRegressiva = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 4; i++) {
                contagemRegressiva.add(getResource("contagem-" + i));
            }
        }
        return contagemRegressiva;
    }

    public static ImageIcon getImgEstrela() {
        if (imgEstrela == null) {
            imgEstrela = getResource("estrela");
        }
        return imgEstrela;
    }

    public static ImageIcon getImgEstrelaCinzaGrande() {
        if (imgEstrelaCinzaGrande == null) {
            imgEstrelaCinzaGrande = getResource("estrela-cinza-grande");
        }
        return imgEstrelaCinzaGrande;
    }

    public static ImageIcon getImgEstrelaCinza() {
        if (imgEstrelaCinza == null) {
            imgEstrelaCinza = getResource("estrela-cinza");
        }
        return imgEstrelaCinza;
    }

    public static ImageIcon getImgEstrelaGrande() {
        if (imgEstrelaGrande == null) {
            imgEstrelaGrande = getResource("estrela-grande");
        }
        return imgEstrelaGrande;
    }

    public static ImageIcon getImgEstrelaVermelha() {
        if (imgEstrelaVermelha == null) {
            imgEstrelaVermelha = getResource("estrela-vermelha");
        }
        return imgEstrelaVermelha;
    }

    public static Image getIconeJumpBall() {
        if (iconeJumpBall == null) {
            iconeJumpBall = getResource("icone-jump-ball").getImage();
        }
        return iconeJumpBall;
    }

    public static ImageIcon getLogoJumpBall() {
        if (logoJumpBall == null) {
            logoJumpBall = getResource("logo-jump-ball");
        }
        return logoJumpBall;
    }

    public static ArrayList<ImageIcon> getSpriteMoeda() {
        if (spriteMoeda == null) {
            spriteMoeda = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 6; i++) {
                spriteMoeda.add(getResource("moeda-" + i));
            }
        }
        return spriteMoeda;
    }

    public static ImageIcon getPinAzul() {
        if (pinAzul == null) {
            pinAzul = getResource("pin-azul");
        }
        return pinAzul;
    }

    public static ImageIcon getPinBranco() {
        if (pinBranco == null) {
            pinBranco = getResource("pin-branco");
        }
        return pinBranco;
    }

    public static ImageIcon getSetasTeclado() {
        if (setasTeclado == null) {
            setasTeclado = getResource("setas-cima-baixo");
        }
        return setasTeclado;
    }
}
