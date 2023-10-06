package com.alura.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull String mensaje,
        @NotNull boolean solucion
) {
}
