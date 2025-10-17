import java.util.*;

public class TSP {
    static final int INF = (int) 1e9;

    static int tsp(int n, int[][] dist) {
        int N = 1 << n;
        int[][] dp = new int[N][n];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[1][0] = 0;

        for (int mask = 1; mask < N; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0) continue;
                    int nextMask = mask | (1 << v);
                    dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][u] + dist[u][v]);
                }
            }
        }

        int ans = INF;
        for (int i = 1; i < n; i++)
            ans = Math.min(ans, dp[(1 << n) - 1][i] + dist[i][0]);
        return ans;
    }

    public static void main(String[] args) {
        int[][] dist = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int n = dist.length;
        System.out.println("Минимальная длина пути: " + tsp(n, dist));
    }
}
