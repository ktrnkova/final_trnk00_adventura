/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vezmi.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazVyndej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vyndej";
    private HerniPlan hPlan;
        
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     * @param hPlan herní plán, ve kterém bude ve hře možné něco "vyndat".
     */
    public PrikazVyndej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "vyndej". Zkouší vyndat zadanou věc z batohu. 
	 *  Pokud je věc v batohu dojde k vyndání věci z batohu do aktuálního prostoru. 
     *  Pokud věc není v batohu, 
     *  pak se vypíše chybové hlášení.
     *  
     *  Zahrnuta i kontrola maximální kapacity batohu.
     *
     *@param parametry - jako  parametr obsahuje název věci,
     *                   kteru chce hráč vzít
     *                         
     *@return zpráva, která se vypíše hráči
     *
     */
	public String proved(String... parametry){
        if (parametry.length == 0){
            return "\nNevím co mám vyndat, přidej název věci.";
        }
        String nazevVeci = parametry [0];
        Prostor aktualniProstor = hPlan.getAktualniProstor();
        Batoh batoh = hPlan.getBatoh();
        Vec vyndavana = batoh.getVec(nazevVeci);
        
        if (vyndavana == null){
            return "\n" + nazevVeci + "v batohu opravdu není.";
        }
        else {
            vyndavana = batoh.vyndejVecZBatohu(nazevVeci);
            aktualniProstor.vlozVec(vyndavana);
            batoh.notifyObservers();
            hPlan.notifyObservers();
            return "Z batohu bylo vyndáno '" + nazevVeci + "'" ;
        }
    }
   
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */    
    public String getNazev(){
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
