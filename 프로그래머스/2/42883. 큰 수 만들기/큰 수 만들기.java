import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length() - k;
        int start = 0;
        
        while(start < number.length() && sb.length() != len) {
            int leftNum = k + sb.length() + 1;
            int max = 0;
            for(int i=start; i<leftNum; i++) {
                if(number.charAt(i) - '0' > max) {
                    max = number.charAt(i) - '0';
                    start = i+1;
                }
            }
            sb.append(Integer.toString(max));
        }
        return sb.toString();
    }
}