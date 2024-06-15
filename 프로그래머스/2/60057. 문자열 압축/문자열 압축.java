class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int i=1; i<=s.length()/2; i++) {
            StringBuilder base = new StringBuilder();
            String prev = s.substring(0, i);
            int repeatCount = 1;
            for(int j=i; j<=s.length(); j+=i) {
                String current;
                if(j+i > s.length()) current = s.substring(j);
                else current = s.substring(j, j+i);
                if(current.equals(prev)) {
                    repeatCount++;
                    continue;
                }
                if(repeatCount > 1) base.append(repeatCount + prev);
                else base.append(prev);
                repeatCount = 1;
                prev = current;
            }
            if(repeatCount > 1) base.append(repeatCount + prev);
            else base.append(prev);
            answer = Math.min(answer, base.length());
        }
        
        return answer;
    }
}