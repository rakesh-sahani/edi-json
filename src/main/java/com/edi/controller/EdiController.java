package com.edi.controller;


import com.edi.service.EdiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class EdiController {

    @Value("${fileUrl}")
    String fileUrl;

    private final EdiService ediService;

    @GetMapping
    public ResponseEntity<?> ediToJson() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileUrl);
        return ResponseEntity.ok(ediService.getEdiX834(fileInputStream));
    }
}
