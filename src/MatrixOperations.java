import java.util.Random;
import java.util.Scanner;

public class MatrixOperations {

    private static final int MIN_RANDOM_VALUE = -100;
    private static final int MAX_RANDOM_VALUE = 100;
    private static final int MAX_MATRIX_SIZE = 20;

    private int[][] matrix;
    private int rows;
    private int cols;

    public MatrixOperations(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }

    public void createMatrixManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Некоректний ввід. Введіть ціле число:");
                    scanner.next(); // Видаляємо некоректний ввід
                }
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
    }

    public void createMatrixRandomly() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = MIN_RANDOM_VALUE + random.nextInt(MAX_RANDOM_VALUE - MIN_RANDOM_VALUE + 1);
            }
        }
    }

    public void findMinMaxAvg() {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Матриця порожня.");
            return;
        }

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        double sum = 0;

        for (int[] row : matrix) {
            for (int val : row) {
                minVal = Math.min(minVal, val);
                maxVal = Math.max(maxVal, val);
                sum += val;
            }
        }

        double avg = sum / (rows * cols);

        System.out.println("Мінімальний елемент: " + minVal);
        System.out.println("Максимальний елемент: " + maxVal);
        System.out.println("Середнє арифметичне: " + avg);
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість рядків (не більше " + MAX_MATRIX_SIZE + "): ");
        int rows = scanner.nextInt();
        System.out.print("Введіть кількість стовпців (не більше " + MAX_MATRIX_SIZE + "): ");
        int cols = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        if (rows <= 0 || cols <= 0 || rows > MAX_MATRIX_SIZE || cols > MAX_MATRIX_SIZE) {
            System.out.println("Некоректні розміри матриці.");
            scanner.close();
            return;
        }

        MatrixOperations matrixOps = new MatrixOperations(rows, cols);

        System.out.print("Як створити матрицю? (m - вручну, r - випадково): ");
        char choice = scanner.next().charAt(0);

        if (choice == 'm') {
            matrixOps.createMatrixManually();
        } else if (choice == 'r') {
            matrixOps.createMatrixRandomly();
        } else {
            System.out.println("Некоректний вибір.");
            scanner.close();
            return;
        }

        System.out.println("\nЗгенерована матриця:");
        matrixOps.printMatrix();
        matrixOps.findMinMaxAvg();
        scanner.close();
    }
}
