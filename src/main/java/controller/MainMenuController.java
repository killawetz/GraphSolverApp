package controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Parsing;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuBarOpenButton;

    @FXML
    private MenuItem menuBarQuitButton;

    @FXML
    private MenuItem helpAboutButton;

    @FXML
    private Button chooseFileButton;

    private Desktop desktop = Desktop.getDesktop();

    private File selectedFile;

    @FXML
    void initialize() {
    }

    public  void openNewScene(String sceneName, Node node) {        // метод для открытия следующего окна
        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sceneName));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void closeCurrentScene(String sceneName, Node node) {  // метод для закрытия
        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sceneName));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.close();
    }



    @FXML
    private void clickStart(ActionEvent actionEvent) throws IOException {
        if(Parsing.countOfNodes == 0) {
            ExceptionController.ShowAlertCantStart();
            return;
        }
        openNewScene("/view/Action.fxml", startButton);
    }

    @FXML
    public void clickChoose(ActionEvent actionEvent) throws IOException {
        fileExplorer();
        try {
            Parsing.scanText(selectedFile);
        } catch (NullPointerException e) {
        }
    }

    @FXML
    public void closeScene(ActionEvent actionEvent) {
        closeCurrentScene("MainMenu.fxml", startButton);
    }

    public void clickFileChooser(ActionEvent actionEvent) throws IOException {
    fileExplorer();
        if (selectedFile != null) {
            openFile(selectedFile);
        }
    }

    private void fileExplorer() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(menuBar.getScene().getWindow());

    }
        private void openFile(File file) {
            try {
                this.desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void clickHelpAbout(ActionEvent actionEvent) throws IOException { // открытие вью при нажатии Help -> About
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/view/HelpAbout.fxml"));
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();


    }
}
