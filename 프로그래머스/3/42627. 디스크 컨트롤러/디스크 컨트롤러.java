import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int time = 0;
        int finishTime = 0;
        
        List<Task> remains = new ArrayList<>();
        Queue<Task> pq = new PriorityQueue<>();
        for(int[] job: jobs) {
            pq.add(new Task(job[0], job[1]));
        }        
        while(!pq.isEmpty()) {
            //System.out.println(time);
            Task current = pq.peek();
            if(time >= current.inputTime) {
                time += current.duration;
                finishTime += (time - current.inputTime);
                pq.remove();
                pq.addAll(remains);
                remains.clear();
                continue;
            }
            
            int count = 0;
            int min = Integer.MAX_VALUE;
            for(Task remain: pq) {
                if(remain.inputTime > time) {
                    count++;
                    min = Math.min(min, remain.inputTime);
                }
            }
            if(count == pq.size()) {
                time = min;
                continue;
            }
            remains.add(pq.remove());
            
        }
        
        return finishTime/jobs.length;
    }
    
    public class Task implements Comparable<Task> {
        int inputTime, duration;
        
        public Task(int inputTime, int duration) {
            this.inputTime = inputTime;
            this.duration = duration;
        }
        
        @Override
        public int compareTo(Task o) {
            return this.duration - o.duration;
        }
    }
}