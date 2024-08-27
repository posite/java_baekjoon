import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int length = id_list.length;
        int[] answer = new int[length];
        Map<String, Integer> indexes = new HashMap<>();
        Map<String, Set<String>> map = new HashMap<>();
        for(int i=0; i<length; i++) {
            indexes.put(id_list[i], i);
            map.put(id_list[i], new HashSet<>());
        } 
        for(String rep: report) {
            StringTokenizer st = new StringTokenizer(rep);
            String reporter = st.nextToken(), user = st.nextToken();
            map.get(user).add(reporter);
        }
        for(Set<String> reporters: map.values()) {
            if(reporters.size() >= k) {
                for(String reporter: reporters) {
                    answer[indexes.get(reporter)]++;
                }
            }
        }
        return answer;
    }
}