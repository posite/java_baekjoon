import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int[] totalQueue = new int[queue1.length + queue2.length];
        System.arraycopy(queue1, 0, totalQueue, 0, queue1.length);
        System.arraycopy(queue2, 0, totalQueue, queue1.length, queue2.length);
        
        long sum = 0;
        for(int i=0; i< queue1.length; i++) {
            sum += queue1[i];
        }
        long sumQ1 = sum;
        //System.out.println(sumQ1);
        for(int i=0; i<queue2.length; i++) {
            sum += queue2[i];
        }
        long mid = sum/2;
        int start = 0, end = queue1.length;
        //System.out.println(mid);
        for(int i=0; i<queue1.length * 3; i++) {
            if(start == end || end == totalQueue.length) return -1;
            if(sumQ1 == mid) return i;
            if(sumQ1 > mid) {
                sumQ1 -= totalQueue[start];
                start++;
            } else {
                sumQ1 += totalQueue[end];
                end++;
            }
        }
        
        return -1;
    }
}