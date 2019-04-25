package io.zipcoder.casino;

import io.zipcoder.casino.utilities.ImageUtilities;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.Function;

public class Main extends Application {
    public static Display startingDisplay = new Display();
    public static Stage mainStage;
    public static Casino casino = new Casino();
    public ImageUtilities imageUtilities;

    public static void main(String[] args) {
        String choice = consoleOrGUI();
        if (choice.equals("retro")) {
            casino.startCasinoExperience();
        } else {
            launch(args);
        }
    }

    public static String consoleOrGUI() {

        while (true) {
            String choice = (Casino.console.getStringInput("Would you like to gamble using the <Retro> or <Modern> Interface").toLowerCase());
            if (choice.equals("modern") || choice.equals("retro")) {
                return choice;
            } else {
                return Casino.console.getStringInput("Incorrect input, please try again. <Retro> or <Modern>").toLowerCase();
            }
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        imageUtilities = new ImageUtilities();
        mainStage = stage;
        mainStage.setTitle("Casino Royale");
        mainStage.setScene(new Scene(startingDisplay.createContent()));
        mainStage.show();

    }


}
