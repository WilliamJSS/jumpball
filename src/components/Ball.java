package components;

import java.util.ArrayList;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Imagens;

public class Ball extends JLabel {

	private static final long serialVersionUID = 1L;

	// Constantes para definir o tipo de bola
	public static final int TIPO_PRAIA = 1;
	public static final int TIPO_FUTEBOL = 2;
	public static final int TIPO_PEDRA = 3;
	public static final int TIPO_ESPINHO = 4;
	public static final int TIPO_VOLEI = 5;
	public static final int TIPO_GOLFE = 6;
	public static final int TIPO_BASQUETE = 7;
	public static final int TIPO_VULCAO = 8;

	private int xInicial, yInicial, alturaPulo, tipo, qntMoedas;
	private boolean pulando, caindo, emCimaPlataforma, dead, caindoDeProposito, rodando;
	private ArrayList<ImageIcon> sprite;

	public Ball(){
		super();

		// Distancia 'y' maxima que a bola percorre ao pular
		alturaPulo = 125;

		// Status do movimento da bola
		pulando = false;
		caindo = false;
		emCimaPlataforma = false;
		dead = false;
		caindoDeProposito = false;
		rodando = false;

		// Outras coisinhas
		qntMoedas = 0;

	}

	public void setLocalizacaoInicial(int xInicial, int yInicial) {
		this.xInicial = xInicial;
		this.yInicial = yInicial;
	}

	public Point getLocalizacaoInicial() {
		return new Point(xInicial, yInicial);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;

		switch (tipo) {

		case Ball.TIPO_FUTEBOL:
			setSprite(Imagens.getSpriteBolaFutebol());
			setIcon(getSprite().get(0));
			break;

		case Ball.TIPO_PRAIA:
			setSprite(Imagens.getSpriteBolaPraia());
			setIcon(getSprite().get(0));
			break;

		case Ball.TIPO_PEDRA:
			setSprite(Imagens.getSpriteBolaPedra());
			setIcon(getSprite().get(0));
			break;

		case Ball.TIPO_ESPINHO:
			setSprite(Imagens.getSpriteBolaEspinho());
			setIcon(getSprite().get(0));
			break;

		case Ball.TIPO_VOLEI:
			setSprite(Imagens.getSpriteBolaVolei());
			setIcon(getSprite().get(0));
			break;

		case Ball.TIPO_GOLFE:
			setSprite(Imagens.getSpriteBolaGolfe());
			setIcon(getSprite().get(0));
			break;
			
		case Ball.TIPO_BASQUETE:
			setSprite(Imagens.getSpriteBolaBasquete());
			setIcon(getSprite().get(0));
			break;
			
		case Ball.TIPO_VULCAO:
			setSprite(Imagens.getSpriteBolaVulcao());
			setIcon(getSprite().get(0));
			break;

		default:
			System.out.println("Erro ao definir o tipo da bola: o tipo '" + tipo + "' nao existe");
		}

		setSize(getIcon().getIconWidth(), getIcon().getIconHeight());

		// Posicao inicial da bola
		xInicial = 30 * 4;
		yInicial = Block.NIVEL_MEDIO_BAIXO - getHeight();

		setLocalizacaoInicial(xInicial, yInicial);
		setLocation(xInicial, yInicial);
	}

	public double distancia2Pontos(Point a, Point b) {
		return Math.sqrt((b.x - a.x)*(b.x - a.x) + (b.y - a.y)*(b.y - a.y));
	}

	public boolean verificaColisao(Coin moeda) {

		// Pega o ponto central e raio da bola
		int xCentroBola = getX() + getWidth()/2;
		int yCentroBola = getY() + getHeight()/2;
		int raioBola = xCentroBola - getX();

		// Pega o ponto central e raio da moeda
		int xCentroMoeda = moeda.getX() + moeda.getWidth()/2;
		int yCentroMoeda = moeda.getY() + moeda.getHeight()/2;
		int raioMoeda = xCentroMoeda - moeda.getX();

		// Verifica se a distancia do centro da bola at� o centro da moeda � menor que a soma dos seus raios
		double distancia = distancia2Pontos(new Point(xCentroBola, yCentroBola), new Point(xCentroMoeda, yCentroMoeda));

		if (distancia <= raioBola + raioMoeda) {
			return true;
		}

		return false;
	}

	// Verifica se a bola caiu em cima de alguma plataforma
	public boolean verificaColisao(Block bloco) {

		// Pega o ponto central e raio da bola
		int xCentro = getX() + getWidth()/2;
		int yCentro = getY() + getHeight()/2;
		int raio = xCentro - getX();

		// Pega a posicao do bloco
		int xBloco = bloco.getX();
		int yBloco = bloco.getY();

		// Verifica se a bola esta acima da plataforma (dentro de um limite especificado)
		boolean faixaLargura = false;
		boolean faixaAltura = false;

		if (xCentro >= xBloco && xCentro <= xBloco + bloco.getWidth())
			faixaLargura = true;

		if (yCentro + raio >= yBloco - 2 && yCentro + raio < yBloco + 5)
			faixaAltura = true;

		// Calcula a distancia de cada pixel do bloco ate o centro da bola
		if (faixaLargura && faixaAltura) {
			for (int i = 0; i < bloco.getWidth(); i++) {
				double distancia = distancia2Pontos(new Point(xBloco + i, yBloco), new Point(xCentro, yCentro));
				if (distancia <= raio)
					return true;
			}
		}

		return false;	
	}

	public boolean isEmCimaPlataforma() {
		return emCimaPlataforma;
	}

	public void setEmCimaPlataforma(boolean emCimaPlataforma) {
		this.emCimaPlataforma = emCimaPlataforma;
	}

	public boolean isCaindo() {
		return caindo;
	}

	public void setCaindo(boolean caindo) {
		this.caindo = caindo;
	}

	public boolean isPulando() {
		return pulando;
	}

	public void setPulando(boolean pulando) {
		this.pulando = pulando;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getAlturaPulo() {
		return alturaPulo;
	}

	public ArrayList<ImageIcon> getSprite() {
		return sprite;
	}

	public void setSprite(ArrayList<ImageIcon> sprite) {
		this.sprite = sprite;
	}

	public boolean isCaindoDeProposito() {
		return caindoDeProposito;
	}

	public void setCaindoDeProposito(boolean caindoDeProposito) {
		this.caindoDeProposito = caindoDeProposito;
	}

	public int getQntMoedas() {
		return qntMoedas;
	}

	public void setQntMoedas(int qntMoedas) {
		this.qntMoedas = qntMoedas;
	}

	public boolean isRodando() {
		return rodando;
	}

	public void setRodando(boolean rodando) {
		this.rodando = rodando;
	}

}
