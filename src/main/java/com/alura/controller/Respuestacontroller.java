package com.alura.controller;

import com.alura.domain.respuesta.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuesta> retornarDatosRespuesta(@PathVariable Long id){
        var respuesta = respuestaRepository.findById(id).get();
        return ResponseEntity.ok(new DatosRespuesta(respuesta.getId(), respuesta.getMensaje(),
                respuesta.getTopico().getId(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(), respuesta.getSolucion()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datos, @PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarRespuesta(datos);
        return ResponseEntity.ok(new DatosRespuesta(respuesta.getId(), respuesta.getMensaje(),
                respuesta.getTopico().getId(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(), respuesta.getSolucion()));
    }

}
