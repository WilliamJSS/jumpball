package williamjss.animations.coin;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import williamjss.components.Coin;
import williamjss.view.Cenario;

public class AnimationCoinSpin extends Thread {

    private Cenario cenario;
    private ArrayList<Coin> moedas;
    private ArrayList<ImageIcon> sprite;

    public AnimationCoinSpin(Cenario cenario, ArrayList<Coin> moedas) {
        super("AnimationCoinSpin");
        this.cenario = cenario;
        this.moedas = moedas;
        this.sprite = moedas.get(0).getSprite();
    }

    @Override
    public void run() {

        while (!cenario.isFimDeJogo()) {
            for (ImageIcon spriteIcon : sprite) {
                try {
                    sleep(Coin.DELAY_GIRO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Coin moeda : moedas) {
                    moeda.setIcon(spriteIcon);
                }
            }
        }
    }
}
