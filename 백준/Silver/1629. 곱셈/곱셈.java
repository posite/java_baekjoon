import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            System.out.println(mod(a, b, c));
            br.close();
        } catch (IOException e) {

        }
    }

    private static long mod(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }
        long r = mod(a, b / 2, c);
        if (b % 2 == 0) {
            return (r * r) % c;
        }
        return (r * r % c) * a % c;
    }
}
