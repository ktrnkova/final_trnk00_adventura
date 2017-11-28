/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import javafx.scene.media.AudioClip;



/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vezmi.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;
    private Batoh batoh;
    private  AudioClip collect;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     * @param hPlan herní plán, ve kterém bude ve hře možné něco "vzít".
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
        this.batoh = herniPlan.getBatoh();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "vezmi". Zkouší vzít zadanou věc. Pokud je věc
     *  přenositelná/sebratelná a existuje, dojde k vložení věci do batohu. 
     *  Pokud věc není přenositelná nebo neexistuje, 
     *  pak se vypíše chybové hlášení.
     *  
     *  Zahrnuta i kontrola maximální kapacity batohu.
     *
     *@param parametry - jako  parametr obsahuje název věci,
     *                   kteru chce hrá vzít
     *                         
     *@return zpráva, která se vypíše hráči
     *
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "Nevím, co mám sebrat, zadej název věci.";
        }
        
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec vec = aktualniProstor.odeberVec(nazevVeci);
        herniPlan.notifyObservers();
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        else{
            if (vec.isPrenositelna()) {
                if (batoh.jeNaplnitelny()) {
                    batoh.vlozVec(vec);
                    
                    collect = new AudioClip(this.getClass().getResource("/zdroje/collect.mp3").toString());
                    collect.play();
                    
                    batoh.notifyObservers();
                    return "věc '" + nazevVeci + "' jsi uložil do batohu";
                    
                    
                }
                else{
                    aktualniProstor.vlozVec(vec);
                    herniPlan.notifyObservers();
                    return "batoh je plný";
                }
            }
            else{
                aktualniProstor.vlozVec(vec);
                herniPlan.notifyObservers();
                return "věc '" + nazevVeci + "' není určena k přenášení";
            }
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
