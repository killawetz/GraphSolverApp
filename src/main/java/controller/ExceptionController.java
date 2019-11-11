package controller;

import javafx.scene.control.Alert;

public class ExceptionController {

    public static void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Неверный формат ввода! Попробуйте еще раз!");
        alert.setContentText("Файл ввода не может быть пустым" +
                "и не может содержать ничего кроме цифр." +
                " Ознакомление с форматом ввода Help -> About.");

        alert.showAndWait();
    }

    public static void showAlertCantFindRoute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Ошибка!");
        alert.setContentText("У данного графа нельзя построить маршрут.");
        alert.showAndWait();
    }

    public static void ShowAlertCantStart() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Не выбран входной файл!");
        alert.setContentText("Файл ввода не был указан. Нажмите File Chooser и выберите необходимый файл в директории.");
        alert.showAndWait();
    }
}
