package io.zipcoder.casino;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static Display startingDisplay = new Display();
    public static Stage mainStage;
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
        this.mainStage = stage;
        this.mainStage.setScene(new Scene(startingDisplay.createContent()));
        this.mainStage.show();
    }

    public static String consoleOrGUI(){
        return (casino.console.getStringInput("Would you like to gamble using the <Console> or <GUI> Interface").toLowerCase());
    }
}
