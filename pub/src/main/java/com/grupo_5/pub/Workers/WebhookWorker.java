package com.grupo_5.pub.Workers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
 

@Service
public class WebhookWorker {
 
    private static final Logger log = LoggerFactory.getLogger(WebhookWorker.class);
 
    //Essa url é só para testes, bater e retornar o statusCode
    private static final String WEBHOOK_URL = "https://httpbin.org/post";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);
    private static final int MAX_RETRIES = 3;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
 
    public WebhookWorker() {

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
 
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }
 
    @Async("webhookTaskExecutor")
    public CompletableFuture<Void> notificarFechamentoComanda(WebhookPayload payload) {
 
        String threadName = Thread.currentThread().getName();
        log.info("[WebhookWorker] Iniciando envio assíncrono | thread={} | comanda={}",
                 threadName, payload.getIdComanda());
 
        boolean sucesso = false;
        Exception ultimoErro = null;
 
        for (int tentativa = 1; tentativa <= MAX_RETRIES; tentativa++) {
            try {
                log.info("[WebhookWorker] Tentativa {}/{} | comanda={}",
                         tentativa, MAX_RETRIES, payload.getIdComanda());
 
                String json = objectMapper.writeValueAsString(payload);
 
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(WEBHOOK_URL))
                        .timeout(REQUEST_TIMEOUT)
                        .header("Content-Type", "application/json")
                        .header("X-Pub-Event", "COMANDA_FECHADA")
                        .header("X-Pub-Timestamp", LocalDateTime.now().toString())
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();
 
                HttpResponse<String> response = httpClient.send(
                        request, HttpResponse.BodyHandlers.ofString());
 
                if (response.statusCode() >= 200 && response.statusCode() < 300) {
                    log.info("[WebhookWorker] Webhook enviado com sucesso | " +
                             "comanda={} | status_http={} | tentativa={}",
                             payload.getIdComanda(), response.statusCode(), tentativa);
                    sucesso = true;
                    break; 
                } else {
                    log.warn("[WebhookWorker] API externa retornou status inesperado | " +
                             "comanda={} | status_http={} | body={}",
                             payload.getIdComanda(), response.statusCode(),
                             response.body().substring(0, Math.min(200, response.body().length())));
                }
 
            } catch (Exception e) {
                ultimoErro = e;
                log.warn("[WebhookWorker] Falha na tentativa {}/{} | comanda={} | erro={}",
                         tentativa, MAX_RETRIES, payload.getIdComanda(), e.getMessage());
            }
 
            if (!sucesso && tentativa < MAX_RETRIES) {
                long waitMs = (long) Math.pow(2, tentativa - 1) * 1000L;
                log.info("[WebhookWorker] Aguardando {}ms antes da próxima tentativa...", waitMs);
                try {
                    Thread.sleep(waitMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    log.error("[WebhookWorker] Thread interrompida durante backoff | comanda={}",
                              payload.getIdComanda());
                    break;
                }
            }
        }
 
        if (!sucesso) {
            log.error("[WebhookWorker] FALHA DEFINITIVA — todas as {} tentativas esgotadas | " +
                      "comanda={} | ultimoErro={}",
                      MAX_RETRIES, payload.getIdComanda(),
                      ultimoErro != null ? ultimoErro.getMessage() : "status HTTP inesperado");
        }
 
        return CompletableFuture.completedFuture(null);
    }
}
 