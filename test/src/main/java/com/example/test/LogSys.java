package com.example.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogSys {


    public static void main(String[] args) {
        String content = read("C:\\Users\\thinkpad\\Downloads\\CmQBMF04Co2vhEtdAAPxvN66j0g931\\Log_cdm201907240938.txt");

        String[] data = content.split("\\n");

        int count = 0;

        for (String temp : data) {
            if (temp.contains("romVersionCode")) {
                count++;
                System.out.println(temp);
            }
        }

        System.out.println("success:" + count);

    }


    private static String read(String path) {
        StringBuffer res = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                res.append(line + "\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

}
