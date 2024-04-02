import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] board;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);


        System.out.print(count);

    }

    private static void dfs(int index, int sum) {
        if (index == n) {

            return;
        } else {
            if (sum + board[index] == m) {
                count++;
            }
        }
        //현재 수 더하기
        dfs(index + 1, sum + board[index]);

        //현재 수 넘기기
        dfs(index + 1, sum);
    }
}