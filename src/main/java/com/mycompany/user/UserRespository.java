package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRespository extends CrudRepository<User, Integer> {
	public Long countById(Integer id);
}
