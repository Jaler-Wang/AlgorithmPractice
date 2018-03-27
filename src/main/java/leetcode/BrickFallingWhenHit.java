package leetcode;

import org.junit.Test;

public class BrickFallingWhenHit {
    //https://leetcode.com/articles/bricks-falling-when-hit/
    public int[] hitBricks(int[][] grid, int[][] hits) {
        if (hits.length == 0 || hits[0].length == 0) return null;
        removeHitBrick(grid, hits);
        markRemainBricks(grid);
        return searchFallingBrick(grid, hits);
    }

    private void markRemainBricks(int[][] grid) {
        for (int i = 0; i < grid[0].length; i++) {
            deepSearch(grid, 0, i);
        }
    }

    private void removeHitBrick(int[][] grid, int[][] hits) {
        for (int i = 0; i < hits.length; i++) {
            grid[hits[i][0]][hits[i][1]] = grid[hits[i][0]][hits[i][1]] - 1;
        }
    }

    private int[] searchFallingBrick(int[][] grid, int[][] hits) {
        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 0) {
                grid[hits[i][0]][hits[i][1]] = 1;
                if (isConnectToTop(grid, hits[i][0], hits[i][1])) {
                    result[i] = deepSearch(grid, hits[i][0], hits[i][1]) - 1;
                } else {
                    result[i] = 0;
                }
            }
        }
        return result;
    }

    private boolean isConnectToTop(int[][] grid, int i, int j) {
        if(i == 0) return true;

        if (i - 1 >= 0 && grid[i - 1][j] == 2) {
            return true;
        }
        if (i + 1 < grid.length && grid[i + 1][j] == 2) {
            return true;
        }
        if (j - 1 >= 0 && grid[i][j - 1] == 2) {
            return true;
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == 2) {
            return true;
        }
        return false;
    }

    private int deepSearch(int[][] data, int row, int column) {
        int arrayRow = data.length;
        int arrayLine = data[0].length;
        int effectBricks = 0;
        if (row < 0 || row >= arrayRow) return effectBricks;
        if (column < 0 || column >= arrayLine) return effectBricks;
        if (data[row][column] == 1) {
            data[row][column] = 2;
            effectBricks = 1;
            effectBricks += deepSearch(data, row + 1, column);
            effectBricks += deepSearch(data, row - 1, column);
            effectBricks += deepSearch(data, row, column + 1);
            effectBricks += deepSearch(data, row, column - 1);
        }
        return effectBricks;
    }

    @Test
    public void test2() {
        int[][] data = new int[][]{{1, 0, 0, 0}, {1, 1, 0, 0}};
        int[][] hits = new int[][]{{1, 1}, {1, 0}};
        int[] fall = hitBricks(data, hits);
        for (int i = 0; i < fall.length; i++) {
            System.out.println(fall[i]);
        }
    }

    @Test
    public void test1() {
        int[][] data = new int[][]{{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = new int[][]{{1, 0}};
        int[] fall = hitBricks(data, hits);
        for (int i = 0; i < fall.length; i++) {
            System.out.println(fall[i]);
        }
    }

    @Test
    public void test3() {
        int[][] data = new int[][]{{1}, {1}, {1}, {1}, {1}};
        int[][] hits = new int[][]{{3, 0}, {4, 0}, {1, 0}, {2, 0}, {0, 0}};
        int[] fall = hitBricks(data, hits);
        for (int i = 0; i < fall.length; i++) {
            System.out.println(fall[i]);
        }
    }
    /*
    * [[1,0,0,0],[1,1,1,0]]
[[1,0]]
    * */
}
