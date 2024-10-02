import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int curE = -30001;
        Queue<Entry> pq = new PriorityQueue<>();
        for(int[] route: routes) {
            pq.add(new Entry(route[0], route[1]));
        }
        
        while(!pq.isEmpty()) {
            Entry current = pq.remove();
            if(current.start > curE) {
                curE = current.end;
                answer++;
            }
        }
        
        
        return answer;
    }
    
    public class Entry implements Comparable<Entry> {
        int start, end;
        
        public Entry(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Entry o) {
            return this.end - o.end;
        }
    }
}