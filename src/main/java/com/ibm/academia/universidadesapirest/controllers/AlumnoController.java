package com.ibm.academia.universidadesapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.universidadesapirest.exceptions.NotFoundException;
import com.ibm.academia.universidadesapirest.models.entities.Carrera;
import com.ibm.academia.universidadesapirest.models.entities.Persona;
import com.ibm.academia.universidadesapirest.services.AlumnoDAO;
import com.ibm.academia.universidadesapirest.services.CarreraDAO;
import com.ibm.academia.universidadesapirest.services.PersonaDAO;

@RestController
@RequestMapping("/alumno")
public class AlumnoController 
{
	private final PersonaDAO alumnoDao;
	
	private final CarreraDAO carreraDao;

	@Autowired
	public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDao, CarreraDAO carreraDao) {
		this.alumnoDao = alumnoDao;
		this.carreraDao = carreraDao;
	}

	@PostMapping
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno) {
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<>(alumnoGuardado, HttpStatus.CREATED);
	}
	

	@GetMapping("/alumnos/obtenerTodos")
	public ResponseEntity<?> obtenerTodos() {
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		
		if(alumnos.isEmpty())
			throw new NotFoundException("No se encontraron alumnos");
		return new ResponseEntity<>(alumnos, HttpStatus.OK);
	}
	

	@GetMapping("/idAlumno/{alumnoId}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer alumnoId) {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
        
        if(oAlumno.isEmpty())
            throw new NotFoundException(String.format("No se encontro el alumno con el id %d", alumnoId));
        
        return new ResponseEntity<>(oAlumno.get(), HttpStatus.OK);
    }
	

	@PutMapping("/actualizar/idAlumno/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		
		if(oAlumno.isEmpty())
			throw new NotFoundException(String.format("No se encontro el alumno con el id %d", alumnoId));
		
		Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(oAlumno.get(), alumno);
		return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
	}
	

	@DeleteMapping("/idAlumno/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		
		if(oAlumno.isEmpty())
			throw new NotFoundException(String.format("No se encontro el alumno con el id %d", alumnoId));
		
		alumnoDao.eliminarPorId(oAlumno.get().getId()); 
		
		return new ResponseEntity<>("El alumno con el id " + alumnoId + " se elimino", HttpStatus.NO_CONTENT);
	}
	

	@PutMapping("/idAlumno/{alumnoId}/idCarrera/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
        if(oAlumno.isEmpty())
            throw new NotFoundException(String.format("No se encontro el alumno con el id %d", alumnoId));
        
        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
        if(oCarrera.isEmpty())
            throw new NotFoundException(String.format("No se encontro la carrera con el id %d", carreraId));
        
        Persona alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
        
        return new ResponseEntity<>(alumno, HttpStatus.OK);
	}
}
