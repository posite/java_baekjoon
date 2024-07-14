import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        List<List<Integer>> list = new ArrayList();
        for(int i=1; i<= relation[0].length; i++) {
            findCandidate(list, new ArrayList<>(), relation, 0, i);
        }
        //System.out.println(list);
        return list.size();
    }
    
    public void findCandidate(List<List<Integer>> list, List<Integer> now, String[][] relation, int start, int size) {
        if(now.size() == size) {
            List<String> key = new ArrayList<>();
            for(int i=0; i<relation.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int index: now) {
                    sb.append(relation[i][index]);
                }
                key.add(sb.toString());
            }
            if(isUnique(key)) {
                list.add(new ArrayList<>(now));
            }
            return;
        }
        
        for(int i=start; i<relation[0].length; i++) {
            now.add(i);
            boolean isMinimum = true;
            for(List<Integer> candidateKey: list) {
                if(now.containsAll(candidateKey)) {
                    isMinimum = false;
                    break;
                }
            }
            if(isMinimum) {
                findCandidate(list, now, relation, i, size);
            }
            now.remove(now.size() -1);
        }
    }
    
    public boolean isUnique(List<String> key) {
        Set<String> set = new HashSet<>(key);
        return set.size() == key.size();
        
    }
}