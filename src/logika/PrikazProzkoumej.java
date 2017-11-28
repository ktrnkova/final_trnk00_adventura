/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazProzkoumej implementuje pro hru příkaz "prozkoumej".
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor pro příkaz prozkoumej
     *  
     *  @param hPlan herní plán, ve kterém bude ve hře možné něco "prozkoumat".
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "prozkoumej". Zkouší prozkoumat zadanou věc. 
     *  Pokud je věc existuje a nachází se v aktuálním prostoru,
     *  dojde k jejímu prozkoumání. 
     *  Pokud věc neexistuje nebo není v aktuálním prostoru, 
     *  pak se vypíše chybové hlášení.
     *  
     *
     *@param parametry - jako  parametr obsahuje název věci,
     *                   kteru chce hráč prozkoumat
     *                         
     *@return zpráva, která se vypíše hráči
     *
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            //pokud hráč nezadá co chce prozkoumat
            return "nevím, co mám prozkoumat, zadej název věci";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            //pokud daná věc v prostoru není
            return "věc '" + nazevVeci + "' tu není";
        }
        
        hPlan.getAktualniProstor().vlozVec(vec);
        
        return nazevVeci + ": " + vec.getPopis();
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
