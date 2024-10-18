import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class VarC {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите n");
        int n = scan.nextInt();
        System.out.println("Введите К");
        int k = scan.nextInt();
        Random random = new Random();
        int[][] matrix = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }
        printMatrix(matrix);//обычная только созданная матрица
        sortMatrixByColumn(matrix,k);//1 сортирую

        //2 Циклический сдвиг
        System.out.println("Введите направление сдвига(влево/вправо/вниз/вверх");
        String direction = scan.next();
        int[][] shiftedMatrix = cyclicShift(matrix, k, direction);
        System.out.println("Матрица после сдвига:");
        printMatrix(shiftedMatrix);

        //3 Наибольшее число возрастающих и убывающих элементов
        int[] lengths = longestIncreasingDecreasing(matrix);
        System.out.println("Максимальная длина возрастающей последовательности: " + lengths[0]);
        System.out.println("Максимальная длина убывающей последовательности: " + lengths[1]);

        //4 Сумма элементов между первыми положительными элементами
        int[] sumsBetweenPositives = sumBetweenPositiveElements(matrix);
        System.out.println("Суммы элементов между первыми положительными элементами в строках: " + Arrays.toString(sumsBetweenPositives));

        //5 Создание матрицы от 1 до k
        int[][] numberMatrix = createNumberMatrix(k);
        System.out.println("Матрица от 1 до " + k + ":");
        printMatrix(numberMatrix);


        //6 Округление элементов матрицы
        double[][] doubleMatrixForRounding = new double[n][n]; // Пример матрицы для округления
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                doubleMatrixForRounding[i][j]=random.nextDouble(2 * n + 1) - n;
            }
        }
        System.out.println("Исходная матрица для округления:");
        printMatrix(doubleMatrixForRounding);
        int[][] roundedMatrix = roundMatrix(doubleMatrixForRounding);
        System.out.println("Матрица с округленными элементами:");
        printMatrix(roundedMatrix);

        //8 Определитель матрицы
        double detValue = determinant(doubleMatrixForRounding);
        System.out.println("Определитель матрицы: " + detValue);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%3s",matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%3s",matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    // 1
    //Упорядочить строки (столбцы) матрицы в порядке возрастания значений
    //элементов k-го столбца (строки).
    public static void sortMatrixByColumn(int[][] matrix, int k) {
        Arrays.sort(matrix, (a, b) -> Integer.compare(a[k], b[k]));
    }




//2
    //Выполнить циклический сдвиг заданной матрицы на k позиций вправо
    //(влево, вверх, вниз).

    public static int[][] cyclicShift(int[][] matrix, int k, String direction) {
        int n = matrix.length;
        int[][] shiftedMatrix = new int[n][n];

        switch (direction.toLowerCase()) {
            case "вправо":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        shiftedMatrix[i][(j + k) % n] = matrix[i][j];
                    }
                }
                break;
            case "влево":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        shiftedMatrix[i][(j - k + n) % n] = matrix[i][j];
                    }
                }
                break;
            case "вниз":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        shiftedMatrix[(i + k) % n][j] = matrix[i][j];
                    }
                }
                break;
            case "вверх":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        shiftedMatrix[(i - k + n) % n][j] = matrix[i][j];
                    }
                }
                break;
        }
        return shiftedMatrix;
    }
    //3
    // Поиск максимальной длины возрастающих и убывающих последовательностей
    public static int[] longestIncreasingDecreasing(int[][] matrix) {
        int maxInc = 0, maxDec = 0;

        for (int[] row : matrix) {
            int currentInc = 1, currentDec = 1;
            for (int i = 1; i < row.length; i++) {
                if (row[i] > row[i - 1]) {
                    currentInc++;
                    maxInc = Math.max(maxInc, currentInc);
                    currentDec = 1;
                } else if (row[i] < row[i - 1]) {
                    currentDec++;
                    maxDec = Math.max(maxDec, currentDec);
                    currentInc = 1;
                } else {
                    currentInc = 1;
                    currentDec = 1;
                }
            }
        }
        return new int[]{maxInc, maxDec};
    }
    //4
    // Сумма элементов между первыми положительными элементами
    public static int[] sumBetweenPositiveElements(int[][] matrix) {
        int[] sums = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            boolean foundFirstPositive = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    if (!foundFirstPositive) {
                        foundFirstPositive = true;
                    } else {
                        sums[i] = sum;
                        break;
                    }
                } else if (foundFirstPositive) {
                    sum += matrix[i][j];
                }
            }
        }
        return sums;
    }
    //5
// Создание матрицы от 1 до k
    public static int[][] createNumberMatrix(int k) {
        int size = (int)Math.sqrt(k);
        int[][] numberMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                numberMatrix[i][j] = i * size + j + 1;
            }
        }
        return numberMatrix;
    }
    //6
    // Округление элементов матрицы
    public static int[][] roundMatrix(double[][] matrix) {
        int[][] roundedMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                roundedMatrix[i][j] = (int)Math.round(matrix[i][j]);
            }
        }
        return roundedMatrix;
    }
    //8
    // Определитель матрицы
    public static double determinant(double[][] matrix) {
        int n = matrix.length;

        // Если матрица нулевая, возвращаем 0
        if (n == 0) return 0;

        // Если матрица 1x1, возвращаем единственный элемент
        if (n == 1) return matrix[0][0];

        // Если матрица 2x2, возвращаем определитель по формуле ad - bc
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;

        for (int p = 0; p < n; p++) {
            double[][] temp = new double[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int k = 0; // Индекс для столбцов в temp
                for (int j = 0; j < n; j++) {
                    if (j == p) continue;
                    temp[i - 1][k++] = matrix[i][j];
                }
            }
            det += Math.pow(-1, p) * matrix[0][p] * determinant(temp);
        }

        return det;
    }

}
