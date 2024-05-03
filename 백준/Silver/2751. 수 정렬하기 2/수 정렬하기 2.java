import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[2000001];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken()) + 1000000] = true;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                bw.write(Integer.toString(i - 1000000));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
