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
import logika.Postava;
import utils.Observer;

/**
 *
 * @author trnko
 */
public class VypisPostav extends ComboBox implements Observer {
    
    private IHra hra;
    private final ObservableList <String> postavy = FXCollections.observableArrayList();
    private final ObservableList <String> prostory = FXCollections.observableArrayList();
    String value = " ";
    
    /**
     * konstruktor
     * volání metody init, zaregistruje pozorovatele
     * 
     * 
     * @param hra 
     */
    public VypisPostav(IHra hra){
        this.hra = hra;
        init();
    }
    
    /**
     * 
     * Metoda vrací ComboBox2
     * @return (comboBox2)
     */
     
    public ComboBox getComboBox2(){
        return this;
    }
    
    
    /**
     * Metoda aktualizuje postavy v aktuálním prosturu
     */
    @Override
    public void update() {
       postavy.clear();
        for(Postava postava: hra.getHerniPlan().getAktualniProstor().getPostava()){
            postavy.add(postava.getJmeno());
        }
        
    }
    /**
     * nastavení zobrazení okna s postavami
     */
    public void init(){
        this.setItems(postavy);
        this.setEditable(true);
        this.setPrefWidth(150);
        this.setPrefHeight(40);
        this.setPromptText("Vyber postavu");
        hra.getHerniPlan().registerObserver(this);
        update();
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
