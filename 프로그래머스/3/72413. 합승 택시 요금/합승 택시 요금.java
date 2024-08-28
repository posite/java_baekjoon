import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = Integer.MAX_VALUE;
        int answer = INF;
        Map<Integer, List<Route>> map = new HashMap<>();
        for(int i=1; i<=n; i++) map.put(i, new ArrayList<>());
        for(int[] fare: fares) {
            map.get(fare[0]).add(new Route(fare[1], fare[2]));
            map.get(fare[1]).add(new Route(fare[0], fare[2]));
        }
        
        int[] startFees = new int[n+1];
        int[] aFees = new int[n+1];
        int[] bFees = new int[n+1];
        
        Arrays.fill(startFees, INF);
        Arrays.fill(aFees, INF);
        Arrays.fill(bFees, INF);
        
        Queue<Route> pq = new ArrayDeque<>();
        pq.add(new Route(s, 0));
        startFees[s] = 0;
        while(!pq.isEmpty()) {
            Route current = pq.remove();
            if(current.fee > startFees[current.node]) continue;
            for(Route next: map.get(current.node)) {
                int nextFee = current.fee + next.fee;
                if(startFees[next.node] > nextFee) {
                    pq.add(new Route(next.node, nextFee));
                    startFees[next.node] = nextFee;
                }
            }
        }
        
        pq.add(new Route(a, 0));
        aFees[a] = 0;
        while(!pq.isEmpty()) {
            Route current = pq.remove();
            if(current.fee > aFees[current.node]) continue;
            for(Route next: map.get(current.node)) {
                int nextFee = current.fee + next.fee;
                if(aFees[next.node] > nextFee) {
                    pq.add(new Route(next.node, nextFee));
                    aFees[next.node] = nextFee;
                }
            }
        }
        
        pq.add(new Route(b, 0));
        bFees[b] = 0;
        while(!pq.isEmpty()) {
            Route current = pq.remove();
            if(current.fee > bFees[current.node]) continue;
            for(Route next: map.get(current.node)) {
                int nextFee = current.fee + next.fee;
                if(bFees[next.node] > nextFee) {
                    pq.add(new Route(next.node, nextFee));
                    bFees[next.node] = nextFee;
                }
            }
        }
        
        for(int i=1; i<=n; i++) {
            int sum = startFees[i] + aFees[i] + bFees[i];
            if(sum == 0) continue;
            answer = Math.min(answer, sum);
        }
        
        return answer;
    }
    
    public class Route implements Comparable<Route> {
        int node, fee;
        
        public Route(int node, int fee) {
            this.node = node;
            this.fee = fee;
        }
        
        @Override 
        public int compareTo(Route o) {
            return this.fee - o.fee;
        }
    }
}