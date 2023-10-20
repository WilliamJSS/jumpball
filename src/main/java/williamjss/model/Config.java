package williamjss.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Config {

    private static File scenesFile;
    private static File defaultScenesFile;
    private static File soundFile;
    private static File levelsFile;
    private static JsonArray scenes;
    private static JsonArray defaultScenes;
    private static JsonObject sound;
    private static JsonArray levels;

    private static File getFile(String name) {
        return new File("src/main/resources/config/" + name);
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

    public static File getSoundFile() {
        if (soundFile == null) {
            soundFile = getFile("sound.json");
        }
        return soundFile;
    }

    public static File getLevelsFile() {
        if (levelsFile == null) {
            levelsFile = getFile("levels.json");
        }
        return levelsFile;
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

    public static JsonObject getSound() {
        if (sound == null) {
            try {
                FileReader fr = new FileReader(getSoundFile());
                BufferedReader br = new BufferedReader(fr);

                sound = JsonParser.parseReader(br).getAsJsonObject();

                br.close();
                fr.close();

            } catch (Exception e) {
                return null;
            }
        }

        return sound;
    }

    public static JsonArray getLevels() {
        if (levels == null) {
            try {
                FileReader fr = new FileReader(getLevelsFile());
                BufferedReader br = new BufferedReader(fr);

                levels = JsonParser.parseReader(br).getAsJsonArray();

                br.close();
                fr.close();

            } catch (Exception e) {
                return null;
            }
        }

        return levels;
    }
}
