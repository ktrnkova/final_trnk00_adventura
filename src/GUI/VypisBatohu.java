/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author trnko
 * 
 *  Třída, která vytváří panel obsahu věcí v batohu
 * 
 */
public class VypisBatohu extends ListView implements Observer{

    private IHra hra;
    private ObservableList<Object> obsah = FXCollections.observableArrayList();
        
    /**
     * Vytváří panel pro zobrazení věcí v batohu pomocí obrázků
     * @param hra 
     */
    public VypisBatohu (IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().getBatoh().registerObserver(this);
                        
        this.setItems(obsah);
        this.setPrefWidth(150);
        this.setPrefHeight(110);
        update();
    }
    
    /**
     * Metoda aktualizuje veci v batohu
     */
    @Override
    public void update() {
      Map<String, Vec> seznamVeci = hra.getHerniPlan().getBatoh().getSeznamVeci();
      obsah.clear();
      for (String s : seznamVeci.keySet()){
          Vec pom = seznamVeci.get(s);
          ImageView picture = new ImageView(new Image (Main.class.getResourceAsStream("/zdroje/" + pom.getObrazek()), 100, 100, false, false));
          obsah.add(picture);
                }
    }
    
    /**
     * Nastavení parametrů pro novou hru
     * @param hra 
     */
    public void newGame (IHra hra){
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

}
