package com.example.PDFTool.controller;

import com.example.PDFTool.factory.PDFFactory;
import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.factory.PDFType;
import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdf/generate")
public class PdfController {

    @Autowired
    PDFFactory pdfFactory;


    @PostMapping("/onurbelgesi")
    public ResponseEntity<byte[]> generateOnurBelgesi(@RequestBody OnurBelgesiContent data) {
        try {

            PDFGenerator generator=pdfFactory.createPDFGenerator(PDFType.ONUR_BELGESI);
            byte[] pdfContent = generator.generateOnurBelgesi(data);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("onurbelgesi.pdf").build());

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/tanitimbelgesi")
    public ResponseEntity<byte[]> generateTanitimBelgesi(@RequestBody TanitimBelgesiContent data) {
        try {
            PDFGenerator generator=pdfFactory.createPDFGenerator(PDFType.TANITIM_BELGESI);
            byte[] pdfContent = generator.generateTanitimBelgesi(data);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("tanitimbelgesi.pdf").build());
            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);

        } catch (IOException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
