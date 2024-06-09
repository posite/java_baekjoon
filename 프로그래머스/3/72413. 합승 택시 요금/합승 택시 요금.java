import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = Integer.MAX_VALUE;
        int min = INF;
        int[][] graph = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
        int[] startDistance = new int[n+1];
        int[] aDistance = new int[n+1];
        int[] bDistance = new int[n+1];
        Arrays.fill(startDistance, INF);
        Arrays.fill(aDistance, INF);
        Arrays.fill(bDistance, INF);
        
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(s, 0));
        startDistance[s] = 0;
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.distance > startDistance[current.node]) continue;
            for(int i=1; i<=n; i++) {
                if(graph[current.node][i] != INF) {
                    int nextDistance = current.distance + graph[current.node][i];
                    if(startDistance[i] > nextDistance) {
                        pq.add(new Point(i, nextDistance));
                        startDistance[i] = nextDistance;
                    }
                }
            }
        }
        
        pq.add(new Point(a, 0));
        aDistance[a] = 0;
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.distance > aDistance[current.node]) continue;
            for(int i=1; i<=n; i++) {
                if(graph[current.node][i] != INF) {
                    int nextDistance = current.distance + graph[current.node][i];
                    if(aDistance[i] > nextDistance) {
                        pq.add(new Point(i, nextDistance));
                        aDistance[i] = nextDistance;
                    }
                }
            }
        }
        
        
        pq.add(new Point(b, 0));
        bDistance[b] = 0;
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.distance > bDistance[current.node]) continue;
            for(int i=1; i<=n; i++) {
                if(graph[current.node][i] != INF) {
                    int nextDistance = current.distance + graph[current.node][i];
                    if(bDistance[i] > nextDistance) {
                        pq.add(new Point(i, nextDistance));
                        bDistance[i] = nextDistance;
                    }
                }
            }
        }
        for(int i=1; i<=n; i++) {
            min = Math.min(min, startDistance[i] + aDistance[i] + bDistance[i]);
        }
        return min;
    }
    
    public class Point implements Comparable<Point> {
        int node, distance;
        public Point(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Point o) {
            return this.distance - o.distance;
        }
    }
}