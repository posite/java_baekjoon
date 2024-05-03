import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int divide = 0;
        for (int i = 1; i < n; i++) {
            int sum = i;
            int m = i;
            while (m > 0) {
                sum += m % 10;
                m = m / 10;
            }
            if (sum == n) {
                bw.write(Integer.toString(i));
                bw.flush();
                return;
            }
        }
        if (divide == 0) {
            bw.write("0");
        }
        bw.flush();
    }
}