import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "color 02").inheritIO().start().waitFor();

        Scanner scanner = new Scanner(System.in);
        int width, height, userInput;
        MazeGenerator mazeGenerator;
        MazeSolver mazeSolver;

        while (true) {
            try {
                System.out.println("Maze  (Enter...)");
                scanner.nextLine();

                System.out.print("Width(10-30): ");
                width = scanner.nextInt();
                if (!(width >= 10 && width <= 30)) {
                    throw new Exception();
                }

                System.out.print("Height(10-30): ");
                height = scanner.nextInt();
                if (!(height >= 10 && height <= 30)) {
                    throw new Exception();
                }

                mazeGenerator = new MazeGenerator(width, height);
                mazeGenerator.createMaze();
                MazePrinter.printMaze(mazeGenerator.getMazeMatrix(), width, height);

                System.out.print("\nSolve with stack(1) / Solve with queue(2): ");
                userInput = scanner.nextInt();
                if (userInput == 1) {
                    mazeSolver = new MazeSolver();
                    mazeSolver.solveMazeWithStack(mazeGenerator.getMazeMatrix(), width, height);
                    break;
                } else if (userInput == 2) {
                    mazeSolver = new MazeSolver();
                    mazeSolver.solveMazeWithQueue(mazeGenerator.getMazeMatrix(), width, height);
                    break;
                } else
                    throw new Exception();

            } catch (Exception e) {
                System.out.println("Invalid!\n");
            }
        }

        System.out.println("Maze solved!");
        scanner.nextLine();
        scanner.nextLine();
    }
}
