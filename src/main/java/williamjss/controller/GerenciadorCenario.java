package williamjss.controller;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import williamjss.components.Block;
import williamjss.components.Ball;
import williamjss.components.Coin;
import williamjss.components.Platform;
import williamjss.model.Config;
import williamjss.model.image.ImageObject;
import williamjss.model.image.ImageScene;
import williamjss.view.Cenario;

public class GerenciadorCenario {

    // Fases
    public static final int FASE_1 = 0;
    public static final int FASE_2 = 1;
    public static final int FASE_3 = 2;
    public static final int FASE_4 = 3;

    private Cenario cenario;
    private Ball bola;
    private Platform plataformaInferior;
    private Platform plataformas;
    private ArrayList<Coin> moedas;
    private int[] posicaoMoedas;
    private JsonArray levels;

    public GerenciadorCenario(Cenario cenario) {
        this.cenario = cenario;
        this.levels = Config.getLevels();
    }

    public void gerarBandeira() {
        Block bandeira = new Block();
        bandeira.atualizarBloco(Block.TIPO_BANDEIRA, ImageObject.getImgBandeira());

        Block ultimoBloco = getPlataformas().get(getPlataformas().size() - 1);
        bandeira.setLocation(ultimoBloco.getX() - 3, ultimoBloco.getY() - bandeira.getHeight());

        getPlataformas().add(bandeira);
    }

    public void gerarMoedas() {
        ArrayList<Coin> moedas = new ArrayList<Coin>();
        int dx = Block.SIZE + Block.SIZE / 2;
        for (int i = 0; i < getPosicaoMoedas().length; i++) {
            // Espero que um dia alguem tire esse armengue daqui
            if (i == 6 || i == 12 || i == 18 || i == 23) {
                dx += Block.SIZE;
            }
            Coin moeda = new Coin();
            Block bloco = getPlataformas().get(getPosicaoMoedas()[i] * 4);
            moeda.setLocation(dx + bloco.getX(), bloco.getY() - moeda.getHeight() - 10);
            moedas.add(moeda);
        }
        setMoedas(moedas);
    }

    // Deletar os objetos do cenario ao final do jogo
    public void removeObjetosCenario() {
        setPosicaoMoedas(null);
        setMoedas(null);
        setPlataformaInferior(null);
        setPlataformas(null);
        setBola(null);

        cenario.removeAll();
    }

    // Define o cenario, o tipo da bola e o tipo das plataformas
    public void definirCenario(int tipoCenario, int fase) {

        switch (tipoCenario) {

            case Cenario.TIPO_CAMPO:

                getBola().setTipo(Ball.TIPO_FUTEBOL);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_GRAMA));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

                cenario.setImgCenario(ImageScene.getCenarioCampo());

                break;

            case Cenario.TIPO_MONTANHAS:

                getBola().setTipo(Ball.TIPO_ESPINHO);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_PEDRA));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

                cenario.setImgCenario(ImageScene.getCenarioMontanhas());

                break;

            case Cenario.TIPO_NEVE:

                getBola().setTipo(Ball.TIPO_PEDRA);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_GELO));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_NEVE));

                cenario.setImgCenario(ImageScene.getCenarioNeve());

                break;

            case Cenario.TIPO_PRAIA:

                getBola().setTipo(Ball.TIPO_PRAIA);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_AREIA));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

                cenario.setImgCenario(ImageScene.getCenarioPraia());

                break;

            case Cenario.TIPO_VULCAO:

                getBola().setTipo(Ball.TIPO_VULCAO);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_PEDRA));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_LAVA));

                cenario.setImgCenario(ImageScene.getCenarioVulcao());

                break;

            case Cenario.TIPO_VOLEI:

                getBola().setTipo(Ball.TIPO_VOLEI);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_AGUA));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AREIA));

                cenario.setImgCenario(ImageScene.getCenarioVolei());

                break;

            case Cenario.TIPO_BASQUETE:

                getBola().setTipo(Ball.TIPO_BASQUETE);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_BASQUETE));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_ESPINHO));

                cenario.setImgCenario(ImageScene.getCenarioBasquete());

                break;

            case Cenario.TIPO_GOLFE:

                getBola().setTipo(Ball.TIPO_GOLFE);

                setPlataformas(gerarPlataformas(fase, Block.TIPO_GRAMADO));
                setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

                cenario.setImgCenario(ImageScene.getCenarioGolfe());

                break;
        }

    }

    // Gera a plataforma inferior de acordo com o tipo especificado
    public Platform gerarPlataformaInferior(int tipo) {
        Platform plataformaInferior = new Platform(tipo);
        return plataformaInferior;
    }

    // Gera uma sequencia de plataformas para montar cada fase
    public Platform gerarPlataformas(int codFase, int tipo) {

        // Considerando a velocidade padrao de movimento das plataformas, com 8 padroes
        // o jogo duraria cerca de 20s, criar arrays com 8 padroes para cada fase...

        JsonObject level = levels.get(codFase).getAsJsonObject();

        int[] coins = level.get("coins")
                .getAsJsonArray()
                .asList()
                .stream()
                .mapToInt(element -> element.getAsInt())
                .toArray();

        int[] platforms = level.get("platforms")
                .getAsJsonArray()
                .asList()
                .stream()
                .mapToInt(element -> element.getAsInt())
                .toArray();

        setPosicaoMoedas(coins);

        return new Platform(tipo, platforms);
    }

    public int[] getPosicaoMoedas() {
        return posicaoMoedas;
    }

    public void setPosicaoMoedas(int[] posicaoMoedas) {
        this.posicaoMoedas = posicaoMoedas;
    }

    public Platform getPlataformaInferior() {
        return plataformaInferior;
    }

    public void setPlataformaInferior(Platform plataformaInferior) {
        this.plataformaInferior = plataformaInferior;
    }

    public Platform getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(Platform plataformas) {
        this.plataformas = plataformas;
    }

    public ArrayList<Coin> getMoedas() {
        return moedas;
    }

    public void setMoedas(ArrayList<Coin> moedas) {
        this.moedas = moedas;
    }

    public Ball getBola() {
        if (bola == null) {
            bola = new Ball();
        }
        return bola;
    }

    public void setBola(Ball bola) {
        this.bola = bola;
    }
}
