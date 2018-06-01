package com.dg.word;

import java.io.File;
import java.io.IOException;

public class Entry {

    public static void main(String[] args) throws IOException {
        String destA = "C:\\Users\\Administrator\\Desktop\\word\\A.txt";
        String destB = "C:\\Users\\Administrator\\Desktop\\word\\B.txt";
        String src="C:\\Users\\Administrator\\Desktop\\word\\user\\app\\src\\main\\java\\com\\rxxny_user\\Activity";
        File srcFile=new File(src);
        WordManager manager = new WordManager();
        manager.createDestFile(destA,destB);
        manager.getWordFromJavaFile(srcFile);
        manager.close();
    }
}
