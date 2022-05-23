package com.ibm.academia.universidadesapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.ibm.academia.universidadesapirest.exceptions.BadRequestException;
import com.ibm.academia.universidadesapirest.exceptions.NotFoundException;
import com.ibm.academia.universidadesapirest.mapper.CarreraMapper;
import com.ibm.academia.universidadesapirest.models.dto.CarreraDTO;
import com.ibm.academia.universidadesapirest.models.entities.Carrera;
import com.ibm.academia.universidadesapirest.services.CarreraDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrera")
public class CarreraController 
{
	private final CarreraDAO carreraDao;
	@Autowired
	public CarreraController(CarreraDAO carreraDao) {
		this.carreraDao = carreraDao;
	}

	@GetMapping("/todasLasCarreras")
	public List<Carrera> buscarTodas() {
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		if(carreras.isEmpty())
			throw new BadRequestException("No existen carreras");
		
		return carreras;
	}
	
	@GetMapping("/{carreraId}")
	public Carrera buscarCarreraPorId(@PathVariable Integer carreraId) {
		Carrera carrera = carreraDao.buscarPorId(carreraId).orElse(null);
		if(carrera == null)
			throw new BadRequestException(String.format("La carrera con ID: %d no existe", carreraId));
		
		return carrera;
	}
	
	@PostMapping
	public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result) {
		Map<String, Object> validaciones = new HashMap<>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Carrera carreraGuardada = carreraDao.guardar(carrera);
		
		return new ResponseEntity<>(carreraGuardada, HttpStatus.CREATED);
	}
	

	@PutMapping("/actualizar/{carreraId}")
	public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera) {
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		
		if(oCarrera.isEmpty())
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
		
		Carrera carreraActualizada = carreraDao.actualizar(oCarrera.get(), carrera); 
		
		return new ResponseEntity<>(carreraActualizada, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{carreraId}")
	public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId) {
		Map<String, Object> respuesta = new HashMap<>();
		
		Optional<Carrera> carrera = carreraDao.buscarPorId(carreraId);
		
		if(carrera.isEmpty())
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
		
		carreraDao.eliminarPorId(carreraId);
		respuesta.put("OK", "Carrera ID: " + carreraId + " eliminada exitosamente");
		return new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
	}

	@GetMapping("/carreras/dto")
	public ResponseEntity<?> obtenerCarrerasDTO() {
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		
		if(carreras.isEmpty())
			throw new NotFoundException("No existen carreras en la base de datos.");
		
		List<CarreraDTO> listaCarreras = carreras
				.stream()
				.map(CarreraMapper::mapCarrera)
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaCarreras, HttpStatus.OK);
	}

	@GetMapping("/profesor")
	public ResponseEntity<Iterable<CarreraDTO>> obtenerCarrerasPorProfesorNombreYApellido(
		@RequestParam(name = "nombre") String nombre,
		@RequestParam(name = "apellido") String apellido
	) {
		List<Carrera> carreras = (List<Carrera>) carreraDao.findCarrerarPorProfesorNombreYApellido(nombre, apellido);

		if (carreras.isEmpty()) {
			throw new NotFoundException(String.format("No hay carreras con docente %s %s", nombre, apellido));
		}

		List<CarreraDTO> listaCarreras = carreras
		.stream()
		.map(CarreraMapper::mapCarrera)
		.collect(Collectors.toList());

		return ResponseEntity.ok(listaCarreras);
	}
}