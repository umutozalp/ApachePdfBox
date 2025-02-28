package com.example.PDFTool.factory.impl;

import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;
import com.example.PDFTool.service.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OnurBelgesiGenerator implements PDFGenerator {


    @Autowired
    QRCodeGenerator qrCodeGenerator;

    @Override
    public byte[] generateOnurBelgesi(OnurBelgesiContent data) throws IOException {

        try {
            //PDF Şablonu yükleme
            ClassPathResource pdfTemplate = new ClassPathResource("templates/onurbelgesi.pdf");
            InputStream inputStream = pdfTemplate.getInputStream();
            PDDocument document = PDDocument.load(inputStream);
            PDPage page = document.getPage(0);

            //Türkce destekli font yükleme
            InputStream fontStream = new ClassPathResource("templates/fonts/openSans-Bold.ttf").getInputStream();
            PDType0Font font = PDType0Font.load(document, fontStream);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

            contentStream.beginText();
            contentStream.setFont(font, 16);
            contentStream.newLineAtOffset(335, 500);
            contentStream.showText("ONUR BELGESİ");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font, 14);
            contentStream.newLineAtOffset(660, 550);
            contentStream.showText(data.getTarih());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font, 14);
            contentStream.newLineAtOffset(117, 400);
            contentStream.showText("Sayın " + data.getAdSoyad() + " meslekte 5. yılınız sebebiyle Onur Belgesi almaya hak kazandınız.");
            contentStream.endText();
            contentStream.close();


            try {
                byte[] qrCodeImage = qrCodeGenerator.generateQRCode("test", 200, 200);
                qrCodeGenerator.addQRCodeToPDF(document, qrCodeImage, 50, 50);
            } catch (WriterException e) {
                e.printStackTrace();
            }



            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }


    @Override
    public byte[] generateTanitimBelgesi(TanitimBelgesiContent data) throws IOException {

        return null;
    }
}
