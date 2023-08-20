package com.edi.service;

import com.edi.model.x12.EDI834;

import java.io.FileInputStream;
import java.io.IOException;

public interface EdiService {
    EDI834 getEdiX834(FileInputStream fileInputStream) throws IOException;
}