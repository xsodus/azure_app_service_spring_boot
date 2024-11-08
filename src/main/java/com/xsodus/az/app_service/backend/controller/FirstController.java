package com.xsodus.az.app_service.backend.controller;

import com.xsodus.az.app_service.backend.api.MyFirstApi;
import com.xsodus.az.app_service.backend.service.MyStoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController implements MyFirstApi {
    private final MyStoryService myStoryService;

    public FirstController(MyStoryService myStoryService) {
        this.myStoryService = myStoryService;
    }

    @GetMapping(
            value = "/my-first-story",
            produces = "text/plain"
    )
    public ResponseEntity<String> getMyFirstStory() {
        return ResponseEntity.ok(myStoryService.getMyFirstStory());
    }
}
