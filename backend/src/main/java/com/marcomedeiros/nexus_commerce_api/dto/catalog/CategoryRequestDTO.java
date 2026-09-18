package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank(message = "A categoria Ã© obrigatÃ³ria") String name){}
