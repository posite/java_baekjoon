import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        final int INF = Integer.MAX_VALUE;
        int[] answer = {0, INF};
        Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        Deque<Integer> queue = new ArrayDeque<>();
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
        for(int gate: gates) {
            queue.add(gate);
            visited[gate] = 0;
        }
        while(!queue.isEmpty()) {
            int current = queue.poll();
            boolean isSummit = false;
            for(int summit: summits) {
                if(current == summit) {
                    isSummit = true;
                    break;
                }
            }
            if(isSummit) continue;
            for(Edge edge: map.get(current)) {
                int dest = edge.destination;
                int weight = Math.max(edge.weight, visited[current]);
                if(visited[dest] > weight) {
                    visited[dest] = weight;
                    queue.add(dest);
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
            }else if(intensity == visited[summit]) {
                if(index > summit) {
                    index = summit;
                }
            }
        }
        answer[0] = index;
        answer[1] = intensity;
        return answer;
    }
    
    public class Edge {
        int destination, weight;
        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}