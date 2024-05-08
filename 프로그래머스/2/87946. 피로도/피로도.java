import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        List<List<Integer>> list = new ArrayList();
        fatigue(k, dungeons, new ArrayList(), list);
        int max = 0;
        for(List<Integer> order : list) {
            if(order.size() > max) {
                max = order.size();
            }
        }
        
        return max;
    }
    
    public void fatigue(int k, int[][] dungeons, ArrayList<Integer> now, List<List<Integer>> list) {
        list.add(new ArrayList<>(now));
        for(int i = 0; i < dungeons.length; i++) {
            //최소 피로도 확인
            if(k >= dungeons[i][0] && !now.contains(i)) {
                now.add(i);
                fatigue(k - dungeons[i][1], dungeons, now, list);
                now.remove(now.size() - 1);
            }
        }
    }
}