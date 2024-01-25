package org.example;

import java.io.File;
import java.net.URISyntaxException;

public class Runner {
    static PdfService pdfService = new PdfService();
    public static void main(String[] args) throws URISyntaxException {
        getImageFromTxt();
        getImageFromRtf();
    }

    private static void getImageFromRtf() {
        String fileDoc = getFilePath("test.rtf");
        String imageResultDoc = "image-rtf.jpeg";
        pdfService.makeImage(fileDoc, imageResultDoc);
    }

    private static void getImageFromDoc() {
        String fileDoc = getFilePath("test2.docx");
        String imageResultDoc = "image-doc.jpeg";
        pdfService.makeImage(fileDoc, imageResultDoc);
    }

    private static void getImageFromPdf() {
        String filePdf = getFilePath("test.pdf");
        String imageResultPdf = "image-pdf.jpeg";
        pdfService.makeImage(filePdf, imageResultPdf);
    }

    private static void getImageFromTxt() {
        String fileText = getFilePath("test.txt");
        String imageResultText = "image-text.jpeg";
        pdfService.makeImage(fileText, imageResultText);
    }

    private static String getFilePath(String resourceFileName) {
        return new File(Runner.class.getClassLoader().getResource(resourceFileName)
                .getFile()).getAbsolutePath();
    }

//    @SneakyThrows
//    private static void createTestSource(String fileData) {
//        AtomicInteger number = new AtomicInteger();
//        String text = Stream.generate(() -> "some text " + number.addAndGet(1))
//                .limit(100)
//                .collect(Collectors.joining("\n"));
//        try (FileWriter fileWriter = new FileWriter(fileData)) {
//            fileWriter.write(text);
//        }
//    }
}