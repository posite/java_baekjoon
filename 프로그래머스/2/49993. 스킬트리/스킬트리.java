class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int skillIndex = 0;
        outer: for(String skill_tree: skill_trees) {
            System.out.print(skill_tree + " ");
            skillIndex = 0;
            for(int i=0; i<skill_tree.length(); i++) {
                if(skill.length() == skillIndex) break;
                char current = skill_tree.charAt(i);
                System.out.print("current " + current + " ");
                if(current == skill.charAt(skillIndex)) {
                    skillIndex++;
                } else {
                    for(int j=skillIndex+1; j<skill.length(); j++) {
                        if(current == skill.charAt(j)) {
                            System.out.println();
                            continue outer;
                        }
                    }
                    System.out.print("nothing ");
                }
                
            }
            answer++;
        }
        return answer;
    }
}