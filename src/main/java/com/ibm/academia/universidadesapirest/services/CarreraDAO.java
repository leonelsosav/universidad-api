package com.ibm.academia.universidadesapirest.services;

import com.ibm.academia.universidadesapirest.models.entities.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera>
{
	Iterable<Carrera> findCarrerasByNombreContains(String nombre);
	Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
	Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
	Carrera actualizar(Carrera carreraEncontrada, Carrera carrera);
	Iterable<Carrera> findCarrerarPorProfesorNombreYApellido(String nombre, String apellido);
}