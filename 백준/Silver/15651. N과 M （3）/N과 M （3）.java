import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;
    static int[] arr;
    static int number, unit;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            number = Integer.parseInt(st.nextToken());
            unit = Integer.parseInt(st.nextToken());
            visit = new boolean[number];
            arr = new int[unit];
            dfs(0);
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dfs(int depth) {
        if (depth == unit) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < number; i++) {
            // 해당 노드를 방문하지 않았다면
            if (!visit[i]) {
                visit[i] = true; // 해당 노드 방문상태 변경
                visit[i] = false; // 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
                arr[depth] = i + 1; // 해당 깊이를 index로 하여 i+1 값 저장
                dfs(depth + 1);// 다음 자식 노드 방문 위해 depth 1 증가시키며 재귀 호출
                visit[i] = false; // 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
            }
        }
    }
}