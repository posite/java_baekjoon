import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n-lost.length;
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(lost);
        for(int i=0; i<=n+1; i++) {
            map.put(i, 0);
        }
        outer: for(int res: reserve) {
            for(int lostS: lost) {
                if(res == lostS) {
                    answer++;
                    map.put(lostS, -1);
                    continue outer;
                }
            }   
            map.put(res, 1);
        }
        //System.out.println(map);
        for(int student: lost) {
            if(map.get(student)==-1)continue;
            System.out.print(student + " ");
            if(map.get(student-1) == 1) {
                map.put((student-1), 0);
                answer++;
                System.out.println(map.get(student-1));
                continue;
            }
            if(map.get(student+1) == 1) {
                map.put((student+1), 0);
                System.out.println(map.get(student+1));
                answer++;
                continue;
            }
        }
        
        return answer;
    }
}