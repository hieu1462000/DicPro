package Application;

import Commandline.Dictionary;
import Commandline.DictionaryManagement;
import Commandline.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        successNotification.setVisible(false);
        dictionaryManagement.insertFromFile(dictionary, "src/main/resources/text/data.txt");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                add();
            }
        });
    }

    public void add() {
        String targetText = targetField.getText().trim();
        String explainText = explanationField.getText().trim();
        Word word = new Word(targetText, explainText);
        if(!dictionary.contains(word)){
            dictionary.add(word);
            try {
                FileWriter fileWriter = new FileWriter("src/main/resources/text/data.txt",true);
                BufferedWriter buf = new BufferedWriter(fileWriter);
                buf.newLine();
                buf.write("@"+ word.getWord_target()+ "\n" +word.getWord_explain());
                buf.newLine();
                buf.close();
                successNotification.setText("Thành công");
                successNotification.setVisible(true);
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }else {
            successNotification.setText("Từ điển đã có từ này");
            successNotification.setVisible(true);
        }
    }



    @FXML
    public void directSearch() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxmls/searcherController.fxml"));
        wrapperAdd.getChildren().clear();
        wrapperAdd.getChildren().add(anchorPane);
    }
    @FXML
    private Button addButton;

    @FXML
    private TextField targetField;

    @FXML
    private TextArea explanationField;

    @FXML
    private AnchorPane wrapperAdd;
    @FXML
    private  Label successNotification;
}
