package com.example.PDFTool.factory.ımpl;
import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.model.PDFContent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
@Component
public class TanitimBelgesi implements PDFGenerator {

    @Override
    public byte[] generatePDF(PDFContent data) throws IOException {
        //Şablonu yükleme
        PDDocument document= PDDocument.load(new File("C:/Users/Umut/Desktop/Staj/PDFTool/src/main/resources/templates/prizma.pdf"));


       PDPageContentStream contentStream=new PDPageContentStream(document,document.getPage(0),true,true);

       //Şablon üzerine veri yazma işlemleri
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(300,600);
        contentStream.showText("Ad:"+data.getName() );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(300,500);
        contentStream.showText("Soyad:"+data.getSurname() );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(300,400);
        contentStream.showText("Telefon:"+data.getPhone() );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(300,300);
        contentStream.showText("Adres:"+data.getAddress() );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(300,200);
        contentStream.showText("Kimlik No:"+data.getTcno() );
        contentStream.endText();

        contentStream.close();

        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        return baos.toByteArray();
    }


}
