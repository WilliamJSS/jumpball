package williamjss.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Config {

    private static File defaultScenesFile = new File("src/resources/config/scenes.default.json");
    private static JsonArray defaultScenes;

    private static File scenesFile = new File("src/resources/config/scenes.json");
    private static JsonArray scenes;

    public static File getScenesFile() {
        return scenesFile;
    }

    public static File getDefaultScenesFile() {
        return defaultScenesFile;
    }

    public static JsonArray getDefaultScenes() {
        if (defaultScenes == null) {
            try {
                FileReader fr = new FileReader(defaultScenesFile);
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

    public static JsonArray getScenes() {
        if (scenes == null) {
            try {
                FileReader fr = new FileReader(scenesFile);
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

}
