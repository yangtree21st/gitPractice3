package io.zipcoder.casino;

import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.utilities.ImageUtilities;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;


public class HiLoDisplay extends Display {

    //current game
    HiLo displayGame;

    Text hiLoBanner = new Text("HiLo Game");
    TextArea areaInfo = new TextArea();
    GridPane hiLoGrid = createStandardGrid();
    TextField takeBetField =  new TextField("Place your bet. Min is 5.00");

    //Variables for gameplay
    Double betAmount = 0.0;
    private Card currentCard;
    private Card nextCard;
    boolean choseHigher;
    boolean nextCardIsLess;

    String betTooLowText = "Please bet the minimum amount[$5.00] or more.";
    String insuffiencientFundsText = "Insufficient funds in account balance.\n Please return to casino floor to add funds at the cashier.";

    public Parent createHiLoContent(Game game) {
        this.displayGame = (HiLo) game;
        Image bgImage = new Image("File:src/main/java/io/zipcoder/casino/Images/honors_spade-14.png",450,275,true,true);
        ImageView bgImageView = new ImageView(bgImage);
        hiLoGrid.add(bgImageView,2,2,4,4);
        //GridPane hiLoGrid = createStandardGrid();
        Button btnStart = new Button("Start HiLo Game");
        btnStart.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        hiLoGrid.add(btnStart,3,6,1,1);

        btnStart.setOnAction(e ->{
            hiLoGrid.getChildren().remove(btnStart);
            hiLoGrid.getChildren().remove(bgImageView);
            placeBetContent();
        });

        return hiLoGrid;
    }


    public Parent placeBetContent() {
        //GridPane placeBetGridPane = createStandardGrid();
        hiLoGrid.add(areaInfo,2,2,3,2);
        Button btnPlaceBet = new Button("Place Bet");
        btnPlaceBet.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        hiLoGrid.add(btnPlaceBet,3,6,1,1);
        hiLoGrid.add(takeBetField,2,5,3,1);

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
        hiLoGrid.getChildren().remove(areaInfo);

        Button btnHigher = new Button("Higher");
        btnHigher.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button btnLower = new Button("Lower");
        btnLower.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        hiLoGrid.add(btnHigher,2,6,1,1);
        hiLoGrid.add(btnLower,4,6,1,1);

        currentCard = displayGame.deal();
        nextCard = displayGame.deal();

        nextCardIsLess = displayGame.isLess(currentCard,nextCard);

        //Image image = new Image("File:src/main/java/io/zipcoder/casino/Images/2C.png",100,100,false,false);

        Image cardToDisplay = ImageUtilities.cardImageCreator(createCardKey(currentCard));
        ImageView imageViewToDisplay = new ImageView(cardToDisplay);
        hiLoGrid.add(imageViewToDisplay,3,3);

        btnHigher.setOnAction(e ->{
            choseHigher = true;
            hiLoGrid.getChildren().remove(imageViewToDisplay);
            hiLoGrid.getChildren().remove(btnHigher);
            hiLoGrid.getChildren().remove(btnLower);
            determineWinnerHiloGameContent();
        });

        btnLower.setOnAction(e ->{
            choseHigher = false;
            hiLoGrid.getChildren().remove(imageViewToDisplay);
            hiLoGrid.getChildren().remove(btnHigher);
            hiLoGrid.getChildren().remove(btnLower);
            determineWinnerHiloGameContent();

        });

        return hiLoGrid;

    }

    public Parent determineWinnerHiloGameContent(){

        Image cardToDisplay = ImageUtilities.cardImageCreator(createCardKey(currentCard));
        ImageView imageViewToDisplay = new ImageView(cardToDisplay);
        hiLoGrid.add(imageViewToDisplay,2,3);

        Image nextCardToDisplay = ImageUtilities.cardImageCreator(createCardKey(nextCard));
        ImageView imageViewToDisplay2 = new ImageView(nextCardToDisplay);
        hiLoGrid.add(imageViewToDisplay2,4,3);

        Button btnContinue = new Button("Continue");

        hiLoGrid.add(areaInfo,2,6,3,1);
        hiLoGrid.add(btnContinue,3,7,1,1);


        Text outcomeBanner = new Text();

        setExitButtonAccess(false);
        hiLoGrid.add(outcomeBanner,1,3,5,2);

        if(choseHigher == nextCardIsLess){
            outcomeBanner.setText("Sorry! You lose, try again!");
            outcomeBanner.setFont(Font.font ("Verdana", 50));
            outcomeBanner.setFill(Color.CRIMSON);
            areaInfo.setText(Main.casino.accountToString(Main.casino.getGuest()));
        }else{
            outcomeBanner.setText("You Win! Must Have Been Luck!");
            outcomeBanner.setFont(Font.font ("Verdana", 50));
            outcomeBanner.setFill(Color.BLACK);
            Main.casino.getGuest().addFunds(betAmount*1.25);
            areaInfo.setText(Main.casino.accountToString(Main.casino.getGuest()));
        }


        btnContinue.setOnAction( e ->{
            hiLoGrid.getChildren().remove(areaInfo);
            hiLoGrid.getChildren().remove(btnContinue);
            hiLoGrid.getChildren().remove(imageViewToDisplay);
            hiLoGrid.getChildren().remove(imageViewToDisplay2);
            hiLoGrid.getChildren().remove(outcomeBanner);
            placeBetContent();

        });


        return hiLoGrid;
    }

    /**
     *
     * @return
     */

    public GridPane createStandardGrid(){
        GridPane standardGridPane = super.createGameGrid();
        hiLoBanner.setFont(Font.font ("Verdana", 32));
        areaInfo.setPrefRowCount(3);
        areaInfo.setEditable(false);

        standardGridPane.add(hiLoBanner, 1,0, 5,1);


        btnForInstructions.setOnAction(e ->{
            setInstructionsForButton(returnInstructions());

        });
        return standardGridPane;
    }

    /**
     *
     * @param betAmount
     * @return
     */

    public boolean proceedWithHiloGameSwitch(Double betAmount){

        boolean enoughMoney = displayGame.enoughMoneyForBet(betAmount,displayGame.getPlayer());

        if(!enoughMoney && betAmount < 5.0){
            areaInfo.setText(betTooLowText + "\n" + insuffiencientFundsText);
            return false;
        }
        else if (!enoughMoney){
            areaInfo.setText(insuffiencientFundsText);
            return false;
        }else if(betAmount < 5.0){
            areaInfo.setText(betTooLowText);
            return false;
        }
        else if(betAmount > 5.0 && enoughMoney){
            displayGame.receiveBetFromPlayer(betAmount);
            return true;
        } else {
            areaInfo.setText("Unknown error - please let the floor manager know and enjoy a drink on us.");
            return  false;
        }
    }

    /**
     *
     * @param cardToDisplay
     * @return
     */

    public String createCardKey(Card cardToDisplay){
        String cardKeyString = cardToDisplay.getCardSuit().toString() + cardToDisplay.getValue().toString();
        return cardKeyString;
    }

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
