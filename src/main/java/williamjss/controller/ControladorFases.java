package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import williamjss.view.Cenario;
import williamjss.view.Fases;
import williamjss.view.Frame;
import williamjss.view.Menu;

public class ControladorFases implements KeyListener {

    private Frame frame;
    private Fases fases;
    private Menu menu;
    private Cenario cenario;
    private ControladorCenario controladorCenario;
    private GerenciadorSom gs;
    private boolean selecting;

    public ControladorFases(Frame frame, Fases fases, ControladorCenario controladorCenario,
            Cenario cenario, Menu menu, GerenciadorSom gs) {

        this.frame = frame;
        this.fases = fases;
        this.menu = menu;
        this.cenario = cenario;
        this.controladorCenario = controladorCenario;
        this.gs = gs;
        this.selecting = false;
    }

    public void addEventos() {
        frame.addKeyListener(this);
    }

    public void removeEventos() {
        frame.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Mover entre os miniCenarios
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !isSelecting()) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    if (fases.getCenarioSelecionado() == Cenario.TIPO_CAMPO
                            || fases.getCenarioSelecionado() == Cenario.TIPO_PRAIA) {
                        fases.atualizarSelecao(fases.getCenarioSelecionado() + 3);
                    } else {
                        fases.atualizarSelecao(fases.getCenarioSelecionado() - 1);
                    }

                    gs.playToqueNavegarMenu();

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
        }

        // Mover entre os miniCenarios
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isSelecting()) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    if (fases.getCenarioSelecionado() == Cenario.TIPO_BASQUETE
                            || fases.getCenarioSelecionado() == Cenario.TIPO_GOLFE) {
                        fases.atualizarSelecao(fases.getCenarioSelecionado() - 3);
                    } else {
                        fases.atualizarSelecao(fases.getCenarioSelecionado() + 1);
                    }

                    gs.playToqueNavegarMenu();

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
        }

        // Mover entre os miniCenarios
        if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && !isSelecting()) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    if (fases.getCenarioSelecionado() >= Cenario.TIPO_CAMPO
                            && fases.getCenarioSelecionado() <= Cenario.TIPO_BASQUETE) {
                        fases.atualizarSelecao(fases.getCenarioSelecionado() + 4);
                    } else {
                        fases.atualizarSelecao(fases.getCenarioSelecionado() - 4);
                    }

                    gs.playToqueNavegarMenu();

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
        }

        // Voltar para o menu
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isSelecting()) {

            gs.playToqueNavegarMenu();

            frame.setContentPane(menu);
            frame.repaint();
            frame.validate();
        }

        // Selecionar um miniCenario se ele nao estiver bloqueado
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) &&
                !fases.getMiniCenario(fases.getCenarioSelecionado()).isBloqueado() && !isSelecting()) {

            gs.playToqueSelecionarBotao();
            gs.stopMusicaMenu();

            switch (fases.getCenarioSelecionado()) {

                case Cenario.TIPO_CAMPO:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_CAMPO, GerenciadorCenario.FASE_1);
                    break;

                case Cenario.TIPO_MONTANHAS:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_MONTANHAS, GerenciadorCenario.FASE_2);
                    break;

                case Cenario.TIPO_NEVE:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_NEVE, GerenciadorCenario.FASE_3);
                    break;

                case Cenario.TIPO_BASQUETE:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_BASQUETE, GerenciadorCenario.FASE_4);
                    break;

                case Cenario.TIPO_PRAIA:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_PRAIA, GerenciadorCenario.FASE_1);
                    break;

                case Cenario.TIPO_VULCAO:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_VULCAO, GerenciadorCenario.FASE_2);
                    break;

                case Cenario.TIPO_VOLEI:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_VOLEI, GerenciadorCenario.FASE_3);
                    break;

                case Cenario.TIPO_GOLFE:
                    controladorCenario.iniciarObjetosCenario(Cenario.TIPO_GOLFE, GerenciadorCenario.FASE_4);
                    break;
            }

            frame.setContentPane(cenario);
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
