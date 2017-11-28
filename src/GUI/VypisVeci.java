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
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author trnko
 * 
 * Třída, která vytváří obsah věcí v prostoru
 */
public class VypisVeci extends ListView implements Observer{
    
   private IHra hra;
   private ObservableList<Object> umisteno = FXCollections.observableArrayList();
   
   /**
    * Vytváří panel pro zobrazení věcí v prostoru pomocí obrázků
    * @param hra 
    */
   public VypisVeci (IHra hra){
       this.hra = hra;
       hra.getHerniPlan().registerObserver(this);
       hra.getHerniPlan().getAktualniProstor().registerObserver(this);
       
       this.setItems(umisteno);
       this.setPrefWidth(150);
       this.setPrefHeight(300);
       update();
   }

    /**
     * Metoda aktualizuje seznam věcí v prostoru
     */
    @Override
    public void update() {
        Map <String, Vec> seznamVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
        umisteno.clear();
        for (String x: seznamVeci.keySet()){
            Vec pomoc = seznamVeci.get(x);
            ImageView obr = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + pomoc.getObrazek()),100,100,false,false));
            umisteno.add(obr);
                    
        }
        
    }
    
    /**
     * Nastavení parametrů pro novou hru
     * @param hra 
     */
    public void newGame(IHra hra){
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
    
}
