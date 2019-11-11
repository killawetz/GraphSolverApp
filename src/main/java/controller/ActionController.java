package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import model.AlgorithmFindRoute;
import model.NodeCircle;
import model.Parsing;

import static java.lang.Math.PI;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

public class ActionController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public AnchorPane anchoPane;

    @FXML
    public static Label currentProgressLabel;

    @FXML
    private Button solveItButton;

    @FXML
    private TextFlow textFlow;

    @FXML
    void initialize() {
        draw(Parsing.countOfNodes);
        createLine();

}


    public void clickSolve(ActionEvent actionEvent) throws InterruptedException { // обработка нажатия кнопки Solve it для запуска решателя
        AlgorithmFindRoute algo = new AlgorithmFindRoute(Parsing.countOfNodes, Parsing.matrix);
        if(!algo.isEuler() || !algo.allConnections()) {
            ExceptionController.showAlertCantFindRoute();
        }
        else {
            algo.printEulerTour();
            printPassedRibs();
        }
    }


        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO ;
        Duration pause = Duration.seconds(1);

        public void printPassedRibs() throws InterruptedException { // отрисовка по ребру с задержкой в секунду
            for (int i = 0; i < listOfPassedRibs.size() ; i++) {
                String bufferString = AlgorithmFindRoute.sourceDestination.get(i); // строка, которую будем выводить в "Current progress"
                Line bufferLine = listOfPassedRibs.get(i); // пройденно ребро, которое будем отрисовывать на сцене
                timepoint = timepoint.add(pause);
                KeyFrame keyFrame = new KeyFrame(timepoint, e -> { // определяем набор объектов, которые будут рисоваться вдоль временной шкалы
                    anchoPane.getChildren().add(bufferLine);
                    textFlow.getChildren().add(new Text(bufferString + "\n "));
                });
                timeline.getKeyFrames().add(keyFrame);
            }
            timeline.play();
        }

        private static ArrayList<NodeCircle> listOfNodes = new ArrayList<>(); // список в котором упорядоченно хранятся все узлы.

        public void setNodeCircle(double distanceX, double distanceY, String textOnNodes, int numberOfNode) { // метод задающий значения для узлов на сцене
            Text text = new Text(textOnNodes);
            text.setFill(Color.WHITE);
            final StackPane stack = new StackPane();
            NodeCircle nodeCircle = new NodeCircle(distanceX, distanceY, 15, numberOfNode);
            listOfNodes.add(new NodeCircle(distanceX, distanceY, 15, numberOfNode));
            stack.getChildren().addAll(nodeCircle, text);
            anchoPane.getChildren().add(stack);
            stack.setLayoutX(distanceX);
            stack.setLayoutY(distanceY);
        }

    /*метод отрисовывающий узлы по сцене
    суть алгоритма:
    узлы располагаются как точки по окружности с заданным центром и радиусом
    Радиус константен. Центр окружности - центр сцены.
    Шаг между двумя узлами равен углу.
    360° делим на количество узлов - получаем шаг.
    Узлы располгаются по такой формуле
    узел(цетр сцены по X + синус(шаг) * радиус, центр сцены по Y + косинус(шаг) * радиус)
    */
    public void draw(int countOfNodes) {
            int bufferCountOfNodes = 0;
            int bufferNumberOfNode = 0;
            System.out.println(bufferCountOfNodes);
            int step = 360 / countOfNodes; // считаем, на какой угл должны отступать узлы друг от друга
            int radius = 90;
            double sceneCenterX = anchoPane.getPrefWidth() / 2;
            double sceneCenterY = anchoPane.getPrefHeight() / 2;
            for (double angle = 0; angle < 2 * PI; angle += angleInRadian(step)) {
                if(bufferCountOfNodes < Parsing.countOfNodes) {
                    setNodeCircle(sceneCenterX + sin(angle) * radius, sceneCenterY + cos(angle) * radius, String.valueOf(bufferCountOfNodes),bufferNumberOfNode); // расположение
                    bufferCountOfNodes++;
                    bufferNumberOfNode++;
                }

            }
        }

         static ArrayList<Line> listOfPassedRibs = new ArrayList<>(); // список пройденных ребер

         public void createPassedRib(int vertex, int destination) { // метод для создания пройденного ребра
            Line rib = new Line(listOfNodes.get(vertex).getCenterX() + 10,
                    listOfNodes.get(vertex).getCenterY() + 10,
                    listOfNodes.get(destination).getCenterX() + 10,
                    listOfNodes.get(destination).getCenterY() + 10);
             rib.setStroke(Color.RED);
             listOfPassedRibs.add(rib);
         }

        public void createLine() { // метод для создания ребра между узлами
            for (int i = 0; i < Parsing.countOfNodes; i++) {
                for (int j = 0; j < Parsing.countOfNodes; j++) {
                    if(Parsing.matrix[i][j]) {
                        Line line = new Line(listOfNodes.get(i).getCenterX() + 10,
                                listOfNodes.get(i).getCenterY() + 10,
                                listOfNodes.get(j).getCenterX() + 10,
                                listOfNodes.get(j).getCenterY() + 10);
                        anchoPane.getChildren().add(line);
                    }
            }
            }
        }

        private double angleInRadian(double grad) {
        return PI*grad/180;
        }




}
