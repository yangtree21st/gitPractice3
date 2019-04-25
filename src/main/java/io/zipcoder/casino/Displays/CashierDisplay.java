package io.zipcoder.casino.Displays;

import io.zipcoder.casino.Display;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Main;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CashierDisplay extends Display {
    TextArea areaInfo = new TextArea();
    GridPane casinoGrid = super.createGrid();
    Button btnExit = new Button("Exit");

    public Parent createCashierContent(Guest guest) {

        areaInfo.setPrefRowCount(2);

        Text cashierBanner = new Text("Please select an option");
        cashierBanner.setTextAlignment(TextAlignment.CENTER);
        cashierBanner.setFont(Font.font("Verdana", 25));

        Button btnAddFunds = new Button("Add Funds");
        btnAddFunds.setAlignment(Pos.CENTER);

        Button btnRemoveFunds = new Button("Remove Funds");
        btnRemoveFunds.setAlignment(Pos.CENTER);

        Button btnCashout = new Button("Cashout");
        btnCashout.setAlignment(Pos.CENTER);


        Text amountText = new Text("Amount to withdraw or deposit");
        amountText.setFont(Font.font("Verdana", 20));


        casinoGrid.add(cashierBanner, 0, 0, 5, 1);
        casinoGrid.add(btnAddFunds, 1, 1, 1, 1);
        casinoGrid.add(btnRemoveFunds, 2, 1, 1, 1);
        casinoGrid.add(btnCashout, 3, 1, 1, 1);
        TextField amountField = new TextField();
        casinoGrid.getChildren().remove(btnExit);
        casinoGrid.add(areaInfo, 1, 2, 3, 2);
        casinoGrid.add(amountText, 1, 4, 3, 1);
        casinoGrid.add(amountField, 1, 5, 3, 1);
        areaInfo.setText(Main.casino.accountToString(guest));
        casinoGrid.add(btnExit, 2, 6);


        btnExit.setOnAction(e -> {
            CasinoDisplay casinoDisplay = new CasinoDisplay();
            Main.mainStage.setScene(new Scene(casinoDisplay.createSecondaryCasinoContent()));
            Main.mainStage.show();
        });

        btnAddFunds.setOnAction(e -> {//TODO - if amount field is empty, then don't call method
            double amountToRemove = Double.parseDouble(amountField.getText());
            guest.addFunds(amountToRemove);
            areaInfo.setText(Main.casino.accountToString(guest));
            amountField.clear();
        });

        btnRemoveFunds.setOnAction(e -> {//TODO - if amount field is empty, then don't call method
            double amountToRemove = Double.parseDouble(amountField.getText());
            //TODO - if amount field is more than balance then don't allow it to take the amount
            guest.removeFunds(amountToRemove);
            areaInfo.setText(Main.casino.accountToString(guest));
            amountField.clear();
        });

        btnCashout.setOnAction(e -> {
            guest.removeFunds(guest.getAccountBalance());
            areaInfo.setText(Main.casino.accountToString(guest));
        });

        return casinoGrid;
    }

}
