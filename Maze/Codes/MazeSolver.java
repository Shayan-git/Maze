import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeSolver {
    public void solveMazeWithStack(int[][] mazeMatrix, int widthSizeMaze, int heightSizeMaze) throws InterruptedException, IOException {
        Stack<Node> mazeStack = new Stack<>();
        List<Node> mazeFlag = new ArrayList<>();
        List<Node> mazeChance = new ArrayList<>();
        Random random = new Random();
        int x = 1;
        int y = 1;
        mazeMatrix[1][1] = 1;
        mazeMatrix[widthSizeMaze - 2][heightSizeMaze - 2] = 1;

        while (x != widthSizeMaze - 2 || y != heightSizeMaze - 2) {
            mazeMatrix[x][y] = 4;
            MazePrinter.printMaze(mazeMatrix, widthSizeMaze, heightSizeMaze);

            if (x - 1 > 0 && y - 1 > 0 && mazeMatrix[x - 1][y - 1] == 1) {
                mazeFlag.add(new Node(x - 1, y - 1));
                mazeChance.add(new Node(x - 1, y - 1));
            }
            if (x - 1 > 0 && mazeMatrix[x - 1][y] == 1) {
                mazeFlag.add(new Node(x - 1, y));
                mazeChance.add(new Node(x - 1, y));
            }
            if (x - 1 > 0 && y + 1 < heightSizeMaze - 1 && mazeMatrix[x - 1][y + 1] == 1) {
                mazeFlag.add(new Node(x - 1, y + 1));
                mazeChance.add(new Node(x - 1, y + 1));
            }
            if (y - 1 > 0 && mazeMatrix[x][y - 1] == 1) {
                mazeFlag.add(new Node(x, y - 1));
                mazeChance.add(new Node(x, y - 1));
            }
            if (x + 1 < widthSizeMaze - 1 && y - 1 > 0 && mazeMatrix[x + 1][y - 1] == 1) {
                mazeFlag.add(new Node(x + 1, y - 1));
                mazeChance.add(new Node(x + 1, y - 1));
            }
            if (y + 1 < heightSizeMaze - 1 && mazeMatrix[x][y + 1] == 1) {
                mazeFlag.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
            }
            if (x + 1 < widthSizeMaze - 1 && mazeMatrix[x + 1][y] == 1) {
                mazeFlag.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
            }
            if (x + 1 < widthSizeMaze - 1 && y + 1 < heightSizeMaze - 1 && mazeMatrix[x + 1][y + 1] == 1) {
                mazeFlag.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
            }

            if (mazeFlag.size() == 0) {
                if (mazeStack.size() > 0) {
                    Node temp = mazeStack.pop();
                    x = temp.getX();
                    y = temp.getY();
                    continue;
                }
            } else if (mazeFlag.size() == 1) {
                x += (mazeFlag.get(0).getX() - x);
                y += (mazeFlag.get(0).getY() - y);
            } else {
                for (int i = 0; i < mazeFlag.size(); i++)
                    mazeStack.push(new Node(x, y));
            }

            if (mazeStack.size() > 0) {
                mazeStack.pop();
                Node temp = mazeChance.get(random.nextInt(mazeChance.size()));
                x += (temp.getX() - x);
                y += (temp.getY() - y);
            }

            mazeFlag.clear();
            mazeChance.clear();
        }

        mazeMatrix[x][y] = 4;
        MazePrinter.printMaze(mazeMatrix, widthSizeMaze, heightSizeMaze);
    }

    public void solveMazeWithQueue(int[][] mazeMatrix, int widthSizeMaze, int heightSizeMaze) throws InterruptedException, IOException {
        Queue<Node> mazeQueue = new Queue<>();
        List<Node> mazeFlag = new ArrayList<>();
        List<Node> mazeChance = new ArrayList<>();
        Random random = new Random();
        int x = 1;
        int y = 1;
        mazeMatrix[1][1] = 1;
        mazeMatrix[widthSizeMaze - 2][heightSizeMaze - 2] = 1;

        while (x != widthSizeMaze - 2 || y != heightSizeMaze - 2) {
            mazeMatrix[x][y] = 4;
            MazePrinter.printMaze(mazeMatrix, widthSizeMaze, heightSizeMaze);

            if (x - 1 > 0 && y - 1 > 0 && mazeMatrix[x - 1][y - 1] == 1) {
                mazeFlag.add(new Node(x - 1, y - 1));
                mazeChance.add(new Node(x - 1, y - 1));
            }
            if (x - 1 > 0 && mazeMatrix[x - 1][y] == 1) {
                mazeFlag.add(new Node(x - 1, y));
                mazeChance.add(new Node(x - 1, y));
            }
            if (x - 1 > 0 && y + 1 < heightSizeMaze - 1 && mazeMatrix[x - 1][y + 1] == 1) {
                mazeFlag.add(new Node(x - 1, y + 1));
                mazeChance.add(new Node(x - 1, y + 1));
            }
            if (y - 1 > 0 && mazeMatrix[x][y - 1] == 1) {
                mazeFlag.add(new Node(x, y - 1));
                mazeChance.add(new Node(x, y - 1));
            }
            if (x + 1 < widthSizeMaze - 1 && y - 1 > 0 && mazeMatrix[x + 1][y - 1] == 1) {
                mazeFlag.add(new Node(x + 1, y - 1));
                mazeChance.add(new Node(x + 1, y - 1));
            }
            if (y + 1 < heightSizeMaze - 1 && mazeMatrix[x][y + 1] == 1) {
                mazeFlag.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
                mazeChance.add(new Node(x, y + 1));
            }
            if (x + 1 < widthSizeMaze - 1 && mazeMatrix[x + 1][y] == 1) {
                mazeFlag.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
                mazeChance.add(new Node(x + 1, y));
            }
            if (x + 1 < widthSizeMaze - 1 && y + 1 < heightSizeMaze - 1 && mazeMatrix[x + 1][y + 1] == 1) {
                mazeFlag.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
                mazeChance.add(new Node(x + 1, y + 1));
            }

            if (mazeFlag.size() == 0) {
                if (mazeQueue.size() > 0) {
                    Node temp = mazeQueue.dequeue();
                    x = temp.getX();
                    y = temp.getY();
                    continue;
                }
            } else if (mazeFlag.size() == 1) {
                x += (mazeFlag.get(0).getX() - x);
                y += (mazeFlag.get(0).getY() - y);
            } else {
                for (int i = 0; i < mazeFlag.size(); i++)
                    mazeQueue.enqueue(new Node(x, y));
            }

            if (mazeQueue.size() > 0) {
                mazeQueue.dequeue();
                Node temp = mazeChance.get(random.nextInt(mazeChance.size()));
                x += (temp.getX() - x);
                y += (temp.getY() - y);
            }

            mazeFlag.clear();
            mazeChance.clear();
        }

        mazeMatrix[x][y] = 4;
        MazePrinter.printMaze(mazeMatrix, widthSizeMaze, heightSizeMaze);
    }
}
