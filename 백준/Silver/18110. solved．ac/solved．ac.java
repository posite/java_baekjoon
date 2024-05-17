import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int m = (int) Math.round(((double) n / (double) 10) * 1.5);
        int size = n - 2 * m;
        int[] evaluations = new int[31];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int evaluation = Integer.parseInt(br.readLine());
            sum += evaluation;
            evaluations[evaluation] += 1;
        }
        int downI = 0;
        int downCount = 0;
        int upI = 30;
        int upCount = 0;
        while (downCount < m) {
            if (evaluations[downI] > 0) {
                if (downCount + evaluations[downI] < m) {
                    sum = sum - downI * evaluations[downI];
                    downCount += evaluations[downI];
                } else if (downCount + evaluations[downI] == m) {
                    sum = sum - downI * evaluations[downI];
                    break;
                } else {
                    int count = m - downCount;
                    sum = sum - downI * count;
                    break;
                }

            }
            downI++;
        }
        while (upCount < m) {
            if (evaluations[upI] > 0) {
                if (upCount + evaluations[upI] < m) {
                    sum = sum - upI * evaluations[upI];
                    upCount += evaluations[upI];
                } else if (upCount + evaluations[upI] == m) {
                    sum = sum - upI * evaluations[upI];
                    break;
                } else {
                    int count = m - upCount;
                    sum = sum - upI * count;
                    break;
                }
            }
            upI--;
        }
        System.out.println(Math.round((double) sum / size));
    }
}