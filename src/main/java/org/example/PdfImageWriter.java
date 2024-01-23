package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Записывает страницу из pdf в image
 */
public class PdfImageWriter {
    private static final int FIRST_PAGE = 0;
    private static final String JPEG_FORMAT = "jpeg";
    private static final int IMAGE_QUALITY_DPI = 300;

    public void savePdfPageToImageFile(String fileSource, String fileOutput) {
        savePdfPageToImageFile(fileSource, fileOutput, JPEG_FORMAT);
    }

    public void savePdfPageToImageFile(String fileSource, String fileOutput,
                                       String imageFormat) {
        savePdfPageToImageFile(fileSource, fileOutput, imageFormat, FIRST_PAGE);
    }

    public void savePdfPageToImageFile(String fileSource, String fileOutput,
                                       String imageFormat, int pageIndex) {
        try {
            File imageFile = new File(fileOutput);
            BufferedImage image = getPageAsImageFromPdf(fileSource, pageIndex);
            ImageIO.write(image, imageFormat, imageFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayOutputStream getImageFromPdfPage(String fileSource, String imageFormat) {
        return getImageFromPdfPage(fileSource, imageFormat, FIRST_PAGE);
    }

    public ByteArrayOutputStream getImageFromPdfPage(String fileSource, String imageFormat,
                                                     int pageIndex) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage image = getPageAsImageFromPdf(fileSource, pageIndex);
            ImageIO.write(image, imageFormat, outputStream);
            return outputStream;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void savePdfPageToImageFile(byte[] data, String imageFile) {
        try {
            BufferedImage image = getPageAsImageFromPdf(data, FIRST_PAGE);
            ImageIO.write(image, JPEG_FORMAT, new File(imageFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedImage getPageAsImageFromPdf(byte[] data, int pageIndex) {
        try {
            PDDocument doc = Loader.loadPDF(data);
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            return pdfRenderer.renderImageWithDPI(pageIndex, IMAGE_QUALITY_DPI, ImageType.RGB);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedImage getPageAsImageFromPdf(String pdfFileSource, int pageIndex) {
        try {
            PDDocument doc = Loader.loadPDF(new File(pdfFileSource));
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            return pdfRenderer.renderImageWithDPI(pageIndex, IMAGE_QUALITY_DPI, ImageType.RGB);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
