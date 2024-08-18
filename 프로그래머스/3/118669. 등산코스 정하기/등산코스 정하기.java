import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int intensities[] = new int[n+1];
        Arrays.sort(summits);
        Arrays.fill(intensities, Integer.MAX_VALUE);
        Map<Integer, List<Course>> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] path: paths) {
            map.get(path[0]).add(new Course(path[1], path[2]));
            map.get(path[1]).add(new Course(path[0], path[2]));
        }
        
        Queue<Course> pq = new PriorityQueue<>();
        for(int gate: gates) {
            pq.add(new Course(gate, 0));
            intensities[gate] = 0;
        }
        
        outer: while(!pq.isEmpty()) {
            Course current = pq.remove();
            if(current.intensity > intensities[current.destination]) continue;
            for(int summit: summits) {
                if(current.destination == summit) continue outer;
            }
            
            for(Course next: map.get(current.destination)) {
                int nextIntensity = Math.max(next.intensity, intensities[current.destination]);
                if(intensities[next.destination] > nextIntensity) {
                    pq.add(new Course(next.destination, nextIntensity));
                    intensities[next.destination] = nextIntensity;
                }
            }
        }        
        int index = -1;
        int minIntensity = Integer.MAX_VALUE;
        for(int summit: summits) {
            if(minIntensity > intensities[summit]) {
                index = summit;
                minIntensity = intensities[summit];
            }
        }
        int[] answer = {index, minIntensity};
        return answer;
    }
    
    public class Course implements Comparable<Course> {
        int destination, intensity;
        
        public Course(int destination, int intensity) {
            this.destination = destination;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Course o) {
            return this.intensity - o.intensity;
        }
    }
}