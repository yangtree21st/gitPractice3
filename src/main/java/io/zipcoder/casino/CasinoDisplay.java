package io.zipcoder.casino;


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


    public Parent createCasinoContent() {
        GridPane casinoGrid = createStandardGridPane();

        //casinoBanner.setTextAlignment(TextAlignment.CENTER);
        casinoBanner.setFont(Font.font ("Verdana", 35));

        //casinoSubBanner.setTextAlignment(TextAlignment.CENTER);
        casinoSubBanner.setFont(Font.font ("Verdana", 15));

        Button submitInfo = new Button("Submit Information");
        submitInfo.setMaxWidth(Double.MAX_VALUE);

        casinoGrid.add(submitInfo, 2, 4,1,1);


        submitInfo.setOnAction(e -> {
            Main.mainStage.setScene(new Scene(createSecondaryCasinoContent()));
            Main.mainStage.show();
        });



        return casinoGrid;
    }

    public Parent createSecondaryCasinoContent(){

        GridPane casinoGrid = createStandardGridPane();

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefRowCount(2);

        Button btnEnterCasinoFloor = new Button("Enter Game Floor");
        btnEnterCasinoFloor.setMaxWidth(Double.MAX_VALUE);
        btnEnterCasinoFloor.setAlignment(Pos.CENTER);

        Button btnCashier = new Button("Cashier");
        btnCashier.setAlignment(Pos.CENTER);
        btnCashier.setMaxWidth(Double.MAX_VALUE);

        Button btnExit = new Button("Exit Casino ");
        btnExit.setMaxWidth(Double.MAX_VALUE);

        //getUserInput
        String name = nameField.getText();
        Double amount = Double.parseDouble(startingBalanceField.getText());

        areaInfo.setText(Main.casino.initializeAccountCreation(name, amount));


        nameField.setEditable(false);
        startingBalanceField.setEditable(false);
        areaInfo.setEditable(false);

        casinoGrid.add(areaInfo, 1,4,3,1);
        casinoGrid.add(btnCashier, 1, 5,1,1);
        casinoGrid.add(btnEnterCasinoFloor, 2, 5,1,1);
        casinoGrid.add(btnExit, 3, 5,1,1);

        btnExit.setOnAction(e -> {
            Stage casinoStage = super.setExitAction(btnExit);
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

        standardGridPane.add(casinoBanner, 0,0, 5,1);
        standardGridPane.add(casinoSubBanner, 1,1, 3,1);
        standardGridPane.add(enterNameField, 1,2,1,1);
        standardGridPane.add(nameField, 1,3,1,1);
        standardGridPane.add(enterStartingBalanceField, 3,2,1,1);
        standardGridPane.add(startingBalanceField, 3,3,1,1);

        return standardGridPane;
    }

}
