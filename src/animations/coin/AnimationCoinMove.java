package animations.coin;

import java.util.ArrayList;

import components.Block;
import components.Ball;
import components.Coin;
import controller.GerenciadorSom;
import view.Cenario;

public class AnimationCoinMove extends Thread {

    private Cenario cenario;
    private Ball bola;
    private ArrayList<Coin> moedas;
    private GerenciadorSom gs;

    public AnimationCoinMove(Cenario cenario, Ball bola, ArrayList<Coin> moedas, GerenciadorSom gs) {
        super("AnimationCoinMove");
        this.cenario = cenario;
        this.bola = bola;
        this.moedas = moedas;
        this.gs = gs;
    }

    @Override
    public void run() {

        while (bola.isRodando()) {
            for (int i = 0; i < moedas.size(); i++) {
                Coin moeda = moedas.get(i);

                // Verificar colisao com a bola
                if (bola.verificaColisao(moeda) && moeda.isVisible()) {
                    bola.setQntMoedas(bola.getQntMoedas() + 1);
                    moeda.setVisible(false);

                    gs.playEfeitoPegarMoeda();
                }

                moeda.setLocation(moeda.getX() - 5, moeda.getY());

                // Caso a moeda va para fora do cenario, ela eh apagada da existencia
                if (moeda.getX() + moeda.getWidth() < 0) {
                    moeda.setVisible(false);
                    cenario.repaint();
                }
            }

            // Delay para mover as moedas
            try {
                sleep(Block.SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
