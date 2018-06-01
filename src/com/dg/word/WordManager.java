package com.dg.word;

import java.io.*;

public class WordManager {
    private String srcPath;
    private String destA;
    private String destB;
    private File destFileA;
    private File destFileB;
    private int numA = 0;
    private int numB = 0;
    private BufferedWriter bwA;
    private BufferedWriter bwB;

    boolean isOver=false;

    /**
     * 创建目标文件
     */
    public void createDestFile(String destA, String destB) throws IOException {
        destFileA = new File(destA);
        destFileB = new File(destB);
//        if (!destFileA.exists()){
//            destFileA.mkdir();
//        }
//        if (!destFileB.exists()){
//            destFileB.mkdir();
//        }
        bwA =new BufferedWriter(new FileWriter(destFileA));
        bwB =new BufferedWriter(new FileWriter(destFileB));
    }

    //遍历文件夹
    public void getWordFromJavaFile(File file) {
        if (isOver){
            return;
        }
        //判断是否是文件夹
        boolean isD = file.isDirectory();
        // 如果是文件夹，获取文件列表
        if (isD) {
            File[] files = file.listFiles();
            for (File f:files){
                getWordFromJavaFile(f);
            }

        } else {
            //判断是否是.java结尾的文件
            String fileName = file.getName();
            if (fileName.endsWith(".java")) {
                System.out.println("正在读取--->>"+fileName);
                //读取文本数据
                readWordFromFile(file);
                System.out.println("读取完成--->>"+fileName);

            }
        }
    }

    /**
     * 读取文本数据
     * @param file
     * @return
     */
    private String readWordFromFile(File file) {
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder sb = null;

        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            sb = new StringBuilder();


            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                if (numA < 2000) {
                    bwA.write(line+"\n");
                    numA++;
                } else if (numB < 2000) {
                    bwB.write(line+"\n");
                    numB++;
                } else {
                    isOver=true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
                if (br!=null){
                    br.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return sb.toString();

    }

    public void close() throws IOException {
        if (bwA!=null){
            bwA.close();
        }
        if (bwB!=null){
            bwB.close();
        }
    }


}
