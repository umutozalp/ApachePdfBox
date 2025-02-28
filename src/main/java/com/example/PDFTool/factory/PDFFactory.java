package com.example.PDFTool.factory;

import com.example.PDFTool.factory.impl.OnurBelgesiGenerator;
import com.example.PDFTool.factory.impl.TanitimBelgesiGenerator;
import org.springframework.stereotype.Component;

@Component
public class PDFFactory {

 public PDFGenerator createPDFGenerator(PDFType type) {

       switch (type){
           case ONUR_BELGESI: return new OnurBelgesiGenerator();
           case TANITIM_BELGESI: return new TanitimBelgesiGenerator();
           default: throw new IllegalArgumentException("Bilinmeyen belge tipi:"+ type);
       }

 }

}
