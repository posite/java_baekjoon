import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] cards = new int[20000001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[Integer.parseInt(st.nextToken()) + 10000000] += 1;
        }
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            bw.write(Integer.toString(cards[Integer.parseInt(st.nextToken()) + 10000000]));
            bw.newLine();
        }
        bw.flush();
    }
}
