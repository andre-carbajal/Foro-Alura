package com.alura.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarRespuesta(
        @NotBlank String mensaje,
        @NotNull Long idTopico,
        @NotNull Long idAutor,
        @NotNull Boolean solucion
        ) {
}