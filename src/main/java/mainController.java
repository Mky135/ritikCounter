import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    private Database database;

    @FXML
    private Label counter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = new Database();
        database.connectDB();
        database.refreshDB();
        counter.setText("Counter: " + database.getRitik());
    }

    @FXML
    private void clicked() {
        database.updateDB();
        database.refreshDB();
        counter.setText("Counter: " + database.getRitik());
    }

    @FXML
    private void refresh(){
        database.refreshDB();
        counter.setText("Counter: " + database.getRitik());
    }
}