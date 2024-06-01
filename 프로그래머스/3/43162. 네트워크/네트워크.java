class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                answer++;
                dfs(computers, visited, i, n);
            }
        }
        return answer;
    
    }
    
    public void dfs(int[][] computers, boolean[] visited, int x, int n) {
        visited[x] = true;
        for(int i=0; i<n; i++) {
            if(i != x) {
                if(computers[x][i] == 1 && computers[i][x] == 1) {
                    if(!visited[i]) {
                        dfs(computers, visited, i, n);
                    }
                }
            }
        }
    }
}