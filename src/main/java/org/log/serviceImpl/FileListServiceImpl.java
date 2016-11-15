package org.log.serviceImpl;

import org.log.entity.FileListBean;
import org.log.entity.UserLoginBean;
import org.log.mapper.FileOperationMapper;
import org.log.service.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 2016/4/28.
 */
@Service
public class FileListServiceImpl implements FileListService {

    @Autowired
    private FileOperationMapper flm;

    public List<FileListBean> showFiles(UserLoginBean userLoginBean) {
        List<FileListBean> list =  flm.findFileByUsername(userLoginBean);
//        for(FileListBean fileListBean:list)
//        {
//            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
//            String newtime = sdf.format(new Date(fileListBean.getUpdateTime()));
//            fileListBean.setUpdateTime(newtime);
//            System.out.println(fileListBean.getUpdateTime());
//        }
        if(list.isEmpty()) {
            System.out.println("the result is null!");
            return null;
        }
        else
            return list;
    }

    public List<FileListBean> chooseType(List<FileListBean> list, String file_type) {
        if(!file_type.equals("all"))
        {
            if(file_type.equals("others")) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    FileListBean fbb = (FileListBean) it.next();
                    if (fbb.getFileType().startsWith("text") || fbb.getFileType().startsWith("video")|| fbb.getFileType().startsWith("audio")|| fbb.getFileType().startsWith("application")|| fbb.getFileType().startsWith("image"))
                        it.remove();
                }
            }

            else {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    FileListBean fbb = (FileListBean) it.next();
                    if (!fbb.getFileType().startsWith(file_type))
                        it.remove();
                }
            }
        }
        return list;
    }
}
