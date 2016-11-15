package org.log.util;

import java.io.File;
import java.util.Map;

/**
 * Created by Admin on 2016/4/26.
 */
public class FileNameUpdateUtil {

    public static void FileNameUpdate(File file, Map<String, String> map) {
        if(file.isFile()){
            String uuidName = file.getName();

            String oldName = uuidName.substring(uuidName.indexOf("_")+1);
            map.put(uuidName, oldName);
        }else{
            File[] fs = file.listFiles();
            for(File f:fs){
                FileNameUpdate(f,map);
            }
        }
    }

}
