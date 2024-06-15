import java.util.*;

class Solution {
    public int solution(String s) {
        if(s.length() == 1) {
            return 1;
        }
        int answer = s.length();
        Deque<String> stack = new ArrayDeque<>();
        for(int i=1; i<=s.length()/2; i++) {
            int currentLength = 0;
            int currentIndex = 0;
            
            while(s.length() > currentIndex) {
                String current;
                if(currentIndex + i > s.length()) current = s.substring(currentIndex);
                else current = s.substring(currentIndex, currentIndex + i);
                currentIndex += i;
                if(stack.isEmpty()) {
                    stack.push(current);
                    continue;
                }
                if(current.equals(stack.peek())) {
                    stack.push(current);
                    continue;
                }
                int count = stack.size();
                stack.clear();
                stack.push(current);
                if (count > 1) {
                    currentLength += (Integer.toString(count).length() + i);
                    continue;
                }
                currentLength += i;
            }
            if(!stack.isEmpty()) {
                String last = stack.peek();
                int count = stack.size();
                if (count > 1) {
                    currentLength += (Integer.toString(count).length() + last.length());
                } else {
                    currentLength += last.length();
                }
                stack.clear();
            }
            answer = Math.min(answer, currentLength);
        }
        return answer;
    }
}