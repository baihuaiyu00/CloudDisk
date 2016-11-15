package org.log.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 2016/4/19.
 */
public class MD5Util {

    public static String encoder(String message){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(message.getBytes());
            return new BASE64Encoder().encode(b);

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

}
