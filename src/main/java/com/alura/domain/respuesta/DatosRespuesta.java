package com.alura.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        Long topico_id,
        LocalDateTime fechaCreacion,
        Long autor_id,
        Boolean solucion
) {
}
