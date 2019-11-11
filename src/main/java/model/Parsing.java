package model;

import controller.ExceptionController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parsing {

    public ArrayList<String> inputStrings = new ArrayList<>();
    public static int countOfNodes; // количество узлов в матрице
    public static boolean[][] matrix; // считанная с текстового файла матрица

    public static void scanText(File input) throws IOException { // метод для получения текста
        Scanner scan = new Scanner(input);

        if(input.length() == 0) { // если входной файл пустой, вывести алерт
            ExceptionController.showAlertWithHeaderText();
            return;
        }
        String nodes = scan.nextLine();
            if (nodes.matches("\\d+")) { // проверка первой строчки входного файла, т.е количество вершин у графа
                countOfNodes = Integer.parseInt(nodes);
                if (countOfNodes <= 15) { // нельзя построить граф, если в нем больше 15 вершин
                    boolean[][] bufferMatrix = new boolean[countOfNodes][countOfNodes];
                    while (scan.hasNextLine()) {
                        String[] s = scan.nextLine().split(" ");
                        if(s.length > 2) { // если во входном файле на строчке больше двух символов, то вывести алерт
                            ExceptionController.showAlertWithHeaderText();
                            return;
                        }
                        try { // назначаем ребра между вершинами
                            bufferMatrix[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = true;
                            bufferMatrix[Integer.parseInt(s[1])][Integer.parseInt(s[0])] = true;
                        }catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                            ExceptionController.showAlertWithHeaderText();
                            return;
                        }
                    }
                    matrix = bufferMatrix;
                }
                else {
                    ExceptionController.showAlertWithHeaderText();
                    return;
                }
            }
        else {
                ExceptionController.showAlertWithHeaderText();
        }
    }


}
