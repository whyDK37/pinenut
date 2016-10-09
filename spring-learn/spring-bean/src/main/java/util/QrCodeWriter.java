package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * based on google zxing lib.
 */
public abstract class QrCodeWriter {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static final String DEFAULT_CHARACTER_SET = "UTF-8";
    public static final String DEFAULT_IMAGE_FORMATE = "jpg";

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static void writeToFile(String content, String format, File file)
            throws IOException, WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        //内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHARACTER_SET);
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);

        writeToFile(bitMatrix, format, file);
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }


    public static void writeToStream(String content, String format, OutputStream stream)
            throws IOException, WriterException {
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        Map hints = new HashMap();
//        //内容所使用编码
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);

        writeToStream(content, format, stream, 200, 200);
    }

    public static void writeToStream(String content, String format, OutputStream stream, int width, int height)
            throws IOException, WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        //内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHARACTER_SET);
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        writeToStream(bitMatrix, format, stream);
    }

}