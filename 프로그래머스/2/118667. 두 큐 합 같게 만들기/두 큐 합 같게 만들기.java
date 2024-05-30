class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int[] board = new int[queue1.length + queue2.length];
        System.arraycopy(queue1, 0, board, 0, queue1.length);
        System.arraycopy(queue2, 0, board, queue1.length, queue2.length);
        long sum1 = 0;
        long sum2 = 0;
        for(int num: queue1) {
            sum1 += num;
        }
        for(int num: queue2) {
            sum2 += num;
        }
        long target = (sum1 + sum2) / 2;
        int start = 0, end = queue1.length;
        for(int i=0; i< 3* queue1.length * 3; i++) {
            if(start == end || end == board.length) {
                return -1;
            }
            if(sum1 > target) {
                sum1 -= board[start];
                start++;
                continue;
            }
            if(target > sum1) {
                sum1 += board[end];
                end++;
                continue;
            }
            return i;
        }
        return -1;
    }
}