package williamjss.model.audio;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Ringtone {

    private static File toqueVitoria;
    private static File toqueDerrota;
    private static File navegarMenu;
    private static File selecionarBotao;

    public static File getToqueDerrota() {
        if (toqueDerrota == null) {
            URL resource = Ringtone.class.getResource("/audio/toque-derrota.wav");
            try {
                toqueDerrota = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        return toqueDerrota;
    }

    public static File getToqueVitoria() {
        if (toqueVitoria == null) {
            URL resource = Ringtone.class.getResource("/audio/toque-vitoria.wav");
            try {
                toqueVitoria = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        return toqueVitoria;
    }

    public static File getToqueNavegarMenu() {
        if (navegarMenu == null) {
            URL resource = Ringtone.class.getResource("/audio/navegar-menu.wav");
            try {
                navegarMenu = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        return navegarMenu;
    }

    public static File getToqueSelecionarBotao() {
        if (selecionarBotao == null) {
            URL resource = Ringtone.class.getResource("/audio/selecionar-botao.wav");
            try {
                selecionarBotao = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        return selecionarBotao;
    }
}
