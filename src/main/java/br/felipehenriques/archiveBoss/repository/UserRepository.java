package br.felipehenriques.archiveBoss.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.felipehenriques.archiveBoss.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByName(String name);

	List<User> findByEmail(String email);

	User findByUsername(String username);
}
