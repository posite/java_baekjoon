import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int mode = 1234567891;
        long hashSum = 0;
        String string = br.readLine();
        char[] str = new char[n];
        for (int i = 0; i < n; i++) {
            str[i] = string.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            long hash = (str[i] - 96) * (long) Math.pow(31, i);
            hashSum += hash;
        }
        if (hashSum > mode) {
            System.out.println((hashSum % mode));
        } else {
            System.out.println(hashSum);
        }
    }

}