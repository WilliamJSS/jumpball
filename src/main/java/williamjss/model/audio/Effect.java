package williamjss.model.audio;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Effect {

    private static File efeitoPulo;
    private static File efeitoPegarMoeda;

    public static File getEfeitoPulo() {
        if (efeitoPulo == null) {
            URL resource = Effect.class.getClassLoader().getResource("audio/pulo.wav");
            try {
                efeitoPulo = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        return efeitoPulo;
    }

    public static File getEfeitoPegarMoeda() {
        if (efeitoPegarMoeda == null) {
            URL resource = Effect.class.getClassLoader().getResource("audio/pegar-moeda.wav");
            try {
                efeitoPegarMoeda = new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        return efeitoPegarMoeda;
    }
}
