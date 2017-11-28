package logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import utils.Observer;
import utils.Subject;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Katerina Trnkova
 * @version ZS 2016/2017
 */
public class Prostor implements Subject{
   
    private final List<Observer> listObserveru = new ArrayList<>();

    private String nazev; //nazev prostoru
    private String popis; //popis prostoru
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Map <String, Postava> postavy;
    private Vec klic;
    private boolean uzamcen = false; //když je prostor uzamčen
    private double posTop;
    private double posLeft;
    private Set<Postava> postava;
    

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param posLeft
     * @param posTop
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        this.posLeft = posLeft;
        this.posTop = posTop;
        this.uzamcen = uzamcen;
        vychody = new HashSet<>();
        postava = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<> ();
    }

    public double getPosLeft() {
        return posLeft;
    }
    
    public double getPosTop() {
        return posTop;
    }
       
    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }
    public void setPostavy(Postava napln){
        postava.add(napln);
    }
    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     * @return 
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }
    
    public Postava vratPostavu(String jmeno){
        return postavy.get(jmeno);
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.uzamcen ()){
                vracenyText += "(zamčeno)";
            }
        }
        return vracenyText;
    }
    
    private String popisVeci() {
        String vracenyText = "veci:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }
        
        return vracenyText;
    }
    
     private String popisPostav() {
        String vracenyText = "postavy:";
        for (String jmeno : postavy.keySet()) {
            vracenyText += " " + jmeno;
        }
        
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
      public Collection<Postava> getPostava() {
        return Collections.unmodifiableCollection(postava);
    }
    
    
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }
    
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }
 
       
    public void vlozPostavu(Postava postava) {
        postavy.put(postava.getJmeno(), postava);
    }
    
    public Postava odeberPostavu(String jmeno) {
        return postavy.remove(jmeno);
    }
    
    /**
     * Metodou zjistíme, jestli je prostor/místnost zamknutá.
     * @return true pro zamknutý prostor
     * @return false pro odemknutý prostor
     */
    public boolean uzamcen(){
        return uzamcen;
    }
    
    /**
     * Metoda uzamkne/odemkne prostor/místnost
     */
    public void setUzamcen(boolean stav) {
        this.uzamcen = stav;
    }
    
    /*
    /*
     * Metoda přiřadí správný klíč potřebný k odemknutí prostoru/místnosti
     */
/*
    public void nastavKlic(Vec klic) {
        this.klic = klic;
    }
    */
    /*
     * Metoda vráti klíč
     */

    /*public Vec getKlic() {
        return klic;
    }
    */

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

    public Map<String, Vec> getVeci() {
        return veci;
    }
   
    
    
}