package org.example;

public enum DocumentType {
    TXT,
    DOCX,
    RTF,
    XLSX,
    PDF;

    public static DocumentType getType(String filename) {
        for (DocumentType value : DocumentType.values()) {
          if (filename.toUpperCase().endsWith("." + value.name())) {
              return value;
          }
        }
        throw new IllegalArgumentException("No enum constant for " + filename);
    }
}
