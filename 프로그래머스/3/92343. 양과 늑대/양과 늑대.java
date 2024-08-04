class Solution {
    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        visited[0] = true;
        return sheepWolf(info, edges, visited, 1, 0);
    }
    
    public int sheepWolf(int[] info, int[][] edges, boolean[] visited, int sheep, int wolf) {
        if(sheep == wolf) return sheep;
        int max = sheep;
        for(int[] edge: edges) {
            int parent = edge[0];
            int child = edge[1];
            if(visited[parent] && !visited[child]) {
                visited[child] = true;
                if(info[child] == 0) {
                    max = Math.max(max, sheepWolf(info, edges, visited, sheep+1, wolf));
                } else {
                    max = Math.max(max, sheepWolf(info, edges, visited, sheep, wolf+1));
                }
                visited[child] = false;
            }
        }
        return max;
    }
}