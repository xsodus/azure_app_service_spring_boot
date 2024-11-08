package com.xsodus.az.app_service.backend.api;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface MyFirstApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found", content = @Content)})
    ResponseEntity<String> getMyFirstStory();
}
