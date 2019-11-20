package model;

import controller.ActionController;

import java.util.ArrayList;

public class AlgorithmFindRoute { // класс алгоритм для обхода графа, за основу взят Эйлеров Алгоритм


    public boolean[][] adjacencyMatrix; // матрица смежности
    private int numberOfNodes; // количество узлов

    public AlgorithmFindRoute (int numberOfNodes, boolean[][] adjacencyMatrix) { // конструктор
        this.numberOfNodes = numberOfNodes;
        this.adjacencyMatrix = new boolean[numberOfNodes] [numberOfNodes];
        for (int sourceVertex = 0; sourceVertex <= numberOfNodes - 1; sourceVertex++) {
            System.arraycopy(adjacencyMatrix[sourceVertex], 0, this.adjacencyMatrix[sourceVertex], 0, numberOfNodes - 1 + 1);
        }
    }


    public boolean allConnections() { // метод, который проверяет, можно ли построить маршрут
        boolean result = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length ; j++) {
                if(adjacencyMatrix[i][j]) {
                    result = adjacencyMatrix[i][j];
                    break;
                }
                result = adjacencyMatrix[i][j];
            }
        }
        return result;
    }

    public int degree (int vertex) { // метод считает количество ребер исходящих из вершины, т.е четность узла
        int degree = 0;
        for (int destinationvertex = 0; destinationvertex <= numberOfNodes - 1 ; destinationvertex++) {
            if (adjacencyMatrix[vertex][destinationvertex]
                    || adjacencyMatrix[destinationvertex][vertex]) {
                degree++;
            }
        }
        return degree;
    }

    public boolean isEuler() { // проверка, является ли граф Эйлеровым: если в нем не больше двух нечетных узлов, то граф эйлеров
        int countOfOdd = 0;
        for (int node = 0; node <= numberOfNodes - 1; node++) {
            if ((degree(node) % 2) != 0) {
                countOfOdd++;
            }
        }
        return !(countOfOdd > 2);
    }

    private int oddDegreeVertex() { // метод проверяющий существует ли вообще нечетная вершина, для того, чтобы начать с нее ход
        int vertex = -1;
        for (int node = 0; node <= numberOfNodes - 1; node++) {
            if ((degree(node) % 2) != 0) {
                vertex = node;
                break;
            }
        }
        return vertex;
    }

    public static ArrayList<String> sourceDestination = new ArrayList<>();

    private ActionController actController = new ActionController();

    private void printEulerTourUtil(int vertex) {
        for (int destination = 0; destination <= numberOfNodes-1; destination++)
        {
            if(adjacencyMatrix[vertex][destination]  && isVaildNextEdge(vertex, destination)) // если существует ребро, и следом за ним существует путь
            {
                System.out.println(" source : " + vertex + " destination " + destination);
                // добавляет текст в массив под тем же индексом, что и пройденное ребро
                sourceDestination.add(" source : " + String.valueOf(vertex) + " destination " + String.valueOf(destination));
                actController.createPassedRib(vertex,destination); // делает ребро пройденым
                removeEdge(vertex, destination);
                printEulerTourUtil(destination);
            }
        }
    }

    public void printEulerTour () { // главный метод алгоритма, запускающий решатель
        int vertex = 0;
        if (oddDegreeVertex() != -1) {
            vertex = oddDegreeVertex();
        }
        printEulerTourUtil(vertex);
    }

    private boolean isVaildNextEdge(int source, int destination) { // существует ли следующее ребро
        int count = 0;
        for (int vertex = 0; vertex <= numberOfNodes - 1; vertex++) { // считаем количество ребер у узла
            if (adjacencyMatrix[source][vertex])
            {
                count++;
            }
        }

        if (count == 1 ) {
            return true;
        }

        int[] visited = new int[numberOfNodes]; // пройденные ребра
        int count1 = DFSCount(source, visited);

        removeEdge(source, destination);
        for (int vertex = 0; vertex <= numberOfNodes - 1; vertex++)
        {
            visited[vertex] = 0;
        }

        int count2 = DFSCount(source, visited);
        addEdge(source, destination);

        return count1 <= count2;
    }

    private int DFSCount(int source, int[] visited) { // алгоритм поиска в глубину
        visited[source] = 1;
        int count = 1;
        int destination = 0;

        while (destination < numberOfNodes) {
            if(adjacencyMatrix[source][destination] && visited[destination] == 0) {
                count += DFSCount(destination, visited);
            }
            destination++;
        }
        return count;
    }

    public void removeEdge (int source, int destination) { // метод: убрать ребро
        adjacencyMatrix[source][destination]  = false;
        adjacencyMatrix[destination][source] = false;
    }

    public void addEdge (int source, int destination) { // метод: добавить ребро
        adjacencyMatrix[source][destination] = true;
        adjacencyMatrix[destination][source] = true;
    }


}