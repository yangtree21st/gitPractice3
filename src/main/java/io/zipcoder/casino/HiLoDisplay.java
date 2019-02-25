package io.zipcoder.casino;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class HiLoDisplay extends Display {

    public Parent createHiLoContent(){
        GridPane hiLoGrid = super.createGrid();


        return hiLoGrid;
    }
}
