import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        int maxIndex = 0;
        boolean many = false;
        String word = st.nextToken();
        String upper = word.toUpperCase(Locale.ROOT);
        boolean[] check = new boolean[upper.length()];
        for (int i = 0; i < upper.length(); i++) {
            if (!check[i]) {
                int count = 1;
                if (i == 0) {
                    for (int j = i + 1; j < upper.length(); j++) {
                        if (upper.charAt(i) == upper.charAt(j) && !check[j]) {
                            check[j] = true;
                            count++;
                        }
                    }
                    if (max < count) {
                        max = count;
                        maxIndex = i;
                        many = false;
                    } else if (max == count && upper.charAt(maxIndex) != upper.charAt(i)) {
                        many = true;
                    }
                } else {
                    if (upper.charAt(maxIndex) != upper.charAt(i)) {
                        for (int j = i + 1; j < upper.length(); j++) {
                            if (upper.charAt(i) == upper.charAt(j) && !check[j]) {
                                check[j] = true;
                                count++;
                            }
                        }
                        if (max < count) {
                            max = count;
                            maxIndex = i;
                            many = false;
                        } else if (max == count && upper.charAt(maxIndex) != upper.charAt(i)) {
                            many = true;
                        }
                    }
                }
            }
        }
        if (many) {
            bw.write("?");
        } else {
            bw.write(upper.charAt(maxIndex));
        }
        bw.flush();
    }
}
