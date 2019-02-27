package io.zipcoder.casino.Displays;

import io.zipcoder.casino.Display;
import io.zipcoder.casino.Games.Craps;
import io.zipcoder.casino.Interfaces.Game;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CrapsDisplay extends Display {

    Game displayGame;

    Text crapsBanner = new Text("Craps Game");
    TextArea areaInfo = new TextArea();


    public Parent createCrapsContent(Game game){
        this.displayGame = (Craps) game;

        GridPane crapsGrid = createStandardGrid();
        GridPane centerGrid = createCenterGrid();

        crapsGrid.add(centerGrid, 0,4);
        return crapsGrid;
    }

    public GridPane createCenterGrid(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        /*
        //sets the padding between cells
        grid.setVgap(10);
        grid.setHgap(10);
        //sets padding
        grid.setPadding(new Insets(15, 15, 15, 15));
        */


        return grid;
    }

    private GridPane createStandardGrid(){
        GridPane crapsStandardGrid = super.createGameGrid();

        crapsStandardGrid.add(crapsBanner, 1,0, 5,1);
        crapsStandardGrid.add(areaInfo,1,1,3,4);

        btnForInstructions.setOnAction(e ->{
            setInstructionsForButton(returnInstructions());

        });

        return crapsStandardGrid;

    }


    private String returnInstructions(){
        return "Craps is a dice game involving the rolling of two dice. You win or lose money depending on what the result of the dice tosses are.\n" +
                "The game is split into two phases: the first roll, called the Come-Out roll, and every subsequent roll, which are called Point rolls.\n\n" +
                "There are two main bets in the game of Craps - Pass and Don’t Pass, which both pay even money. Let’s explain the Pass bet first.\n\n" +
                "Let’s say you make a Pass bet. The first thing you do is pick up the two dice and roll them for the come-out roll. If you roll a 7 or\n" +
                "an 11 on your come-out roll (this is called rolling a Natural) you win. If you roll a 2, 3, or 12 (called rolling Craps) you lose.\n\n" +
                "If you roll anything else (a 4, 5, 6, 8, 9, or 10) that specific roll result becomes the “Point”, and you enter the second phase of the\n" +
                "game. Once you establish a Point, your goal becomes to roll the Point again before rolling a 7. If you roll the Point, you win, if you\n" +
                "roll a 7 (called a Seven-Out), you lose, if you roll anything else, nothing happens and you roll again.\n\n" +
                "Now let’s explain the Don’t Pass bet.\n\n" +
                "The Don’t Pass bet is essentially the opposite of the Pass bet. You win when the Pass bet loses, and you lose when the Pass bet wins.\n" +
                "Thus, the ways of winning a Don’t Pass bet are either rolling Craps or getting a Seven-Out. The ways of losing are rolling a Natural\n" +
                "or rolling the Point. The only difference is that if you roll a 12 on the come-out roll, the round is over but you neither win nor lose\n" +
                "(in other words, your bet stays where it is, and you roll the come-out roll again). Rolling a 12 first on a Don't Pass bet is called a Push.\n\n" +
                "After a round is over (by rolling a Natural, rolling your Point, rolling Craps, or getting a Seven-Out) you have the choice to try again.\n" +
                "Each time you try again, you can change your bet type and amount.\n\n";
    }
}
