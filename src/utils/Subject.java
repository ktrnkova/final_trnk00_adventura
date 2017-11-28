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
 * Instance třídy upozorní na toto rozhraní ve hře všechny pozorovatele
 * na každou změnu stavu.
 */
public interface Subject {
    
    /**
     * Metoda registruje pozorovatele
     * musí to být instance třídy, která implem. rozhraní Observer
     *       
     * @param observer registrovaný pozorovatel
     */
    void registerObserver(Observer observer);
    
    /**
     * Metoda slouží ke zrušení registrace pozorovatele
     * musí to být instance třídy, která implem. rozhraní Observer
     * 
     * @param observer pozorovatel, který nadále není informovám o změnách
     */
    void removeObserver(Observer observer);
    
    /**
     * Metoda se volá, když dojde k nějaké změně
     * upozorní pozorovatele, že se stala změna 
     * 
     */
    void notifyObservers();
}
