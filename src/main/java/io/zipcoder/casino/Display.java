package io.zipcoder.casino;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Display{

    Button btnExit = new Button("Exit");
    Button btnForInstructions = new Button("Instructions");
    Stage instructionStage = new Stage();

    /***
     * Method creates the Welcome scene that users enter into when choosing to use the GUI
     * @return (Parent) GridPane
     */
    public Parent createContent(){
        GridPane grid = createGrid();

        Text enterBanner = new Text("Welcome to the Casino Royale");
        enterBanner.setFont(Font.font ("Verdana", 40));


        Button btnEnter = new Button("Enter");
        btnEnter.setAlignment(Pos.CENTER);
        btnEnter.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Image bgImage = new Image("File:src/main/java/io/zipcoder/casino/Images/honors_spade-14.png",400,250,true,true);
        grid.add(new ImageView(bgImage),1,1,3,3);

        grid.add(enterBanner, 0, 0,5,1);
        grid.add(btnEnter, 2,4,1,1);


        btnEnter.setOnAction(e -> {
            Main.casino.startCasinoGuiExperience();
        });

        return grid;
    }

    /**
     * Creates the grid which is used in all non game Display classes
     * @return GridPane
     */
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
        String style = "-fx-background-color: rgba(51, 102, 0, 1.0);";
        grid.setStyle(style);

        //column constraints which sets alignment to horizontally center and set width to 15% of the total size screen
        ColumnConstraints colConstraint = createColumnConstraints();

        //row constraints which sets alignment to vertically center and set height to 15% of the total size screen
        RowConstraints rowConstraints = createRowConstraints();

        // add constraints for columns and rows
        grid.getColumnConstraints().addAll(colConstraint, colConstraint, colConstraint,colConstraint,colConstraint);
        grid.getRowConstraints().addAll(rowConstraints,rowConstraints,rowConstraints,rowConstraints, rowConstraints);
        //grid.setGridLinesVisible(true);

        return grid;
    }

    /**
     * Takes in a button who's action is to close the window and returns the corresponding stage that button is located on
     * @param buttonToExit
     * @return Stage
     */

    public Stage setExitAction(Button buttonToExit){
        return (Stage) buttonToExit.getScene().getWindow();

    }

    /**
     * Returns the Column Constraints once Halignment and Width percentage are set
     * @return ColumnConstraints
     */

    public ColumnConstraints createColumnConstraints(){
        //column constraints which sets alignment to horizontally center and set width to 15% of the total size screen
        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setHalignment(HPos.CENTER);
        colConstraint.setPercentWidth(15);

        return colConstraint;
    }

    /**
     * Returns the Row Constraints once Valignment and Height percentage are set
     * @return RowConstraints
     */

    public RowConstraints createRowConstraints(){
        //column constraints which sets alignment to horizontally center and set width to 15% of the total size screen
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.CENTER);
        rowConstraints.setPercentHeight(10);

        return rowConstraints;
    }

    /**
     *
     * @return
     */

    public GridPane createGameGrid(){
        GridPane gameGridPane = createGrid();


        btnExit.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnForInstructions.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        gameGridPane.add(btnForInstructions, 0,8,1,1);
        gameGridPane.add(btnExit,6,8,1,1);

        btnExit.setOnAction(e ->{
            CasinoGamesDisplay casinoGamesDisplay = new CasinoGamesDisplay();
            Main.mainStage.setScene(new Scene(casinoGamesDisplay.createCasinoGamesContent()));
            Main.mainStage.show();
        });

        gameGridPane.getColumnConstraints().addAll(createColumnConstraints(),createColumnConstraints());
        gameGridPane.getRowConstraints().addAll(createRowConstraints(), createRowConstraints(),createRowConstraints());

        return gameGridPane;
    }

    /**
     * \
     * @param stringInstructions
     */
    public void setInstructionsForButton(String stringInstructions){

        btnForInstructions.setOnAction(e ->{
            InstructionDisplay instructionDisplay = new InstructionDisplay();
            instructionStage.setScene(new Scene(instructionDisplay.createInstructionContent(stringInstructions)));
            instructionStage.show();

        });
    }

    public void setExitAndInstructions(boolean bool){
        setExitButtonAccess(bool);
        setInstructionsButtonAccess(bool);
    }


    public void setExitButtonAccess(boolean bool){
        this.btnExit.setDisable(bool);
    }


    public void setInstructionsButtonAccess(Boolean bool){
        this.btnForInstructions.setDisable(bool);
    }




}
