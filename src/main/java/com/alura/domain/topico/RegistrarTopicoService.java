package com.alura.domain.topico;

import com.alura.repository.CursoRepository;
import com.alura.repository.TopicoRepository;
import com.alura.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarTopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public DatosRespuestaTopico registrarTopico(DatosRegistrarTopico datos) {

        if (!usuarioRepository.findById(datos.idAutor()).isPresent()) {
            throw new ValidationException("Este id del usuario no fue encontrado");
        }
        if (datos.idCurso()!=null && !cursoRepository.existsById(datos.idCurso())) {
            throw new ValidationException("Este id del curso no fue encontrado");
        }

        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();

        boolean existeTopico = topicoRepository.existsByTitulo(datos.titulo());
        if (existeTopico) {
            throw new ValidationException("Ya existe un topico con ese titulo");
        }

        Topico topico = topicoRepository.save(new Topico(datos.titulo(), datos.mensaje(), autor, curso));

        return new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());
    }

}
