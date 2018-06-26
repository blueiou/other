package com.gxzytech.contract.test;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {

    //功能性操作
    public static void reacFile(){

    }

    public  static void checkFileAgain(String before,String after,int count)
    {
        try {
            // read file content from file
            StringBuffer sb= new StringBuffer("");
//            String before = "D://项目//杂活//翻译类//俄国片//2娜斯佳和玛丽娜：问题和回答.ass";
            FileReader reader = new FileReader(before);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            int i=0;
            int j=0;
            int d=0;
//            int k;
            Set<String> setList = new HashSet<>();
            String setString = null;
            while((str = br.readLine()) != null) {
                i++;
                if (i<=count){
                    sb.append(str+"\n");
                }
                else if (i>count)//从这一行开始，进行核验字幕
                {
                    setString=str.substring(10,33);
                    setList.add(setString);//和转不转换英文中文字体样式无关
                    //从一行开始算,需确保第一行要对应的，i为奇数时候
                    if (i%2==0){
                        j++;
                        System.out.println("i is :"+i+";count is :"+count+";setList is :"+setList.size()+";相加为 " +(setList.size()+count));
                        System.out.println("j is :"+j);
                        d=i-(count+setList.size());
                        System.out.println("d is :"+d);
                        if (j!=d)
                        {
                            System.err.println("时间不对称的为:"+str);
                            setList.remove(setString);//去掉，异常已抛出，需人工变成之前规律模样
                            System.out.println(setList.size());
                        }
                    }
                    //如果那一行为中文文字,将英文字幕切换为中文字幕.
                    if (replaceSpecStr(str) > 6)
                    {
                        str = str.replace("英文字幕样式", "单语字幕样式");
                    }
                    sb.append(str + "\n");
                }
            }
//            System.out.println("sb is :"+sb);
            br.close();
            reader.close();
            // write string to file
            FileWriter writer = new FileWriter(after);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            bw.close();
            writer.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件写入失败！" + e);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("文件写入失败！" + e);
        }
    }
    public static int replaceSpecStr(String orgStr){
        //匹配有汉字
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(orgStr);
        while (matcher.find())
        {
            str+= matcher.group(0);
        }
//        System.out.println("str length is :"+str.length());
        return str.length();//经过分析，正常情况下，非中文字幕的一般数量为6
    }

    private void checkTime(Set<String> set)
    {
//        int i=0;
       /* if (set.size()==2)
        {
//            set.
//            str.substring(10,33);
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String str = it.next().substring(10,33);
                System.out.println(str);
            }
        }*/
    }
    public static void main(String[] args) {

        String before = "D://项目//杂活//翻译类//双胞胎//双胞胎test1.ass";
        String after = "D://项目//杂活//翻译类//双胞胎//双胞胎test1_after.ass";
        //输入的[Events]所在的行数
        checkFileAgain(before,after,42);
        System.out.println((int)1/2);

    }

//输入原路径和之后的路径
    /*public static int checkFile(String before,String after){
        try {
            // read file content from file
            StringBuffer sb= new StringBuffer("");
//            String before = "D://项目//杂活//翻译类//俄国片//2.ass";
            FileReader reader = new FileReader(before);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            int count=0;
            while((str = br.readLine()) != null) {
                sb.append(str+"\n");
                count++;
                if (str.contains("Events"))//从这一行开始，进行核验字幕
                {
                return count;
                }
            }
            br.close();
            reader.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }*/
    //可参照
/*int ok = 1
for(int i = 0;i < (int) i/2;i++ ){
        if (a[2*i] !=a[2*i+1]){
            printf("Wrong!");
            ok = 0;
            break;
        }
    }
if (ok == 1) {
        printf("Right");
    }*/
}

