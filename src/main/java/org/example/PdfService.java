package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class PdfService {
    private final PdfDocumentConverter pdfDocumentConverter = new PdfDocumentConverter();
    private final PdfImageWriter pdfImageWriter = new PdfImageWriter();
    private final WordConvertPDF wordConvertPDF = new WordConvertPDF();
    private final RtfReader rtfReader = new RtfReader();

    public void makeImage(String fileSource, String fullPathToImage) {
        DocumentType type = DocumentType.getType(fileSource);
        switch (type) {
            case TXT -> getImageFromTxtFile(fileSource, fullPathToImage);
            case PDF -> getImageFromPdf(fileSource, fullPathToImage);
            case DOCX -> getImageFromDocx(fileSource, fullPathToImage);
            case RTF -> getImageFromRtf(fileSource, fullPathToImage);
            default -> throw new UnsupportedOperationException();
        }
    }

    private void getImageFromRtf(String fileSource, String fullPathToImage) {
        byte[] data = pdfDocumentConverter.createPdfArray(rtfReader.readRtf(fileSource));
        pdfImageWriter.savePdfPageToImageFile(data, fullPathToImage);
    }

    private void getImageFromDocx(String fileSource, String fullPathToImage) {
        byte[] pdfArray = wordConvertPDF.convertToPdfArray(fileSource);
        pdfImageWriter.savePdfPageToImageFile(pdfArray, fullPathToImage);
    }

    @SneakyThrows
    private void getImageFromTxtFile(String fileSource, String fullPathToImage) {
        String data = Files.readString(Path.of(fileSource));
        byte[] pdfArray = pdfDocumentConverter.createPdfArray(data);
        pdfImageWriter.savePdfPageToImageFile(pdfArray, fullPathToImage);
    }

    private void getImageFromPdf(String fileSource, String fullPathToImage) {
         pdfImageWriter.savePdfPageToImageFile(fileSource, fullPathToImage);
    }
}
