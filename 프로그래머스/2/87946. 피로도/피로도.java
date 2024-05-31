import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        List<List<Integer>> list = new ArrayList();
        backtraking(list, new ArrayList(), dungeons, k, visited);
        int max = 0;
        for(List<Integer> explore: list) {
            if(explore.size() > max) {
                max = explore.size();
            }
        }
        return max;
    }
    
    public void backtraking(List<List<Integer>> list, ArrayList<Integer> now, int[][] dungeons, int fatigue, boolean[] visited) {
        //System.out.println(now);
        list.add(new ArrayList<>(now));
        if(now.size() == dungeons.length) {
            return;
        }
        for(int i=0; i<dungeons.length; i++) {
            int[] dungeon = dungeons[i];
            if(fatigue >= dungeon[0] && !visited[i]) {
                visited[i] = true;
                now.add(i);
                backtraking(list, now, dungeons, (fatigue - dungeon[1]), visited);
                visited[i] = false;
                now.remove(now.size() -1);
            }
        }
        
    }
}