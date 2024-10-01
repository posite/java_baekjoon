import java.util.*;

class Solution {
    public String solution(String number, int k) {
        List<Character> stack = new ArrayList<>();
        int count = k;
        for(int i=0; i<number.length(); i++) {
            char current = number.charAt(i);
            if(stack.isEmpty()) {
                stack.add(current);
                continue;
            }
            while(count > 0 && !stack.isEmpty()) {
                if(current > stack.get(stack.size()-1)) {
                    count--;
                    stack.remove(stack.size()-1);
                } else break;
            }
            stack.add(current);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<number.length()-k; i++) sb.append(stack.get(i));
        return sb.toString();
    }
}