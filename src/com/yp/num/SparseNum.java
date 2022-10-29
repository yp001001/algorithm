package com.yp.num;

/**
 * 稀疏数组
 */
public class SparseNum {
    public static void main(String[] args) {
        int[][] num = new int[11][11];
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[0].length; j++) {
                if (i == 1 && j == 3) {
                    num[i][j] = 1;
                }
                if (i == 2 && j == 4) {
                    num[i][j] = 2;
                }
            }
        }

        foreach(num);

        int count = 0;

        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[0].length; j++) {
                if (num[i][j] != 0) {
                    count++;
                }
            }
        }
        int[][] sparce = Space(num, count);

        System.out.println("==============转换成稀疏数组===============");

        foreach(sparce);


        System.out.println("==============将稀疏数组转换成原数组===============");

        int[][] normal = normal(sparce);

        foreach(normal);
    }

    /**
     * 转换成稀疏数组
     *
     * @param num
     * @param count
     * @return
     */
    public static int[][] Space(int[][] num, int count) {

        int row = 1;

        int[][] sparse = new int[count + 1][3];

        sparse[0][0] = num.length;
        sparse[0][1] = num[0].length;
        sparse[0][2] = count;

        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[0].length; j++) {
                if (num[i][j] != 0) {
                    sparse[row][0] = i;
                    sparse[row][1] = j;
                    sparse[row][2] = num[i][j];
                    row++;
                }
            }
        }
        return sparse;
    }


    /**
     * 数组的遍历
     *
     * @param num
     */
    public static void foreach(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[0].length; j++) {
                System.out.print(num[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 稀疏数组转换成原数组
     *
     * @param sparce
     * @return
     */
    public static int[][] normal(int[][] sparce) {
        int num[][] = new int[sparce[0][0]][sparce[0][1]];

        for (int i = 1; i < sparce.length; i++) {
                num[sparce[i][0]][sparce[i][1]] = sparce[i][2];
        }

        return num;
    }

}
