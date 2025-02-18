package com.example.PDFTool.controller;

import com.example.PDFTool.model.PDFContent;
import com.example.PDFTool.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    PDFService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePDF(@RequestBody PDFContent pdfrequest) {
        try {
            byte[] pdfContent = pdfService.createPDF(pdfrequest);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("tanitimbelgesi.pdf").build());
            return new ResponseEntity<>(pdfContent,headers, HttpStatus.OK);

        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }}
