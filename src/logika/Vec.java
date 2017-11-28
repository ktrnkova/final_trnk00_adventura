/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;

/*******************************************************************************
 * Třída Vec popisuje jednotlivé věci ve hře
 *
 * Tato třída je součástí jednoduché textové hry.
 * parametry zde budou nazev,popis, prenositelna
 *
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private  String obrazek;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
	 *  Zde určíme název, popis a přenositelnost
     */
    public Vec(String nazev, String popis, boolean prenositelna, String obrazek)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.obrazek = obrazek;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vrací název věci.
     * @return nazev, název věci.
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Metoda vrací popis věci.
     * @return popis, popis věci.
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     * Metoda zjišťuje, zda už je věc přenositelná.
     * @return vrátí true, pokud je přenositelná.
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
    
    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.

    //== Soukromé metody (instancí i třídy) ========================================

    public String getObrazek() {
       return obrazek;
    }

}
