import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        for(int i=0; i<n; i++) {
            Arrays.fill(visited[i], n*m);
        }
        visited[0][0] = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.remove();
            //System.out.println(Arrays.toString(current));
            if(current[0] == n-1 && current[1] == m-1) return visited[n-1][m-1]+1;
            for(int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if(maps[nx][ny] == 1) {
                      if(visited[nx][ny] > visited[current[0]][current[1]]+1) {
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = visited[current[0]][current[1]]+1;
                        }  
                    }
                    
                }
            }
        }
        
        return -1;
    }
}