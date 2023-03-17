package cn.mrcode.study.dsalgtutorialdemo.datastructure.chessArr1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  稀疏数组：
 *      1. 二维数组转稀疏数组
 *      2. 稀疏数组转二维数组
 * </pre>
 */
public class SparseArray {
    public static void main(String[] args) {
//        //创建原始二维数组
//        //0：没有棋子，1：黑棋，2：白棋
//        //棋盘大小11 X 11
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
//        // 预览棋盘上的棋子位置
        System.out.println("预览原始数组");
        printChessArray(chessArr);
////        // 二维数组转稀疏数组并存盘
        int[][] sparseArr = chessToSparse(chessArr);
         //int[][] sparseArr = chessToSparse2(chessArr); // 紧凑版本可以参考笔记配套项目
        System.out.println("二维数组转稀疏数组");
        printChessArray(sparseArr);
////        // 稀疏数组转二维数组
        //读盘
        int[][] ints = discReading();
        int[][] chessArr2 = sparseToChess(ints);
        System.out.println("稀疏数组转二维数组");
        printChessArray(chessArr2);
    }

    /**
     * 二维数组转稀疏数组
     *
     * @param chessArr
     */
    private static int[][] chessToSparse(int[][] chessArr) {
        //获取有效棋子个数
        int sum = 0;
        for (int[] row : chessArr) {
            for (int chess : row) {
                if (chess != 0) {
                    sum++;
                }
            }
        }
        //定义稀疏数组
        /*
        二维数组转稀疏数组思路：
        1遍历原始的二维数组，得到有效个数 sum
        2根据 sum 创建 稀疏数组 sparseArr = int[sum + 1][3]
        3将二维数据的有效数据存入到稀疏数组中（从第 2 行开始存储）
        4最后将棋盘大小和有效个数写入第一行
        */
        int[][] sparseArr = new int[sum + 1][3];
        // 3. 将二维数据的有效数据存入到稀疏数组中（从第 2 行开始存储）
        int chessRow = chessArr.length;  // 行： 棋盘大小
        int chessCol = 0;  // 列： 棋盘大小
        int count = 0; // 记录当前是第几个非 0 的数据
        for (int i = 0; i < chessArr.length; i++) {
            int[] rows = chessArr[i];
            if (chessCol == 0) {
                chessCol = rows.length;
            }
            for (int j = 0; j < rows.length; j++) {
                int chess = rows[j];
                if (chess == 0) {
                    continue;
                }
                count++;  // 第一行是棋盘信息，所以先自增
                sparseArr[count][0] = i;
                sparseArr[count][1] = j;
                sparseArr[count][2] = chess;
            }
        }
        // 4. 补全第一行的棋盘大小和有效数据
        sparseArr[0][0] = chessRow;
        sparseArr[0][1] = chessCol;
        sparseArr[0][2] = sum;
        save(sparseArr);
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     *
     * @param sparseArr
     * @return
     */
    private static int[][] sparseToChess(int[][] sparseArr) {
        //创建二维数组
        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            int[] rows = sparseArr[i];
            chessArr[rows[0]][rows[1]] = rows[2];
        }
        return chessArr;
    }

    /**
     * 打印棋盘上的棋子布局
     *
     * @param chessArr
     */
    public static void printChessArray(int[][] chessArr) {
        for (int[] row : chessArr) {
            for (int data : row) {
                // 左对齐，使用两个空格补齐 2 位数
                System.out.printf("%-2d\t", data);
            }
            System.out.println("");
        }
}

    /**
     * 存盘
     *
     * @param chessArr
     */
    public static void save(int[][] chessArr) {
        //存盘
        File file = new File("D:\\MyCodeSpace\\IdeaSpace\\MyAlgorithms\\ map.data");
        try (Writer writer = new FileWriter(file);) {
            if (!file.exists()){
                if (!file.createNewFile()) {
                    return;
                }
            }
            for (int[] row : chessArr) {
                for (int i = 0; i < row.length; i++) {
                    // 左对齐，使用两个空格补齐 2 位数
                    if (i==row.length-1){
                        writer.write(row[i]+"");
                        System.out.printf("%-2d\t", row[i]);
                    }else {
                        writer.write(row[i]+"\t");
                        System.out.printf("%-2d\t", row[i]);
                    }

                }
                System.out.println("");
                writer.write("\n");
            }
            //9.把writeFile里的数据全部刷新一次，全部写入文件中
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读盘
     *
     */
    public static int[][] discReading() {
        File file = new File("D:\\MyCodeSpace\\IdeaSpace\\MyAlgorithms\\ map.data");
        //3.声明一个二维数组
        int[][] array = null;
        try (Reader reader = new FileReader(file);BufferedReader readerBuf = new BufferedReader(reader)){
            //6.创建一个集合，用来存放读取的文件的数据
            List<String> strList = new ArrayList<>();
            //7.用来存放一行的数据
            String lineStr;
            //8.逐行读取txt文件中的内容
            while((lineStr = readerBuf.readLine()) != null) {
                //9.把读取的行添加到list中
                strList.add(lineStr);
            }
            //11.获取数组有多少列
            String s =  strList.get(0);
            int columnNum = s.split("\t").length;

            //12.根据文件行数创建对应的数组
            array = new int[strList.size()][columnNum];
            //13.记录输出当前行
            int count = 0;
            //14.循环遍历集合，将集合中的数据放入数组中
            for(String str : strList) {
                //15.将读取的str按照","分割，用字符串数组来接收
                String[] strs = str.split("\t");
                for(int i = 0; i < columnNum; i++) {
                    array[count][i] = Integer.valueOf(strs[i]);
                }
                //16.将行数 + 1
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
      return array;
    }
}