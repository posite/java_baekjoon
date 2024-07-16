import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int[] totalQueue = new int[queue1.length + queue2.length];
        System.arraycopy(queue1, 0, totalQueue, 0, queue1.length);
        System.arraycopy(queue2, 0, totalQueue, queue1.length, queue2.length);
        //System.out.println(Arrays.toString(totalQueue));
        long sumQ1 = 0;
        long sumQ2 = 0;
        for(int i=0; i<queue1.length; i++) {
            sumQ1 += queue1[i];
        }
        for(int i=0; i<queue2.length; i++) {
            sumQ2 += queue2[i];
        }
        int left = 0, right = queue1.length;
        long target = (sumQ1 + sumQ2)/2;
        //System.out.println(sumQ1 + " " + target);
        
        for(int i=0; i<queue1.length*3; i++) {
            if(left == right || right == totalQueue.length) return -1;
            
            
            if(sumQ1 == target) return i;
            if(sumQ1 > target) {
                sumQ1 -= totalQueue[left];
                left++;
            } else {
                sumQ1 += totalQueue[right];
                right++;
            }
        }
        
        
        return -1;
    }
}