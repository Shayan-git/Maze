import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    0.wall ('0')
    1.path (' ')
    2.(1, 1) -> start ('2')
    3.(n - 2, n - 2) -> end ('3')
    4.solver ('X')

    1  2  3
    4  X  6
    5  7  8
*/

public class MazeGenerator {
    private final List<Node> mazeList;
    private int[][] mazeMatrix;
    private final int widthSizeMaze; // X
    private final int heightSizeMaze; // Y
    private final List<Node> mazeFlag;
    private final Random random;

    public MazeGenerator(int widthSizeMaze, int heightSizeMaze) {
        mazeList = new ArrayList<>();
        mazeMatrix = new int[widthSizeMaze][heightSizeMaze];
        this.widthSizeMaze = widthSizeMaze;
        this.heightSizeMaze = heightSizeMaze;
        mazeFlag = new ArrayList<>();
        random = new Random();
    }

    public int[][] getMazeMatrix() {
        return mazeMatrix;
    }

    private Node peek() {
        return mazeList.get(mazeList.size() - 1);
    }

    public void createMaze() {
        createMainPathMaze();
        createBranchPathMaze();
    }

    private void createMainPathMaze() {
        mazeList.add(new Node(1, 1));

        while (peek().getX() != widthSizeMaze - 2 || peek().getY() != heightSizeMaze - 2) {
            if (peek().getX() - 1 > 0 && peek().getY() - 1 > 0)
                mazeFlag.add(new Node(peek().getX() - 1, peek().getY() - 1));
            if (peek().getX() - 1 > 0)
                mazeFlag.add(new Node(peek().getX() - 1, peek().getY()));
            if (peek().getX() - 1 > 0 && peek().getY() + 1 < heightSizeMaze - 1)
                mazeFlag.add(new Node(peek().getX() - 1, peek().getY() + 1));
            if (peek().getY() - 1 > 0)
                mazeFlag.add(new Node(peek().getX(), peek().getY() - 1));
            if (peek().getX() + 1 < widthSizeMaze - 1 && peek().getY() - 1 > 0)
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY() - 1));
            if (peek().getY() + 1 < heightSizeMaze - 1) {
                mazeFlag.add(new Node(peek().getX(), peek().getY() + 1));
                mazeFlag.add(new Node(peek().getX(), peek().getY() + 1));
                mazeFlag.add(new Node(peek().getX(), peek().getY() + 1));
            }
            if (peek().getX() + 1 < widthSizeMaze - 1) {
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY()));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY()));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY()));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY()));
            }
            if (peek().getX() + 1 < widthSizeMaze - 1 && peek().getY() + 1 < heightSizeMaze - 1) {
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY() + 1));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY() + 1));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY() + 1));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY() + 1));
                mazeFlag.add(new Node(peek().getX() + 1, peek().getY() + 1));
            }

            if (mazeFlag.size() > 0) {
                mazeList.add(mazeFlag.get(random.nextInt(mazeFlag.size())));
                mazeFlag.clear();
            } else
                mazeList.remove(peek());
        }

        mazeMatrix = MazeListToArray.listToArray(mazeList, widthSizeMaze, heightSizeMaze);
    }

    private void createBranchPathMaze() {
        int x, y;
        for (int i = 0; i < (widthSizeMaze + heightSizeMaze) * 10; i++) {
            x = random.nextInt(widthSizeMaze - 1) + 1;
            y = random.nextInt(heightSizeMaze - 1) + 1;
            if (mazeMatrix[x][y] != 1) {
                while (mazeMatrix[x][y] != 1) {
                    x = random.nextInt(widthSizeMaze - 1) + 1;
                    y = random.nextInt(heightSizeMaze - 1) + 1;
                }
            }
            while (x != widthSizeMaze - 2 || y != heightSizeMaze - 2) {
                if (random.nextInt(2) == 0 && x != widthSizeMaze - 2)
                    x++;
                else if (random.nextInt(2) == 1 && y != heightSizeMaze - 2)
                    y++;
                if (mazeMatrix[x][y] == 0 && mazeMatrix[x + 1][y] != 1 && mazeMatrix[x][y + 1] != 1 && mazeMatrix[x + 1][y + 1] != 1)
                    mazeMatrix[x][y] = 1;
            }
        }

        for (int i = 0; i < (widthSizeMaze + heightSizeMaze) * 10; i++) {
            x = random.nextInt(widthSizeMaze - 1) + 1;
            y = random.nextInt(heightSizeMaze - 1) + 1;
            if (mazeMatrix[x][y] != 1) {
                while (mazeMatrix[x][y] != 1) {
                    x = random.nextInt(widthSizeMaze - 1) + 1;
                    y = random.nextInt(heightSizeMaze - 1) + 1;
                }
            }
            while (x != 1 || y != 1) {
                if (random.nextInt(2) == 0 && x != 1)
                    x--;
                else if (random.nextInt(2) == 1 && y != 1)
                    y--;
                if (mazeMatrix[x][y] == 0 && mazeMatrix[x - 1][y] != 1 && mazeMatrix[x][y - 1] != 1 && mazeMatrix[x - 1][y - 1] != 1)
                    mazeMatrix[x][y] = 1;
            }
        }

        mazeMatrix[widthSizeMaze - 2][heightSizeMaze - 2] = 3;
    }
}
