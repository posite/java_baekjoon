import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[] startFees = new int[n+1];
        int[] aFees = new int[n+1];
        int[] bFees = new int[n+1];
        
        
        Map<Integer, List<Point>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int i=0; i<fares.length; i++) {
            map.get(fares[i][0]).add(new Point(fares[i][1], fares[i][2]));
            map.get(fares[i][1]).add(new Point(fares[i][0], fares[i][2]));
        }
        
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(s, 0));
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.fee > startFees[current.node]) continue;
            for(Point next: map.get(current.node)) {
                if(next.node == s) continue;
                int nextFee = startFees[current.node] + next.fee;
                if(startFees[next.node] == 0 || startFees[next.node] > nextFee) {
                    startFees[next.node] = nextFee;
                    pq.add(new Point(next.node, nextFee));
                }
            }
        }
        

        pq.add(new Point(a, 0));
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.fee > aFees[current.node]) continue;
            for(Point next: map.get(current.node)) {
                if(next.node == a) continue;
                int nextFee = aFees[current.node] + next.fee;
                if(aFees[next.node] == 0 || aFees[next.node] > nextFee) {
                    aFees[next.node] = nextFee;
                    pq.add(new Point(next.node, nextFee));
                }
            }
        }
        
        pq.add(new Point(b, 0));
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            if(current.fee > bFees[current.node]) continue;
            for(Point next: map.get(current.node)) {
                if(next.node == b) continue;
                int nextFee = bFees[current.node] + next.fee;
                if(bFees[next.node] == 0 || bFees[next.node] > nextFee) {
                    bFees[next.node] = nextFee;
                    pq.add(new Point(next.node, nextFee));
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            if(startFees[i] == 0 && aFees[i] == 0 && bFees[i] == 0) {
                continue;
            }
            min = Math.min(min, (startFees[i] + aFees[i] + bFees[i]));
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