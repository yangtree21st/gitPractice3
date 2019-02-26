package io.zipcoder.casino;

import io.zipcoder.casino.Interfaces.Game;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HiLoDisplay extends Display {

    Game displayGame;

    Text hiLoBanner = new Text("HiLo Game");
    TextArea areaInfo = new TextArea();
    Button btnExit = new Button("Exit");
    Button btnForInstructions = new Button("Instructions");

    public Parent createHiLoContent(Game game) {
        this.displayGame = game;
        GridPane hiLoGrid = createStandardGrid();




        return hiLoGrid;
    }


    public Parent placeBetContent() {
        GridPane placeBetGridPane = createStandardGrid();

        return placeBetGridPane;

    }

    public GridPane createStandardGrid(){
        GridPane standardGridPane = super.createGameGrid();
        hiLoBanner.setFont(Font.font ("Verdana", 35));
        areaInfo.setPrefRowCount(3);

        standardGridPane.add(hiLoBanner, 0,0, 5,1);
        standardGridPane.add(areaInfo,2,1,3,4);
        standardGridPane.add(btnForInstructions, 0,7,1,1);
        standardGridPane.add(btnExit,6,7,1,1);

        return standardGridPane;
    }


}
