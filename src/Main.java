import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static Scanner sc = new Scanner(System.in);
    private final static int INFINITY = Integer.MAX_VALUE/2;
    private final static int[][] WTEST = {
            {0,1,INFINITY, 1,5},
            {9,0,3,2,INFINITY},
            {INFINITY,INFINITY,0,4,INFINITY},
            {INFINITY,INFINITY,2,0,3},
            {3,INFINITY,INFINITY,INFINITY,0}
    };

    private final static int[][] WTEST2 = {
            {0,1,5,10},
            {5,0,2,6},
            {4,9,0,5},
            {7,2,1,0}
    };

    public static void main(String[] args) {
	// test
        int[][] test = WTEST;
        int n = test.length;
        Data data = floyd(n, test);
        System.out.println("TEST");
        print2DArr(test);

        System.out.println("D");
        print2DArr(data.getD());

        System.out.println("P");
        print2DArr(data.getP());

        while (true){
            menu(data);
        }
    }

    private static void menu(Data data){
        System.out.println("Enter 2 vertices or enter -1 to quit");
        int val = sc.nextInt();

        if (val == -1)
            System.exit(1);

        int val2 = sc.nextInt();
        int weight = 0;

        path(data.getP(), data.getD(), val, val2, weight);
        System.out.println("Weight is " + weight);

    }

    private static void path(int[][] p, int[][]d, int q, int r, int weight){
        if (p[q][r] != -1){
            path(p, d, q, p[q][r], weight+d[q][p[q][r]]);
            System.out.printf("v %d\t" + p[q][r]);
            path(p, d, p[q][r], r, weight+d[p[q][r]][r]);
        }
    }

    private static Data floyd(int n, int[][] w){
        int d[][] = new int[n][n];
        int p[][] = new int[n][n];

        for (int i=0; i < n; i++){
            for (int j=0; j<n; j++){
                p[i][j] = -1;
            }
        }

        //make d copy of w
        for (int i=0; i < n; i++){
            System.arraycopy(w[i],0,d[i],0,n);
        }

        for (int k=0; k < n; k++){
            for (int i=0; i < n; i++){
                for (int j=0; j < n; j++){
                    if ((d[i][k] + d[k][j]) < d[i][j]){
                        p[i][j] = k;
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }

        return new Data(p, d);
    }

    private static void print2DArr(int[][] arr){
        for (int i=0; i< arr.length; i++){
            for (int j=0; j< arr.length; j++){
                if (arr[i][j] == INFINITY){
                    System.out.printf("%-10s","Infinity");
                }else{
                    System.out.printf("%-10d",arr[i][j]);
                }
            }
            System.out.println();
        }
    }

}
