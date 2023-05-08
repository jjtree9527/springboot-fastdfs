package top.inson.springboot;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FastDfsTest {

    @Test
    public void testDfs(){
        String filePath = "http://192.168.1.254:8888/group1/M00/00/00/wKgB_mC66caAX3foAAAnL365EGQ853.jpg";
        StorePath storePath = StorePath.parseFromUrl(filePath);
        String group = storePath.getGroup();
        String fullPath = storePath.getFullPath();
        String path = storePath.getPath();
        log.info("fullPath:{},path:{},group: {}", fullPath, path, group);


    }

    @Test
    public void handler(){
        String webServerUrl = "http://upload.jxsliot.cn";
        if (!webServerUrl.endsWith("/"))
            webServerUrl += "/";
        log.info(webServerUrl);
    }



}
