package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author xzenj02, trnko
 */
public class Mapa extends AnchorPane implements Observer {

    private IHra hra;
    private ImageView tecka;
    
   

    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    /**
     * Metoda nastaví pozici detektiva (tecka) a volá aktualizaci
     * Metoda nastaví mapu
     */
    private void init() {

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"), 571,277, false, true));

        tecka = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/sherlock.png"),50,50,true ,true));

//        this.setTopAnchor(tecka, 0.0);
//        this.setLeftAnchor(tecka, 0.0);

        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
    
    /**
     * 
     * Nastavení parametrů pro novou hru
     * 
     * @param novaHra 
     */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Metoda aktualizuje mapu 
     */
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

}