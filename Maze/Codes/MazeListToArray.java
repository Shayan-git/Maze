import java.util.List;

public class MazeListToArray {
    public static int[][] listToArray(List<Node> list, int width, int height) {
        int[][] matrix = new int[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                matrix[i][j] = 0;

        for (Node node : list)
            matrix[node.getX()][node.getY()] = 1;
        matrix[1][1] = 2;
        matrix[width - 2][height - 2] = 3;

        return matrix;
    }
}
