import java.util.*;

class Solution {
    public String solution(String new_id) {
        StringBuilder answer = new StringBuilder(new_id.toLowerCase());

        //사용불가 문자 제거
        for (int i = 0; i < answer.length(); i++) {
            char current = answer.charAt(i);
            if (Character.isDigit(current) || Character.isLowerCase(current)) continue;
            if (current == '.' || current == '-' || current == '_') continue;
            answer.deleteCharAt(i);
            i--;
        }

        //연속된 . 제거
        while (answer.indexOf("..") != -1) {
            int index = answer.indexOf("..");
            answer.deleteCharAt(index + 1);
        }

        //처음과 끝의 . 제거
        if (answer.charAt(0) == '.') answer.deleteCharAt(0);
        if (answer.length() > 0) {
            if (answer.charAt(answer.length() - 1) == '.') answer.deleteCharAt(answer.length() - 1);
        }

        //비어있으면 a 추가
        if (answer.length() == 0) answer.append('a');

        //길이가 16 이상이면 15개로 자르고 처음과 끝의 . 제거
        if (answer.length() > 15) answer.replace(15, answer.length(), "");
        if (answer.charAt(0) == '.') answer.deleteCharAt(0);
        if (answer.charAt(answer.length() - 1) == '.') answer.deleteCharAt(answer.length() - 1);

        //길이가 2이하면 3이 될때 까지 마지막 문자 반복
        while (answer.length() < 3) {
            answer.append(answer.charAt(answer.length() - 1));
        }
        return answer.toString();
    }
}