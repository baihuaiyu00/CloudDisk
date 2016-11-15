package org.log.util;

import org.log.controller.FileUploadController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Admin on 2016/5/3.
 */
public class PathUtil {

    public static String makeStorePath(String storePath, String fileName) throws IOException {

        int hashCode = fileName.hashCode();

        int dir1 = hashCode&0xf;

        int dir2 = (hashCode&0xf0)>>4;
        InputStream in = FileUploadController.class.getClassLoader().getResourceAsStream("/path.properties");
        Properties properties = new Properties();
        properties.load(in);
        String pathGet = properties.getProperty("dirpath");

//        String path = storePath+pathGet+dir1+pathGet+dir2;   //Windows;
        String path = storePath+pathGet+dir1+pathGet+dir2;   //Linux;

        File file = new File(path);
        if(!file.exists())
            file.mkdirs();
        return path ;
    }
}
