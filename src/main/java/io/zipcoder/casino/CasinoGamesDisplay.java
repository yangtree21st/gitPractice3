package io.zipcoder.casino;

import io.zipcoder.casino.Games.Craps;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CasinoGamesDisplay extends Display {

    public Parent createCasinoGamesContent() {
        GridPane casinoGamesGrid = super.createGrid();
        casinoGamesGrid.getColumnConstraints().remove(1);

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefRowCount(2);

        Text casinoGamesBanner = new Text("Which game would you like to play?");
        casinoGamesBanner.setFont(Font.font("Verdana", 30));


        Button btnBlackJack = new Button("BlackJack");
        btnBlackJack.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        //btnBlackJack.setAlignment(Pos.CENTER);

        Button btnCraps = new Button("Craps");
        btnCraps.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button btnGoFish = new Button("Go Fish");
        btnGoFish.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button btnHilo = new Button("Hi-Lo");
        //btnHilo.setAlignment(Pos.CENTER);
        btnHilo.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button btnExit = new Button("Exit Casino");
        btnExit.setMaxWidth(Double.MAX_VALUE);

        casinoGamesGrid.add(casinoGamesBanner, 0, 0, 4, 2);
        casinoGamesGrid.add(btnBlackJack, 0, 2, 1, 1);
        casinoGamesGrid.add(btnCraps, 1, 2,1,1);
        casinoGamesGrid.add(btnGoFish, 2, 2,1,1);
        casinoGamesGrid.add(btnHilo, 3, 2,1,1);
        casinoGamesGrid.add(btnExit, 1, 4, 2, 1);


        btnExit.setOnAction(e -> {
            Stage casinoStage = super.setExitAction(btnExit);
            casinoStage.close();
        });

        btnBlackJack.setOnAction(e -> {

        });

        btnCraps.setOnAction(e -> {
            Craps crapsGames = new Craps(Main.casino.getGuest());
            crapsGames.playCrapsGUIFullGame();
        });

        btnGoFish.setOnAction(e -> {

        });

        btnHilo.setOnAction(e -> {

        });

        return casinoGamesGrid;
    }
}
