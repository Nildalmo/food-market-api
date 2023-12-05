package com.academinadodesenvolvedor.market.controllers;
import com.academinadodesenvolvedor.market.utils.UploadFile;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/storage")
@RestController

public class StorageControllers {

    private final HttpServletRequest request;

    public StorageControllers(HttpServletRequest request) {
        this.request = request;

    }

    @GetMapping("/**")
    public ResponseEntity<byte[]> getFiles() throws IOException {
        String[] uri = this.request.getRequestURI()
                .replace("storage/", "")
                .split("/");
        List<String> path = Arrays.stream(uri).toList();
        String fileName = path.get(path.size() - 1);
        List<String> pathStorage = path.subList(0, path.size() - 1);
        Path storage = Paths.get("storage",
                pathStorage.toArray(new String[0]));
        Path file = storage.resolve(fileName);
        if(Files.exists(file)){
            byte[] fileBlob = Files.readAllBytes(file);
            String mimeType = UploadFile.getMimeType(fileBlob);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("mimeType"))
                            .body(fileBlob);
        }
        return ResponseEntity.notFound().build();
    }
}
