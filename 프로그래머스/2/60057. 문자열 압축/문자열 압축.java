import java.util.*;

class Solution {
    public int solution(String s) {
        if(s.length() == 1) {
            return 1;
        }
        int sLength = s.length();
        int minLength = sLength;
        
        for (int i = 1; i <= sLength / 2; i++) {
            StringBuilder prev = new StringBuilder(s.substring(0, i));
            StringBuilder base = new StringBuilder();
            int repeatCount = 1;
            for(int j=i; j<sLength; j+=i) {
                String current;
                if(j+i > sLength) current = s.substring(j);
                else current = s.substring(j, j+i);
                
                if(current.equals(prev.toString())) {
                    repeatCount++;
                    continue;
                }
                if(repeatCount > 1) base.append(repeatCount + prev.toString());
                else base.append(prev.toString());
                prev.replace(0, prev.length(), "");
                prev.append(current);
                repeatCount = 1;
            }
            if(repeatCount > 1) base.append(repeatCount + prev.toString());
            else base.append(prev.toString());
            //System.out.println(base.toString());
            minLength = Math.min(minLength, base.length());
        }
        return minLength;
    }
}