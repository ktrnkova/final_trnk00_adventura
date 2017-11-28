/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;
import utils.Subject;
import utils.Observer;

/*******************************************************************************
 * Instance třídy Batoh nám umožní dávat věci do batohu a z batohu,
 * maximální obsah batohu je 8 věcí.
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Katerina Trnkova
 * @version   ZS 2016
 */
public class Batoh implements Subject
{
    //== Datové atributy (statické i instancí)======================================
    //private List <Vec> seznamVeci; // Přestavuje seznam věcí v batohu.
    private Map <String, Vec> seznamVeci;
    private static final int MAXIMALNI_KAPACITA = 8; // Maximální počet věcí nesených v batohu

    private final List<Observer> listObserveru = new ArrayList<>();
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor seznamu věcí v batohu
     */
    public Batoh()
    {
        //seznamVeci = new ArrayList<Vec>();
        seznamVeci = new HashMap<String, Vec> ();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Pokud se věc vejde do batohu pak ji tam vloží
     * @param vec Je vec, kterou chceme přidat do batohu
     * @return vec Když se věc vloží do batohu
     * @return null Pokud se věc do batohu nevloží
     */
    public Vec vlozVec (Vec vec) {
        if (jeNaplnitelny() ) {
            //seznamVeci.add(vec);
            seznamVeci.put(vec.getNazev(), vec);
            return vec;
        }
		// pokud je batoh plný nevloží se 
		System.out.println("Batoh je plný, věc se nevložila");
        return null;
    }
    
    /**
     * Metoda, která kontroluje, zda je v batohu ještě místo pro přidání daší věci
     */
    public boolean jeNaplnitelny(){
        if (seznamVeci.size() < MAXIMALNI_KAPACITA){
            return true;
        }
        return false;
    }
    
    /**
     * Metoda vrátí informaci o tom, zda je daná věc v batohu či ne
	 *
     * @return true , jestliže daná věc v batohu je
     * @return false , jestliže daná věc v batohu není
     */
    public boolean vBatohuJe (String hledana) {
        //for(Vec pridavana: seznamVeci){
           // if(pridavana.getNazev().equals(hledana)){
           if (this.seznamVeci.containsKey(hledana)){
                return true;
            }
        //}
        return false;
    }
    
    /**
     * Metoda nám vypíše seznam všech věcí, které se nacházejí v batohu
     * @retun String (to co se v batohu nachází)
     */
	/**public String getSeznamVeci(){
        String seznam = "";
        for (Vec vypisovana : seznamVeci){
            if (!seznam.equals("")){
                seznam += ", ";
            }
            seznam += " " + vypisovana.getNazev();
        }
        return seznam; 
    }**/
    
    public Map<String, Vec> getSeznamVeci(){
        return seznamVeci;
    }
    
    /**
     * Vyndá věc z batohu
     * return vrátí název věci, která byla odebrána z batohu
     */
  /**  public Vec vyndejVecZBatohu (String odstranovana){
        Vec vyndanaVec = null;
        for(Vec vec: seznamVeci){
            if (vec.getNazev().equals(odstranovana)){
                vyndanaVec = vec;
                seznamVeci.remove(vec);
                break;
            }
        }
        return vyndanaVec;
    }
    * **/
    public Vec vyndejVecZBatohu (String odstranovana){
        return seznamVeci.remove(odstranovana);
    }
        
    /**
     * Metoda, která vypíše (zadanou) věc, pokud se v batohu nachází
     * pokud v batohu věc není vrátí se null
     */
   /** public Vec getVec (String potrebnaVec){
        Vec hledana = null;
        for(Vec porovnavana: seznamVeci){
            if(porovnavana.getNazev().equals(potrebnaVec)){
                hledana = porovnavana;
                break;
            }
        }
        return hledana;
    } **/
    
     public Vec getVec (String potrebnaVec){
        return seznamVeci.get(potrebnaVec);
    }
        
        /**
         * 
         * Metoda zjišťuje, zda se daná věc vyskytuje v batohu.
         * @param nazev
         * @return 
         */  
    public boolean obsahujeVec (String nazev) {
        if (this.seznamVeci.containsKey(nazev)) {
            return true;
        }
        return false;
    }
    
    /**
     * Vypíše jaká je kapacita batohu
     */
    public int getMaximalniKapacita(){
        return MAXIMALNI_KAPACITA;
    }
    
    //== Soukromé metody (instancí i třídy) ========================================

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : listObserveru) {
            observer.update();
        }
    
    }
}
