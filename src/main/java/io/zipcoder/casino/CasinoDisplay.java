package io.zipcoder.casino;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CasinoDisplay extends Display {

    //Casino casino = new Casino();


    public Parent createCasinoContent() {
        GridPane casinoGrid = super.createGrid();

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefRowCount(2);

        Text casinoBanner = new Text("We hope you enjoy your experience!");
        //casinoBanner.setTextAlignment(TextAlignment.CENTER);
        casinoBanner.setFont(Font.font ("Verdana", 25));

        Text casinoSubBanner = new Text("Please enter the below information to create a guest account");
        //casinoSubBanner.setTextAlignment(TextAlignment.CENTER);
        casinoSubBanner.setFont(Font.font ("Verdana", 15));

        Text enterNameField = new Text("Enter your full name");
        TextField nameField = new TextField();

        Text enterStartingBalanceField = new Text("Enter starting balance");
        TextField startingBalanceField = new TextField();

        Button submitInfo = new Button("Submit Information");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        Button btnEnterCasinoFloor = new Button("Enter Game Floor");
        btnEnterCasinoFloor.setMaxWidth(Double.MAX_VALUE);
        btnEnterCasinoFloor.setAlignment(Pos.CENTER);

        Button btnAddFunds = new Button("Add Funds");
        btnAddFunds.setAlignment(Pos.CENTER);
        btnAddFunds.setMaxWidth(Double.MAX_VALUE);

        Button btnExit = new Button("Exit Casino ");
        btnExit.setMaxWidth(Double.MAX_VALUE);


        casinoGrid.add(casinoBanner, 1,0, 4,1);
        casinoGrid.add(casinoSubBanner, 1,1, 3,1);
        casinoGrid.add(enterNameField, 1,2,1,1);
        casinoGrid.add(nameField, 1,3,1,1);
        casinoGrid.add(enterStartingBalanceField, 3,2,1,1);
        casinoGrid.add(startingBalanceField, 3,3,1,1);
        casinoGrid.add(submitInfo, 2, 4,1,1);


        submitInfo.setOnAction(e -> {
            String name = nameField.getText();
            Double amount = Double.parseDouble(startingBalanceField.getText());
            casinoGrid.add(areaInfo, 1,4,3,1);
            areaInfo.setText(Main.casino.initializeAccountCreation(name, amount));
            casinoGrid.getChildren().remove(submitInfo);
            casinoGrid.add(btnAddFunds, 1, 5,1,1);
            casinoGrid.add(btnEnterCasinoFloor, 2, 5,1,1);
            casinoGrid.add(btnExit, 3, 5,1,1);

        });

        btnEnterCasinoFloor.setOnAction(e -> {
            CasinoGames casinoGames = new CasinoGames(Main.casino.getGuest());
            casinoGames.runSelectedGames();
        });

        return casinoGrid;
    }

}
