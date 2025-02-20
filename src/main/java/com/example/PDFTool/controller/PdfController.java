package com.example.PDFTool.controller;

import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;
import com.example.PDFTool.service.PDFService;
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
    PDFService pdfService;

    @PostMapping("/onurbelgesi")
    public ResponseEntity<byte[]> generateOnurBelgesi(@RequestBody OnurBelgesiContent data) {
        try {

            byte[] pdfContent = pdfService.generateOnurBelgesi(data);

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
            byte[] pdfContent = pdfService.generateTanitimBelgesi(data);

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
