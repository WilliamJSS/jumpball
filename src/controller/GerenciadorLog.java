package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.Config;
import view.Fases;
import view.MiniCenario;

public class GerenciadorLog {

    private ArrayList<MiniCenario> miniCenarios;
    private JsonArray scenes;
    private File scenesFile;
    private int totalEstrelas;

    public GerenciadorLog(Fases fases) {
        this.miniCenarios = new ArrayList<MiniCenario>();
        this.miniCenarios.add(fases.getMiniCenarioCampo());
        this.miniCenarios.add(fases.getMiniCenarioMontanhas());
        this.miniCenarios.add(fases.getMiniCenarioNeve());
        this.miniCenarios.add(fases.getMiniCenarioPraia());
        this.miniCenarios.add(fases.getMiniCenarioVulcao());
        this.miniCenarios.add(fases.getMiniCenarioVolei());
        this.miniCenarios.add(fases.getMiniCenarioBasquete());
        this.miniCenarios.add(fases.getMiniCenarioGolfe());

        this.scenes = Config.getScenes();
        this.scenesFile = Config.getScenesFile();

        loadMiniCenariosConfig();
        updateMiniCenarios();
    }

    public void updateMiniCenarios() {

        for (MiniCenario miniCenario : miniCenarios) {
            miniCenario.setEstrelasRestantes(miniCenario.getEstrelasDesbloquear() - getTotalEstrelas());
            miniCenario.atualizarMiniCenario();
            if (miniCenario.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenario.isBloqueado()) {
                miniCenario.desbloquearCenario();
            }
        }
    }

    public void loadMiniCenariosConfig() {

        boolean bloqueado;
        int qntEstrelas, sceneId;

        setTotalEstrelas(0);

        for (JsonElement element : scenes) {

            if (element.isJsonObject()) {
                JsonObject scene = element.getAsJsonObject();

                bloqueado = scene.get("lock").getAsBoolean();
                qntEstrelas = scene.get("stars").getAsInt();
                sceneId = scene.get("id").getAsInt();

                miniCenarios.get(sceneId).setBloqueado(bloqueado);
                miniCenarios.get(sceneId).setQntEstrelas(qntEstrelas);
            }
        }
    }

    public void updateMiniCenariosConfig() {

        boolean bloqueado;
        int qntEstrelas, sceneId;

        try {

            FileWriter fw = new FileWriter(scenesFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (JsonElement element : scenes) {
                JsonObject scene = element.getAsJsonObject();

                sceneId = scene.get("id").getAsInt();
                bloqueado = miniCenarios.get(sceneId).isBloqueado();
                qntEstrelas = miniCenarios.get(sceneId).getQntEstrelas();

                scene.addProperty("lock", bloqueado);
                scene.addProperty("stars", qntEstrelas);

                scenes.set(sceneId, scene);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(scenes, bw);

            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(int cenario, int qntEstrelas) {

        boolean update = false;

        if (miniCenarios.get(cenario).getQntEstrelas() < qntEstrelas) {
            miniCenarios.get(cenario).setQntEstrelas(qntEstrelas);
            update = true;
        }

        if (update) {

            int totalEstrelas = miniCenarios.stream()
                    .mapToInt(miniCenario -> miniCenario.getQntEstrelas())
                    .reduce(Integer::sum)
                    .getAsInt();

            setTotalEstrelas(totalEstrelas);

            updateMiniCenarios();
            updateMiniCenariosConfig();
        }

    }

    public int getTotalEstrelas() {
        return totalEstrelas;
    }

    public void setTotalEstrelas(int totalEstrelas) {
        this.totalEstrelas = totalEstrelas;
    }

}
