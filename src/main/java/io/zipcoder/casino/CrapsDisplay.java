package io.zipcoder.casino;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class CrapsDisplay extends Display {


    public Parent createCrapsContent(){
        GridPane crapsGrid = super.createGrid();
        crapsGrid.getColumnConstraints().addAll(super.createColumnConstraints(),super.createColumnConstraints());
        crapsGrid.getRowConstraints().addAll(super.createRowConstraints(), super.createRowConstraints(),super.createRowConstraints());
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

        //sets the background to the rgba value
        grid.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(67, 30, 30, 0.8);";
        grid.setStyle(style);

        grid.setGridLinesVisible(true);


        return grid;
    }
}
