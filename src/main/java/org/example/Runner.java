package org.example;

import lombok.SneakyThrows;

import java.io.FileWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) {
        String fileData = "data.txt";
        createTestSource(fileData);

        String imageResultFile = "image-1.jpeg";
        PdfService pdfService = new PdfService();
        pdfService.makeImage(fileData, imageResultFile);
    }

    @SneakyThrows
    private static void createTestSource(String fileData) {
        AtomicInteger number = new AtomicInteger();
        String text = Stream.generate(() -> "some text " + number.addAndGet(1))
                .limit(100)
                .collect(Collectors.joining("\n"));
        try (FileWriter fileWriter = new FileWriter(fileData)) {
            fileWriter.write(text);
        }
    }
}