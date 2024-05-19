import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        Deque<Integer> q1 = new LinkedList<>();
        Deque<Integer> q2 = new LinkedList<>();
        for (int num : queue1) {
            sum1 += num;
            q1.add(num);
        }
        for (int num : queue2) {
            sum2 += num;
            q2.add(num);
        }
        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }
        for(int i=0; i<3*queue1.length; i++) {
            if (sum1 == sum2) {
                return i;
            }
            else if (sum1 > sum2) {
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
                sum2 += num;
            } else {
                int number = q2.poll();
                q1.add(number);
                sum2 -= number;
                sum1 += number;
            }
        }
        return -1;
    }
}