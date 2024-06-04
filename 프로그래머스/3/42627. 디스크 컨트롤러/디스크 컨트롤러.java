import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int total = 0;
        int finishTime = 0;
        Queue<Job> pq = new PriorityQueue<>();
        for(int i=0; i<jobs.length; i++) {
            total += jobs[i][1];
            pq.add(new Job(jobs[i][0], jobs[i][1]));
        }
        ArrayList<Job> remain = new ArrayList();
        int time = 0;
        while(!pq.isEmpty()) {
            Job next = pq.peek();
            //System.out.println(next.inputTime + " " + time);
            if(next.inputTime <= time) {
                next = pq.remove();
                time += next.duration;
                finishTime += time - next.inputTime;
                pq.addAll(remain);
                remain.clear();
                continue;
            }
            int count = 0;
            for(Job job: pq) {
                if(job.inputTime > time) {
                    count++;
                }
            }
            if(count == pq.size()) {
                time++;
                continue;
            }
            remain.add(pq.remove());
        }
        return finishTime/jobs.length;
    }
    
    static class Job implements Comparable<Job>{
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