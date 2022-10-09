package com.mycompany;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.mycompany.user.User;
import com.mycompany.user.UserRespository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRespositoryTest {
	@Autowired private UserRespository repo;
	
	@Test
	public void testAddNew() {
		 User user = new User();
		 user.setEmail("vantoan@gmail.com");
		 user.setPassword("toan12345");
		 user.setFirstName("Van");
		 user.setLastName("Toan");
		 
		 User savedUser = repo.save(user);
	
		 Assertions.assertThat(savedUser).isNotNull();
		 Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAll() {
		 Iterable<User> users = repo.findAll();
		 Assertions.assertThat(users).hasSizeGreaterThan(0);
		 for (User user : users) {
			 System.out.println(user);
		 }
	}
	
 	@Test
 	public void testUpdate() {
 		Integer userID = 1;
 		Optional<User> optionalUser = repo.findById(userID);
 		User user = optionalUser.get();
 		user.setPassword("abc12345");
 		repo.save(user);
 		
 		User updatedUser = repo.findById(userID).get();
 		Assertions.assertThat(updatedUser.getPassword()).isEqualTo("abc12345");			
 	}
 	
 	@Test
 	public void testGet() {
 		Integer userID = 2;
 		Optional<User> optionalUser = repo.findById(userID);
 		Assertions.assertThat(optionalUser).isPresent();
 		System.out.println(optionalUser.get());
 	}
 	
 	@Test
 	public void testDelete() {
 		Integer userID = 2;
 		repo.deleteById(userID);
 		Optional<User> optionalUser = repo.findById(userID);
 		Assertions.assertThat(optionalUser).isNotPresent();
 	}
}
