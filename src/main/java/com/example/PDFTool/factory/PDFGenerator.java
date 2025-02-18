package com.example.PDFTool.factory;

import com.example.PDFTool.model.PDFContent;

import java.io.IOException;


public interface PDFGenerator {

 byte[] generatePDF(PDFContent data) throws IOException;

}
