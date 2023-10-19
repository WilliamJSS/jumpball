package williamjss.model.audio;

import java.io.File;

public class Effect {

    private static File efeitoPulo;
    private static File efeitoPegarMoeda;

    private static File getFile(String name) {
        return new File("src/main/resources/audio/" + name);
    }

    public static File getEfeitoPulo() {
        if (efeitoPulo == null) {
            efeitoPulo = getFile("pulo.wav");
        }
        return efeitoPulo;
    }

    public static File getEfeitoPegarMoeda() {
        if (efeitoPegarMoeda == null) {
            efeitoPegarMoeda = getFile("pegar-moeda.wav");
        }
        return efeitoPegarMoeda;
    }
}
