package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelpAboutController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vboxHelpAbout;

    @FXML
    void initialize() {
        Label text = new Label();
        text.setText("Инструкция по работе с приложением. \n" +
                "\n" +
                "File -> Open - открыть проводник с возможностью открытия окна нужного txt файла.\n" +
                "File -> Quit - выход из приложения.\n" +
                "\n" +
                "Help -> About - пояснение к приложению \n" +
                "\n" +
                "Последовательность работы Graph Solver:\n" +
                "\n" +
                "Создаем документ формата .txt, заполяем его входными данными и сохраняем.\n" +
                "File Chooser -> выбираем нужный txt файл\n" +
                "Start - переход на новое окно с отрисовкой введеного графа\n" +
                "\n" +
                "Шаблон входных данных:\n" +
                "n - кол-во узлов графа\n" +
                "a b - ребра графа от узла a до b\n" +
                "a c - ребра графа от узла a до с\n" +
                "c b - ребра графа от узла с до b\n" +
                "\n" +
                "Пробелов после последней, перед передней цифрами строки быть не должно.\n" +
                "Пустые строки, пустой файл, кириллица и латиница не считываются.\n" +
                "Узлы задаются цифрами от 0 до n - 1, где n - количество узлов.\n" +
                "Два узла указывающие ребро разделяются пробелом \" \".\n" +
                "\n" +
                "Пример \n" +
                "4\n" +
                "0 1\n" +
                "1 2\n" +
                "1 3\n" +
                "2 0\n" +
                "3 0\n");
        vboxHelpAbout.getChildren().add(text);
    }
}