package io.zipcoder.casino;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static Display startingDisplay = new Display();
    public static CasinoGamesDisplay casinoGamesDisplay = new CasinoGamesDisplay();
    public static Stage mainStage;
    public static Stage secondStage = new Stage();
    public static Casino casino = new Casino();

    public static void main(String[] args) {
        String choice = consoleOrGUI();
        if(choice.equals("console")) {
            casino.startCasinoExperience();
        }else{
            launch(args);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
/*
        this.mainStage = stage;
        mainStage.setTitle("Casino Royale");
        this.mainStage.setScene(new Scene(startingDisplay.createContent()));
        this.mainStage.show();

        this.mainStage = stage;
        this.mainStage.setTitle("Casino Royale");
        this.mainStage.setScene(new Scene(casinoDisplay.createCasinoContent()));
        this.mainStage.show();
*/
        this.mainStage = stage;
        this.mainStage.setTitle("Casino Royale");
        this.mainStage.setScene(new Scene(casinoGamesDisplay.createCasinoGamesContent()));
        this.mainStage.show();


    }

    public static String consoleOrGUI(){

        while(true) {
            String choice = (casino.console.getStringInput("Would you like to gamble using the <Console> or <GUI> Interface").toLowerCase());
            if (choice.equals("gui") || choice.equals("console")) {
                return choice;
            } else {
                return casino.console.getStringInput("Incorrect input, please try again. <Console> or <GUI>").toLowerCase();
            }
        }

    }
}
