package com.jyeory.sso.oauth.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class SHA1 {
    private static SHA1 m_instance = null;

    /* private SHA1() {} */

    public SHA1() {
    }

    public SHA1 getInstance() {
        if (m_instance != null) {
            return m_instance;
        } else {
            m_instance = new SHA1();
            return m_instance;
        }
    }

    /**
     * 엔크립션
     * 
     * @param word
     * @return
     * @throws Exception
     */
    public static String encode(String word) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-1");

        md.update(word.getBytes());

        byte[] raw = md.digest();

        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(raw);
    }

    private static String asHex(byte hash[]) {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if (((int) hash[i] & 0xff) < 0x10)
                buf.append("0");

            buf.append(Long.toString((int) hash[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * 사용방법 SHA1 md = new SHA1(); userPass = md.hash(password);
     * 
     * @param arg
     *            : string type
     * @return hash된 결과 값
     */
    public static String hash(String arg) {
        String ret = hash(arg.getBytes());
        /* 2013.04.04 jhkim 암호화 모듈 추가 및 패스워드 로직 변경 */
        ret = convertSHA256(ret);
        return ret;
    }

    public static String convertSHA256(String str) {

        String SHA = "";
        try {

            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            byte byteData[] = sh.digest(str.getBytes());
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            SHA = encoder.encode(byteData);
            /*
             * sh.update(str.getBytes()); byte byteData[] = sh.digest();
             * 
             * StringBuffer sb = new StringBuffer(); for(int i = 0 ; i < byteData.length ;
             * i++){ sb.append(Integer.toString((byteData[i]&0xff) + 0x100,
             * 16).substring(1)); } SHA = sb.toString();
             */

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }

    public String doHash(String arg) {
        return hash(arg.getBytes());
    }

    public static String hash(byte barray[]) {
        byte[] result;

        try {
            MessageDigest m = MessageDigest.getInstance("SHA-1");
            m.reset();
            result = m.digest(barray);
            return asHex(result);
        } catch (NoSuchAlgorithmException ex) {

        }
        return "";
    }

    public static void main(String[] args) {
        String sResult;
        sResult = "";

        /**
         * 사용방법 SHA1 md = new SHA1(); userPass = md.hash(password); hash(String s)를 호출하면
         * SHA-1알로리즘으로 Hash된 값을 얻을 수 있습니다.
         */

        if (args.length > 0) {
            sResult += args[0];
            System.out.println(sResult);
            System.out.println(hash(sResult));
        } else {
            System.out.println("Invalid argment!!!");
            System.out.println("Do test like this (java SHA1 sourcetext)");
        }

    }

}