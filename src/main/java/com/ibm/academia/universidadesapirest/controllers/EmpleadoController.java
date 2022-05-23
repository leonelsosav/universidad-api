package com.ibm.academia.universidadesapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ibm.academia.universidadesapirest.enums.TipoEmpleado;
import com.ibm.academia.universidadesapirest.exceptions.NotFoundException;
import com.ibm.academia.universidadesapirest.models.entities.Persona;
import com.ibm.academia.universidadesapirest.services.EmpleadoDAO;


@RestController
@RequestMapping("/empleado")
public class EmpleadoController implements GenericController<Persona, Integer> {
  
  private final EmpleadoDAO service;

  @Autowired
  public EmpleadoController(EmpleadoDAO service) {
    this.service = service;
  }

  @GetMapping("/porTipo/{tipoEmpleado}")
  public ResponseEntity<Iterable<Persona>> findEmpleadosByTipoEmpleado(
    @RequestParam("tipoEmpleado") TipoEmpleado tipoEmpleado) {
    
    Optional<Iterable<Persona>> result = service.findEmpleadoByTipoEmpleado(tipoEmpleado);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay empleados de tipo " + tipoEmpleado);
    }

    return ResponseEntity.ok(result.get());
  }

  @Override
  @PostMapping
  public ResponseEntity<Persona> create(@RequestBody Persona entity) {
    Persona result = service.guardar(entity);

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<Persona>> findAll() {
    List<Persona> people = (List<Persona>) service.buscarTodos();

    if (people.isEmpty()) {
      throw new NotFoundException("No hay empleados");
    }

    return ResponseEntity.ok(people);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Persona> findById(@PathVariable Integer id) {
    Optional<Persona> result = service.buscarPorId(id);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No se encontró un empleado con el id: " + id);
    }
    
    return ResponseEntity.ok(result.get());
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Persona> update(@PathVariable Integer id, @RequestBody Persona entity) {
    Optional<Persona> result = service.buscarPorId(id);

    if (result.isEmpty()) {
      throw new NotFoundException("No se encontró un empleado con el id: " + id);
    }

    Optional<Persona> resultUpdate = service.updateEmployee(result.get(), entity);

    if (resultUpdate.isEmpty()) {
      throw new NotFoundException("No se pudo actualizar: " + result.get());
    }

    return ResponseEntity.ok(resultUpdate.get());
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Integer id) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Empleado dado de baja");
    service.eliminarPorId(id);
    return ResponseEntity.ok(response);
  }

}
