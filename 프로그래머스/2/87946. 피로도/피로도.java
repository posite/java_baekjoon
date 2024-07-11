import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        List<List<Integer>> list = new ArrayList();
        boolean[] visited = new boolean[dungeons.length];
        fatigue(list, new ArrayList(), dungeons, visited, k);
    
        int max = 0;
        //System.out.println(list);
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).size() > max) {
                max = list.get(i).size();
            }
        }
        return max;
    }
    
    public void fatigue(List<List<Integer>> list, List<Integer> now, int[][] dungeons, boolean[] visited, int health) {
        list.add(new ArrayList<>(now));
        
        for(int i=0; i<dungeons.length; i++) {
            if(!visited[i] && health >= dungeons[i][0]) {
                now.add(i);
                visited[i] = true;
                fatigue(list, now, dungeons, visited, health - dungeons[i][1]);
                now.remove(now.size() -1);
                visited[i] = false;
            }
        }
        
    }
}