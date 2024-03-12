package com.comeup.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @auth: qwf
 * @date: 2024年1月10日 0010
 * @description: 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 */
public class Image_IslandNumber {

    public static class IslandPoint {
        Integer w;
        Integer h;
        IslandPoint left;
        IslandPoint right;
        IslandPoint up;
        IslandPoint down;

        public IslandPoint(Integer w, Integer h) {
            this.w = w;
            this.h = h;
        }

        public IslandPoint buildLeft() {
            this.left = new IslandPoint(w-1, h);
            return this;
        }
        public IslandPoint buildRight() {
            this.right = new IslandPoint(w+1, h);
            return this;
        }
        public IslandPoint buildUp() {
            this.up = new IslandPoint(w, h+1);
            return this;
        }
        public IslandPoint buildDown() {
            this.down = new IslandPoint(w, h-1);
            return this;
        }

        public Integer getW() {
            return w;
        }

        public void setW(Integer w) {
            this.w = w;
        }

        public Integer getH() {
            return h;
        }

        public void setH(Integer h) {
            this.h = h;
        }

        public IslandPoint getLeft() {
            return left;
        }

        public void setLeft(IslandPoint left) {
            this.left = left;
        }

        public IslandPoint getRight() {
            return right;
        }

        public void setRight(IslandPoint right) {
            this.right = right;
        }

        public IslandPoint getUp() {
            return up;
        }

        public void setUp(IslandPoint up) {
            this.up = up;
        }

        public IslandPoint getDown() {
            return down;
        }

        public void setDown(IslandPoint down) {
            this.down = down;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IslandPoint that = (IslandPoint) o;
            return Objects.equals(w, that.w) && Objects.equals(h, that.h);
        }

        @Override
        public int hashCode() {
            return Objects.hash(w, h);
        }
    }

    public static class Island {
        private List<IslandPoint> islandPoints = new ArrayList<>();

        public List<IslandPoint> getIslandPoints() {
            return islandPoints;
        }

        public void setIslandPoints(List<IslandPoint> islandPoints) {
            this.islandPoints = islandPoints;
        }
    }

    public static int numIslands(char[][] grid) {
        List<IslandPoint> list = new ArrayList<>();
        for (int h = 0; h < grid.length; h++) {
            for (int w = 0; w < grid[h].length; w++) {
                char c = grid[h][w];
                if (c == '1') {
                    list.add(new IslandPoint(w, h).buildDown().buildLeft().buildRight().buildUp());
                }
            }
        }
        if (list.isEmpty()) return 0;

        List<Island> islands = new ArrayList<>();

        list.forEach(item -> {
            List<Island> islandsModels = islands.stream().filter(island -> island.getIslandPoints().stream().anyMatch(islandPoint -> islandPoint.left.equals(item) || islandPoint.right.equals(item) || islandPoint.up.equals(item) || islandPoint.down.equals(item))).collect(Collectors.toList());
            if (!islandsModels.isEmpty()) {
                islandsModels.get(0).getIslandPoints().add(item);
            } else {
                Island island = new Island();
                island.getIslandPoints().add(item);
                islands.add(island);
            }
        });
        return islands.size();
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };
        int i = numIslands(grid);
        System.out.println(i);
    }
}
