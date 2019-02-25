package io.zipcoder.casino;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class CrapsDisplay extends Display {



    public Parent createCrapsContent(){
        GridPane crapsGrid = super.createGrid();


        return crapsGrid;
    }
}
