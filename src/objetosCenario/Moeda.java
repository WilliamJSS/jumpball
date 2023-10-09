package objetosCenario;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modelo.Imagens;

public class Moeda extends JLabel {

	private static final long serialVersionUID = 1L;

	public static final long DELAY_GIRO = 85;
	
	private ArrayList<ImageIcon> sprite;
	private ImageIcon img;
	private int largura, altura;
	
	public Moeda() {
		sprite = Imagens.getSpriteMoeda();
		
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
