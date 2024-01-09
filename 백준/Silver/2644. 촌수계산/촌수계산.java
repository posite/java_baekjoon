import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int x, y, total, relation;
    static boolean[] check;
    static int[][] arr;
    static Queue<Integer> q = new LinkedList<>();
    static int[] ans;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        try {
            total = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            relation = Integer.parseInt(br.readLine());
            ans = new int[total + 1];
            arr = new int[total + 1][total + 1];
            check = new boolean[total + 1];
            for (int i = 0; i < relation; i++) {
                StringTokenizer str = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(str.nextToken());
                int b = Integer.parseInt(str.nextToken());

                arr[a][b] = arr[b][a] = 1;
            }
            bfs(x, y);
            if (ans[y] == 0) {
                bw.write(Integer.toString(-1));
            } else {
                System.out.println(Integer.toString(ans[y]));
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bfs(int start, int end) {
        q.add(start);
        check[start] = true;
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (temp == end) break;

            for (int i = 1; i <= total; i++) {
                if (arr[temp][i] == 1 && !check[i]) {
                    q.add(i);
                    check[i] = true;
                    ans[i] = ans[temp] + 1;
                }
            }
        }
    }
}