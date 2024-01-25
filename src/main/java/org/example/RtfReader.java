package org.example;

import lombok.SneakyThrows;
import org.apache.commons.codec.CharEncoding;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class RtfReader {
    private static final String ENCODING = CharEncoding.UTF_8;

    @SneakyThrows
    public String readRtf(String filename) {
        RTFEditorKit rtf = new RTFEditorKit();
        Document doc = rtf.createDefaultDocument();
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader i = new InputStreamReader(fis, ENCODING);
        rtf.read(i,doc,0);
        return doc.getText(0,doc.getLength());
    }
}
