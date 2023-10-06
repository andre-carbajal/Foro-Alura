package com.alura.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListarRespuesta(
        Long id,
        String mensaje,
        Long topico_id,
        LocalDateTime fechaCreacion,
        Long autor_id,
        Boolean solucion,
        Boolean activo
) {
    public DatosListarRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(),
                respuesta.getSolucion(),
                respuesta.getActivo());
    }
}
