package model;

import javafx.scene.shape.Circle;

public class NodeCircle extends Circle { // класс для реализации объекта узла графа
int number; // номер узла

    public NodeCircle(double centerX, double centerY, double radius, int n) {
        super(centerX,centerY,radius); // вызов конструктора суперкласса
        number = n;
    }

    public int getNumber() {
        return number;
    }
    public double getX() {
        return getCenterX();
    }

    public double get() {
        return getCenterY();
    }
}
