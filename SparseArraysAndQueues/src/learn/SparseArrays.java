package learn;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SparseArrays {
    public static void main(String[] args) throws IOException {
        //五子棋程序输赢判断
        //创建一个原始的二维数组,表示11行,11列,前面是行,后面是列
        int[][] arr = new int[11][11];
        //1:黑棋,2:白棋
        arr[3][5]=1;
        arr[1][3]=2;
        arr[2][4]=1;
        arr[0][2]=2;
        //输出原始的二维数组
        System.out.println("原始二维数组如下:");
        for (int[] row : arr) {
            for (int column : row) {
                System.out.printf("%d\t",column);
            }
            System.out.println();
        }
        //二维数组 转 稀疏数组
        //1.先遍历二维数组,得到非0数据的个数
        int sum=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(arr[i][j]!=0){
                    sum++;
                }
            }
        }
        //2. 根据sum就可以创建稀疏数组sparseArr int[sum + 1] [3]
        //数组中3表示3列,分别为原来二维数组的行,列,当前位置元素值
        int[][] sparseArr = new int[sum+1][3];
        //3.给稀疏数组第一行数据赋值,分别表示原二维数组的行,列,不为0数据个数
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;//有效数据的个数,即非0的值
        //在遍历二维数组,非0的存入稀疏数组
        int count=0;//记录是第几个非0的数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(arr[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;	//第一列
                    sparseArr[count][1] = j;	//第二列
                    sparseArr[count][2] = arr[i][j];	//第三列
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("转变后的稀疏数组为:");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\n", ints[0], ints[1], ints[2]);
        }
        System.out.println();
        //稀疏数组转换二维数组思路,sparseArr[0][0]代表二维数组的行,sparseArr[0][1]代表二维数组的列;
        int[][] newArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        //赋值给二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            newArr[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //输出转变后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组:");
        for(int[] row : newArr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        /*
         * 练习要求:
         * 	1.在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data
         *  2.恢复原来的数组时，读取map.data 进行恢复
         */
        File file = new File("SparseArrays/xs.data");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter ow = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        //将稀疏数组写入到文件中,字节流
        /*for (int[] ints : sparseArr) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    fos.write(ints[j]);
                    fos.write("\r\n".getBytes());
                } else {
                    fos.write(ints[j]);
                    fos.write(",".getBytes());
                }
            }
        }*/
        //字符流
        for(int i = 0;i < sparseArr.length;i++){
            if (i == sparseArr.length - 1) {
                ow.append(String.valueOf(sparseArr[i][0])).append(",").append
                        (String.valueOf(sparseArr[i][1])).append(",").append(String.valueOf(sparseArr[i][2]));
            } else {
                ow.append(String.valueOf(sparseArr[i][0])).append(",").append(String.valueOf(sparseArr[i][1])).append(",").append(String.valueOf(sparseArr[i][2])).append("\r\n");
            }
        }
        ow.close();
        //从文件中读取数据
        FileInputStream fis = new FileInputStream(file);
        BufferedReader bs = new BufferedReader(new InputStreamReader(fis,StandardCharsets.UTF_8));
        System.out.println("从文件中读取到的稀疏数组为:");
        while (bs.ready()){
            String[] str = bs.readLine().replaceAll(",", " ").split(" ");
            for (String s : str) {
                System.out.printf("%d\t",Integer.valueOf(s));
            }
            System.out.println();
        }
    }
}
