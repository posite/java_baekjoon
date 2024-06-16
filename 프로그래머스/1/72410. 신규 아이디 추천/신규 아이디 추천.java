class Solution {
    public String solution(String new_id) {
        StringBuilder answer = new StringBuilder(new_id.toLowerCase());
        for(int i=0; i<answer.length(); i++) {
            char current = answer.charAt(i);
            if(Character.isDigit(current) || Character.isLowerCase(current)) continue;
            if(current == '.' || current == '_' || current == '-') continue;
            answer.deleteCharAt(i);
            i--;
        }
        while(answer.indexOf("..") != -1) {
            answer.deleteCharAt(answer.indexOf(".."));
        }
        
        if(answer.charAt(0) == '.') answer.deleteCharAt(0);
        if(answer.length() > 0 && answer.charAt(answer.length()-1) == '.') answer.deleteCharAt(answer.length()-1);
        
        if(answer.length() == 0) answer.append("a");
        
        if(answer.length() > 15) answer.replace(15, answer.length(), "");
        if(answer.length() > 0 && answer.charAt(0) == '.') answer.deleteCharAt(0);
        if(answer.length() > 0 && answer.charAt(answer.length()-1) == '.') answer.deleteCharAt(answer.length()-1);
        while(answer.length()<3) {
            answer.append(answer.charAt(answer.length() -1));
        }
        
        
        
        return answer.toString();
    }
}