package ink.boyuan.wheels.img.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author wyy
 * @version 1.0
 * @Classname QrCode
 * @date 2020/12/4 13:42
 * @description
 **/
public class QrCode {


    /**
     * 生成二维码的方法写入输出流
     * @author wyy
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param filePath 输出流
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCodeImage(String content, int width, int height, OutputStream filePath) throws WriterException, IOException {
        generateQRCode(content, width, height, filePath);
    }


    /**
     * 生成二维码的方法直接输出
     * @author wyy
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param filePath 输出流
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCodeImage(String content, int width, int height, String filePath) throws WriterException, IOException {
        generateQRCode(content, width, height, filePath);

    }


    /**
     * 生成二维码的方法写入直接输出
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param filePath 输出流
     * @throws WriterException
     * @throws IOException
     */
    private static void generateQRCode(String content, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    /**
     * 生成二维码的方法写入输出流
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param filePath 输出流
     * @throws WriterException
     * @throws IOException
     */
    private static void generateQRCode(String content, int width, int height, OutputStream filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",filePath);
    }
}
