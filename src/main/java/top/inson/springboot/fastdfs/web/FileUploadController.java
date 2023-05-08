package top.inson.springboot.fastdfs.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.inson.springboot.fastdfs.utils.FastDFSClient;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FastDFSClient fastDFSClient;


    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file){

        try {
            String fileUrl = fastDFSClient.uploadFile(file);
            log.info("fileUrl：" + fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }

    @PostMapping("/downloadFile")
    public void downloadFile(String fileUrl, HttpServletResponse response){
        ServletOutputStream outputStream = null;
        try {
            byte[] fileDatas = fastDFSClient.downloadFile(fileUrl);

            outputStream = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment;filename=upload.jpg");
            response.setCharacterEncoding("UTF-8");
            outputStream.write(fileDatas);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

}
