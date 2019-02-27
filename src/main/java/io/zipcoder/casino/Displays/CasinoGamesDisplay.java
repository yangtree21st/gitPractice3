package io.zipcoder.casino.Displays;

import io.zipcoder.casino.Display;
import io.zipcoder.casino.Games.Craps;
import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CasinoGamesDisplay extends Display {

    public Parent createCasinoGamesContent() {
        GridPane casinoGamesGrid = super.createGrid();
        casinoGamesGrid.getColumnConstraints().remove(1);

        Text casinoGamesBanner = new Text("Which game would you like to play?");
        casinoGamesBanner.setFont(Font.font("Verdana", 30));


        Button btnBlackJack = new Button("BlackJack");
        btnBlackJack.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnBlackJack.setDisable(true);

        Button btnCraps = new Button("Craps");
        btnCraps.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnCraps.setDisable(true);

        Button btnGoFish = new Button("Go Fish");
        btnGoFish.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnGoFish.setDisable(true);

        Button btnHilo = new Button("Hi-Lo");
        btnHilo.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button btnExit = new Button("Casino Floor");
        btnExit.setMaxWidth(Double.MAX_VALUE);

        casinoGamesGrid.add(casinoGamesBanner, 0, 0, 4, 2);
        casinoGamesGrid.add(btnBlackJack, 0, 2, 1, 1);
        casinoGamesGrid.add(btnCraps, 1, 2,1,1);
        casinoGamesGrid.add(btnGoFish, 2, 2,1,1);
        casinoGamesGrid.add(btnHilo, 3, 2,1,1);
        casinoGamesGrid.add(btnExit, 1, 4, 2, 1);


        btnExit.setOnAction(e -> {
            CasinoDisplay casinoDisplay = new CasinoDisplay();
            Main.mainStage.setScene(new Scene(casinoDisplay.createSecondaryCasinoContent()));
            Main.mainStage.show();
        });

        btnBlackJack.setOnAction(e -> {

        });

        btnCraps.setOnAction(e -> {
            Craps crapsGames = new Craps(Main.casino.getGuest());
            CrapsDisplay crapsDisplay = new CrapsDisplay();
            Main.mainStage.setScene(new Scene(crapsDisplay.createCrapsContent(crapsGames)));
            Main.mainStage.show();
        });

        btnGoFish.setOnAction(e -> {

        });

        btnHilo.setOnAction(e -> {
            HiLo hiLoGames = new HiLo(Main.casino.getGuest());
            HiLoDisplay hiLoDisplay = new HiLoDisplay();
            Main.mainStage.setScene(new Scene(hiLoDisplay.createHiLoContent(hiLoGames)));
            Main.mainStage.show();
        });

        return casinoGamesGrid;
    }
}
