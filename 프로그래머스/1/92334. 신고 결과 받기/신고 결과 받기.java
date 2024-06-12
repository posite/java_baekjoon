import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> map = new HashMap<>();
        for(String id: id_list) {
            map.put(id, new HashSet());
        }
        for(String id: report) {
            StringTokenizer st = new StringTokenizer(id);
            String a = st.nextToken();
            String b = st.nextToken();
            map.get(b).add(a);
        }
        for(String key: map.keySet()) {
            Set<String> current = map.get(key);
            if(current.size() >= k) {
               for(String id: current) {
                   answer[findIndex(id, id_list)]++;
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