package com.example.test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class test2 {
    /**
     * 加密解密的密钥
     */
    public static final String appKey = "r6dstkvjral43brqz9yhetwg";
    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/ECB/PKCS5Padding";

    /***
     * 解密数据
     * @param decryptString
     * @return
     * @throws Exception
     */
    public static String decrypt(String decryptString) {
        try {
            byte[] byteKey = appKey.getBytes();
            DESedeKeySpec spec = new DESedeKeySpec(byteKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
            SecretKey sec = keyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, sec);

            Base64.decode((decryptString).getBytes(), Base64.NO_PADDING);

            return new String(cipher.doFinal(hexStringToBytes(decryptString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 十六进制转 字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 二行制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        String ff = "8039c0b9bd181bfbce34de80f5174a4073eea719196fb31e83170ab1af08d746e1786f3f3abce6a8d477ca50846f0e852ce9e8e8aacfc5471aa22812335c31a25f3d0ebc768ce09fa3234dc2de977f28e545026e3d9fd7a3e1786f3f3abce6a8d477ca50846f0e852ce9e8e8aacfc5471aa22812335c31a25f3d0ebc768ce09fa3234dc2de977f28241039104f5b1b9ff787aa2517d0148f2b35864e14aacc25bf733a235a1a0fd588f665b8d86989bc88f665b8d86989bc88f665b8d86989bc";
        String ffd = decrypt(ff);
        System.out.println("-" + ffd);
    }

}
