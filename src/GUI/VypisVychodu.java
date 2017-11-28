/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author trnko
 * 
 * Třída, ve které je naimplementována metoda k vytvoření ComboBoxu
 * ComboBox se používá pro přechody mezi prostory ve hře.
 */
public class VypisVychodu extends ComboBox implements Observer {
    
    private IHra hra;
    private final ObservableList <String> prostory = FXCollections.observableArrayList();
    String value = " ";
  
    /**
     * konstruktor
     * voláním metody init, zaregistruje pozorovatele
     * 
     * @param hra 
     */
    public VypisVychodu(IHra hra){
        this.hra = hra;
        init();
    }
    
    /**
     * Metoda vrací ComboBox
     * @return (comboBox)
     */
    public ComboBox getComboBox(){
        return this;
    }
          
    /**
     * Metoda aktualizuje seznam východů
     * při změně prostoru
     */
    @Override
    public void update() {
        prostory.clear();
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()){
            prostory.add(prostor.getNazev());
        }
    }

    /**
     * Metoda nastaví rozměry, text...
     * v graf. rozhraní
     */
    public void init() {
        this.setItems (prostory);
        this.setEditable(true);
        this.setPrefWidth(150);
        this.setPrefHeight(40);
        this.setPromptText("Vyber prostor");
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    /**
     * Nastavení parametrů pro novou hru
     * @param hra 
     */
    public void novaHra (IHra hra){
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra; 
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
  
}
