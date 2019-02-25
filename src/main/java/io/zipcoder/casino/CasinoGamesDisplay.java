package io.zipcoder.casino;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CasinoGamesDisplay extends Display {

    public Parent createCasinoGamesContent() {
        GridPane casinoGamesGrid = super.createGrid();

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefRowCount(2);

        Text casinoGamesBanner = new Text("Which game would you like to play?");
        casinoGamesBanner.setFont(Font.font("Verdana", 40));


        Button btnBlackJack = new Button("BlackJack");
        btnBlackJack.setMaxWidth(Double.MAX_VALUE);
        //btnBlackJack.setAlignment(Pos.CENTER);

        Button btnCraps = new Button("Craps");
        btnCraps.setMaxWidth(Double.MAX_VALUE);

        Button btnGoFish = new Button("Go Fish");
        btnGoFish.setMaxWidth(Double.MAX_VALUE);

        Button btnHilo = new Button("Hi-Lo");
        //btnHilo.setAlignment(Pos.CENTER);
        btnHilo.setMaxWidth(Double.MAX_VALUE);

        Button btnExit = new Button("Exit Casino");
        btnExit.setMaxWidth(Double.MAX_VALUE);

        casinoGamesGrid.add(casinoGamesBanner, 0, 0, 4, 1);
        casinoGamesGrid.add(btnBlackJack, 0, 2, 1, 1);
        casinoGamesGrid.add(btnCraps, 1, 2);
        casinoGamesGrid.add(btnGoFish, 2, 2);
        casinoGamesGrid.add(btnHilo, 3, 2);
        casinoGamesGrid.add(btnExit, 2, 4, 2, 1);


        btnExit.setOnAction(e -> {
            Stage casinoStage = super.setExitAction(btnExit);
            casinoStage.close();
        });
/*
        submitInfo.setOnAction(e -> {

        });

        btnEnterCasinoFloor.setOnAction(e -> {
            CasinoGames casinoGames = new CasinoGames(Main.casino.getGuest());
            casinoGames.runSelectedGames();
        });
        */

        return casinoGamesGrid;
    }
}
