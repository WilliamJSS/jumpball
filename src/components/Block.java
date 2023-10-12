package components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block extends JLabel {

	private static final long serialVersionUID = 1L;

	private int tipo;

	// Constantes para determinar o tipo do bloco
	public static final int TIPO_AGUA = 1;
	public static final int TIPO_GELO = 2;
	public static final int TIPO_GRAMA = 3;
	public static final int TIPO_LAVA = 4;
	public static final int TIPO_PEDRA = 5;
	public static final int TIPO_AREIA = 6;
	public static final int TIPO_ESPINHO = 7;
	public static final int TIPO_NEVE = 8;
	public static final int TIPO_BASQUETE = 9;
	public static final int TIPO_GRAMADO = 10;

	public static final int TIPO_BANDEIRA = 15;

	// Constantes para determinar o nivel de altura do bloco
	public static final int NIVEL_TOP = 200;
	public static final int NIVEL_MID = 320;
	public static final int NIVEL_BOTTOM = 440;
	public static final int NIVEL_FLOOR = 571;

	// Constante da velocidade
	public static final int SPEED = 18; // Padrao eh 18, quanto menor, mais rapido

	public Block() {
		super();
		setBounds(0, 0, 30, 30);
	}

	public void atualizarBloco(int tipo, ImageIcon img) {
		setIcon(img);
		setTipo(tipo);

		setSize(img.getIconWidth(), img.getIconHeight());
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
