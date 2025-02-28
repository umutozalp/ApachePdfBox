package com.example.PDFTool.factory.impl;

import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class TanitimBelgesiGenerator implements PDFGenerator {

    @Override
    public byte[] generateTanitimBelgesi(TanitimBelgesiContent data) throws IOException {
        //Şablonu yükleme
        try {


            ClassPathResource pdfTemplate = new ClassPathResource("templates/tanitimbelgesi.pdf");
            InputStream inputStream = pdfTemplate.getInputStream();
            PDDocument document = PDDocument.load(inputStream);
            PDPage page = document.getPage(0);

            InputStream fontStream = new ClassPathResource("templates/fonts/OpenSans-Bold.ttf").getInputStream();
            InputStream fontStream1 = new ClassPathResource("templates/fonts/open_sans.ttf").getInputStream();
            PDType0Font font = PDType0Font.load(document, fontStream);
            PDType0Font font1 = PDType0Font.load(document, fontStream1);

            PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0), true, true);

            //Şablon üzerine veri yazma işlemleri
            contentStream.beginText();
            contentStream.setFont(font, 16);
            contentStream.newLineAtOffset(460, 560);
            contentStream.showText("TANITIM BELGESİ");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font1, 12);
            contentStream.newLineAtOffset(400, 450);
            contentStream.showText("İsim : " + data.getAdSoyad());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font1, 12);
            contentStream.newLineAtOffset(400, 400);
            contentStream.showText("Kimlik No : " + data.getTcno());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font1, 12);
            contentStream.newLineAtOffset(400, 350);
            contentStream.showText("Telefon : " + data.getTelefon());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font1, 12);
            contentStream.newLineAtOffset(400, 300);
            contentStream.showText("Adres : " + data.getAdres());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font1, 12);
            contentStream.newLineAtOffset(400, 250);
            contentStream.showText("Mezun Olunan Okul : " + data.getMezunOkul());
            contentStream.endText();


            contentStream.close();

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
    public byte[] generateOnurBelgesi(OnurBelgesiContent data) throws IOException {
        return null;
    }


}
