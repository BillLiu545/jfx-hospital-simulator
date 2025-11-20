import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;

public class HospitalApp extends Application
{
    private Hospital hosp = new Hospital();
    private final ObservableList<Patient> oList = hosp.toObservableList();
    public void start(Stage mainStage)
    {
        // Create a new border pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 15;");

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene mainScene = new Scene(root, 500,500);
        mainStage.setTitle("Hospital System Manager");
        mainStage.setScene(mainScene);
        
        // Set up the main layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);
        mainLayout.setSpacing(20);
        
        // Create the table
        TableView<Patient> table = new TableView();
        mainLayout.getChildren().add(table);
        table.setItems(oList);
        
        // Patient Condition Column
        TableColumn condCol = new TableColumn("Patient Condition");
        condCol.setCellValueFactory(new PropertyValueFactory("condition"));
        condCol.setMinWidth(150);
        table.getColumns().add(condCol);
        
        // Patient Treated Column
        TableColumn treatedCol = new TableColumn("Patient Treated?");
        treatedCol.setCellValueFactory(new PropertyValueFactory("treated"));
        treatedCol.setMinWidth(160);
        table.getColumns().add(treatedCol);
        
        // remove extra columns/rows
        table.setMaxWidth(310);
        table.setMaxHeight(490);
        
        // Button row
        HBox buttonRow = new HBox();
        buttonRow.setSpacing(10);
        buttonRow.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(buttonRow);
        
        // Admit a patient
        Button admitButton = new Button("Admit New Patient");
        admitButton.setOnAction((event->{
            Patient admitted = hosp.admit();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Patient Admitted");
            alert.setHeaderText("Patient Admitted");
            alert.setContentText("New patient has been admitted with: " + admitted.toString());
            alert.showAndWait();
            oList.add(admitted);
            table.refresh();
        }));
        // Treat a patient
        Button treatButton = new Button("Treat a Patient");
        treatButton.setOnAction((event->
        {
            Patient treated = hosp.input_treat();
            if (treated == null)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Patient Not Treated");
                alert.setHeaderText("Patient Not Treated");
                alert.setContentText("Specified patient has not been treated.");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Patient Treated");
                alert.setHeaderText("Patient Treated");
                alert.setContentText("The following patient has been treated: " + treated.toString());
                alert.showAndWait();
                table.refresh();
            }
        }));
        // Discharge a patient
        Button dischargeButton = new Button("Discharge a Patient");
        dischargeButton.setOnAction((event->
        {
            Patient discharged = hosp.input_discharge();
            if (discharged == null)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Patient Not Discharged");
                alert.setHeaderText("Patient Not Discharged");
                alert.setContentText("Specified patient has not been discharged.");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Patient Treated");
                alert.setHeaderText("Patient Treated");
                alert.setContentText("The following patient has been discharged: " + discharged.toString());
                alert.showAndWait();
                oList.remove(discharged);
                table.refresh();
            }
        }));
        
        buttonRow.getChildren().addAll(admitButton,treatButton,dischargeButton);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        // Menu item - Reset
        MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction((e->
        {
            hosp.clear();
            oList.clear();
            table.refresh();
        }));
        
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
            System.exit(0);
        }));
        fileMenu.getItems().addAll(resetItem, quitItem);
        mainStage.show();
    }
}
