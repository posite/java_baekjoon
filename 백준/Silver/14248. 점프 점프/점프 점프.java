import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n, start;
    private static int[] board;
    private static boolean[] check;
    private static int count;
    private static final int[] dx = {1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        board = new int[n];
        check = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dfs(start - 1);
        bw.write(Integer.toString(count));
        bw.flush();
    }

    private static void dfs(int x) {
        check[x] = true;
        count++;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + (dx[i] * board[x]);
            if (nx >= 0 && nx < n && !check[nx]) {
                dfs(nx);
            }
        }
    }

}