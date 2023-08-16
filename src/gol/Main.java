package gol;
import gol.GolGenerator;
import gol.GolSettings;
import gol.SwingRenderer;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row, column, repetition, count = 0;
        int[][] matriz;
        String begin;
        System.out.println("Enter the row number ");
        row = sc.nextInt();
        System.out.println("Enter the column number ");
        column = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the starting pattern or press 2 to generate a random pattern.");
        begin = sc.nextLine();
        System.out.println("Enter number of repetitions");
        repetition = sc.nextInt();

        if (begin.charAt(0) == '2') {
            matriz = RandomMatriz(row, column);
        } else {
            matriz = Matriz(column, row, begin);
        }
        printMatriz(matriz);
        while (count < repetition) {
            matriz = rules(matriz);
            printMatriz(matriz);
            count++;
        }
        final GolGenerator generator = new GolGenerator() {
            @Override
            public String getNextGenerationAsString(long generation) {
                // Just will switch
                if (generation % 2 == 0) {
                    return "X.X\nX.X\nXXX";
                }
                else
                {
                    return "...\n...\n...";
                }
            }
        };
        SwingRenderer.render(generator, new GolSettings(10, 10,1000, 0));
    }


    public static int[][] Matriz(int col, int row, String par) {
        int[][] matriz = new int[row][col];
        int j = 0;
        int c = 0;

        for (int i = 0; i < par.length(); i++) {
            if (par.charAt(i) != '#') {
                if (par.charAt(i) == '1') {
                    matriz[j][c] = 1;
                } else {
                    matriz[j][c] = 0;
                }
                c++;

                if (c == col) {
                    c = 0;
                    j++;
                }
            } else {
                j++;
                c = 0;
            }
        }

        return matriz;
    }

    public static int[][] RandomMatriz(int rows, int cols) {
        int[][] matriz = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matriz[i][j] = random.nextInt(2); // Genera 0 o 1 aleatoriamente
            }
        }

        return matriz;
    }
    public static int[][] rules(int[][] Matriz) {
        int[][] newMatriz = new int[Matriz.length][Matriz[0].length];
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[0].length; j++) {
                int count = Count_neighbours(Matriz, i, j);
                //System.out.println(i+" "+j+" Tengo "+ count+" vecinos");
                if (Matriz[i][j] == 1) {
                    if (count == 2 || count == 3) {
                        newMatriz[i][j] = 1;
                    } else {
                        newMatriz[i][j] = 0;
                    }
                } else {
                    if (count == 3) {
                        newMatriz[i][j] = 1;
                    }
                }
            }
        }

        return newMatriz;
    }
    public static void printMatriz(int[][] Matriz) {
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[0].length; j++) {
                System.out.print(Matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }
    public static int Count_neighbours(int[][] Matriz, int x, int y) {
        int neighbours = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nuevaX = x + dx[i];
            int nuevaY = y + dy[i];

            if (nuevaX >= 0 && nuevaX < Matriz.length && nuevaY >= 0 && nuevaY < Matriz[0].length) {
                neighbours += Matriz[nuevaX][nuevaY];
            }
        }

        return neighbours;
    }
}
