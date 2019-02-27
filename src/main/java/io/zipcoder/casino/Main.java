package io.zipcoder.casino;

import io.zipcoder.casino.utilities.ImageUtilities;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static Display startingDisplay = new Display();
    public static Stage mainStage;
    public static Casino casino = new Casino();
    public ImageUtilities imageUtilities ;

    public static void main(String[] args) {
        String choice = consoleOrGUI();
        if(choice.equals("retro")) {
            casino.startCasinoExperience();
        }else{
            launch(args);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        imageUtilities= new ImageUtilities();
        this.mainStage = stage;
        mainStage.setTitle("Casino Royale");
        this.mainStage.setScene(new Scene(startingDisplay.createContent()));
        this.mainStage.show();

    }

    public static String consoleOrGUI(){

        while(true) {
            String choice = (casino.console.getStringInput("Would you like to gamble using the <Retro> or <Modern> Interface").toLowerCase());
            if (choice.equals("modern") || choice.equals("retro")) {
                return choice;
            } else {
                return casino.console.getStringInput("Incorrect input, please try again. <Retro> or <Modern>").toLowerCase();
            }
        }

    }

}
