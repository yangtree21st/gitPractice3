package io.zipcoder.casino;

import io.zipcoder.casino.Interfaces.Game;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HiLoDisplay extends Display {

    Game displayGame;

    Text hiLoBanner = new Text("HiLo Game");
    TextArea areaInfo = new TextArea();
    GridPane hiLoGrid = createStandardGrid();

    public Parent createHiLoContent(Game game) {
        this.displayGame = game;
        //GridPane hiLoGrid = createStandardGrid();
        Button btnStart = new Button("Start HiLo Game");
        btnStart.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        hiLoGrid.add(btnStart,3,6,1,1);

        btnStart.setOnAction(e ->{
            //Main.mainStage.setScene(new Scene(placeBetContent()));
            hiLoGrid.getChildren().remove(btnStart);
            //hiLoGrid.getChildren().retainAll(hiLoBanner,btnExit,btnForInstructions,areaInfo);
            placeBetContent();
        });

        return hiLoGrid;
    }


    public Parent placeBetContent() {
        //GridPane placeBetGridPane = createStandardGrid();

        Button btnPlaceBet = new Button("Place Bet");
        btnPlaceBet.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        hiLoGrid.add(btnPlaceBet,3,6,1,1);

        btnPlaceBet.setOnAction(e ->{
            Main.mainStage.setScene(new Scene(placeBetContent()));

        });

        return hiLoGrid;

    }

    public GridPane createStandardGrid(){
        GridPane standardGridPane = super.createGameGrid();
        hiLoBanner.setFont(Font.font ("Verdana", 32));
        areaInfo.setPrefRowCount(3);

        standardGridPane.add(hiLoBanner, 1,0, 5,1);
        standardGridPane.add(areaInfo,2,1,3,4);

        btnForInstructions.setOnAction(e ->{
            setInstructionsForButton(returnInstructions());

        });
        return standardGridPane;
    }

    private String returnInstructions(){
        return "Hi-Lo, or High-Low, is a fairly simple card game. It uses a standard deck of 52 cards, " +
                "and it has players guess whether a certain card is higher or lower than one showing on the table. " +
                "There are variations for drinking and gambling, but it's a pretty standard and simple game.\n\n" +

                "Technically, the game can have any number of players, but at any given time, " +
                "there are only two players actually playing. The dealer, who is in control of the deck," +
                "and the player, who is responsible for guessing the values of cards, are the only participants.\n\n" +

                "After shuffling and cutting the deck, the dealer places one card face-down in front of the player, " +
                "then another card face-up.The rest of the deck, they set aside while the player guesses the value.\n\n" +

                "After the cards are down, the player places his initial bet. The house matches that bet into the pot.\n"+
                "When the player guesses, he wins or loses the pot depending on the outcome of his guess.\n" +
                "After that round, the player can pass the bet to another player, or go double or nothing on the next bet";

    }


}
