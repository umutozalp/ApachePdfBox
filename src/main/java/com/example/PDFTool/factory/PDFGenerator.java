package com.example.PDFTool.factory;

import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;

import java.io.IOException;


public interface PDFGenerator {

 byte[] generateTanitimBelgesi(TanitimBelgesiContent data) throws IOException;
 byte[] generateOnurBelgesi(OnurBelgesiContent data) throws IOException;

}
