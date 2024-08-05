class Solution {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder(new_id.toLowerCase());
        for(int i=0; i<sb.length(); i++) {
            char current = sb.charAt(i);
            if(Character.isDigit(current) || Character.isLowerCase(current)) continue;
            if(current == '-' || current == '_' || current == '.') continue;
            sb.deleteCharAt(i);
            i--;
        }
        
        while(sb.indexOf("..") != -1) {
            int index = sb.indexOf("..");
            sb.deleteCharAt(index+1);
        }
        if(sb.charAt(0) == '.') sb.deleteCharAt(0);
        if(sb.length() > 1) {
            if(sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        }
        if(sb.length() == 0) sb.append('a');
        if(sb.length() > 15) sb.replace(15, sb.length(), "");
        if(sb.length() > 1) {
            if(sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        }
        while(sb.length() < 3) {
            sb.append(sb.charAt(sb.length() - 1));
        }
        return sb.toString();
    }
}