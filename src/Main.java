import java.util.ArrayList;
import java.util.Arrays;

/* Citation:
 https://en.m.wikipedia.org/wiki/Dijkstra%27s_algorithm
 https://en.m.wikipedia.org/wiki/Dijkstra%27s_algorithm
 - Isaac Wu, August 6th, 2025 */

// 1=right, 2=down, 3=diagonal

public class Main {
    private static int n = 3;
    private static int[][] board = new int[n][n];
    private static ArrayList<int[]> unvisited = new ArrayList<int[]>(); // int[]={row, col, costFromStart}
    private static ArrayList<int[]> visited = new ArrayList<int[]>(); // int[]={row, col, minCostFromStart}

    private static void FillBoard() {
        for (int i=0; i<n; i++) {for (int j=0; j<n; j++) {
            board[i][j] = (int)(101*Math.random());
            if (i==0 && j==0) {
                int[] source = {i, j, 0};
                unvisited.add(source);
            }
            else {
                int[] node = {i, j, -1};
                unvisited.add(node);
            }
        }}
    }

    public static void main(String[] args) {
        FillBoard();
        for (int[] row : board) {System.out.println(Arrays.toString(row));}
        for (int[] node : unvisited) {System.out.println(Arrays.toString(node));}
    }
}