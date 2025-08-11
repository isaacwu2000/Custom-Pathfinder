import java.util.ArrayList;
import java.util.Arrays;

/* Citation:
 https://en.m.wikipedia.org/wiki/Dijkstra%27s_algorithm
 - Isaac Wu, August 6th, 2025 */

// 1=right, 2=down, 3=diagonal

public class DijkstrasRightDownDiagonal {
    private static int n = 200;
    private static int[][] board = new int[n][n];
    private static ArrayList<int[]> unvisited = new ArrayList<int[]>(); // int[]={row, col, costFromStart}
    private static ArrayList<int[]> visited = new ArrayList<int[]>(); // int[]={row, col, minCostFromStart}

    private static void FillBoard() {
        for (int i=0; i<n; i++) {for (int j=0; j<n; j++) {
            board[i][j] = (int)(101*Math.random());
            if (i==0 && j==0) {
                int[] source = {i, j, board[0][0]};
                unvisited.add(source);
            }
            else {
                int[] node = {i, j, -1};
                unvisited.add(node);
            }
        }}
    }

    private static boolean mappingCostsFromStart() {
        if (!unvisited.isEmpty()) {
            int[] currentNode = {-1, -1, 100*n};
            for (int[] node : unvisited) {
                if (node[2] != -1 && node[2]<currentNode[2]) {
                    currentNode = node;
                }
            }
            if (currentNode[0]==n-1 && currentNode[1]==n-1) {
                visited.add(currentNode);
                return false;
            }

            for (int i = 0; i < unvisited.size(); i++) {
                int[] node = unvisited.get(i);
                if (node[0]==currentNode[0] && node[1]==currentNode[1]+1) { // Right
                    if (node[2]==-1) {
                        node[2] = currentNode[2]+board[node[0]][node[1]];
                    } else if (node[2] > currentNode[2]+board[node[0]][node[1]]) {
                        node[2] = currentNode[2]+board[node[0]][node[1]];
                    }
                } else if (node[0]==currentNode[0]+1 && node[1]==currentNode[1]) { // Bottom
                    if (node[2]==-1) {
                        node[2] = currentNode[2]+board[node[0]][node[1]];
                    } else if (node[2] > currentNode[2]+board[node[0]][node[1]]) {
                        node[2] = currentNode[2]+board[node[0]][node[1]];
                    }
                } else if (node[0]==currentNode[0]+1 && node[1]==currentNode[1]+1) { // Diagonal
                    if (node[2]==-1) {
                        node[2] = currentNode[2]+board[node[0]][node[1]];
                    } else if (node[2] > currentNode[2]+board[node[0]][node[1]]) {
                        node[2] = currentNode[2]+board[node[0]][node[1]];
                    }
                }
            }
            visited.add(currentNode);
            unvisited.remove(currentNode);
        }
        return true;
    }

    public static void main(String[] args) {
        FillBoard();
        for (int[] row : board) {System.out.println(Arrays.toString(row));}

        boolean searching = true;
        while (searching && !unvisited.isEmpty()) {
            searching = mappingCostsFromStart();
        }
        System.out.println("------------");
        for (int[] node : visited) {
            System.out.println(Arrays.toString(node));
            if (node[0]==n-1 && node[1]==n-1) {
                System.out.println("Minimum Cost: " + node[2]);
            }
        }
    }
}