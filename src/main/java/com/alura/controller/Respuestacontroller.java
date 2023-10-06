package com.alura.controller;

import com.alura.domain.respuesta.DatosListarRespuesta;
import com.alura.domain.respuesta.DatosRegistrarRespuesta;
import com.alura.domain.respuesta.DatosRespuesta;
import com.alura.domain.respuesta.Respuesta;
import com.alura.repository.RespuestaRepository;
import com.alura.repository.TopicoRepository;
import com.alura.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/respuestas")
public class Respuestacontroller {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistrarRespuesta datos){
        var topico = topicoRepository.findById(datos.idTopico()).get();
        var autor = usuarioRepository.findById(datos.idAutor()).get();

        respuestaRepository.save(new Respuesta(datos.mensaje(), topico, autor, datos.solucion()));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarRespuesta>> listarRespuesta(@PageableDefault(size = 5)Pageable pageable){
        return ResponseEntity.ok(respuestaRepository.findByActivoTrue(pageable).map(DatosListarRespuesta::new));
    }
}
