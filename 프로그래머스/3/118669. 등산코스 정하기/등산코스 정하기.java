import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] intensities = new int[n+1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        Arrays.sort(summits);
        Map<Integer, List<Route>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] path: paths) {
            map.get(path[0]).add(new Route(path[1], path[2]));
            map.get(path[1]).add(new Route(path[0], path[2]));
        }
        Queue<Route> pq = new PriorityQueue<>();
        for(int gate: gates) {
            pq.add(new Route(gate, 0));
            intensities[gate] = 0;
        }
        outer: while(!pq.isEmpty()) {
            Route current = pq.remove();
            for(int summit: summits) {
                if(current.node == summit) {
                    continue outer;
                }
            }
            if(current.intensity > intensities[current.node]) continue;
            for(Route next: map.get(current.node)) {
                int nextIntensity = Math.max(next.intensity, current.intensity);
                if(intensities[next.node] > nextIntensity) {
                    pq.add(new Route(next.node, nextIntensity));
                    intensities[next.node] = nextIntensity;
                }
            }
        }
        int index = 0;
        int min = Integer.MAX_VALUE;
        for(int summit: summits) {
            if(min > intensities[summit]) {
                min = intensities[summit];
                index = summit;
            }
        }
        int[] answer = {index, min};
        return answer;
    }
    
    public class Route implements Comparable<Route> {
        int node, intensity;
        
        public Route(int node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Route o) {
            return this.intensity - o.intensity;
        }
    }
}