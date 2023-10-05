package com.alura.controller;

import com.alura.domain.topico.*;
import com.alura.repository.TopicoRepository;
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
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RegistrarTopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistrarTopico datos){
        var respuesta = service.registrarTopico(datos);
        return ResponseEntity.ok().body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listadoTopico(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListarTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                topico.getAutor().getId(), topico.getCurso().getId());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos, @PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datos);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                topico.getAutor().getId(), topico.getCurso().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
