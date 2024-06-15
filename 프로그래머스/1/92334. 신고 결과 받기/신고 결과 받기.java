import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>>  map = new HashMap<>();
        for(int i=0; i<report.length; i++) {
            StringTokenizer st = new StringTokenizer(report[i]);
            String a = st.nextToken();
            String b = st.nextToken();
            map.putIfAbsent(b, new HashSet());
            map.get(b).add(a);
        }
        for(String key: map.keySet()) {
            if(map.get(key).size() >= k) {
                for(String member: map.get(key)) {
                    answer[findIndex(member, id_list)]++;;
                }
            }
        }
        return answer;
    }
    
    public int findIndex(String s, String[] id_list) {
        for(int i=0; i<id_list.length; i++) {
            if(s.equals(id_list[i])) return i;
        }
        return -1;
    }
}