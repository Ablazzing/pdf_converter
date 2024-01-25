package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PdfDocumentConverter {
    private static final float FONT_SIZE = 16;
    private final Font font = createFont();

    public byte[] createPdfArray(String text) {
        try {
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            fillPdfDocument(data, text);
            return data.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private void fillPdfDocument(OutputStream outputStream, String text) {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph paragraph = new Paragraph(text, this.font);
        document.add(paragraph);
        document.close();
    }

    public void saveTextToPdfFile(String fileResult, String text) {
        try {
            fillPdfDocument(new FileOutputStream(fileResult), text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private Font createFont() {
        String path = this.getClass().getClassLoader().getResource("arial.ttf").getPath();
        BaseFont unicode = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(unicode, FONT_SIZE);
    }
}
