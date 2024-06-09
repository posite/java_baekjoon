import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        final int INF = Integer.MAX_VALUE;
        int[] distances = new int[n+1];
        Arrays.fill(distances, INF);
        Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int i=0; i<paths.length; i++) {
            map.get(paths[i][0]).add(new Edge(paths[i][1], paths[i][2]));
            map.get(paths[i][1]).add(new Edge(paths[i][0], paths[i][2]));
        }
        Queue<Entry> pq = new PriorityQueue<>();
        for(int start: gates) {
            distances[start] = 0;
            pq.add(new Entry(start, 0));
        }
        while(!pq.isEmpty()) {
            Entry current = pq.remove();
            boolean isSummit = false;
            for(int summit: summits) {
                if(summit == current.node) {
                    isSummit = true;
                    break;
                }
            }
            if(isSummit) continue;
            if(current.distance > distances[current.node]) continue;
            for(Edge edge: map.get(current.node)) {
                int edgeMax = Math.max(current.distance, edge.weight);
                if(distances[edge.destination] > edgeMax) {
                    distances[edge.destination] = edgeMax;
                    pq.add(new Entry(edge.destination, edgeMax));
                }
            }
        }
        int min = INF;
        int index = 0;
        for(int summit: summits) {
            if(min > distances[summit]) {
                min = distances[summit];
                index = summit;
            } else if(min == distances[summit]) {
                if(index > summit) {
                    index = summit;
                }
            }
        }
        
        return new int[]{index, min};
    }
    
    public class Entry implements Comparable<Entry> {
        int node, distance;
        public Entry(int node, int distance)  {
            this.node = node;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Entry o) {
            return this.distance - o.distance;
        }
    }
    
    public class Edge {
        int destination, weight;
        public Edge(int destination, int weight)  {
            this.destination = destination;
            this.weight = weight;
        }
    }
}