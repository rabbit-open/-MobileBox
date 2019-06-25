package com.example.test;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class cdmToken {

//    public static void main(String[] args) {
//        for (int i = 0; i < 1024; i++) {
//            System.out.println(" <dimen name=\"lay_px" + i +
//                    "\">" + i / 2f +
//                    "dp" +
//                    "</dimen>");
//        }
//
//        for (int i = 12; i < 200; i++) {
//            System.out.println(" <dimen name=\"lay_sp" + i +
//                    "\">" + i / 2f +
//                    "sp" +
//                    "</dimen>");
//        }
//
//    }


    public static void main(String[] args) {
        String token = null;
        try {
            token = encrypt(args[0], SIGN_IN_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(token);
    }

    public static final String SIGN_IN_KEY = "bCGYyyP9hJke32bpKJq2gQ";

    public static String encrypt(String content, String encodingAesKey) {

        ByteGroup byteCollector = new ByteGroup();
        byte[] bytes = content.getBytes(Charset.forName("utf-8"));
        byteCollector.addBytes(bytes);

        byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
        byteCollector.addBytes(padBytes);

        byte[] unencrypted = byteCollector.toBytes();

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

            byte[] aesKey = Base64.decode((encodingAesKey).getBytes(), Base64.NO_PADDING);

            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encrypted = cipher.doFinal(unencrypted);

//            Base64 base64 = new Base64();
            String base64Encrypted = Base64.encodeToString(encrypted, Base64.NO_WRAP);

            return base64Encrypted;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
