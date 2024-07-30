import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int time = 0;
        int finishTime = 0;
        
        List<Job> remain = new ArrayList<>();
        Queue<Job> pq = new PriorityQueue<>();
        for(int i=0; i<jobs.length; i++) {
            pq.offer(new Job(jobs[i][0], jobs[i][1]));
        }
        
        while(!pq.isEmpty()) {
            Job next = pq.peek();
            if(next.inputTime <= time) {
                next = pq.remove();
                time += next.duration;
                finishTime += (time - next.inputTime);
                pq.addAll(remain);
                remain.clear();
                continue;
            }
            
            int count = 0;
            int min = Integer.MAX_VALUE;
            for(Job job: pq) {
                if(job.inputTime > time) {
                    if(min > job.inputTime) {
                        min = job.inputTime;
                    }
                    count++;
                }
            }
            if(count == pq.size()) {
                time = min;
                continue;
            }
            
            remain.add(pq.remove());
            
        }
        
        return finishTime/jobs.length;
    }
    
    public static class Job implements Comparable<Job> {
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