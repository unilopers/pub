package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Workers.FileProcessingWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileProcessingWorker worker;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

        worker.processarArquivo(file); 
        return ResponseEntity
                .accepted()
                .body("Arquivo recebido e em processamento");
    }

}