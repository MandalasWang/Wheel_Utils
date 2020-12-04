package com.djcps.djutils.imgutil;

import com.djcps.djutils.imgutil.util.ImageRemarkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

/**
 * @author wyy
 * @version 1.0
 * @Classname TestController
 * @date 2020/12/3 9:33
 * @description
 **/
@Slf4j
@RestController
public class TestController {


    @RequestMapping("test")
    public void Test(MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\upload\\" + name;
        File filePath = new File(path);

        try {
            InputStream fileInputStream = file.getInputStream();
            OutputStream outputStream = new FileOutputStream(filePath);
            // 给图片添加水印文字
            ImageRemarkUtil.markImageByText("djcps", fileInputStream, outputStream);
        }catch (Exception e){
            log.error("",e);
        }finally {
            try {
                filePath.delete();
            }catch (Exception e1){
            }
        }

    }
}
