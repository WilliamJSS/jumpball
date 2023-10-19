package williamjss.model.image;

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

    private static ImageIcon getResource(String name) {
        return new ImageIcon("src/main/resources/img/botao/botao-" + name + ".png");
    }

    public static ImageIcon getImgBotao1() {
        if (imgBotao1 == null) {
            imgBotao1 = getResource("1");
        }
        return imgBotao1;
    }

    public static ImageIcon getImgBotao2() {
        if (imgBotao2 == null) {
            imgBotao2 = getResource("2");
        }
        return imgBotao2;
    }

    public static ImageIcon getImgBotao1Selecionado() {
        if (imgBotao1Selecionado == null) {
            imgBotao1Selecionado = getResource("1-selecionado");
        }
        return imgBotao1Selecionado;
    }

    public static ImageIcon getImgBotao2Selecionado() {
        if (imgBotao2Selecionado == null) {
            imgBotao2Selecionado = getResource("2-selecionado");
        }
        return imgBotao2Selecionado;
    }

    public static ImageIcon getImgBotaoVoltar() {
        if (imgBotaoVoltar == null) {
            imgBotaoVoltar = getResource("voltar");
        }
        return imgBotaoVoltar;
    }

    public static ImageIcon getImgBotaoRepetir() {
        if (imgBotaoRepetir == null) {
            imgBotaoRepetir = getResource("repetir");
        }
        return imgBotaoRepetir;
    }

    public static ImageIcon getImgBotaoRepetirSelecionado() {
        if (imgBotaoRepetirSelecionado == null) {
            imgBotaoRepetirSelecionado = getResource("repetir-selecionado");
        }
        return imgBotaoRepetirSelecionado;
    }

    public static ImageIcon getImgBotaoMenu() {
        if (imgBotaoMenu == null) {
            imgBotaoMenu = getResource("menu");
        }
        return imgBotaoMenu;
    }

    public static ImageIcon getImgBotaoMenuSelecionado() {
        if (imgBotaoMenuSelecionado == null) {
            imgBotaoMenuSelecionado = getResource("menu-selecionado");
        }
        return imgBotaoMenuSelecionado;
    }

    public static ImageIcon getImgBotaoFechar() {
        if (imgBotaoFechar == null) {
            imgBotaoFechar = getResource("fechar");
        }
        return imgBotaoFechar;
    }

    public static ImageIcon getImgBotaoAvancar() {
        if (imgBotaoAvancar == null) {
            imgBotaoAvancar = getResource("avancar");
        }
        return imgBotaoAvancar;
    }

    public static ImageIcon getImgBotaoAvancarSelecionado() {
        if (imgBotaoAvancarSelecionado == null) {
            imgBotaoAvancarSelecionado = getResource("avancar-selecionado");
        }
        return imgBotaoAvancarSelecionado;
    }

    public static ImageIcon getImgBotaoAvancarBloqueado() {
        if (imgBotaoAvancarBloqueado == null) {
            imgBotaoAvancarBloqueado = getResource("avancar-bloqueado");
        }
        return imgBotaoAvancarBloqueado;
    }
}
