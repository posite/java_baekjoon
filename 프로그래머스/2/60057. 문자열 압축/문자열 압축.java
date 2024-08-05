import java.util.*;

class Solution {
    public int solution(String s) {
        int length = s.length();
        if(length == 1) return 1;
        int minLength = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        for(int i=1; i<=length/2; i++) {
            int currentIndex = 0;
            int currentLength = 0;
            while(length > currentIndex) {
                String currentString = "";
                if(currentIndex + i > length) {
                    currentString = s.substring(currentIndex);
                } else {
                    currentString = s.substring(currentIndex, currentIndex + i);
                }
                
                currentIndex += i;
                if(map.isEmpty()) {
                    map.put(currentString, 1);
                    continue;
                }
                
                if(map.containsKey(currentString)) {
                    map.put(currentString, map.get(currentString) + 1);
                    continue;
                }
                
                String beforeString = s.substring(currentIndex - 2*i, currentIndex - i);
                int beforeCount = map.remove(beforeString);
                map.put(currentString, 1);
                if(beforeCount > 1) {
                    currentLength += (Integer.toString(beforeCount).length() + i);
                } else {
                    currentLength += i;
                }
            }
            if(!map.isEmpty()) {
                int remain = 0;
                if(i==1) {
                    remain = length - 1;
                } else if(length % i == 0) {
                    remain = length - i;
                } else {
                    remain = length - length%i;
                }
                String last = s.substring(remain);
                int lastCount = map.remove(last);
                if(lastCount > 1) {
                    currentLength += (Integer.toString(lastCount).length() + i);
                } else {
                    currentLength += last.length();
                }
            }
            minLength = Math.min(minLength, currentLength);
        }
        return minLength;
    }
}