package williamjss.model.image;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageScene {

    private static Image cenarioBasquete;
    private static Image cenarioCampo;
    private static Image cenarioGolfe;
    private static Image cenarioMenu;
    private static Image cenarioMontanhas;
    private static Image cenarioNeve;
    private static Image cenarioPraia;
    private static Image cenarioVolei;
    private static Image cenarioVulcao;
    private static ImageIcon fundoCenario;
    private static ImageIcon fundoMenu;
    private static ImageIcon fundoMiniCenario;
    private static ImageIcon fundoSair;
    private static ImageIcon miniCenarioBasquete;
    private static ImageIcon miniCenarioCampo;
    private static ImageIcon miniCenarioGolfe;
    private static ImageIcon miniCenarioMontanhas;
    private static ImageIcon miniCenarioNeve;
    private static ImageIcon miniCenarioPraia;
    private static ImageIcon miniCenarioVolei;
    private static ImageIcon miniCenarioVulcao;

    private static URL getResource(String name) {
        return ImageScene.class.getClassLoader().getResource("img/cenario/" + name + ".png");
    }

    public static Image getCenarioBasquete() {
        if (cenarioBasquete == null) {
            cenarioBasquete = new ImageIcon(getResource("cenario-basquete")).getImage();
        }
        return cenarioBasquete;
    }

    public static Image getCenarioCampo() {
        if (cenarioCampo == null) {
            cenarioCampo = new ImageIcon(getResource("cenario-campo")).getImage();
        }
        return cenarioCampo;
    }

    public static Image getCenarioGolfe() {
        if (cenarioGolfe == null) {
            cenarioGolfe = new ImageIcon(getResource("cenario-golfe")).getImage();
        }
        return cenarioGolfe;
    }

    public static Image getCenarioMenu() {
        if (cenarioMenu == null) {
            cenarioMenu = new ImageIcon(getResource("cenario-menu")).getImage();
        }
        return cenarioMenu;
    }

    public static Image getCenarioMontanhas() {
        if (cenarioMontanhas == null) {
            cenarioMontanhas = new ImageIcon(getResource("cenario-montanhas")).getImage();
        }
        return cenarioMontanhas;
    }

    public static Image getCenarioNeve() {
        if (cenarioNeve == null) {
            cenarioNeve = new ImageIcon(getResource("cenario-neve")).getImage();
        }
        return cenarioNeve;
    }

    public static Image getCenarioPraia() {
        if (cenarioPraia == null) {
            cenarioPraia = new ImageIcon(getResource("cenario-praia")).getImage();
        }
        return cenarioPraia;
    }

    public static Image getCenarioVolei() {
        if (cenarioVolei == null) {
            cenarioVolei = new ImageIcon(getResource("cenario-volei")).getImage();
        }
        return cenarioVolei;
    }

    public static Image getCenarioVulcao() {
        if (cenarioVulcao == null) {
            cenarioVulcao = new ImageIcon(getResource("cenario-vulcao")).getImage();
        }
        return cenarioVulcao;
    }

    public static ImageIcon getFundoCenario() {
        if (fundoCenario == null) {
            fundoCenario = new ImageIcon(getResource("fundo-cenario"));
        }
        return fundoCenario;
    }

    public static ImageIcon getFundoMenu() {
        if (fundoMenu == null) {
            fundoMenu = new ImageIcon(getResource("fundo-menu"));
        }
        return fundoMenu;
    }

    public static ImageIcon getFundoMiniCenario() {
        if (fundoMiniCenario == null) {
            fundoMiniCenario = new ImageIcon(getResource("fundo-minicenario"));
        }
        return fundoMiniCenario;
    }

    public static ImageIcon getFundoSair() {
        if (fundoSair == null) {
            fundoSair = new ImageIcon(getResource("fundo-sair"));
        }
        return fundoSair;
    }

    public static ImageIcon getMiniCenarioBasquete() {
        if (miniCenarioBasquete == null) {
            miniCenarioBasquete = new ImageIcon(getResource("minicenario-basquete"));
        }
        return miniCenarioBasquete;
    }

    public static ImageIcon getMiniCenarioCampo() {
        if (miniCenarioCampo == null) {
            miniCenarioCampo = new ImageIcon(getResource("minicenario-campo"));
        }
        return miniCenarioCampo;
    }

    public static ImageIcon getMiniCenarioGolfe() {
        if (miniCenarioGolfe == null) {
            miniCenarioGolfe = new ImageIcon(getResource("minicenario-golfe"));
        }
        return miniCenarioGolfe;
    }

    public static ImageIcon getMiniCenarioMontanhas() {
        if (miniCenarioMontanhas == null) {
            miniCenarioMontanhas = new ImageIcon(getResource("minicenario-montanhas"));
        }
        return miniCenarioMontanhas;
    }

    public static ImageIcon getMiniCenarioNeve() {
        if (miniCenarioNeve == null) {
            miniCenarioNeve = new ImageIcon(getResource("minicenario-neve"));
        }
        return miniCenarioNeve;
    }

    public static ImageIcon getMiniCenarioPraia() {
        if (miniCenarioPraia == null) {
            miniCenarioPraia = new ImageIcon(getResource("minicenario-praia"));
        }
        return miniCenarioPraia;
    }

    public static ImageIcon getMiniCenarioVolei() {
        if (miniCenarioVolei == null) {
            miniCenarioVolei = new ImageIcon(getResource("minicenario-volei"));
        }
        return miniCenarioVolei;
    }

    public static ImageIcon getMiniCenarioVulcao() {
        if (miniCenarioVulcao == null) {
            miniCenarioVulcao = new ImageIcon(getResource("minicenario-vulcao"));
        }
        return miniCenarioVulcao;
    }

}
