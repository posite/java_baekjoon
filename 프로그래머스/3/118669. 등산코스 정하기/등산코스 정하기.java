import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        Map<Integer, List<Point>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for(int i=0; i<paths.length; i++) {
            map.get(paths[i][0]).add(new Point(paths[i][1], paths[i][2]));
            map.get(paths[i][1]).add(new Point(paths[i][0], paths[i][2]));
        }
        
        int[] intensities = new int[n+1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        Queue<Point> pq = new PriorityQueue<>();
        for(int gate: gates) {
            pq.add(new Point(gate, 0));
            intensities[gate] = 0;
        }
    
        outer: while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.weight > intensities[current.node]) continue;
            for(int summit: summits) {
                if(current.node == summit) continue outer;
            }
            
            for(Point next: map.get(current.node)) {
                int nextIntensity = Math.max(current.weight, next.weight);
                if(intensities[next.node] > nextIntensity) {
                    pq.add(new Point(next.node, nextIntensity));
                    intensities[next.node] = nextIntensity;
                }
            }
        }
        int index = -1;
        int min = Integer.MAX_VALUE;
        
        for(int summit: summits){
            if(min > intensities[summit]) {
                index = summit;
                min = intensities[summit];
            } else if(min == intensities[summit]) {
                if(index > summit) {
                    index = summit;
                }
            }
        } 
        answer[0] = index;
        answer[1] = min;
        
        return answer;
    }
    
    public class Point implements Comparable<Point> {
        int node, weight;
        
        public Point(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }
}