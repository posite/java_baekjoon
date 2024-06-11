import java.util.*;

class Solution {
    public int solution(String s) {
        if(s.length() == 1) {
            return 1;
        }
        int minLength = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= s.length() / 2; i++) {
            int currentLength = 0;
            int currentIndex = 0;
            //System.out.println("split " + i);
            while (s.length() > currentIndex) {
                String currentString = "";
                if (currentIndex + i > s.length()) {
                    currentString = s.substring(currentIndex);
                } else {
                    currentString = s.substring(currentIndex, currentIndex + i);
                }
                //System.out.println(currentIndex + " ~ " + (currentIndex + i) + " " + currentString + " ");
                currentIndex += i;
                if (map.containsKey(currentString)) {
                    //System.out.println("add " + currentString);
                    map.put(currentString, map.get(currentString) + 1);
                    continue;
                }
                if (map.isEmpty()) {
                    //System.out.println("put " + currentString);
                    map.put(currentString, 1);
                    continue;
                }
                //System.out.println("put " + currentString);
                map.put(currentString, 1);
                String prevString = s.substring(currentIndex - 2 * i, currentIndex - i);
                //System.out.println("prev " + (currentIndex - i) + " ~ " + currentIndex + " " + prevString);
                int prevCount = map.remove(prevString);
                //System.out.println(map);
                if (prevCount > 1) {
                    currentLength += (Integer.toString(prevCount).length() + i);
                    //System.out.println("remove " + prevString + " currentLength " + currentLength);
                    continue;
                }
                currentLength += i;
                //System.out.println("remove " + prevString + " currentLength " + currentLength);
            }
            /*minLength = Math.min(currentLength, minLength);
            System.out.println(minLength);*/
            //System.out.println(map);
            if (!map.isEmpty()) {
                int remain = 0;
                if (i == 1) {
                    remain = s.length() - 1;
                } else if (s.length() % i != 0) {
                    remain = s.length() - s.length() % i;
                } else {
                    remain = s.length() - i;
                }
                //System.out.print("remain " + remain + " ");
                String lastString = s.substring(remain);
                if (i == 1) {
                    lastString = s.substring(s.length() - 1);
                }
                //System.out.println("remove  last " + lastString);
                int count = map.remove(lastString);
                if (count > 1) {
                    currentLength += Integer.toString(count).length() + lastString.length();
                } else {
                    currentLength += lastString.length();
                }
            }
            minLength = Math.min(currentLength, minLength);
            //System.out.println(minLength);
        }
        return minLength;
    }
}