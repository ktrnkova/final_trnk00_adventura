/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída Postava popisuje jednotlivé postavy ve hře
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * Parametry jsou zde jmeno a proslov
 *
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String proslov;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor vytvoří/nastaví jména postav a jejich proslov
     */
    public Postava(String jmeno, String proslov)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vrací jméno postavy
     */
    public String getJmeno(){
        return jmeno;
    }
    /**
     * Metoda vrací proslov postavy
     */
    public String getProslov() {
        return proslov;
    }


    //== Soukromé metody (instancí i třídy) ========================================

}
