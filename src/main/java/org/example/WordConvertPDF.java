package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class WordConvertPDF {
    @SneakyThrows
    public byte[] convertToPdfArray(String docPath) {
        try (InputStream docxInputStream = new FileInputStream(docPath);
             XWPFDocument document = new XWPFDocument(docxInputStream);
             ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream()) {
            Document pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, pdfOutputStream);
            pdfDocument.open();

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                pdfDocument.add(new Paragraph(paragraph.getText()));
            }
            pdfDocument.close();
            return pdfOutputStream.toByteArray();
        }
    }
}