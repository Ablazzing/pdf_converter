package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class PdfService {

    public void makeImage(String fileSource, String fullPathToImage) {
        DocumentType type = DocumentType.getType(fileSource);
        switch (type) {
            case TXT -> getImageFromTxtFile(fileSource, fullPathToImage);
            default -> throw new UnsupportedOperationException();
        }
    }

    @SneakyThrows
    private void getImageFromTxtFile(String fileSource, String fullPathToImage) {
        String data = Files.readString(Path.of(fileSource));
        PdfDocumentConverter pdfDocumentConverter = new PdfDocumentConverter();
        PdfImageWriter pdfImageWriter = new PdfImageWriter();
        byte[] pdfArray = pdfDocumentConverter.createPdfArray(data);
        pdfImageWriter.savePdfPageToImageFile(pdfArray, fullPathToImage);
    }
}
