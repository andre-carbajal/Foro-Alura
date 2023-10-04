package com.alura.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje
) {
}
