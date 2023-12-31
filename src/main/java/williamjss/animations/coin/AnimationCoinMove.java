package williamjss.animations.coin;

import java.util.ArrayList;

import williamjss.components.Block;
import williamjss.components.Ball;
import williamjss.components.Coin;
import williamjss.controller.GerenciadorCenario;
import williamjss.controller.GerenciadorSom;
import williamjss.view.Cenario;

public class AnimationCoinMove extends Thread {

    private Cenario cenario;
    private Ball bola;
    private ArrayList<Coin> moedas;
    private GerenciadorSom gs;
    private GerenciadorCenario gc;

    public AnimationCoinMove(Cenario cenario, Ball bola, ArrayList<Coin> moedas, GerenciadorSom gs, GerenciadorCenario gc) {
        super("AnimationCoinMove");
        this.cenario = cenario;
        this.bola = bola;
        this.moedas = moedas;
        this.gs = gs;
        this.gc = gc;
    }

    @Override
    public void run() {

        while (bola.isRodando()) {
            for (Coin moeda : moedas) {

                // Verificar colisao com a bola
                if (bola.verificaColisao(moeda) && moeda.isVisible()) {
                    bola.setQntMoedas(bola.getQntMoedas() + 1);
                    moeda.setVisible(false);

                    gs.playEfeitoPegarMoeda();
                }

                moeda.setLocation(moeda.getX() - Block.TICK, moeda.getY());

                // Caso a moeda va para fora do cenario, ela eh apagada da existencia
                if (moeda.getX() + moeda.getWidth() < 0) {
                    moeda.setVisible(false);
                    cenario.repaint();
                }
            }

            // Delay para mover as moedas
            try {
                sleep(gc.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
