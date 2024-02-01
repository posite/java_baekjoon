import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[] result = new int[v + 1];
            boolean[] check = new boolean[v + 1];
            ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= v; i++) {
                graph.add(new ArrayList<>());
                result[i] = Integer.MAX_VALUE;
            }
            result[start] = 0;

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, weight));
            }

            for (int i = 1; i <= v; i++) {
                int nodeValue = Integer.MAX_VALUE;
                int nodeIdx = 1;

                for (int j = 1; j <= v; j++) {
                    if (!check[j] && result[j] < nodeValue) {
                        nodeValue = result[j];
                        nodeIdx = j;
                    }
                }
                check[nodeIdx] = true;
                for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
                    Node adjNode = graph.get(nodeIdx).get(j);
                    if (result[adjNode.idx] > result[nodeIdx] + adjNode.cost) {
                        result[adjNode.idx] = result[nodeIdx] + adjNode.cost;
                    }
                }
            }
            for (int i = 1; i <= v; i++) {
                if (result[i] == Integer.MAX_VALUE) {
                    System.out.println("INF");
                } else {
                    System.out.println(result[i]);
                }
            }
            br.close();
        } catch (IOException e) {

        }

    }

}

class Node {
    int idx;
    int cost;

    // 생성자
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}