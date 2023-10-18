package williamjss.model.audio;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Music {
    private static File musicaMenu;
	private static File musicaCenario;

	public static File getMusicaMenu() {
        if (musicaMenu == null) {
            URL resource = Music.class.getResource("/audio/On The Go.wav");
            try {
                musicaMenu = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
		return musicaMenu;
	}

	public static File getMusicaCenario() {
        if (musicaCenario == null) {
            URL resource = Music.class.getResource("/audio/Saiko Music Fundo.wav");
            try {
                musicaCenario = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
		return musicaCenario;
	}
}
