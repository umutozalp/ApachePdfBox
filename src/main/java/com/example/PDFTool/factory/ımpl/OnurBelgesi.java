package com.example.PDFTool.factory.ımpl;

import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class OnurBelgesi implements PDFGenerator {


    @Override
    public byte[] generateOnurBelgesi(OnurBelgesiContent data) throws IOException {

        try {
        PDDocument document=PDDocument.load(new File("C:/Users/Umut/Desktop/Staj/PDFTool/src/main/resources/templates/onurbelgesi.pdf"));

        //Türkce destekli font yükleme
        InputStream fontStream= new ClassPathResource("templates/fonts/openSans-Bold.ttf").getInputStream();
        PDType0Font font = PDType0Font.load(document,fontStream);

        PDPageContentStream contentStream= new PDPageContentStream(document,document.getPage(0),true,true);

        contentStream.beginText();
        contentStream.setFont(font,16);
        contentStream.newLineAtOffset(335,500);
        contentStream.showText("ONUR BELGESİ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,14);
        contentStream.newLineAtOffset(660,550);
        contentStream.showText(data.getTarih());
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,14);
        contentStream.newLineAtOffset(117, 400);
        contentStream.showText("Sayın "+data.getAdSoyad()+" meslekte 5. yılınız sebebiyle Onur Belgesi almaya hak kazandınız.");
        contentStream.endText();
        contentStream.close();


        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        document.save(baos);
        document.close();
        return baos.toByteArray();}
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }

    }


    @Override
    public byte[] generateTanitimBelgesi(TanitimBelgesiContent data) throws IOException {

        return null;
    }
}
