package williamjss.model.audio;

import java.io.File;

public class Ringtone {

    private static File toqueVitoria;
    private static File toqueDerrota;
    private static File navegarMenu;
    private static File selecionarBotao;

    private static File getFile(String name) {
        return new File("src/main/resources/audio/" + name);
    }

    public static File getToqueDerrota() {
        if (toqueDerrota == null) {
            toqueDerrota = getFile("toque-derrota.wav");
        }
        return toqueDerrota;
    }

    public static File getToqueVitoria() {
        if (toqueVitoria == null) {
            toqueVitoria = getFile("toque-vitoria.wav");
        }
        return toqueVitoria;
    }

    public static File getToqueNavegarMenu() {
        if (navegarMenu == null) {
            navegarMenu = getFile("navegar-menu.wav");
        }
        return navegarMenu;
    }

    public static File getToqueSelecionarBotao() {
        if (selecionarBotao == null) {
            selecionarBotao = getFile("selecionar-botao.wav");
        }
        return selecionarBotao;
    }
}
