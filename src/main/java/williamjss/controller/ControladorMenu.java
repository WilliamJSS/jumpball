package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.google.gson.JsonObject;

import williamjss.model.Config;
import williamjss.view.Ajuda;
import williamjss.view.Fases;
import williamjss.view.Frame;
import williamjss.view.Menu;
import williamjss.view.Options;
import williamjss.view.Sair;

public class ControladorMenu implements KeyListener {

    private Frame frame;
    private Menu menu;
    private Fases fases;
    private Sair sair;
    private Ajuda ajuda;
    private Options opcoes;
    private GerenciadorSom gs;
    private boolean selecting;
    private JsonObject optionsObject;

    public ControladorMenu(Frame frame, Menu menu, Ajuda ajuda, Options opcoes, Fases fases, Sair sair,
            GerenciadorSom gs) {
        this.frame = frame;
        this.menu = menu;
        this.fases = fases;
        this.sair = sair;
        this.ajuda = ajuda;
        this.opcoes = opcoes;
        this.gs = gs;
        this.selecting = false;
        this.optionsObject = Config.getOptions();

        loadOptionsConfig();
        gs.playMusicaMenu();
    }

    public void addEventos() {
        frame.addKeyListener(this);
    }

    public void removeEventos() {
        frame.removeKeyListener(this);
    }

    public void loadOptionsConfig() {
        JsonObject sound = optionsObject.get("sound").getAsJsonObject();
        gs.enableSounds(sound.get("music").getAsBoolean(), sound.get("effects").getAsBoolean());
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Mover entre os botoes
        if (e.getKeyCode() == KeyEvent.VK_UP && !isSelecting() && menu.getBotaoSelecionado() != Menu.BOTAO_JOGAR) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    menu.setBotaoSelecionado(menu.getBotaoSelecionado() - 1);

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
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !isSelecting() && menu.getBotaoSelecionado() != Menu.BOTAO_SAIR) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    menu.setBotaoSelecionado(menu.getBotaoSelecionado() + 1);

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

            switch (menu.getBotaoSelecionado()) {

                case Menu.BOTAO_JOGAR:
                    frame.setContentPane(fases);
                    break;

                case Menu.BOTAO_AJUDA:
                    frame.setContentPane(ajuda);
                    break;

                case Menu.BOTAO_OPCOES:
                    frame.setContentPane(opcoes);
                    break;

                case Menu.BOTAO_SAIR:
                    frame.setContentPane(sair);
                    break;
            }

            frame.repaint();
            frame.validate();
        }

        // Redireciona para a tela de sair ao pressionar ESC
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isSelecting()) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    frame.setContentPane(sair);
                    frame.repaint();
                    frame.validate();

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
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
