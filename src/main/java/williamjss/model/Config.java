package williamjss.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Config {

    private static File scenesFile;
    private static File defaultScenesFile;
    private static JsonArray scenes;
    private static JsonArray defaultScenes;

    private static File getFile(String name) {
        URL resource = Config.class.getClassLoader().getResource("config/" + name);
        try {
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getScenesFile() {
        if (scenesFile == null) {
            scenesFile = getFile("scenes.json");
        }
        return scenesFile;
    }

    public static File getDefaultScenesFile() {
        if (defaultScenesFile == null) {
            defaultScenesFile = getFile("scenes.default.json");
        }
        return defaultScenesFile;
    }

    public static JsonArray getScenes() {
        if (scenes == null) {
            try {
                FileReader fr = new FileReader(getScenesFile());
                BufferedReader br = new BufferedReader(fr);

                scenes = JsonParser.parseReader(br).getAsJsonArray();

                br.close();
                fr.close();

            } catch (Exception e) {
                return null;
            }
        }

        return scenes;
    }

    public static JsonArray getDefaultScenes() {
        if (defaultScenes == null) {
            try {
                FileReader fr = new FileReader(getDefaultScenesFile());
                BufferedReader br = new BufferedReader(fr);

                defaultScenes = JsonParser.parseReader(br).getAsJsonArray();

                br.close();
                fr.close();

            } catch (Exception e) {
                return null;
            }
        }

        return defaultScenes;
    }
}
