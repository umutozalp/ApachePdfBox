package com.example.PDFTool.service;

import com.example.PDFTool.factory.ımpl.OnurBelgesi;
import com.example.PDFTool.factory.ımpl.TanitimBelgesi;
import com.example.PDFTool.factory.PDFGenerator;
import com.example.PDFTool.model.OnurBelgesiContent;
import com.example.PDFTool.model.TanitimBelgesiContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PDFService {


    private final PDFGenerator tanitimbelgesiGenerator;
    private final PDFGenerator onurbelgesiGenerator;

    @Autowired
    public PDFService(TanitimBelgesi tanitimbelgesiGenerator, OnurBelgesi onurbelgesiGenerator) {
        this.tanitimbelgesiGenerator = tanitimbelgesiGenerator;
        this.onurbelgesiGenerator = onurbelgesiGenerator;
    }

    public byte[] generateTanitimBelgesi(TanitimBelgesiContent request) throws IOException {
        return tanitimbelgesiGenerator.generateTanitimBelgesi(request);
    }

    public byte[] generateOnurBelgesi(OnurBelgesiContent request) throws IOException {
        return onurbelgesiGenerator.generateOnurBelgesi(request);
    }

}
