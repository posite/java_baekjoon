import java.util.*;

class Solution {
    public int solution(String s) {
        List<Integer> anwers = new ArrayList();
        Map<String, Integer> map = new HashMap<>();
        int min = s.length();
        for(int i=1; i<=s.length()/2; i++) {
            StringBuilder sb = new StringBuilder();
            int currentIndex = 0;
            while(s.length() > currentIndex) {
                String current;
                if(currentIndex + i > s.length()) current = s.substring(currentIndex);
                else current = s.substring(currentIndex, currentIndex + i);
                currentIndex += i;
                
                if(map.isEmpty()) {
                    map.put(current, 1);
                    continue;
                }
                if(map.containsKey(current)) {
                    map.put(current, map.get(current) + 1);
                    continue;
                }
                map.put(current, 1);
                String prev = s.substring(currentIndex - 2*i, currentIndex -i);
                int prevCount = map.remove(prev);
                if(prevCount >1 ) {
                    sb.append(prevCount + prev);
                } else {
                    sb.append(prev);
                }
            }
            int remain = 0;
            if (i == 1) {
                remain = 1;
            } else if (s.length() % i != 0) {
                remain = s.length() % i;
            } else {
                remain = i;
            }
	        String lastString = s.substring(s.length() - remain);
            int count = map.remove(lastString);
            if(count > 1) {
                sb.append(count + lastString);
            } else {
                sb.append(lastString);
            }
            min = Math.min(min, sb.length());
        }
        
        return min;
    }
}