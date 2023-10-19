package williamjss.model.audio;

import java.io.File;

public class Music {
    private static File musicaMenu;
    private static File musicaCenario;

    private static File getFile(String name) {
        return new File("src/main/resources/audio/" + name);
    }

    public static File getMusicaMenu() {
        if (musicaMenu == null) {
            musicaMenu = getFile("On The Go.wav");
        }
        return musicaMenu;
    }

    public static File getMusicaCenario() {
        if (musicaCenario == null) {
            musicaCenario = getFile("Saiko Music Fundo.wav");
        }
        return musicaCenario;
    }
}
