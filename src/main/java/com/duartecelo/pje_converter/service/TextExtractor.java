package com.duartecelo.pje_converter.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TextExtractor {

    public List<String> extractTextByPage(String pdfPath) throws IOException {
        List<String> pagesText = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(new File(pdfPath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            int totalPages = document.getNumberOfPages();

            for (int i = 0; i <= totalPages; i++) {
                pdfStripper.setStartPage(i);
                pdfStripper.setEndPage(i);
                String pageText = pdfStripper.getText(document);
                pagesText.add(pageText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pagesText;
    }

}