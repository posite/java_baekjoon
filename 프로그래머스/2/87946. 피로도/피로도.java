import java.util.*;

class Solution {
    int max = 0;
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        exploreDungeon(new ArrayList<>(), dungeons, k, visited);
        return max;
    }
    
    public void exploreDungeon(List<Integer> now, int[][] dungeons, int health, boolean[] visited) {
        max = Math.max(max, now.size());
        for(int i=0; i<dungeons.length; i++) {
            if(visited[i]) {
                continue;
            }
            if(dungeons[i][0] > health) {
                continue;
            }
            now.add(i);
            visited[i] = true;
            exploreDungeon(now, dungeons, health - dungeons[i][1], visited);
            now.remove(now.size()-1);
            visited[i] = false;
        }
    }
        
}