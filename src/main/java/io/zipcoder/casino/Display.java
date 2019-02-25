package io.zipcoder.casino;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Display{


    //public static JFrame frame = new JFrame("Casino");
    //public static JLabel label= new JLabel("0");

    public Parent createContent(){
        GridPane grid = createGrid();

        Text enterBanner = new Text("Welcome to the Casino Royale");
        enterBanner.setFont(Font.font ("Verdana", 60));


        Button btnEnter = new Button("Enter");
        btnEnter.setMaxWidth(Double.MAX_VALUE);

        grid.add(btnEnter, 2, 2);
        grid.add(enterBanner, 0,0, 5,2);

        btnEnter.setOnAction(e -> {
            Main.casino.startCasinoGuiExperience();
        });

        return grid;
    }

    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setPrefSize(1200, 600);
        grid.setAlignment(Pos.CENTER);


        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(15);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(15);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(15);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(15);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(15);
        grid.getColumnConstraints().addAll(column1, column2,column3,column4,column5);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(15);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(15);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(15);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(15);
        RowConstraints row5 = new RowConstraints();
        row5.setPercentHeight(15);


        grid.getRowConstraints().addAll(row1,row2,row3);

        return grid;
    }

}
