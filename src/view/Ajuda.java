package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.Fontes;
import model.Imagens;

public class Ajuda extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int PIN_1 = 0;
	public static final int PIN_2 = 1;
	public static final int PIN_3 = 2;
	public static final int PIN_4 = 3;

	private JTextPane textPaneAjuda;
	private JLabel fundoCenario;
	private JLabel botaoVoltar;
	private ArrayList<JLabel> pins;
	private String[] textoAjuda;
	private JLabel tituloAjuda;
	private JLabel iconeTeclas;
	
	private int pinSelecionado;

	public Ajuda() {
		super();
		setSize(944, 601);
		setLayout(null);
		
		add(getTextPaneAjuda());
		add(getIconeTeclas());
		add(getBotaoVoltar());
		
		for (int i = 0; i < getPins().size(); i++) {
			add(getPins().get(i));
		}

		setPinSelecionado(PIN_1);
		add(getTituloAjuda(1));            //JUAO AAAAA
		atualizarSelecao(getPinSelecionado());
		
		add(getFundoCenario());
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(Imagens.getCenarioMenu(), 0, 0, null);
	}

	public void atualizarSelecao(int codPin) {
		setPinSelecionado(codPin);
		
		getIconeTeclas().setVisible(false);
		
		if (codPin == PIN_4) {
			getIconeTeclas().setVisible(true);
		}
		
		for (int i = 0; i < getPins().size(); i++) {
			getPins().get(i).setIcon(Imagens.getPinBranco());
		}
		
		getSelecao(codPin).setIcon(Imagens.getPinAzul());
		getTituloAjuda(codPin);
		getTextPaneAjuda().setText(getTextoAjuda(codPin).toUpperCase());
		getTextPaneAjuda().setFont(Fontes.getMensagemMoedas());
	}

	public JLabel getSelecao(int codPin) {
		return getPins().get(codPin);
	}

	public int getPinSelecionado() {
		return pinSelecionado;
	}

	public void setPinSelecionado(int pinSelecionado) {
		this.pinSelecionado = pinSelecionado;
	}
	
	public String getTextoAjuda(int codPin) {            //JUAO AAAAA
		if (textoAjuda == null) {
			textoAjuda = new String[]{

					"O objetivo do jogo é levar a bola até a "
					+ "bandeira vermelha. Você deve ser ágil e rápido "
					+ "para conseguir capturar todas as moedas. "
					+ "Dessa forma, você conseguirá liberar muito "
					+ "mais cenários de jogo e, consequentemente, "
					+ "obter uma melhor pontuação na partida.",
					
					"Sua pontuação será registrada de acordo com "
					+ "sua performance na partida, ou seja, "
					+ "quanto mais moedas conseguir, maior sua "
					+ "pontuação.",
					
					"Se você precisa de tutorial pra isso, "
					+ "eu tenho até medo de explicar como é que faz.",
					
					"A seta para baixo faz a bola descer da plataforma.\n"
					+ "A seta para cima faz a bola pular."};
					
		}
		
		return textoAjuda[codPin];
	}
	
	public JLabel getTituloAjuda(int codPin) {
		if (tituloAjuda == null) {
			tituloAjuda = new JLabel();
			tituloAjuda.setFont(new Font("Kids Magazine", Font.PLAIN, 28));
			tituloAjuda.setForeground(Color.WHITE);
			tituloAjuda.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		if(codPin == 0) {
			tituloAjuda.setText("OBJETIVO DO JOGO");
		}
		else if(codPin == 1) {
			tituloAjuda.setText("PONTUAÇÃO");
		}
		else if(codPin == 2) {
			tituloAjuda.setText("COMO PERDER");
		}
		else if(codPin == 3) {
			tituloAjuda.setText("COMANDOS");
		}
		
		tituloAjuda.setSize(500, 60);
		tituloAjuda.setLocation(getWidth()/2 - tituloAjuda.getWidth()/2, 20);
		
		return tituloAjuda;
	}

	public ArrayList<JLabel> getPins() {
		if(pins == null) {
			pins = new ArrayList<JLabel>();
			JLabel pinInicial = gerarPin();
			
			pinInicial.setLocation(pinInicial.getX() - pinInicial.getWidth()*2 - 60, pinInicial.getY());
			pins.add(pinInicial);
			
			for (int i = 1; i < 5; i++) {
				JLabel pin = gerarPin();
				JLabel pinAnterior = pins.get(i-1);
				pin.setLocation(pinAnterior.getX() + pin.getWidth() + 30, pin.getY());
				pins.add(pin);
			}
		}
		return pins;
	}

	public JLabel gerarPin() {
		JLabel pin = new JLabel();
		pin.setIcon(Imagens.getPinBranco());
		pin.setSize(Imagens.getPinBranco().getIconWidth(), Imagens.getPinBranco().getIconHeight());
		pin.setLocation(getWidth()/2 - pin.getWidth()/2, getHeight() - pin.getHeight() - 50);
		return pin;
	}

	public JTextPane getTextPaneAjuda() {
		if(textPaneAjuda == null) {
			textPaneAjuda = new JTextPane();
			textPaneAjuda.setEditable(false);
			textPaneAjuda.setOpaque(false);
			textPaneAjuda.setForeground(Color.WHITE);
			textPaneAjuda.setSize(900, 560);
			textPaneAjuda.setLocation(getWidth()/2 - textPaneAjuda.getWidth()/2, 100);
			
			// Centralizar
			StyledDocument doc = textPaneAjuda.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
		}
		return textPaneAjuda;
	}
	
	public JLabel getIconeTeclas() { 
		if (iconeTeclas == null) {
			iconeTeclas = new JLabel();
			iconeTeclas.setIcon(Imagens.getSetasTeclado());
			iconeTeclas.setVisible(false);
			iconeTeclas.setSize(152, 100);
			iconeTeclas.setLocation(getWidth()/2 - iconeTeclas.getWidth()/2, getHeight()/2 + 30); 
		}
		
		return iconeTeclas;
	}

	public JLabel getBotaoVoltar() {
		if (botaoVoltar == null) {
			botaoVoltar = new JLabel("ESC");
			botaoVoltar.setFont(Fontes.getBaloo());
			botaoVoltar.setForeground(Color.WHITE);
			botaoVoltar.setIcon(Imagens.getImgBotaoVoltar());
			botaoVoltar.setSize(Imagens.getImgBotaoVoltar().getIconWidth() + 200, Imagens.getImgBotaoVoltar().getIconHeight());
			botaoVoltar.setLocation(20, 15);
		}
		return botaoVoltar;	
	}
	
	public JLabel getFundoCenario() {
		if (fundoCenario == null) {
			fundoCenario = new JLabel();
			fundoCenario.setIcon(Imagens.getFundoCenario());
			fundoCenario.setBounds(0, 0, getWidth(), getHeight());
			fundoCenario.setVisible(true);
		}
		return fundoCenario;
	}
	
}
