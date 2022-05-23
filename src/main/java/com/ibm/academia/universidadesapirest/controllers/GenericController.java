package com.ibm.academia.universidadesapirest.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController <E, ID>{
    ResponseEntity<E> create(E entity);
    ResponseEntity<List<E>> findAll();
    ResponseEntity<E> findById(ID id);
    ResponseEntity<E> update(ID id, E entity);
    ResponseEntity<?> deleteById(ID id);
}
