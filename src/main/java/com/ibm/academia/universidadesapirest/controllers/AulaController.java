package com.ibm.academia.universidadesapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ibm.academia.universidadesapirest.enums.TipoPizarron;
import com.ibm.academia.universidadesapirest.exceptions.NotFoundException;
import com.ibm.academia.universidadesapirest.mapper.AulaMapper;
import com.ibm.academia.universidadesapirest.models.dto.AulaDTO;
import com.ibm.academia.universidadesapirest.models.entities.Aula;
import com.ibm.academia.universidadesapirest.models.entities.Pabellon;
import com.ibm.academia.universidadesapirest.services.AulaDAO;
import com.ibm.academia.universidadesapirest.services.PabellonDAO;

@RestController
@RequestMapping("/aulas")
public class AulaController implements GenericController<Aula, Integer> {
  
  private final AulaDAO service;

  private final PabellonDAO pabellonService;

  @Autowired
  public AulaController(AulaDAO service, PabellonDAO pabellonService) {
    this.service = service;
    this.pabellonService = pabellonService;
  }

  @GetMapping("/pizarron")
  public ResponseEntity<List<AulaDTO>> findAulasByPizarron(
    @RequestParam(name = "pizarron") TipoPizarron tipoPizarron
    ) {
    
    Optional<List<Aula>> result = service.findAulasByPizarron(tipoPizarron);

    if (result.isEmpty()) {
      throw new NotFoundException("No hay aulas con pizarrón: " + tipoPizarron);
    }

    List<AulaDTO> aulas = result.get()
    .stream().map(AulaMapper::mapAula)
    .collect(Collectors.toList());

    return ResponseEntity.ok(aulas);
  }

  @GetMapping("/pabellon")
  public ResponseEntity<List<AulaDTO>> findAulasByPabellon(
          @RequestParam("pabellon") String nombre
  ) {

    Optional<List<Aula>> result = service.findAulasByPabellonNombre(nombre);

    if (result.isEmpty()) {
      throw new NotFoundException("No hay aulas en el pabellon: " + nombre);
    }

    List<AulaDTO> aulas = result.get().stream()
            .map(AulaMapper::mapAula).collect(Collectors.toList());

    return ResponseEntity.ok(aulas);
  }

  @GetMapping("/aula/{numeroAula}")
  public ResponseEntity<AulaDTO> findAulasByNumeroAula(@PathVariable("numeroAula") Integer numeroAula) {
    Optional<Aula> result = service.findAulaByNumeroAula(numeroAula);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay aula con el número: " + numeroAula);
    }
    
    return ResponseEntity.ok(AulaMapper.mapAula(result.get()));
  }

  @PatchMapping("/aulaPabellon/{aulaId}/{pabellonId}")
  public ResponseEntity<AulaDTO> addPabellonToAula(@PathVariable Integer aulaId, @PathVariable Integer pabellonId) {
    Optional<Aula> aulaFound = service.buscarPorId(aulaId);

    if (aulaFound.isEmpty()) {
      throw new NotFoundException("No existe un aula con id: " + aulaId);
    }

    Optional<Pabellon> pabellonFound = pabellonService.buscarPorId(pabellonId);

    if (pabellonFound.isEmpty()) {
      throw new NotFoundException("No existe un pabellón con id: " + pabellonId);
    }

    Optional<Aula> result = service.addPabellonToAula(aulaFound.get(), pabellonFound.get());
    if (result.isEmpty()){
      throw new NotFoundException("No se puedo agregar: " + aulaFound.get());
    }
    return ResponseEntity.ok(AulaMapper.mapAula(result.get()));
  }

  @Override
  @PostMapping
  public ResponseEntity<Aula> create(@RequestBody Aula entity) {
    Aula aulaSaved = service.guardar(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(aulaSaved);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<Aula>> findAll() {
    List<Aula> result = (List<Aula>) service.buscarTodos();
    return ResponseEntity.ok(result);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Aula> findById(@PathVariable Integer id) {
    Optional<Aula> result = service.buscarPorId(id);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No se encontró un aula con el id: " + id);
    }
    
    return ResponseEntity.ok(result.get());
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Aula> update(@PathVariable Integer id, @RequestBody Aula entity) {

    Optional<Aula> result = service.buscarPorId(id);
    if (result.isEmpty()) {
      throw new NotFoundException("No se encontró un aula con el id: " + id);
    }
    Optional<Aula> updated = service.update(result.get(), entity);

    if (updated.isEmpty()) {
      throw new NotFoundException("No se pudo actualizar: " + result.get());
    }

    return ResponseEntity.ok(updated.get());
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Integer id) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Se eliminó el aula con id " + id);
    service.eliminarPorId(id);
    return ResponseEntity.ok(response);
  }

}
