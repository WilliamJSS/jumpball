package williamjss.animations.platform;

import java.util.ArrayList;

import williamjss.components.Block;
import williamjss.components.Ball;
import williamjss.controller.GerenciadorCenario;

public class AnimationPlatformBottom extends Thread {

    private Ball bola;
    private ArrayList<Block> plataformaInferior;

    public AnimationPlatformBottom(Ball bola, GerenciadorCenario gc) {
        super("AnimationPlatformBottom");
        this.bola = bola;
        this.plataformaInferior = gc.getPlataformaInferior();
    }

    @Override
    public void run() {

        while (bola.isRodando()) {

            for (int i = 0; i < plataformaInferior.size(); i++) {
                Block blocoTemp = plataformaInferior.get(i);
                blocoTemp.setLocation(blocoTemp.getX() - Block.TICK, blocoTemp.getY());

                // Caso a plataforma va para fora do cenario, envia ela para o final do
                // arrayList
                if (blocoTemp.getX() + blocoTemp.getWidth() < 0) {
                    plataformaInferior.remove(i);

                    Block blocoAnterior = plataformaInferior.get(plataformaInferior.size() - 1);
                    blocoTemp.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoTemp.getY());

                    plataformaInferior.add(blocoTemp);
                }
            }

            // Delay para movimentar a plataforma
            try {
                sleep(Block.SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
