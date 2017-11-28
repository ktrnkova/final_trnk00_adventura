/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.VypisBatohu;
import GUI.VypisPostav;
import GUI.VypisVeci;
import GUI.VypisVychodu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author trnko
 */
public class Main extends Application{
    
    private TextArea centralText;
    private IHra hra;
    
    public void setHra (IHra hra){
        this.hra = hra;
    }
    
    private TextField zadejPrikazTextArea;
    
    private Mapa mapa;
    private MenuLista menuLista;
    private Stage stage;
    private VypisPostav vypisPostav;
    private VypisVychodu vypisVychodu;
    private VypisBatohu vypisBatohu;
    private VypisVeci vypisVeci;
    private Button button;
    private Button button2;
    
    @Override
    public void start (Stage primaryStage){
        this.setStage(primaryStage);
        
        hra = new Hra();
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        vypisVychodu = new VypisVychodu (hra);
        vypisPostav = new VypisPostav (hra);
        vypisBatohu = new VypisBatohu (hra);
        vypisVeci = new VypisVeci (hra);
        button = new Button ("Jdi");
        button2 = new Button ("Mluv s");
        
        BorderPane borderPane = new BorderPane();
        BorderPane bocniLista = new BorderPane();
        BorderPane levaLista = new BorderPane();
        BorderPane levaStrana = new BorderPane();
        BorderPane pravaLista = new BorderPane();
        BorderPane vychodyLista = new BorderPane();
        BorderPane mluvLista = new BorderPane();
        
        //Text s průběhem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        // false říká, že centrální text má vypnutou editaci
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        //nacteni ComboBoxu
        initComboBox();
        initComboBox2();
        
        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label ("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial",FontWeight.BOLD, 14));
        
        //textArea do ktereho piseme prikazy
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle (ActionEvent event){
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                zadejPrikazTextArea.setText("");
                
                if (hra.konecHry()){
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });
        
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        //bocni lista
        bocniLista.setTop(vypisVeci);
        bocniLista.setCenter(pravaLista);
        
        pravaLista.setTop(vychodyLista);
        pravaLista.setCenter(mluvLista);
        
        mluvLista.setCenter(vypisPostav);
        mluvLista.setTop(button2);
        
        vychodyLista.setBottom(vypisVychodu);
        vychodyLista.setTop(button);
        
        levaLista.setBottom(vypisBatohu);
        vypisBatohu.setOrientation(Orientation.HORIZONTAL);
        levaLista.setTop(mapa);
        levaStrana.setLeft(levaLista);
        
        borderPane.setLeft(levaStrana);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        borderPane.setRight(bocniLista);
       
        
        
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Adventura");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public static void main (String [] args){
        if (args.length == 0){
            launch(args);
        }
        else{
            if (args[0].equals("-txt")){
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }
    
    private void initComboBox() {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value1 = (String) vypisVychodu.getComboBox().getValue();
                String prikaz1 = "jdi " + value1;
                String text1 = hra.zpracujPrikaz(prikaz1);

                //Zpracovani textu na centralni panel
                centralText.appendText("\n\n" + prikaz1 + "\n");
                centralText.appendText("\n\n" + text1 + "\n");

                zadejPrikazTextArea.setText("");

                if (hra.konecHry()) {
                    centralText.appendText(hra.vratEpilog());
                    zadejPrikazTextArea.setEditable(false);
                }
            }
        });
    } 
    
    /**
     * Nastaveni ComboBoxu 2 který obsahuje postavy
     */
    private void initComboBox2() {
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value2 = (String) vypisPostav.getComboBox2().getValue();
                String prikaz2 = "mluv " + value2;
                String text2 = hra.zpracujPrikaz(prikaz2);

                //Zpracovani textu na centralni panel
                centralText.appendText("\n\n" + prikaz2 + "\n");
                centralText.appendText("\n\n" + text2 + "\n");

                zadejPrikazTextArea.setText("");

                if (hra.konecHry()) {
                    centralText.appendText(hra.vratEpilog());
                    zadejPrikazTextArea.setEditable(false);
                }
            }
        });
    } 
     
    /**
     * Metoda pro nastaveni nove hry
     * @param hra 
     */
    public void newGame(IHra hra) {
        this.mapa.newGame(hra);
        this.vypisBatohu.newGame(hra);
        this.vypisVeci.newGame(hra);
        this.vypisVychodu.novaHra(hra);
        this.vypisPostav.newGame(hra);
        this.zadejPrikazTextArea.setEditable(true);
        
    }
    
    
    
}

