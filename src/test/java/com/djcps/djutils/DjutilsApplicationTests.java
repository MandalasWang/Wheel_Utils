package com.djcps.djutils;

import com.djcps.djutils.imgutil.util.ImageRemarkUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

class DjutilsApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {

        String srcImgPath = "D:\\work\\img\\1.png";
        InputStream inputStream = new FileInputStream(srcImgPath);
        String logoText = "复 印 无 效";
        String iconPath = "d:/2.jpg";

        String targerTextPath = "D:\\work\\img\\1new.png";
        String targerTextPath2 = "D:\\work\\img\\1new45.png";
        OutputStream outputStream = new FileOutputStream(targerTextPath);
        OutputStream outputStream45 = new FileOutputStream(targerTextPath2);

        String targerIconPath = "d:/qie_icon.jpg";
        String targerIconPath2 = "d:/qie_icon_rotate.jpg";

        System.out.println("给图片添加水印文字开始...");
        // 给图片添加水印文字
        ImageRemarkUtil.markImageByText(logoText, inputStream, outputStream);
        // 给图片添加水印文字,水印文字旋转-45
        ImageRemarkUtil.markImageByText(logoText, inputStream, outputStream45, -45);
        System.out.println("给图片添加水印文字结束...");
    }

}
