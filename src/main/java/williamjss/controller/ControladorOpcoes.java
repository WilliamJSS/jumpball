package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import williamjss.model.Config;
import williamjss.view.Frame;
import williamjss.view.Menu;
import williamjss.view.Options;

public class ControladorOpcoes implements KeyListener {

    private Frame frame;
    private Options options;
    private Menu menu;
    private GerenciadorSom gs;
    private boolean selecting;
    private File soundFile;
    private File scenesFile;
    private JsonObject sound;
    private JsonArray defaultScenes;
    private ControladorMiniCenario gl;

    public ControladorOpcoes(Frame frame, Options options, Menu menu, GerenciadorSom gs, ControladorMiniCenario gl) {
        this.frame = frame;
        this.menu = menu;
        this.options = options;
        this.gs = gs;
        this.gl = gl;
        this.selecting = false;
        this.soundFile = Config.getSoundFile();
        this.scenesFile = Config.getScenesFile();
        this.sound = Config.getSound();
        this.defaultScenes = Config.getDefaultScenes();
    }

    public void addEventos() {
        frame.addKeyListener(this);
    }

    public void removeEventos() {
        frame.removeKeyListener(this);
    }

    public void updateSoundConfig() {
        try {

            FileWriter fw = new FileWriter(soundFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            sound.addProperty("music", options.isMusicEnabled());
            sound.addProperty("effects", options.isEffectsEnabled());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(sound, bw);

            gs.enableSounds(sound.get("music").getAsBoolean(), sound.get("effects").getAsBoolean());

            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetGameProgression() {
        try {

            FileWriter fw = new FileWriter(scenesFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(defaultScenes, bw);

            gl.loadMiniCenariosConfig(defaultScenes);
            gl.updateMiniCenarios();

            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Mover entre os botoes
        if (e.getKeyCode() == KeyEvent.VK_UP && !isSelecting()
                && options.getBotaoSelecionado() != Options.BOTAO_REINICIAR) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    options.setBotaoSelecionado(options.getBotaoSelecionado() - 1);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
        }

        // Mover entre os botoes
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !isSelecting()
                && options.getBotaoSelecionado() != Options.BOTAO_EFEITOS) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    options.setBotaoSelecionado(options.getBotaoSelecionado() + 1);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();

        }

        // Selecionar botao
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) && !isSelecting()) {

            gs.playToqueSelecionarBotao();

            switch (options.getBotaoSelecionado()) {

                case Options.BOTAO_REINICIAR:
                    resetGameProgression();
                    menu.setBotaoSelecionado(Menu.BOTAO_JOGAR);
                    frame.setContentPane(menu);
                    frame.repaint();
                    frame.validate();
                    break;

                case Options.BOTAO_MUSICA:
                    options.setMusicEnabled(!options.isMusicEnabled());
                    updateSoundConfig();
                    if (!options.isMusicEnabled()) {
                        gs.stopMusicaMenu();
                    } else {
                        gs.stopMusicaMenu();
                        gs.playMusicaMenu();
                    }
                    break;

                case Options.BOTAO_EFEITOS:
                    options.setEffectsEnabled(!options.isEffectsEnabled());
                    updateSoundConfig();
                    break;

            }
        }

        // Voltar para o menu
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isSelecting()) {

            frame.setContentPane(menu);
            frame.repaint();
            frame.validate();
        }
    }

    public boolean isSelecting() {
        return selecting;
    }

    public void setSelecting(boolean selecting) {
        this.selecting = selecting;
    }

    // Metodos nao utilizados
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
