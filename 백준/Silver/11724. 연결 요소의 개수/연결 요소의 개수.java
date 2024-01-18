import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] check;
    private static int[][] arr;
    private static int node, edge;
    private static int count = 0;
    private static int added = 0;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            node = Integer.parseInt(st.nextToken());
            edge = Integer.parseInt(st.nextToken());
            arr = new int[node + 1][node + 1];
            check = new boolean[node + 1];

            for (int i = 0; i < edge; i++) {
                StringTokenizer str = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(str.nextToken());
                int b = Integer.parseInt(str.nextToken());

                arr[a][b] = arr[b][a] = 1;
            }
            for (int i = 1; i <= node; i++) {
                int before = added;
                dfs(i);
                if (added != before) {
                    count++;
                }
            }
            bw.write(Integer.toString(count));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dfs(int start) {
        if (check[start]) {
            return;
        }
        check[start] = true;
        added++;
        for (int i = 0; i <= node; i++) {
            if (arr[start][i] == 1 && !check[i]) {
                dfs(i);
            }
        }
    }
}