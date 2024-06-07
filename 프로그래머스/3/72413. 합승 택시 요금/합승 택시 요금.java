import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = Integer.MAX_VALUE;
        int min = INF;
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
        int[] fees = new int[n + 1];
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(s, 0));
        Arrays.fill(fees, INF);
        fees[s] = 0;
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.fee > fees[current.node]) continue;
            int fee = current.fee;
            int node = current.node;
            //System.out.println(node + " " + fee + " ");
            for(int i=0; i<=n; i++) {
                if(graph[node][i] != INF && fees[i] > fees[node] + graph[node][i]) {
                    pq.add(new Point(i, fees[node] + graph[node][i]));
                    fees[i] = fees[node] + graph[node][i];
                    //System.out.println(node + " " + fees[node] + " ");
                } 
            }
        }
        //System.out.println(Arrays.toString(fees));
        for(int i=1; i<=n; i++) {
            int[] weights = new int[n + 1];
            Arrays.fill(weights, INF);
            pq.add(new Point(i, 0));
            weights[i] = 0;
            while(!pq.isEmpty()) {
                Point current = pq.remove();
                int fee = current.fee;
                int node = current.node;
                if(fee > weights[node]) continue;
                //System.out.println(node + " " + fee + " ");
                for(int j=0; j<=n; j++) {
                    if(graph[node][j] != INF && weights[j] > weights[node] + graph[node][j]) {
                        pq.add(new Point(j, weights[node] + graph[node][j]));
                        weights[j] = weights[node] + graph[node][j];
                    } 
                }
            
            }
            //System.out.println(weights[a] + " " + weights[b] + " " + fees[i]);
            min = Math.min(min, weights[a] + weights[b] + fees[i]);
        }
        
        return min;
    }
    
    public class Point implements Comparable<Point> {
        int node, fee;
        public Point(int node, int fee) {
            this.node = node;
            this.fee = fee;
        }
        
        @Override
        public int compareTo(Point o) {
            return this.fee - o.fee;
        }
    }
}