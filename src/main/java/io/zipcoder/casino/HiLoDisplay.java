package io.zipcoder.casino;

import io.zipcoder.casino.Games.CardGame;
import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Interfaces.Game;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HiLoDisplay extends Display {

    HiLo displayGame;

    Text hiLoBanner = new Text("HiLo Game");
    TextArea areaInfo = new TextArea();
    GridPane hiLoGrid = createStandardGrid();
    TextField takeBetField =  new TextField("Place your bet. Minimum is 5.00");
    Double betAmount = 0.0;

    String betTooLowText = "Please bet the minimum amount[$5.00] or more.";
    String insuffiencientFundsText = "Insufficient funds in account balance.\n Please return to casino floor to add funds at the cashier.";

    public Parent createHiLoContent(Game game) {
        this.displayGame = (HiLo) game;
        //GridPane hiLoGrid = createStandardGrid();
        Button btnStart = new Button("Start HiLo Game");
        btnStart.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        hiLoGrid.add(btnStart,3,6,1,1);

        btnStart.setOnAction(e ->{
            hiLoGrid.getChildren().remove(btnStart);
            placeBetContent();
        });

        return hiLoGrid;
    }


    public Parent placeBetContent() {
        //GridPane placeBetGridPane = createStandardGrid();

        Button btnPlaceBet = new Button("Place Bet");
        btnPlaceBet.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        hiLoGrid.add(btnPlaceBet,2,6,3,1);
        hiLoGrid.add(takeBetField,3,5,1,1);

        btnPlaceBet.setOnAction(e ->{
            betAmount = Double.parseDouble(takeBetField.getText());

            if(proceedWithHiloGameSwitch(betAmount)){
                hiLoGrid.getChildren().remove(btnPlaceBet);
                hiLoGrid.getChildren().remove(takeBetField);
                beginHiLoGamePlayContent();
            }

        });

        return hiLoGrid;

    }

    public Parent beginHiLoGamePlayContent(){

        super.setExitButtonAccess(true);

        Button btnHigher = new Button("Higher");
        btnHigher.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button btnLower = new Button("Lower");
        btnLower.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        hiLoGrid.add(btnHigher,2,6,1,1);
        hiLoGrid.add(btnLower,4,6,1,1);

        btnHigher.setOnAction(e ->{

        });

        btnLower.setOnAction(e ->{

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

    public boolean proceedWithHiloGameSwitch(Double betAmount){
        if(betAmount > displayGame.checkPlayersBalance(displayGame.getPlayer()) && betAmount < 5.0){
            areaInfo.setText(betTooLowText + "\n" + insuffiencientFundsText);
            return false;
        }
        else if (displayGame.checkPlayersBalance(displayGame.getPlayer()) < betAmount){
            areaInfo.setText(insuffiencientFundsText);
            return false;
        }else if(betAmount < 5.0){
            areaInfo.setText(betTooLowText);
            return false;
        }
        else if(betAmount > 5.0 && displayGame.checkPlayersBalance(displayGame.getPlayer()) >= betAmount){
            displayGame.receiveBetFromPlayer(betAmount);
            return true;
        } else {
            areaInfo.setText("Unknown error - please let the floor manager know and enjoy a drink on us.");
            return  false;
        }
    }

    public String return
    /*
    public Double controlDoubleInput(){
        Double betAmount = Double.parseDouble(takeBetField.getText());
        return  0.0;
    }
    */

    private String returnInstructions(){
        return "Hi-Lo, or High-Low, is a fairly simple card game. It uses a standard deck of 52 cards,\n" +
                "and it has players guess whether a certain card is higher or lower than one showing on the table.\n" +
                "There are variations for drinking and gambling, but it's a pretty standard and simple game.\n\n" +

                "Technically, the game can have any number of players, but at any given time,\n" +
                "there are only two players actually playing. The dealer, who is in control of the deck,\n" +
                "and the player, who is responsible for guessing the values of cards, are the only participants.\n\n" +

                "After shuffling and cutting the deck, the dealer places one card face-down in front of the player, " +
                "then another card face-up.The rest of the deck, they set aside while the player guesses the value.\n\n" +

                "After the cards are down, the player places his initial bet. The house matches that bet into the pot.\n"+
                "When the player guesses, he wins or loses the pot depending on the outcome of his guess.\n" +
                "After that round, the player can pass the bet to another player, or go double or nothing on the next bet\n";

    }


}
