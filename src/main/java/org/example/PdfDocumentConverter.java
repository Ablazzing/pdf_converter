package org.example;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PdfDocumentConverter {
    private static final float FONT_SIZE = 16;
    private static final String FONT_TYPE = FontFactory.COURIER;
    private static final BaseColor FONT_COLOR = BaseColor.BLACK;

    public byte[] createPdfArray(String text) {
        try {
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            fillPdfDocument(data, text);
            return data.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTextToPdfFile(String fileResult, String text) {
        try {
            fillPdfDocument(new FileOutputStream(fileResult), text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private void fillPdfDocument(OutputStream outputStream, String text) {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph paragraph = new Paragraph(text, getFont());
        document.add(paragraph);
        document.close();
    }

    private Font getFont() {
        return FontFactory.getFont(FONT_TYPE, FONT_SIZE, FONT_COLOR);
    }
}
