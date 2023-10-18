package williamjss.model.image;

import java.net.URL;

import javax.swing.ImageIcon;

public class ImageButton {

    private static ImageIcon imgBotao1;
    private static ImageIcon imgBotao2;
    private static ImageIcon imgBotao1Selecionado;
    private static ImageIcon imgBotao2Selecionado;
    private static ImageIcon imgBotaoVoltar;
    private static ImageIcon imgBotaoRepetir;
    private static ImageIcon imgBotaoRepetirSelecionado;
    private static ImageIcon imgBotaoMenu;
    private static ImageIcon imgBotaoMenuSelecionado;
    private static ImageIcon imgBotaoFechar;
    private static ImageIcon imgBotaoAvancar;
    private static ImageIcon imgBotaoAvancarSelecionado;
    private static ImageIcon imgBotaoAvancarBloqueado;

    private static URL getResource(String name) {
        return ImageButton.class.getClassLoader().getResource("img/botao/botao-" + name + ".png");
    }

    public static ImageIcon getImgBotao1() {
        if (imgBotao1 == null) {
            imgBotao1 = new ImageIcon(getResource("1.png"));
        }
        return imgBotao1;
    }

    public static ImageIcon getImgBotao2() {
        if (imgBotao2 == null) {
            imgBotao2 = new ImageIcon(getResource("2.png"));
        }
        return imgBotao2;
    }

    public static ImageIcon getImgBotao1Selecionado() {
        if (imgBotao1Selecionado == null) {
            imgBotao1Selecionado = new ImageIcon(getResource("1-selecionado.png"));
        }
        return imgBotao1Selecionado;
    }

    public static ImageIcon getImgBotao2Selecionado() {
        if (imgBotao2Selecionado == null) {
            imgBotao2Selecionado = new ImageIcon(getResource("2-selecionado.png"));
        }
        return imgBotao2Selecionado;
    }

    public static ImageIcon getImgBotaoVoltar() {
        if (imgBotaoVoltar == null) {
            imgBotaoVoltar = new ImageIcon(getResource("voltar.png"));
        }
        return imgBotaoVoltar;
    }

    public static ImageIcon getImgBotaoRepetir() {
        if (imgBotaoRepetir == null) {
            imgBotaoRepetir = new ImageIcon(getResource("repetir.png"));
        }
        return imgBotaoRepetir;
    }

    public static ImageIcon getImgBotaoRepetirSelecionado() {
        if (imgBotaoRepetirSelecionado == null) {
            imgBotaoRepetirSelecionado = new ImageIcon(getResource("repetir-selecionado.png"));
        }
        return imgBotaoRepetirSelecionado;
    }

    public static ImageIcon getImgBotaoMenu() {
        if (imgBotaoMenu == null) {
            imgBotaoMenu = new ImageIcon(getResource("menu.png"));
        }
        return imgBotaoMenu;
    }

    public static ImageIcon getImgBotaoMenuSelecionado() {
        if (imgBotaoMenuSelecionado == null) {
            imgBotaoMenuSelecionado = new ImageIcon(getResource("menu-selecionado.png"));
        }
        return imgBotaoMenuSelecionado;
    }

    public static ImageIcon getImgBotaoFechar() {
        if (imgBotaoFechar == null) {
            imgBotaoFechar = new ImageIcon(getResource("fechar.png"));
        }
        return imgBotaoFechar;
    }

    public static ImageIcon getImgBotaoAvancar() {
        if (imgBotaoAvancar == null) {
            imgBotaoAvancar = new ImageIcon(getResource("avancar.png"));
        }
        return imgBotaoAvancar;
    }

    public static ImageIcon getImgBotaoAvancarSelecionado() {
        if (imgBotaoAvancarSelecionado == null) {
            imgBotaoAvancarSelecionado = new ImageIcon(getResource("avancar-selecionado.png"));
        }
        return imgBotaoAvancarSelecionado;
    }

    public static ImageIcon getImgBotaoAvancarBloqueado() {
        if (imgBotaoAvancarBloqueado == null) {
            imgBotaoAvancarBloqueado = new ImageIcon(getResource("avancar-bloqueado.png"));
        }
        return imgBotaoAvancarBloqueado;
    }
}
