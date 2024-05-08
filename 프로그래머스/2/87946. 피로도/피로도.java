import java.util.*;

class Solution {
    private static int max = 0;
    public int solution(int k, int[][] dungeons) {
        fatigue(k, dungeons, new ArrayList());
        return max;
    }
    
    public void fatigue(int k, int[][] dungeons, ArrayList<Integer> now) {
        max = Math.max(now.size(), max);
        for(int i = 0; i < dungeons.length; i++) {
            //최소 피로도 확인, 던전 중복 확인
            if(k >= dungeons[i][0] && !now.contains(i)) {
                now.add(i);
                fatigue(k - dungeons[i][1], dungeons, now);
                now.remove(now.size() - 1);
            }
        }
    }
}