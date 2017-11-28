/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import javafx.scene.media.AudioClip;



/*******************************************************************************
 * Třída PrikazOdevzdej implementuje pro hru příkaz odevzdej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazOdevzdej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odevzdej";
    private HerniPlan hPlan;
    private Batoh batoh;
    private Hra hra;
    private AudioClip fanfare;
    

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy PrikazOdevzdej
	 * @param hra odkaz na hru, kterou hrajete
     *  //hPlan herní plán, ve kterém bude ve hře možné něco "vzít".
     * //batoh batoh, do kterého se budou sebrané věci ukládat.
     */
    public PrikazOdevzdej(Hra hra)
    {
        this.hra = hra;
        this.hPlan = hra.getHerniPlan();
        this.batoh = hPlan.getBatoh();
        
            }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "odevzdej". Zkouší odevzdat "ukradnuté věci". 
	 *  Pokud jsou v batohu všechny věci potřebné k odevzdání 
     *  a hráč se nachází ve správném prostoru,
	 *  pak se věci odevzdají a 'Hráč vyhrál'.
     *  Pokud v batohu nejsou všechny potřebné věci 
	 *  nebo se hráč nenachází v požadovaném prostoru,
     *  pak se vypíše chybové hlášení.
     *                         
     *@return zpráva, která se vypíše hráči
     *
     */
    public String proved(String... parametry) {
        //String prostor= parametry[0];
        
        if (over()){
            hra.setKonecHry(true);
            
            fanfare = new AudioClip(this.getClass().getResource("/zdroje/fanfare.mp3").toString());
            fanfare.play();
            
            return "Jsi opravdový kabrňák, nalezl jsi všechny ukradené věci. \n" +"Hru jsi vyhrál! ";
        }
        hra.setKonecHry (false);
        return "nemáš ještě všechny ztracené věci nebo nejsi v muzeu.";
    }
    
    /**
	*  Metoda ověřuje zda jsou obě návratové hodnoty z metod "vMuzeu" a "vBatohu" true. tj, že platí.
	*/
    public boolean over(){
        if (vMuzeu() && vBatohu()){
            //System.out.println("Over-true");
            return true;
            
        }
        //System.out.println("Over-false");
        return false;
     
    }
    
    /**
	*  Metoda ověřuje zda jsou v batohu všechny potřebné věci pro odevzdání.
	*/
	public boolean vBatohu(){
        if ((batoh.vBatohuJe("zlateVejce") && batoh.vBatohuJe("indianskaCelenka") && (batoh.vBatohuJe("prsten") 
        && batoh.vBatohuJe("trilobit") && batoh.vBatohuJe("globus") ))){
            //System.out.println("vbatohu-true");
            return true;
        }
        //System.out.println("vbatohu-false");
        return false;
    }
    
    /**
	*  Metoda ověřuje zda se hráč nachází ve správném prostoru.
	*/
    public boolean vMuzeu(){
        //String pomocna;
        Prostor aktualniProstor = null;
        aktualniProstor = hPlan.getAktualniProstor();
        //pomocna = aktualniProstor.getNazev();
        if (aktualniProstor.getNazev().equals("muzeum")){
            //System.out.println("vmuzeu-true");
            //System.out.println(pomocna);
            return true;
        }
        //System.out.println("vmuzeu-false");
        //System.out.println(pomocna);
        return false;
        
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
