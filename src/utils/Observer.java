/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author trnko
 * 
 * Instance třídy implementující toto rozhraní 
 * ve hře bude dostávat informaci o změně stavu pozorovaného objektu
 */
public interface Observer {
    
    /**
     * Metoda, která aktualizuje pozorovatele
     * 
     * volá se pomocí metody notifyAllObservers (v Subject)
     */
    void update();
}
