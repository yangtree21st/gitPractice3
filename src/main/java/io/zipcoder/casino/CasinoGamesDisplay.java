package io.zipcoder.casino;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CasinoGamesDisplay extends Display {

    public Parent createCasinoGamesContent() {
        GridPane casinoGamesGrid = super.createGrid();

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefRowCount(2);

        Text casinoGameBanner = new Text("Welcome to the Casino Floor");
        casinoGameBanner.setFont(Font.font ("Verdana", 60));


        Button btnEnter = new Button("Enter");
        btnEnter.setMaxWidth(Double.MAX_VALUE);

        casinoGamesGrid.add(btnEnter, 2, 2);
        casinoGamesGrid.add(casinoGameBanner, 0,0, 5,2);

        Text casinoGamesBanner = new Text("Would you like to play a casino game?");
        casinoGamesBanner.setFont(Font.font("Verdana", 44));

        Text casinoSubBanner = new Text("Please enter the below information to create a guest account");
        casinoSubBanner.setFont(Font.font("Verdana", 15));

        TextField nameField = new TextField();
        TextField startingBalanceField = new TextField();

        Button submitInfo = new Button("Submit Information ");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        Button btnEnterCasinoFloor = new Button("Enter Game Floor ");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        casinoGamesGrid.add(casinoGamesBanner, 0, 0, 4, 1);
        casinoGamesGrid.add(casinoSubBanner, 1, 1, 3, 1);
        casinoGamesGrid.add(nameField, 1, 2);
        casinoGamesGrid.add(startingBalanceField, 3, 2);
        casinoGamesGrid.add(submitInfo, 2, 4, 1, 1);


        submitInfo.setOnAction(e -> {
            String name = nameField.getText();
            Double amount = Double.parseDouble(startingBalanceField.getText());
            casinoGamesGrid.add(areaInfo, 1, 4, 3, 1);
            areaInfo.setText(Main.casino.initializeAccountCreation(name, amount));
            casinoGamesGrid.getChildren().remove(submitInfo);
            casinoGamesGrid.add(btnEnterCasinoFloor, 2, 5, 1, 1);

        });

        btnEnterCasinoFloor.setOnAction(e -> {
            CasinoGames casinoGames = new CasinoGames(Main.casino.getGuest());
            casinoGames.runSelectedGames();
        });

        return casinoGamesGrid;
    }
}
