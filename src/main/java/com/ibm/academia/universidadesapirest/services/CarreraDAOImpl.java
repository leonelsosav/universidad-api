package com.ibm.academia.universidadesapirest.services;

import com.ibm.academia.universidadesapirest.models.entities.Carrera;
import com.ibm.academia.universidadesapirest.repositories.CarreraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera, CarreraRepository> implements CarreraDAO 
{
	@Autowired
	public CarreraDAOImpl(CarreraRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre) {
		return repository.findCarrerasByNombreContains(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) {
		
		return repository.findCarrerasByNombreContainsIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios) {
		return repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
	}

	@Override
	@Transactional
	public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera) {
		Carrera carreraActualizada;
		carreraEncontrada.setCantidadAnios(carrera.getCantidadAnios());
		carreraEncontrada.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraActualizada = repository.save(carreraEncontrada);
		return carreraActualizada;
	}

	@Override
	public Iterable<Carrera> findCarrerarPorProfesorNombreYApellido(String nombre, String apellido) {

		return repository.findCarrerasPorProfesorNombreYApellido(nombre, apellido);
	}
}