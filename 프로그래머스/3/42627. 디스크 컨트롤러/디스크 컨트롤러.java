import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int time = 0;
        int finishTime = 0;
        Queue<Task> pq = new PriorityQueue<>();
        for(int i=0; i<jobs.length; i++) {
            pq.add(new Task(jobs[i][0], jobs[i][1]));
        }
        
        List<Task> remains = new ArrayList<>();
        
        while(!pq.isEmpty()) {
            Task current = pq.peek();
            if(current.inputTime <= time) {
                current = pq.remove();
                time += current.duration;
                //System.out.println(time - current.inputTime);
                finishTime += (time - current.inputTime);
                pq.addAll(remains);
                remains.clear();
                continue;
            }
            int count = 0;
            int min = Integer.MAX_VALUE;
            for(Task task: pq) {
                if(task.inputTime > time) {
                    count++;
                    min = Math.min(min, task.inputTime);
                }
            }
            if(count == pq.size()) {
                time = min;
                continue;
            }
            remains.add(pq.remove());
            
        }
        //System.out.println(finishTime);
        return finishTime/jobs.length;
    }
    
    static class Task implements Comparable<Task> {
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