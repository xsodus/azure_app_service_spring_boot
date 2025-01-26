package com.xsodus.az.app_service.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "Upload file response mode"
)
@Data
public class UploadFileResponseDto {
    private Integer statusCode;
    private String message;
}
