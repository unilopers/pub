package com.grupo_5.pub.Workers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class FileProcessingWorker {

    private static final Logger log = LoggerFactory.getLogger(FileProcessingWorker.class);

    @Async("webhookTaskExecutor") 
public void processarArquivo(MultipartFile file) {

    try {
        if (file == null || file.isEmpty()) {
            return;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        );

        int linhas = 0;
        while (reader.readLine() != null) {
            linhas++;
        }

        System.out.println("Linhas do arquivo: " + linhas);

        Thread.sleep(2000); // TESTE

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}