package com.example.PDFTool.service;

import com.example.PDFTool.factory.ımpl.TanitimBelgesi;
import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.model.PDFContent;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PDFService {


    private final PDFGenerator pdfGenerator;

    public PDFService() {
        this.pdfGenerator = new TanitimBelgesi();
    }

    public byte[] createPDF(PDFContent request) throws IOException {

        return pdfGenerator.generatePDF(request);
    }

}
