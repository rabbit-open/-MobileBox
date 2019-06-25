//package com.example.test;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class TestFile {
//    public TestFile() {
//    }
//
//    public static String read(String path) {
//        StringBuffer res = new StringBuffer();
//        String line = null;
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
//            while ((line = reader.readLine()) != null) {
//                res.append(line + "\n");
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res.toString();
//    }
//
//    //     sum += 1;
////       if(sum == start){
////           res.append(line + "\n");
////           if(y < size){
////
////           }
////       }
//    public static String readFor(String path, int num, int size) {
//        StringBuffer res = new StringBuffer();
//        String line = null;
//        int sum = 0;
//        int start = (num - 1) * size + 1;
//        int y = 0;
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
//            while ((line = reader.readLine()) != null) {
//                sum += 1;
//                if (sum == start) {
//                    sum -= 1;
//                    if (y < size) {
//                        res.append(line + ",");
//                        y += 1;
//                    } else {
//                        break;
//                    }
//                }
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res.toString();
//    }
//
//
//    public static boolean write(String cont, File dist) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(dist));
//            writer.write(cont);
//            writer.flush();
//            writer.close();
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static void main(String[] args) {
//        //File src = new File("C:\\mobile\\hualala\\caidanmao_android\\app_m_order\\src\\main\\res\\layout");
//        File src = new File("C:\\mobile\\hualala\\caidanmao_android\\app_m_order\\src\\main\\res\\drawable");
//        File[] files = src.listFiles();
//        if (files != null) {
//            for (File temp : files) {
//                if (temp.isFile()&&temp.getName().toLowerCase().endsWith(".xml")){
//                    saveFile(temp);
//                }
//            }
//        }
//    }
//
//    private static void saveFile(File src) {
//        String cont = TestFile.read(src.getAbsolutePath());
//
//        for (int i = 0; i < 1024; i++) {
//            String regix = "\"" + String.valueOf(i) + "dp\"";
//            cont = cont.replaceAll(regix, "\"@dimen/lay_px" + i + "\"");
//        }
//
//        for (int i = 12; i < 200; i++) {
//            String regix = "\"" + String.valueOf(i) + "sp\"";
//            cont = cont.replaceAll(regix, "\"@dimen/lay_sp" + i + "\"");
//        }
//        //
//        System.out.println(TestFile.write(cont, src));
//    }
//
//
//}