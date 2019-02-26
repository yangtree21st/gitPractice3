package io.zipcoder.casino;

import io.zipcoder.casino.Main;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Display{


    public Parent createContent(){
        GridPane grid = createGrid();

        Text enterBanner = new Text("Welcome to the Casino Royale");
        enterBanner.setFont(Font.font ("Verdana", 40));


        Button btnEnter = new Button("Enter");
        btnEnter.setAlignment(Pos.CENTER);
        btnEnter.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        grid.add(enterBanner, 0, 1,5,1);
        grid.add(btnEnter, 2,3,1,1);


        btnEnter.setOnAction(e -> {
            Main.casino.startCasinoGuiExperience();
        });

        return grid;
    }

    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setPrefSize(1000, 600);
        grid.setAlignment(Pos.CENTER);

        //sets the padding between cells
        grid.setVgap(10);
        grid.setHgap(10);
        //sets padding
        grid.setPadding(new Insets(15, 15, 15, 15));
        //sets the background to the rgba value
        grid.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(67, 30, 30, 0.8);";
        grid.setStyle(style);

        //column constraints which sets alignment to horizontally center and set width to 15% of the total size screen
        ColumnConstraints colConstraint = createColumnConstraints();

        //row constraints which sets alignment to vertically center and set height to 15% of the total size screen
        RowConstraints rowConstraints = createRowConstraints();

        // add constraints for columns and rows
        grid.getColumnConstraints().addAll(colConstraint, colConstraint, colConstraint,colConstraint,colConstraint);
        grid.getRowConstraints().addAll(rowConstraints,rowConstraints,rowConstraints,rowConstraints, rowConstraints);
        grid.setGridLinesVisible(true);

        return grid;
    }

    public Stage setExitAction(Button buttonToExit){
        return (Stage) buttonToExit.getScene().getWindow();

    }

    public ColumnConstraints createColumnConstraints(){
        //column constraints which sets alignment to horizontally center and set width to 15% of the total size screen
        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setHalignment(HPos.CENTER);
        colConstraint.setPercentWidth(15);

        return colConstraint;
    }

    public RowConstraints createRowConstraints(){
        //column constraints which sets alignment to horizontally center and set width to 15% of the total size screen
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.CENTER);
        rowConstraints.setPercentHeight(10);

        return rowConstraints;
    }

    public GridPane createGameGrid(){
        GridPane gameGridPane = createGrid();

        gameGridPane.getColumnConstraints().addAll(createColumnConstraints(),createColumnConstraints());
        gameGridPane.getRowConstraints().addAll(createRowConstraints(), createRowConstraints(),createRowConstraints());

        return gameGridPane;
    }

}
