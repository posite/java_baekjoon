import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Queue<Node> pq = new PriorityQueue<>();
        Map<Integer, List<Node>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] fare: fares) {
            map.get(fare[0]).add(new Node(fare[1], fare[2]));
            map.get(fare[1]).add(new Node(fare[0], fare[2]));
        }
        
        
        int[] startFees = new int[n+1];
        int[] aFees = new int[n+1];
        int[] bFees = new int[n+1];
        
        pq.add(new Node(s, 0));
        while(!pq.isEmpty()) {
            Node current = pq.remove();
            if(current.fee > startFees[current.destination]) continue;
            
            for(Node next: map.get(current.destination)) {
                if(next.destination == s) continue;
                if(startFees[next.destination] == 0 || startFees[next.destination] > startFees[current.destination] + next.fee) {
                    startFees[next.destination] = startFees[current.destination] + next.fee;
                    pq.add(new Node(next.destination, startFees[next.destination]));
                }
            }
        }
        
        pq.add(new Node(a, 0));
        while(!pq.isEmpty()) {
            Node current = pq.remove();
            if(current.fee > aFees[current.destination]) continue;
            
            for(Node next: map.get(current.destination)) {
                if(next.destination == a) continue;
                if(aFees[next.destination] == 0 || aFees[next.destination] > aFees[current.destination] + next.fee) {
                    aFees[next.destination] = aFees[current.destination] + next.fee;
                    pq.add(new Node(next.destination, aFees[next.destination]));
                }
            }
        }
        
        pq.add(new Node(b, 0));
        while(!pq.isEmpty()) {
            Node current = pq.remove();
            if(current.fee > bFees[current.destination]) continue;
            
            for(Node next: map.get(current.destination)) {
                if(next.destination == b) continue;
                if(bFees[next.destination] == 0 || bFees[next.destination] > bFees[current.destination] + next.fee) {
                    bFees[next.destination] = bFees[current.destination] + next.fee;
                    pq.add(new Node(next.destination, bFees[next.destination]));
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            if(startFees[i] == 0 && aFees[i] == 0 && bFees[i] == 0) continue;
            min = Math.min(min, (startFees[i] + aFees[i] + bFees[i]));
        }
        
        return min;
    }
    
    public class Node implements Comparable<Node> {
        int destination, fee;
        
        public Node(int destination, int fee) {
            this.destination = destination;
            this.fee = fee;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.fee - o.fee;
        }
    }
}