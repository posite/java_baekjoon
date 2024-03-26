import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, distance, start;
    private static ArrayList<Integer>[] board;
    private static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distance = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        check = new int[n + 1];
        board = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            check[i] = Integer.MAX_VALUE;
            board[i] = new ArrayList<>();
        }
        check[start] = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a].add(b);
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start, 1));
        while (!q.isEmpty()) {
            Point current = q.poll();
            for (int i = 0; i < board[current.x].size(); i++) {
                int node = board[current.x].get(i);
                if (check[node] > current.y) {
                    check[node] = current.y;
                    q.add(new Point(node, current.y + 1));
                }
            }
        }
        boolean empty = true;
        for (int i = 1; i <= n; i++) {
            if (check[i] == distance) {
                empty = false;
                System.out.println(i);
            }
        }
        if (empty) {
            System.out.println("-1");
        }
    }
}