package com.alura.domain.topico;

public record DatosListarTopico(
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        Long idAutor,
        Long idCurso
) {
    public DatosListarTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getStatus().toString(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
