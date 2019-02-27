package io.zipcoder.casino.Displays;


import io.zipcoder.casino.CasinoGames;
import io.zipcoder.casino.Display;
import io.zipcoder.casino.Displays.CashierDisplay;
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
import javafx.stage.Stage;

public class CasinoDisplay extends Display {

    Text casinoBanner = new Text("We hope you enjoy your experience!");

    Text casinoSubBanner = new Text("Please enter the below information to create a guest account");

    Text enterNameField = new Text("Enter your full name");
    TextField nameField = new TextField();

    Text enterStartingBalanceField = new Text("Enter starting balance");
    TextField startingBalanceField = new TextField();

    TextArea areaInfo = new TextArea();


    public Parent createCasinoContent() {
        GridPane casinoGrid = createStandardGridPane();



        Button submitInfo = new Button("Submit Information");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        casinoGrid.add(submitInfo, 2, 4,1,1);


        submitInfo.setOnAction(e -> {
            String nameInput = nameField.getText();
            Double balanceInput = Double.parseDouble(startingBalanceField.getText());
            Main.casino.initializeAccountCreation(nameInput, balanceInput);

            Main.mainStage.setScene(new Scene(createSecondaryCasinoContent()));
            Main.mainStage.show();
        });



        return casinoGrid;
    }

    public Parent createSecondaryCasinoContent(){

        GridPane casinoGrid = createStandardGridPane();

        areaInfo.setPrefRowCount(2);

        Button btnEnterCasinoFloor = new Button("Enter Game Floor");
        btnEnterCasinoFloor.setMaxWidth(Double.MAX_VALUE);
        btnEnterCasinoFloor.setAlignment(Pos.CENTER);

        Button btnCashier = new Button("Cashier");
        btnCashier.setAlignment(Pos.CENTER);
        btnCashier.setMaxWidth(Double.MAX_VALUE);
        btnCashier.setDisable(true);

        Button btnExitCompletely = new Button("Exit Casino ");
        btnExitCompletely.setMaxWidth(Double.MAX_VALUE);

        //getUserInput

        areaInfo.setText(Main.casino.accountToString(Main.casino.getGuest()));

        nameField.setEditable(false);
        startingBalanceField.setEditable(false);
        areaInfo.setEditable(false);

        casinoGrid.add(areaInfo, 1,4,3,1);
        casinoGrid.add(btnCashier, 1, 5,1,1);
        casinoGrid.add(btnEnterCasinoFloor, 2, 5,1,1);
        casinoGrid.add(btnExitCompletely, 3, 5,1,1);

        btnExitCompletely.setOnAction(e -> {
            Stage casinoStage = super.setExitAction(btnExitCompletely);
            casinoStage.close();
        });

        btnCashier.setOnAction(e -> {
            CashierDisplay cashierDisplay = new CashierDisplay();
            Stage newStage = new Stage();
            Scene newScene = new Scene(cashierDisplay.createCashierContent(Main.casino.getGuest()));
            newStage.setScene(newScene);
            newStage.show();
        });

        btnEnterCasinoFloor.setOnAction(e -> {
            CasinoGames casinoGames = new CasinoGames(Main.casino.getGuest());
            casinoGames.runSelectedGUIGames();
        });

        return casinoGrid;
    }

    public GridPane createStandardGridPane(){
        GridPane standardGridPane = super.createGrid();

        //casinoBanner.setTextAlignment(TextAlignment.CENTER);
        casinoBanner.setFont(Font.font ("Verdana", 35));

        //casinoSubBanner.setTextAlignment(TextAlignment.CENTER);
        casinoSubBanner.setFont(Font.font ("Verdana", 15));

        standardGridPane.add(casinoBanner, 0,0, 5,1);
        standardGridPane.add(casinoSubBanner, 1,1, 3,1);
        standardGridPane.add(enterNameField, 1,2,1,1);
        standardGridPane.add(nameField, 1,3,1,1);
        standardGridPane.add(enterStartingBalanceField, 3,2,1,1);
        standardGridPane.add(startingBalanceField, 3,3,1,1);

        return standardGridPane;
    }

}
