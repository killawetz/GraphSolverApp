package model;

import controller.ActionController;

import java.util.ArrayList;

public class AlgorithmFindRoute { // класс алгоритм для обхода графа, за основу взят Ейлеров Алгоритм


    public boolean[][] adjacencyMatrix; // матрица смежности
    public int numberOfNodes;

    public AlgorithmFindRoute (int numberOfNodes, boolean[][] adjacencyMatrix) { // конструктор
        this.numberOfNodes = numberOfNodes;
        this.adjacencyMatrix = new boolean[numberOfNodes] [numberOfNodes];
        for (int sourceVertex = 0; sourceVertex <= numberOfNodes - 1; sourceVertex++) {
            for (int destinationVertex = 0; destinationVertex <= numberOfNodes - 1; destinationVertex++) {
                this.adjacencyMatrix[sourceVertex][destinationVertex]
                        = adjacencyMatrix[sourceVertex] [destinationVertex];
            }
        }
    }

    public boolean allConnections() {
        boolean result = false;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if(adjacencyMatrix[i][j]) result = true;
            }
            if (!result) return result;
        }
        return true;
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

    public boolean isEuler() { // проверка является ли граф Ейлеровым, если в нем не больше двух нечетных узлов, то граф эйлеров
        int countOfOdd = 0;
        for (int node = 0; node <= numberOfNodes - 1; node++) {
            if ((degree(node) % 2) != 0) {
                countOfOdd++;
            }
        }
        return !(countOfOdd > 2);
    }

    public int oddDegreeVertex () { // метод проверяющий существует ли вообще нечетная вершина, для того, чтобы начать с нее ход
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

    ActionController actController = new ActionController();

    public void printEulerTourUtil (int vertex) {
        for (int destination = 0; destination <= numberOfNodes-1; destination++)
        {
            if(adjacencyMatrix[vertex][destination]  && isVaildNextEdge(vertex, destination)) // если существует ребро, и следом за ним существует путь
            {
                System.out.println(" source : " + vertex + " destination " + destination);
                // добавляет текст в массив, под тем же индексом, что и пройденное ребро
                sourceDestination.add(" source : " + String.valueOf(vertex) + " destination " + String.valueOf(destination));
                actController.createPassedRib(vertex,destination); // делает ребро пройденым
                removeEdge(vertex, destination);
                printEulerTourUtil(destination);
            }
        }
    }

    public void printEulerTour () {
        int vertex = 0;
        if (oddDegreeVertex() != -1) {
            vertex = oddDegreeVertex();
        }
        printEulerTourUtil(vertex);
        return;
    }

    public boolean isVaildNextEdge (int source, int destination) {
        int count = 0;
        for (int vertex = 0; vertex <= numberOfNodes - 1; vertex++) {
            if (adjacencyMatrix[source][vertex])
            {
                count++;
            }
        }

        if (count == 1 ) {
            return true;
        }

        int visited[] = new int[numberOfNodes];
        int count1 = DFSCount(source, visited);

        removeEdge(source, destination);
        for (int vertex = 0; vertex <= numberOfNodes - 1; vertex++)
        {
            visited[vertex] = 0;
        }

        int count2 = DFSCount(source, visited);
        addEdge(source, destination);

        return (count1 > count2 ) ? false : true;
    }

    public int DFSCount (int source, int visited[]) { // алгоритм поиска в глубину
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