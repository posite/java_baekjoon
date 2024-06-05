import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        final int INF = Integer.MAX_VALUE;
        int[] answer = {0, INF};
        Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        Queue<Entry> pq = new PriorityQueue<>();
        for(int i=0; i<paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            int weight = paths[i][2];
            map.get(a).add(new Edge(b, weight));
            map.get(b).add(new Edge(a, weight));
        }
        int[] visited;
        visited = new int[n+1];
        Arrays.fill(visited, INF); 
        for(int i=0; i<gates.length; i++) {
            pq.add(new Entry(gates[i], 0));
            visited[gates[i]] = 0;
        }
        while(!pq.isEmpty()) {
            Entry current = pq.remove();
            if(current.weight > visited[current.destination]) continue;
            boolean isSummit = false;
            for(int summit: summits) {
                if(current.destination == summit) {
                    isSummit = true;
                    break;
                }
            }
            if(isSummit) continue;
            for(Edge edge: map.get(current.destination)) {
                int dest = edge.destination;
                int weight = Math.max(edge.weight, visited[current.destination]);
                if(visited[dest] > weight) {
                    visited[dest] = weight;
                    pq.add(new Entry(dest, weight));
                    //System.out.println(dest + " " + weight);
                }
            }
        }
        int intensity = Integer.MAX_VALUE;
        int index = 0;
        for(int i=0; i<summits.length; i++) {
            int summit = summits[i];
            if(intensity > visited[summit]) {
                index = summit;
                intensity = visited[summit];
            } else if(intensity == visited[summit]) {
                if(index > summit) {
                    index = summit;
                }
            }
        }
        answer[0] = index;
        answer[1] = intensity;
        return answer;
    }
    
    public class Entry implements Comparable<Entry> {
        int destination, weight;
        public Entry(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Entry o) {
            return this.weight - o.weight;
        }
    }
    
    public class Edge {
        int destination, weight;
        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}