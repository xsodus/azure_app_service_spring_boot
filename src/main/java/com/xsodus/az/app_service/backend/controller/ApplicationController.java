package com.xsodus.az.app_service.backend.controller;

import com.xsodus.az.app_service.backend.api.ApplicationApi;
import com.xsodus.az.app_service.backend.dto.UploadFileResponseDto;
import com.xsodus.az.app_service.backend.service.ApplicationService;

import com.xsodus.az.app_service.backend.util.LinkList;
import java.util.ArrayList;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ApplicationController implements ApplicationApi {
    private final ApplicationService applicationService;

    private final LinkList linkList;

    public ApplicationController(ApplicationService applicationService, LinkList linkList) {
        this.applicationService = applicationService;
        this.linkList = linkList;
    }

    @GetMapping(
            value = "/my-first-story"
    )
    public ResponseEntity<String> getMyFirstStory() {
        return ResponseEntity.ok(applicationService.getMyFirstStory());
    }

    @GetMapping(
            value = "/linked-list-items",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getItems() {
        return ResponseEntity.ok(linkList.toArrayList());
    }

    @PostMapping(
            value = "/file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UploadFileResponseDto> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        var response = new UploadFileResponseDto();
        if (file.isEmpty()) {
            response.setStatusCode(400);
            response.setMessage("File is empty");
            return ResponseEntity.badRequest().body(response);
        }

        // Process the file (e.g., save it to the server)
        // For now, just return a success message
        response.setStatusCode(200);
        response.setMessage("File uploaded successfully: " + file.getOriginalFilename());

        return ResponseEntity.ok(response);
    }
}
