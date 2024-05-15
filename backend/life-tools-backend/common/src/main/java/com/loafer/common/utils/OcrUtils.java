package com.loafer.common.utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * tess4j Ocr图片文字识别
 * @author loafer
 * @since 2024-04-22 09:19:46
 **/
public class OcrUtils {

    public Tesseract tesseract;

    public OcrUtils() {
        tesseract = new Tesseract();
        tesseract.setDatapath("D:\\Tess4J\\tessdata");
        // 设置为中文简体
        tesseract.setLanguage("chi_sim");
    }

    public OcrUtils(String dataPath, String language) {
        tesseract = new Tesseract();
        tesseract.setDatapath(dataPath);
        tesseract.setLanguage(language);
    }

    /**
     * File识别文字
     * @param file
     * @return
     * @throws TesseractException
     */
    public String doOcr(File file) throws TesseractException {
        return tesseract.doOCR(file);
    }

    /**
     * 图片BufferedImage识别文字
     * @param image
     * @return
     * @throws TesseractException
     */
    public String doOcr(BufferedImage image) throws TesseractException {
        return tesseract.doOCR(image);
    }

    /**
     * 图片InputStream识别文字
     * @param inputStream
     * @return
     * @throws IOException
     * @throws TesseractException
     */
    public String doOcr(InputStream inputStream) throws IOException, TesseractException {
        BufferedImage image = ImageIO.read(inputStream);
        return tesseract.doOCR(image);
    }
    
}
