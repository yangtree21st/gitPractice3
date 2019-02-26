package io.zipcoder.casino;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InstructionDisplay extends Display {


    public Parent createInstructionContent(String instructionsToDisplay){
        GridPane instructionGridPane = super.createGrid();

        TextArea instructionsArea = new TextArea();

        Button btnExitInstructions = new Button("Exit Instructions");

        instructionGridPane.add(instructionsArea,0,0,5,5);
        instructionGridPane.add(btnExitInstructions,2,6,1,1);

        instructionsArea.setEditable(false);
        instructionsArea.setText(instructionsToDisplay);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(instructionsArea);
        scrollPane.setFitToWidth(true);


        btnExitInstructions.setOnAction(e -> {
            Stage instructionStage = super.setExitAction(btnExitInstructions);
            instructionStage.close();
        });

        return instructionGridPane;
    }


}
