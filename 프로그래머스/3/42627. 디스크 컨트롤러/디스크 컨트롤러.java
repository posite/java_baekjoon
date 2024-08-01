import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int time = 0;
        int finishTime = 0;
        int jobCount = jobs.length;
        List<Job> remains = new ArrayList<>();
        Queue<Job> pq = new PriorityQueue<>();
        for(int i=0; i<jobCount; i++) {
            pq.add(new Job(jobs[i][0], jobs[i][1]));
        }
        
        while(!pq.isEmpty()) {
            Job current = pq.peek();
            if(time >= current.inputTime) {
                current = pq.remove();
                time += current.duration;
                finishTime += (time - current.inputTime);
                pq.addAll(remains);
                remains.clear();
                continue;
            }
            int count = 0;
            int min = Integer.MAX_VALUE;
            for(Job job: pq) {
                if(job.inputTime > time) {
                    count++;
                    min = Math.min(min, job.inputTime);
                }
            }
            if(count == pq.size()) {
                time = min;
                continue;
            }
            remains.add(pq.remove());
        }
        
        return finishTime/jobCount;
    }
    
    public class Job implements Comparable<Job> {
        int inputTime, duration;
        
        public Job(int inputTime, int duration) {
            this.inputTime = inputTime;
            this.duration = duration;
        }
        
        @Override
        public int compareTo(Job o) {
            return this.duration - o.duration;
        }
    }
}