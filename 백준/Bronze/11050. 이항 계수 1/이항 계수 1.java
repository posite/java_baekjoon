import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int nP = factorial(n);
        int kP = factorial(k);
        int nkP = factorial(n - k);
        double result = (double) nP / ((double) nkP * (double) kP);
        sb.append(Math.round(result));
        System.out.println(sb);
    }

    private static int factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }
}
