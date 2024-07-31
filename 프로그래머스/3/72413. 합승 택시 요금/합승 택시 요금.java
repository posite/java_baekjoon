import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] graph = new int[n+1][n+1];
        for(int[] fare: fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
        
        int[] startDistance = new int[n+1];
        int[] aDistance = new int[n+1];
        int[] bDistance = new int[n+1];
        
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(s, 0));
        while(!pq.isEmpty()) {
            Point current = pq.poll();
            if(current.distance > startDistance[current.node]) continue;
            for(int i=0; i<=n; i++) {
                if(i == s) continue;
                if(graph[current.node][i] == 0) continue;
                int nextDistance = graph[current.node][i] + current.distance;
                if(startDistance[i] == 0 || startDistance[i] > nextDistance) {
                    pq.add(new Point(i, nextDistance));
                    startDistance[i] = nextDistance;
                }
            }
        }
        
        pq.add(new Point(a, 0));
        while(!pq.isEmpty()) {
            Point current = pq.poll();
            if(current.distance > aDistance[current.node]) continue;
            for(int i=0; i<=n; i++) {
                if(i == a) continue;
                if(graph[current.node][i] == 0) continue;
                int nextDistance = graph[current.node][i] + current.distance;
                if(aDistance[i] == 0 || aDistance[i] > nextDistance) {
                    pq.add(new Point(i, nextDistance));
                    aDistance[i] = nextDistance;
                }
            }
        }
        
        pq.add(new Point(b, 0));
        while(!pq.isEmpty()) {
            Point current = pq.poll();
            if(current.distance > bDistance[current.node]) continue;
            for(int i=0; i<=n; i++) {
                if(i == b) continue;
                if(graph[current.node][i] == 0) continue;
                int nextDistance = graph[current.node][i] + current.distance;
                if(bDistance[i] == 0 || bDistance[i] > nextDistance) {
                    pq.add(new Point(i, nextDistance));
                    bDistance[i] = nextDistance;
                }
            }
        }
        
        
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            if(startDistance[i] == 0 && aDistance[i] == 0 && bDistance[i] == 0) continue;
            min = Math.min(min, (startDistance[i] + aDistance[i] + bDistance[i]));
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