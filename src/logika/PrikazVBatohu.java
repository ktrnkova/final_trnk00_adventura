/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vBatohu.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazVBatohu implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vBatohu";
    private HerniPlan hPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     * @param hPlan herní plán, ve kterém bude ve hře možné zjistit co je "vBatohu".
     */
    public PrikazVBatohu(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "vBatohu". Vypisuje obsah batohu
     *  
     *  Pokud jsou v batohu nějaké věci, vypíše seznam.
     *  Pokud v batohu nic není, vypíše "Batoh je prázdný"
     *
     *                         
     *@return zpráva, která se vypíše hráči
     *
     */    
	public String proved(String... parametry){
        if (hPlan.getBatoh().getSeznamVeci().isEmpty()) {
            // pokud je batoh prázdný
            return "\nBatoh je prázdný.";
        }
        else {
            // pokud je v batohu alespoň jedna věc.
            return "\nV batohu je:" + hPlan.getBatoh().getSeznamVeci() + ".";
        }
    }

     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */    
    public String getNazev() {
            return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
