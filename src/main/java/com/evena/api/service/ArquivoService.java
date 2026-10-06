package com.evena.api.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.evena.api.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

@Service
public class ArquivoService {

    @Value("${AZURE_STORAGE_CONNECTION_STRING:}")
    private String connectionString;

    @Value("${AZURE_STORAGE_CONTAINER:imagens}")
    private String containerName;

    public String uploadImagem(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new RegraNegocioException("Selecione uma imagem.");
        }

        String contentType = arquivo.getContentType();
        if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith("image/")) {
            throw new RegraNegocioException("O arquivo enviado precisa ser uma imagem.");
        }

        if (connectionString == null || connectionString.isBlank()) {
            throw new RegraNegocioException("O armazenamento de imagens não está configurado.");
        }

        String extensao = extensao(arquivo.getOriginalFilename());
        String nome = UUID.randomUUID() + extensao;

        BlobContainerClient container = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(containerName);

        BlobClient blob = container.getBlobClient(nome);

        try {
            blob.upload(arquivo.getInputStream(), arquivo.getSize(), true);
            blob.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));
            return blob.getBlobUrl();
        } catch (IOException ex) {
            throw new RegraNegocioException("Não foi possível enviar a imagem.");
        }
    }

    private String extensao(String nomeOriginal) {
        if (nomeOriginal == null) {
            return "";
        }

        int indice = nomeOriginal.lastIndexOf('.');
        if (indice < 0 || indice == nomeOriginal.length() - 1) {
            return "";
        }

        String valor = nomeOriginal.substring(indice).toLowerCase(Locale.ROOT);
        return valor.matches("\\.[a-z0-9]{1,10}") ? valor : "";
    }
}
