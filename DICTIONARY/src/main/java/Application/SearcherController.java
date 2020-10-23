package Application;

import Commandline.Dictionary;
import Commandline.DictionaryManagement;
import Commandline.CustomVoice;
import Commandline.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SearcherController implements Initializable {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    ObservableList<String> list = FXCollections.observableArrayList();
    int selectedWord = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement.insertFromFile(dictionary, "src/main/resources/text/data.txt");
        if (searchField.getText().trim().equals("")) {
            cancelButton.setDisable(true);
        }
        searchField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (searchField.getText().trim().equals("")) {
                    cancelButton.setDisable(true);
                } else {
                    cancelButton.setDisable(false);
                    clickSearch();
                }
            }
        });
        meaningField.setEditable(false);
        saveButton.setVisible(false);
        repairButton.setVisible(true);

    }

    public void clickCancel() {
        searchField.setText("");
        displayItem();
    }

    public void displayItem() {
        list.clear();
        for (int i = 0; i < 20; i++) {
            list.add(dictionary.get(i).getWord_target());
        }
        listSuggestion.setItems(list);
    }

    @FXML
    public void clickSearch() {
        list.clear();
        String searchKey = searchField.getText();
        selectedWord = dictionaryManagement.dictionaryLookup(dictionary, searchKey);
        if (selectedWord == -1) {
            meaningField.setText("");
        } else {
            meaningField.setText(dictionary.get(selectedWord).getWord_explain());
        }
        list = dictionaryManagement.searcher(dictionary, searchKey);
        if (list != null) {
            listSuggestion.setItems(list);
        }
    }

    @FXML
    public void clickAline() {
        String selectedLine = listSuggestion.getSelectionModel().getSelectedItem();
        selectedWord = dictionaryManagement.dictionaryLookup(dictionary, selectedLine);

        if (selectedWord == -1) {
            meaningField.setText("");
        } else {
            meaningField.setText(dictionary.get(selectedWord).getWord_explain());
        }
    }

    @FXML
    public void clickSpeakerButton() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        CustomVoice voice1 = new CustomVoice("kevin16");
        voice1.say(searchField.getText());
    }

    public void delete() throws IOException {
        String selectedLine = listSuggestion.getSelectionModel().getSelectedItem();
        selectedWord = dictionaryManagement.dictionaryLookup(dictionary, selectedLine);
//        dictionaryManagement.deleteWords(dictionary,selectedWord);
        resetAfterDeleting();
        dictionary.remove(selectedWord);
        dictionaryManagement.exportToFile(dictionary,"src/main/resources/text/data.txt");
        meaningField.setText("Successfully");
    }

    private void resetAfterDeleting() {
        System.out.println(selectedWord);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(dictionary.get(selectedWord).getWord_target())) {
                list.remove(i);
                break;
            }
        }
        listSuggestion.setItems(list);
    }

    @FXML
    public void repair() {
        meaningField.setEditable(true);
        saveButton.setVisible(true);
        repairButton.setVisible(false);
    }

    @FXML
    public void save() {
        String updateMeaning = meaningField.getText() + "\n";
        System.out.println(updateMeaning);
        dictionary.get(selectedWord).setWord_explain(updateMeaning);
        dictionaryManagement.exportToFile(dictionary,"src/main/resources/text/data.txt");
        // update status
        meaningField.setEditable(false);
        saveButton.setVisible(false);
        repairButton.setVisible(true);
    }

    @FXML
    public void directAdd() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxmls/addGui.fxml"));
        wrapperSearch.getChildren().clear();
        wrapperSearch.getChildren().add(anchorPane);
    }

    @FXML
    private TextField searchField;

    @FXML
    private TextArea meaningField;

    @FXML
    private ListView<String> listSuggestion;

    @FXML
    private AnchorPane wrapperSearch;

    @FXML
    private Button cancelButton, saveButton, repairButton;

}

