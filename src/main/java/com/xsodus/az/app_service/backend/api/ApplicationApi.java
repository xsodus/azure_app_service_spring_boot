package com.xsodus.az.app_service.backend.api;

import com.xsodus.az.app_service.backend.dto.UploadFileResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationApi {

    @Operation(summary = "Get my first story")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found", content = @Content)})
    ResponseEntity<String> getMyFirstStory();

    @Operation(summary = "Get items from linked list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found", content = @Content)})
    ResponseEntity<List<String>> getItems();

    @Operation(summary = "Upload file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = @Content)})
    ResponseEntity<UploadFileResponseDto> uploadFile(
            @RequestParam("file") MultipartFile file
    );
}
