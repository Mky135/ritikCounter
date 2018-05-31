package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable
{
    public AnchorPane pane;

    @FXML
    private Label counter;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        counter.setText("Counter: " + 1);
    }

    @FXML
    private void clicked()
    {
        int count = Integer.valueOf(counter.getText().substring(counter.getText().indexOf(" ")+1));
        count++;
        counter.setText("Counter: " + count);
    }
}
