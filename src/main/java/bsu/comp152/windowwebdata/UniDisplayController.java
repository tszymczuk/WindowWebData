package bsu.comp152.windowwebdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class UniDisplayController implements Initializable {
    @FXML
    private TextField NameField;
    @FXML
    private TextField UniversityCountryField;
    @FXML
    private TextField websiteDisplayField;
    @FXML
    private ListView<DataHandler.UniversityDataType> ListControl;
    private DataHandler Model;

    public void loadData(){
        var site = "http://universities.hipolabs.com/search?name=";
        String param = getQueryParam();
        var wholeSite = site+param;
        Model = new DataHandler(wholeSite);
        var universities = Model.getData();
        ObservableList<DataHandler.UniversityDataType> UnivList = FXCollections.observableArrayList(universities);
        ListControl.setItems(UnivList);

    }

    private String getQueryParam() {
        var inputDialog = new TextInputDialog("Young");
        inputDialog.setContentText("What University Should we Search for?");
        inputDialog.setHeaderText("Gathering Information");
        var response = inputDialog.showAndWait();
        if (response.isEmpty()){
            return ""; 
        }
        else {
            return response.get();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}