package it.romaeur.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.romaeur.siw.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
}