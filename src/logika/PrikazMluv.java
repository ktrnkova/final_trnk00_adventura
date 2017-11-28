/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazMluv implementuje pro hru příkaz mluv.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    private HerniPlan hPlan;
    private Hra hra;
    
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
	 *  @param hra odkaz na hru,
     */
    public PrikazMluv(Hra hra)
    {
        this.hra = hra;
        this.hPlan = hra.getHerniPlan();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "mluv". Zkouší mluvit se zadanou postavou.
     *  Pokud postava je v aktuálním prostoru a existuje,
     *  pak dojde k "rozhovoru/mluveni" 
     *  Pokud postava není v prostoru nebo neexistuje, 
     *  pak se vypíše chybové hlášení.
     *  
     *  Zahrnuta i kontrola maximální kapacity batohu.
     *
     *@param parametry - jako  parametr obsahuje jméno postavy,
     *                   se kteru chce hráč mluvit
     *                         
     *@return zpráva, která se vypíše hráči
	 *
	 *  Pokud hráč zadá "mluv zlodej" hra skončí. 
     *
     */    
	public String proved(String... parametry) {
        // není uvedena proměnná s kým se má mluvit
		if (parametry.length < 1) {
            return "Nevím s kým mám mluvit, zadej jméno postavy.";
        }
        
        String jmenoPostavy = parametry[0];
        
        Postava postava = hPlan.getAktualniProstor().odeberPostavu(jmenoPostavy);
        //postava není v prostoru
		if (postava == null) {
            return "postava '" + jmenoPostavy + "' tady není";
        }
        
        hPlan.getAktualniProstor().vlozPostavu(postava);
		//mluv zlodej - po zadání tohoto příkazu hra skončí
        if (jmenoPostavy.equals("zlodej")){
            hra.setKonecHry(true);
            System.out.println(jmenoPostavy + ": " + postava.getProslov());
            return "Zloděj se bojí udání a proto tě omráčí\n" +
            "a odtáhne do zlodějského doupěte, kde se o tebe jak se patří 'postará'.\n" +
            "Hra končí, snad příště budeš moudřejší.";
        }
        
        return jmenoPostavy + ": " + postava.getProslov();
    }
    
         /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return
     */
       
    public String getNazev(){
        return NAZEV;
    }
    

    //== Soukromé metody (instancí i třídy) ========================================

}
