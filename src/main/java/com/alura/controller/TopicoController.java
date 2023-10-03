package com.alura.controller;

import com.alura.domain.topico.DatosRegistrarTopico;
import com.alura.domain.topico.Topico;
import com.alura.repository.CursoRepository;
import com.alura.repository.TopicoRepository;
import com.alura.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistrarTopico datos){
        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();

        boolean existeTopico = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if (existeTopico) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        var response = new Topico(datos.titulo(), datos.mensaje(), autor, curso);

        topicoRepository.save(response);

        return ResponseEntity.ok(response);
    }
}
