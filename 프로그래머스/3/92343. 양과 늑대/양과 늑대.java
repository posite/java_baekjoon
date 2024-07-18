import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        visited[0] = true;
        int max = sheepWolf(visited, info, edges, 1, 0);
        return max;
    }
    
    public int sheepWolf(boolean[] visited, int[] info, int[][] edges, int sheep, int wolf) {
        if (sheep == wolf) return sheep;
        int max = sheep;
        for(int[] edge: edges) {
            int parent = edge[0];
            int child = edge[1];
            if(visited[parent] && !visited[child]) {
                visited[child] = true;
                if(info[child] == 0) {
                    max = Math.max(max, sheepWolf(visited, info, edges, sheep+1, wolf));
                } else {
                    max = Math.max(max, sheepWolf(visited, info, edges, sheep, wolf+1));
                }
                visited[child] = false;
            }
        }
        return max;
    }
}