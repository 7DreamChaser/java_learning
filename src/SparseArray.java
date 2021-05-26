import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int[] row : chessArr1){
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组
        //1、先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0)sum++;
            }
        }
        System.out.println("sum= "+sum);

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = 2;
        //遍历二维数组，将值存储到稀疏数组中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组：");
//      1)在前面的基础上，将稀疏数组保存到磁盘上，比如map.data存的是sparseArr

        for(int i = 0; i<sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            System.out.println();
        }
        System.out.println();
        String filePath = "E:\\Desktop\\java数据结构与算法\\代码\\sparsearry\\mydata";
        File dir = new File(filePath);
        if(!dir.exists()){dir.mkdirs();}
        File checFile = new File(filePath+"\\map.data");
        FileWriter writer = null;
        if(!checFile.exists()) {
            try {
                checFile.createNewFile();
                //写入内容
                writer = new FileWriter(checFile,false);
                for(int i = 0; i<sparseArr.length; i++){
                    writer.append(sparseArr[i][0]+" "+sparseArr[i][1]+" "+sparseArr[i][2]+"\r\n");

                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //将稀疏数组还原成原始数组
        //先读取第一行获取原属数组的行和列
        //2)恢复原来的数组时，读取map.data进行恢复，用的chessArr2接受
        File new_dir = new File(filePath+"\\map.data");
        Reader reader = null;
        StringBuilder str_data = new StringBuilder();
        if(new_dir.exists()){
            try {
                reader = new FileReader(new_dir);
                char[] chs = new char[1024];
                int len = 0;
                while((len=reader.read(chs))!=-1){

                    str_data.append(chs);
                    //System.out.println(chs);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String s_data = str_data.toString();
        String[] strings = s_data.split("\r\n");

//        String[] s1 = strings[0].split(" ");
//
//        sparseArr[0][0] = Integer.parseInt(s1[0]);
//        sparseArr[0][1] = Integer.parseInt(s1[1]);
//        sparseArr[0][2] = Integer.parseInt(s1[2]);


        int[][] new_sparseArr = new int[sum+1][3];
        for (int i = 0; i < strings.length-1; i++) {

            for (int j = 0; j < strings[i].split(" ").length; j++) {
                new_sparseArr[i][j] = Integer.parseInt(strings[i].split(" ")[j]);
            }
        }
        for (int[] row :
                new_sparseArr) {
            for (int data :
                    row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
//        System.out.println(strings.length);



//        System.out.println(str_data.toString());

        int chessArr2[][] = new int[new_sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < new_sparseArr.length; i++) {
            chessArr2[new_sparseArr[i][0]][new_sparseArr[i][1]] = new_sparseArr[i][2];
        }

        for (int[] row:
             chessArr2) {
            for (int data:
                 row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



//
//        2)恢复原来的数组时，读取map.data进行恢复，用的chessArr2接受




    }
}
