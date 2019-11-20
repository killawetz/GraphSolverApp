import model.AlgorithmFindRoute;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphSolverTest {

    private boolean[][] matrixBad = {{false, true, true, false}, // несвязный граф
            {true, false, false, false},
            {true, false, false, false},
            {false, false, false, false}};

    private boolean[][] matrixGood = {{false, true, true, false}, // связный граф
            {true, false, false, true},
            {true, false, false, true},
            {false, true, true, false}};

    AlgorithmFindRoute algoBad = new AlgorithmFindRoute(matrixBad.length, matrixBad);
    AlgorithmFindRoute algoGood = new AlgorithmFindRoute(matrixGood.length, matrixGood);
    @Test
    void testPossibilityOfPassing() { // тест возможно ли вообще построить маршрут
        assertEquals(algoBad.allConnections(), false);
        assertEquals(algoGood.allConnections(), true);
    }

    @Test
    void testIsEuler() { // проверка графа на Эйлеровость
        assertEquals(algoGood.isEuler(), true);
    } // проверка графа на эйлеровость

    @Test
    void testDegree() { // тест метода подсчета ребер исходящих из узла
        assertEquals(algoGood.degree(1), 2);
        assertEquals(algoGood.degree(0), 2);
    }

    @Test
    void testAddEdge() { // добавить ребро в граф который нельзя обойти
        algoBad.addEdge(3,1);
        assertEquals(algoBad.adjacencyMatrix[3][1], true);
    }

    @Test
    void testRemoveEdge() { // удалить раннее добавленное ребро
        algoBad.removeEdge(3,1);
        assertEquals(algoBad.adjacencyMatrix[3][1], false);
    }

}
