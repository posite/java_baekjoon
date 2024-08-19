import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] intensities = new int[n+1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        Arrays.sort(summits);
        Queue<Point> pq = new PriorityQueue<>();
        for(int gate: gates) {
            pq.add(new Point(gate, 0));
            intensities[gate] = 0;
        }
        Map<Integer, List<Point>> map = new HashMap<>();
        for(int i=1; i<=n; i++) map.put(i, new ArrayList<>());
        for(int[] path: paths) {
            map.get(path[0]).add(new Point(path[1], path[2]));
            map.get(path[1]).add(new Point(path[0], path[2]));
        }
        
        outer: while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.intensity > intensities[current.node]) continue;
            for(int summit: summits) {
                if(current.node == summit) continue outer;
            }
            
            for(Point next: map.get(current.node)) {
                int nextIntensity = Math.max(next.intensity, intensities[current.node]);
                if(intensities[next.node] > nextIntensity) {
                    pq.add(new Point(next.node, nextIntensity));
                    intensities[next.node] = nextIntensity;
                }
            }
            
        }
        int min = Integer.MAX_VALUE;
        int out = -1;
        for(int summit: summits) {
            if(min > intensities[summit]) {
                out = summit;
                min = intensities[summit];
            }
        }
        
        int[] answer = {out, min};
        return answer;
    }
    
    public class Point implements Comparable<Point> {
        int node, intensity;
        
        public Point(int node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Point o) {
            return this.intensity - o.intensity;
        }
    }
}