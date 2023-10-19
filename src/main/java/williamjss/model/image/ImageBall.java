package williamjss.model.image;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ImageBall {

    private static ImageIcon bolaPadrao;
    private static ArrayList<ImageIcon> spriteBolaPedra;
    private static ArrayList<ImageIcon> spriteBolaFutebol;
    private static ArrayList<ImageIcon> spriteBolaPraia;
    private static ArrayList<ImageIcon> spriteBolaEspinho;
    private static ArrayList<ImageIcon> spriteBolaVolei;
    private static ArrayList<ImageIcon> spriteBolaGolfe;
    private static ArrayList<ImageIcon> spriteBolaBasquete;
    private static ArrayList<ImageIcon> spriteBolaVulcao;

    private static ImageIcon getResource(String name) {
        return new ImageIcon("src/main/resources/img/bola/" + name + ".png");
    }

    public static ImageIcon getBolaPadrao() {
        if (bolaPadrao == null) {
            bolaPadrao = getResource("bola-padrao");
        }
        return bolaPadrao;
    }

    public static ArrayList<ImageIcon> getSpriteBolaVulcao() {
        if (spriteBolaVulcao == null) {
            spriteBolaVulcao = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaVulcao.add(getResource("vulcao/bola-vulcao-" + i));
            }
        }
        return spriteBolaVulcao;
    }

    public static ArrayList<ImageIcon> getSpriteBolaBasquete() {
        if (spriteBolaBasquete == null) {
            spriteBolaBasquete = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaBasquete.add(getResource("basquete/bola-basquete-" + i));
            }
        }
        return spriteBolaBasquete;
    }

    public static ArrayList<ImageIcon> getSpriteBolaPedra() {
        if (spriteBolaPedra == null) {
            spriteBolaPedra = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaPedra.add(getResource("pedra/bola-pedra-" + i));
            }
        }
        return spriteBolaPedra;
    }

    public static ArrayList<ImageIcon> getSpriteBolaFutebol() {
        if (spriteBolaFutebol == null) {
            spriteBolaFutebol = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaFutebol.add(getResource("futebol/bola-futebol-" + i));
            }
        }
        return spriteBolaFutebol;
    }

    public static ArrayList<ImageIcon> getSpriteBolaPraia() {
        if (spriteBolaPraia == null) {
            spriteBolaPraia = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaPraia.add(getResource("praia/bola-praia-" + i));
            }
        }
        return spriteBolaPraia;
    }

    public static ArrayList<ImageIcon> getSpriteBolaEspinho() {
        if (spriteBolaEspinho == null) {
            spriteBolaEspinho = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 16; i++) {
                spriteBolaEspinho.add(getResource("espinho/bola-espinho-" + i));
            }
        }
        return spriteBolaEspinho;
    }

    public static ArrayList<ImageIcon> getSpriteBolaVolei() {
        if (spriteBolaVolei == null) {
            spriteBolaVolei = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaVolei.add(getResource("volei/bola-volei-" + i));
            }
        }
        return spriteBolaVolei;
    }

    public static ArrayList<ImageIcon> getSpriteBolaGolfe() {
        if (spriteBolaGolfe == null) {
            spriteBolaGolfe = new ArrayList<ImageIcon>();
            for (int i = 1; i <= 8; i++) {
                spriteBolaGolfe.add(getResource("golfe/bolinha-de-golfe-" + i));
            }
        }
        return spriteBolaGolfe;
    }
}
