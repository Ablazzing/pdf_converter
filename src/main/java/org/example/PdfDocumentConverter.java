package org.example;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class PdfDocumentConverter {
    private static final float FONT_SIZE = 16;
    private static final String FONT_TYPE = FontFactory.COURIER;
    private static final BaseColor FONT_COLOR = BaseColor.BLACK;

    public byte[] createPdfArray(String text) {
        try {
            ByteArrayOutputStream data = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, data);
            document.open();
            Paragraph paragraph = new Paragraph(text, getFont());
            document.add(paragraph);
            document.close();
            return data.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Font getFont() {
        return FontFactory.getFont(FONT_TYPE, FONT_SIZE, FONT_COLOR);
    }

    public void saveTextToPdfFile(String fileResult, String text) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileResult));

            document.open();
            Paragraph paragraph = new Paragraph(text, getFont());
            document.add(paragraph);
            document.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
