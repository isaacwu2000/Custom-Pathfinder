import java.util.ArrayList;
import java.util.Arrays;

/* Citation:
 https://en.m.wikipedia.org/wiki/Dijkstra%27s_algorithm
 https://en.m.wikipedia.org/wiki/Dijkstra%27s_algorithm
 - Isaac Wu, August 6th, 2025 */

// 1=right, 2=down, 3=diagonal

public class Main {
    private static int n = 4;
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
// todo: don't go to nodes from nodes that are already above the lowest cost

    private static void mappingCostsFromStart() {
        if (!unvisited.isEmpty()) {
            int[] defaultNode = {-1, -1, 100*n};
            int[] currentNode = defaultNode;
            for (int[] node : unvisited) {
                if (node[2] != -1 && node[2]<currentNode[2]) {
                    currentNode = node;
                }
            }
            if (currentNode != defaultNode) {
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
        }
    }

    public static void main(String[] args) {
        FillBoard();
        for (int[] row : board) {System.out.println(Arrays.toString(row));}

        for (int i=0; i<n*n; i++) {
            mappingCostsFromStart();
        }
        System.out.println("------------");
        for (int[] node : visited) {
            if (node[0]==n-1 && node[1]==n-1) {
                System.out.println(Arrays.toString(node));
            }
        }
    }
}