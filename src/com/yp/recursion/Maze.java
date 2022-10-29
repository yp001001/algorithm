package com.yp.recursion;

public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];

        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("======================================");
        setWay(map, 1, 1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 约定：
     * 当map[i][j]为0表示该点没有走过，为1表示墙，为2表示通路可以走，3表示该点已经走过，但是走不通
     * 我们指定一个策略 下 -> 右->  上 -> 左
     *
     * @param map 表示地图
     * @param i   表示小球开始出发的横坐标
     * @param j   表示小球开始出发的纵坐标
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 表示找到了出口
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; // 假设该路可以走通
                if (setWay(map, i + 1, j)) {         // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {  // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {  // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {  // 向左走
                    return true;
                } else {
                    map[i][j] = 3;                     // 表示走不通
                    return false;
                }
            } else {                                   // 表示该点为 1/2/3
                return false;
            }
        }
    }
}
