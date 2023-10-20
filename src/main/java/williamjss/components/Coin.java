package williamjss.components;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import williamjss.model.image.ImageObject;

public class Coin extends JLabel {

	private static final long serialVersionUID = 1L;

	public static final long DELAY_GIRO = 85;

	public static final int QNT_TOTAL = 30;

	private ArrayList<ImageIcon> sprite;
	private ImageIcon img;
	private int largura, altura;

	public Coin() {
		sprite = ImageObject.getSpriteMoeda();

		img = sprite.get(0);
		largura = img.getIconWidth();
		altura = img.getIconHeight();

		setSize(largura, altura);
		setIcon(img);
	}

	public ArrayList<ImageIcon> getSprite() {
		return sprite;
	}

}
