/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 * Třída PrikazDej implementuje pro hru příkaz dej.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazDej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "dej";
    private HerniPlan hPlan;
    //private Batoh batoh;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     * @param hPlan herní plán, ve kterém bude ve hře možné něco "vzít".
     * param batoh batoh, do kterého se budou sebrané věci ukládat.
     */
    public PrikazDej(HerniPlan hPlan /*,Batoh batoh*/)
    {
        this.hPlan = hPlan;
        //this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "dej". Zkouší dát zadanou věc. 
	 *  Pokud je věc v batohu a lze ji dát v daném prostoru,
     *  pak dojde k "darování" věci osobě v prostoru. 
     *  Pokud věc není v batohu nebo hráč není ve správném prostoru, 
     *  pak se vypíše chybové hlášení.
     *  
     *
     *@param parametry - jako  parametr obsahuje název věci,
     *                   kteru chce hráč dát.
     *                         
     *@return zpráva, která se vypíše hráči
     *
     */
	public String proved(String... parametry){
        if (parametry.length == 0){
            return "\nNevím co mám dát, přidej název věci.";
        }
        String nazevVeci = parametry [0];
        Prostor aktualniProstor = hPlan.getAktualniProstor();
        Batoh batoh = hPlan.getBatoh();
        Vec odevzdana = batoh.getVec(nazevVeci);
        
        if (odevzdana == null){
            return "\nTohle v batohu nemáš.";
        }
        
        else if (odevzdana.getNazev().equals("mince")&& aktualniProstor.getNazev().equals("namesti")){
            batoh.vyndejVecZBatohu("mince");
            batoh.notifyObservers();
            batoh.vlozVec(new Vec("prsten","snubní prsten šesté manželky Jindřicha VIII.",true,"prsten.PNG"));
            batoh.notifyObservers();
            return "Překupník: Tak tohle má opravdovou hodnotu, tady za to máš prsten.";
        }
        
        else if (odevzdana.getNazev().equals("potvrzeni")&& aktualniProstor.getNazev().equals("ulice")){
            batoh.vyndejVecZBatohu("potvrzeni");
            batoh.notifyObservers();
            hPlan.getAntikvariat().setUzamcen(false);
            return "Majitel obchodu: Dobře teď už ti věřím, můžeš jít do mého obchodu.";
        }
        else if (odevzdana.getNazev().equals("babovicka")&& aktualniProstor.getNazev().equals("hriste")){
            batoh.vyndejVecZBatohu("babovicka");
            batoh.notifyObservers();
            batoh.vlozVec(new Vec("indianskaCelenka","",true,"indianskaCelenka.PNG"));
            batoh.notifyObservers();
            return "Bambino: Hihi haha, tu máš tu čelenku!";
        }
        else {
            return "Není tu nikdo koho by '" + nazevVeci +"' zajímal";
        }
    }
          
            
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     * @return 
     */    
    public String getNazev(){
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
