import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static final int[] dx = {1, 2, 3, 4, 5, 6};
    private static int[] path = new int[101];
    private static boolean[] visited = new boolean[101];
    private static int[] distances = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 100; i++) {
            path[i] = 0;
        }
        for (int i = 0; i < (n + m); i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            path[a] = b;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        distances[1] = 0;
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (temp == 100) {
                bw.write(Integer.toString(distances[temp]));
                bw.flush();
                return;
            }
            for (int j : dx) {
                int nx = temp + j;
                if (nx > 100) continue;
                if (visited[nx]) continue;
                if (path[nx] != 0) {
                    if (!visited[path[nx]]) {
                        q.add(path[nx]);
                        visited[path[nx]] = true;
                        distances[path[nx]] = (distances[temp] + 1);
                    }
                } else {
                    q.add(nx);
                    distances[nx] = distances[temp] + 1;
                    visited[nx] = true;
                }
            }

        }
        bw.write(Integer.toString(distances[100]));
        bw.flush();
    }
}
