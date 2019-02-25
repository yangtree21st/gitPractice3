package io.zipcoder.casino;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CasinoDisplay extends Display {

    //Casino casino = new Casino();


    public Parent createCasinoContent() {
        GridPane casinoGrid = super.createGrid();

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefRowCount(2);

        Text casinoBanner = new Text("We hope you enjoy your experience!");
        casinoBanner.setFont(Font.font ("Verdana", 42));

        Text casinoSubBanner = new Text("Please enter the below information to create a guest account");
        casinoSubBanner.setFont(Font.font ("Verdana", 15));

        TextField nameField = new TextField();
        TextField startingBalanceField = new TextField();

        Button submitInfo = new Button("Submit Information ");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        Button btnEnterCasinoFloor = new Button("Enter Game Floor ");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        casinoGrid.add(casinoBanner, 0,0, 4,1);
        casinoGrid.add(casinoSubBanner, 1,1, 3,1);
        casinoGrid.add(nameField, 1,2);
        casinoGrid.add(startingBalanceField, 3,2);
        casinoGrid.add(submitInfo, 2, 4,1,1);


        submitInfo.setOnAction(e -> {
            String name = nameField.getText();
            Double amount = Double.parseDouble(startingBalanceField.getText());
            casinoGrid.add(areaInfo, 1,4,3,1);
            areaInfo.setText(Main.casino.initializeAccountCreation(name, amount));
            casinoGrid.getChildren().remove(submitInfo);
            casinoGrid.add(btnEnterCasinoFloor, 2, 5,1,1);

        });

        btnEnterCasinoFloor.setOnAction(e -> {
            CasinoGames casinoGames = new CasinoGames(Main.casino.getGuest());
            casinoGames.runSelectedGames();
        });

        return casinoGrid;
    }

}
