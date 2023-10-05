package com.alura.domain.topico;

public record DatosListarTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        Long idAutor,
        Long idCurso
) {
    public DatosListarTopico(Topico topico){
        this( topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getStatus().toString(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
