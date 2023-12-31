package williamjss.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.google.gson.JsonObject;

import williamjss.model.Config;
import williamjss.model.Fontes;
import williamjss.model.image.ImageButton;
import williamjss.model.image.ImageScene;

public class Options extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel fundoCenario;
    private JLabel botaoVoltar;
    private JLabel tituloOpcoes;

    private JLabel buttonClearStats;
    private JLabel textButtonClearStats;
    private JLabel descriptionClearStats;

    private JLabel buttonEnableMusic;
    private JLabel textButtonEnableMusic;
    private JLabel descriptionEnableMusic;

    private JLabel buttonEnableEffects;
    private JLabel textButtonEnableEffects;
    private JLabel descriptionEnableEffects;

    private JLabel buttonDifficult;
    private JLabel textButtonDifficult;
    private JLabel descriptionDifficult;

    private int botaoSelecionado;

    private JsonObject optionsObject;
    private boolean effectsEnabled;
    private boolean musicEnabled;
    private String difficult;

    public static final int BOTAO_REINICIAR = 0;
    public static final int BOTAO_MUSICA = 1;
    public static final int BOTAO_EFEITOS = 2;
    public static final int BOTAO_DIFICULDADE = 3;

    public Options() {
        super();
        setSize(944, 601);
        setLayout(null);

        add(getBotaoVoltar());
        add(getTituloOpcoes());

        add(getTextButtonClearStats());
        add(getButtonClearStats());
        add(getDescriptionClearStats());

        add(getTextButtonEnableMusic());
        add(getButtonEnableMusic());
        add(getDescriptionEnableMusic());

        add(getTextButtonEnableEffects());
        add(getButtonEnableEffects());
        add(getDescriptionEnableEffects());

        add(getTextButtonDifficult());
        add(getButtonDifficult());
        add(getDescriptionDifficult());

        add(getFundoCenario());

        setBotaoSelecionado(BOTAO_REINICIAR);

        this.optionsObject = Config.getOptions();
        JsonObject sound = optionsObject.get("sound").getAsJsonObject();
        setEffectsEnabled(sound.get("effects").getAsBoolean());
        setMusicEnabled(sound.get("music").getAsBoolean());
        setDifficult(optionsObject.get("difficult").getAsString());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(ImageScene.getCenarioMenu(), 0, 0, null);
    }

    public JLabel getBotao(int codBotao) {
        if (codBotao == BOTAO_REINICIAR) {
            return getButtonClearStats();
        } else if (codBotao == BOTAO_MUSICA) {
            return getButtonEnableMusic();
        } else if (codBotao == BOTAO_EFEITOS) {
            return getButtonEnableEffects();
        } else if (codBotao == BOTAO_DIFICULDADE) {
            return getButtonDifficult();
        }
        return null;
    }

    public int getBotaoSelecionado() {
        return botaoSelecionado;
    }

    public void setBotaoSelecionado(int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;

        getBotao(BOTAO_REINICIAR).setIcon(ImageButton.getImgBotao2());
        getBotao(BOTAO_MUSICA).setIcon(ImageButton.getImgBotao2());
        getBotao(BOTAO_EFEITOS).setIcon(ImageButton.getImgBotao2());
        getBotao(BOTAO_DIFICULDADE).setIcon(ImageButton.getImgBotao2());

        getBotao(botaoSelecionado).setIcon(ImageButton.getImgBotao1Selecionado());
    }

    public boolean isEffectsEnabled() {
        return effectsEnabled;
    }

    public void setEffectsEnabled(boolean effectsEnabled) {
        getTextButtonEnableEffects().setText(effectsEnabled ? "DESATIVAR" : "ATIVAR");
        this.effectsEnabled = effectsEnabled;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        getTextButtonDifficult().setText(difficult);
        this.difficult = difficult;
    }

    public boolean isMusicEnabled() {
        return musicEnabled;
    }

    public void setMusicEnabled(boolean musicEnabled) {
        getTextButtonEnableMusic().setText(musicEnabled ? "DESATIVAR" : "ATIVAR");
        this.musicEnabled = musicEnabled;
    }

    public JLabel getTituloOpcoes() {
        if (tituloOpcoes == null) {
            tituloOpcoes = new JLabel("OPÇÕES");
            tituloOpcoes.setFont(new Font("Kids Magazine", Font.PLAIN, 28));
            tituloOpcoes.setForeground(Color.WHITE);
            tituloOpcoes.setHorizontalAlignment(SwingConstants.CENTER);
            tituloOpcoes.setSize(500, 60);
            tituloOpcoes.setLocation(getWidth() / 2 - tituloOpcoes.getWidth() / 2, 20);
        }
        return tituloOpcoes;
    }

    public JLabel getBotaoVoltar() {
        if (botaoVoltar == null) {
            botaoVoltar = new JLabel("ESC");
            botaoVoltar.setFont(Fontes.getBaloo());
            botaoVoltar.setForeground(Color.WHITE);
            botaoVoltar.setIcon(ImageButton.getImgBotaoVoltar());
            botaoVoltar.setSize(ImageButton.getImgBotaoVoltar().getIconWidth() + 200,
                    ImageButton.getImgBotaoVoltar().getIconHeight());
            botaoVoltar.setLocation(20, 15);
        }
        return botaoVoltar;
    }

    public JLabel getFundoCenario() {
        if (fundoCenario == null) {
            fundoCenario = new JLabel();
            fundoCenario.setIcon(ImageScene.getFundoCenario());
            fundoCenario.setBounds(0, 0, getWidth(), getHeight());
            fundoCenario.setVisible(true);
        }
        return fundoCenario;
    }

    // LINHA 1 - CLEAR STATS
    public JLabel getButtonClearStats() {
        if (buttonClearStats == null) {
            buttonClearStats = new JLabel();
            buttonClearStats.setIcon(ImageButton.getImgBotao2());
            buttonClearStats.setSize(ImageButton.getImgBotao2().getIconWidth(),
                    ImageButton.getImgBotao2().getIconHeight());
            buttonClearStats.setLocation(getWidth() - buttonClearStats.getWidth() - 50, 125);
        }
        return buttonClearStats;
    }

    public JLabel getTextButtonClearStats() {
        if (textButtonClearStats == null) {
            textButtonClearStats = new JLabel("REINICIAR");
            textButtonClearStats.setHorizontalAlignment(SwingConstants.CENTER);
            textButtonClearStats.setForeground(Color.WHITE);
            textButtonClearStats.setFont(Fontes.getBotao());
            textButtonClearStats.setBounds(getButtonClearStats().getBounds());
        }
        return textButtonClearStats;
    }

    public JLabel getDescriptionClearStats() {
        if (descriptionClearStats == null) {
            descriptionClearStats = new JLabel("Reiniciar progresso do jogo:");
            descriptionClearStats.setForeground(Color.WHITE);
            descriptionClearStats.setFont(Fontes.getBaloo());
            descriptionClearStats.setSize(getWidth() - getButtonClearStats().getWidth() - 110,
                    getButtonClearStats().getHeight());
            descriptionClearStats.setLocation(50, getButtonClearStats().getY());
        }
        return descriptionClearStats;
    }

    // LINHA 2 - MUSICA
    public JLabel getButtonEnableMusic() {
        if (buttonEnableMusic == null) {
            buttonEnableMusic = new JLabel();
            buttonEnableMusic.setIcon(ImageButton.getImgBotao2());
            buttonEnableMusic.setSize(ImageButton.getImgBotao2().getIconWidth(),
                    ImageButton.getImgBotao2().getIconHeight());
            buttonEnableMusic.setLocation(getWidth() - buttonEnableMusic.getWidth() - 50,
                    getButtonClearStats().getY() + getButtonClearStats().getHeight() + 25);
        }
        return buttonEnableMusic;
    }

    public JLabel getTextButtonEnableMusic() {
        if (textButtonEnableMusic == null) {
            textButtonEnableMusic = new JLabel("DESATIVAR");
            textButtonEnableMusic.setHorizontalAlignment(SwingConstants.CENTER);
            textButtonEnableMusic.setForeground(Color.WHITE);
            textButtonEnableMusic.setFont(Fontes.getBotao());
            textButtonEnableMusic.setBounds(getButtonEnableMusic().getBounds());
        }
        return textButtonEnableMusic;
    }

    public JLabel getDescriptionEnableMusic() {
        if (descriptionEnableMusic == null) {
            descriptionEnableMusic = new JLabel("Ativar/desativar música de fundo:");
            descriptionEnableMusic.setForeground(Color.WHITE);
            descriptionEnableMusic.setFont(Fontes.getBaloo());
            descriptionEnableMusic.setSize(getWidth() - getButtonEnableMusic().getWidth() - 110,
                    getButtonEnableMusic().getHeight());
            descriptionEnableMusic.setLocation(50, getButtonEnableMusic().getY());
        }
        return descriptionEnableMusic;
    }

    // LINHA 3 - Efeitos Sonoros
    public JLabel getButtonEnableEffects() {
        if (buttonEnableEffects == null) {
            buttonEnableEffects = new JLabel();
            buttonEnableEffects.setIcon(ImageButton.getImgBotao2());
            buttonEnableEffects.setSize(ImageButton.getImgBotao2().getIconWidth(),
                    ImageButton.getImgBotao2().getIconHeight());
            buttonEnableEffects.setLocation(getWidth() - buttonEnableEffects.getWidth() - 50,
                    getButtonEnableMusic().getY() + getButtonEnableMusic().getHeight() + 25);
        }
        return buttonEnableEffects;
    }

    public JLabel getTextButtonEnableEffects() {
        if (textButtonEnableEffects == null) {
            textButtonEnableEffects = new JLabel("DESATIVAR");
            textButtonEnableEffects.setHorizontalAlignment(SwingConstants.CENTER);
            textButtonEnableEffects.setForeground(Color.WHITE);
            textButtonEnableEffects.setFont(Fontes.getBotao());
            textButtonEnableEffects.setBounds(getButtonEnableEffects().getBounds());
        }
        return textButtonEnableEffects;
    }

    public JLabel getDescriptionEnableEffects() {
        if (descriptionEnableEffects == null) {
            descriptionEnableEffects = new JLabel("Ativar/desativar efeitos sonoros:");
            descriptionEnableEffects.setForeground(Color.WHITE);
            descriptionEnableEffects.setFont(Fontes.getBaloo());
            descriptionEnableEffects.setSize(getWidth() - getButtonEnableEffects().getWidth() - 110,
                    getButtonEnableEffects().getHeight());
            descriptionEnableEffects.setLocation(50, getButtonEnableEffects().getY());
        }
        return descriptionEnableEffects;
    }

    // LINHA 4 - Dificuldade
    public JLabel getButtonDifficult() {
        if (buttonDifficult == null) {
            buttonDifficult = new JLabel();
            buttonDifficult.setIcon(ImageButton.getImgBotao2());
            buttonDifficult.setSize(ImageButton.getImgBotao2().getIconWidth(),
                    ImageButton.getImgBotao2().getIconHeight());
            buttonDifficult.setLocation(getWidth() - buttonDifficult.getWidth() - 50,
                    getButtonEnableEffects().getY() + getButtonEnableEffects().getHeight() + 25);
        }
        return buttonDifficult;
    }

    public JLabel getTextButtonDifficult() {
        if (textButtonDifficult == null) {
            textButtonDifficult = new JLabel("NORMAL");
            textButtonDifficult.setHorizontalAlignment(SwingConstants.CENTER);
            textButtonDifficult.setForeground(Color.WHITE);
            textButtonDifficult.setFont(Fontes.getBotao());
            textButtonDifficult.setBounds(getButtonDifficult().getBounds());
        }
        return textButtonDifficult;
    }

    public JLabel getDescriptionDifficult() {
        if (descriptionDifficult == null) {
            descriptionDifficult = new JLabel("Dificuldade:");
            descriptionDifficult.setForeground(Color.WHITE);
            descriptionDifficult.setFont(Fontes.getBaloo());
            descriptionDifficult.setSize(getWidth() - getButtonDifficult().getWidth() - 110,
                    getButtonDifficult().getHeight());
            descriptionDifficult.setLocation(50, getButtonDifficult().getY());
        }
        return descriptionDifficult;
    }

}
