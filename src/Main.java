import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row, column;
        int[][] matriz;
        String begin;
        System.out.println("Enter the row number ");
        row =sc.nextInt();
        System.out.println("Enter the column number ");
        column = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the start pattern ");
        begin = sc.nextLine();
        matriz=Matriz(column,row,begin);
        for (int i = 0; i < matriz.length ; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }



    public static int[][] Matriz (int col, int row, String par){
        int[][] Matriz = new int[row][col];
        int j = 0;
        int c = 0;

        for (int i = 0; i < par.length(); i++) {
            if (par.charAt(i) != '#') {
                switch (par.charAt(i)) {
                    case 'X':
                        Matriz[j][c] = 1;
                        break;
                    case 'Y':
                        Matriz[j][c] = 2;
                        break;
                    case 'Z':
                        Matriz[j][c] = 0;
                        break;
                    default:
                        Matriz[j][c] = '0';
                        break;
                }
                c++;
                if (c == 3) {
                    c = 0;
                    j++;
                }
            }
        }


    return Matriz;}
}