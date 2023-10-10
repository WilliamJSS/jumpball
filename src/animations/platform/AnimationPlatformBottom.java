package animations.platform;

import java.util.ArrayList;

import controle.GerenciadorCenario;
import objetosCenario.Bloco;
import objetosCenario.Bola;

public class AnimationPlatformBottom extends Thread {

    private Bola bola;
    private ArrayList<Bloco> plataformaInferior;

    public AnimationPlatformBottom(Bola bola, GerenciadorCenario gc) {
        super("AnimationPlatformBottom");
        this.bola = bola;
        this.plataformaInferior = gc.getPlataformaInferior();
    }

    @Override
    public void run() {

        while (bola.isRodando()) {

            for (int i = 0; i < plataformaInferior.size(); i++) {
                Bloco blocoTemp = plataformaInferior.get(i);
                blocoTemp.setLocation(blocoTemp.getX() - 5, blocoTemp.getY());

                // Caso a plataforma va para fora do cenario, envia ela para o final do
                // arrayList
                if (blocoTemp.getX() + blocoTemp.getWidth() < 0) {
                    plataformaInferior.remove(i);

                    Bloco blocoAnterior = plataformaInferior.get(plataformaInferior.size() - 1);
                    blocoTemp.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoTemp.getY());

                    plataformaInferior.add(blocoTemp);
                }
            }

            // Delay para movimentar a plataforma
            try {
                sleep(Bloco.SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
