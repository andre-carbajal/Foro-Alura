package com.alura.controller;

import com.alura.domain.topico.DatosRegistrarTopico;
import com.alura.domain.topico.DatosRespuestaTopico;
import com.alura.domain.topico.Topico;
import com.alura.repository.CursoRepository;
import com.alura.repository.TopicoRepository;
import com.alura.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistrarTopico datos){
        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();

        boolean existeTopico = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if (existeTopico) {
            throw new ValidationException("Topico ya existente");
        }

        Topico topico = topicoRepository.save(new Topico(datos.titulo(), datos.mensaje(), autor, curso));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());

        return ResponseEntity.ok().body(datosRespuestaTopico);
    }
}
