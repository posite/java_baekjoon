import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        Map<Integer, List<Point>> map = new HashMap<>();
        for(int i=1; i<=n; i++) map.put(i, new ArrayList<>());
        for(int[] fare: fares) {
            map.get(fare[0]).add(new Point(fare[1], fare[2]));
            map.get(fare[1]).add(new Point(fare[0], fare[2]));
        }
        
        int[] startFees = new int[n+1];
        int[] aFees = new int[n+1];
        int[] bFees = new int[n+1];
        
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(s, 0));
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            for(Point next: map.get(current.node)) {
                if(next.node == s) continue;
                if(startFees[next.node] == 0 || startFees[next.node] > startFees[current.node] + next.fee) {
                    startFees[next.node] = startFees[current.node] + next.fee;
                    pq.add(new Point(next.node, startFees[current.node] + next.fee));
                }
            }
        }
        
        pq.add(new Point(a, 0));
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            for(Point next: map.get(current.node)) {
                if(next.node == a) continue;
                if(aFees[next.node] == 0 || aFees[next.node] > aFees[current.node] + next.fee) {
                    aFees[next.node] = aFees[current.node] + next.fee;
                    pq.add(new Point(next.node, aFees[current.node] + next.fee));
                }
            }
        }
        
        pq.add(new Point(b, 0));
        while(!pq.isEmpty()) {
            Point current = pq.remove();
            for(Point next: map.get(current.node)) {
                if(next.node == b) continue;
                if(bFees[next.node] == 0 || bFees[next.node] > bFees[current.node] + next.fee) {
                    bFees[next.node] = bFees[current.node] + next.fee;
                    pq.add(new Point(next.node, bFees[current.node] + next.fee));
                }
            }
        }
        
        //System.out.println(Arrays.toString(startFees));
        //System.out.println(Arrays.toString(aFees));
        //System.out.println(Arrays.toString(bFees));
        
        for(int i=1; i<=n; i++) {
            if(startFees[i] + aFees[i] + bFees[i] == 0) continue;
            answer = Math.min(answer, (startFees[i] + aFees[i] + bFees[i]));
        }
        
        return answer;
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