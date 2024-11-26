import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MazePrinter {
    public static void printMaze(int[][] mazeArray, int width, int height) throws InterruptedException, IOException {
        TimeUnit.MILLISECONDS.sleep(200);

        System.out.println("\n\n\n");

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (mazeArray[i][j] == 0)
                    System.out.print("0 ");
                else if (mazeArray[i][j] == 1)
                    System.out.print("  ");
                else if (mazeArray[i][j] == 2)
                    System.out.print("S ");
                else if (mazeArray[i][j] == 3)
                    System.out.print("E ");
                else if (mazeArray[i][j] == 4)
                    System.out.print("X ");
            }
            System.out.println();
        }
    }
}
