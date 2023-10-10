package animations.coin;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import objetosCenario.Moeda;
import visao.Cenario;

public class AnimationCoinSpin extends Thread {

    private Cenario cenario;
    private ArrayList<Moeda> moedas;
    private ArrayList<ImageIcon> sprite;

    public AnimationCoinSpin(Cenario cenario, ArrayList<Moeda> moedas) {
        super("AnimationCoinSpin");
        this.cenario = cenario;
        this.moedas = moedas;
        this.sprite = moedas.get(0).getSprite();
    }

    @Override
    public void run() {

        while (!cenario.isFimDeJogo()) {
            for (int i = 0; i < sprite.size(); i++) {
                try {
                    sleep(Moeda.DELAY_GIRO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < moedas.size(); j++) {
                    Moeda moeda = moedas.get(j);
                    moeda.setIcon(sprite.get(i));
                }
            }
        }
    }
}
