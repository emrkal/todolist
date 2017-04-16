/**
 * 
 */
package com.emrkal.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emrkal.model.User;
import com.emrkal.repository.UserRepository;

/**
 * UserRepository {@link com.emrkal.service.UserRepository} sınıfındaki
 * metodların testi yapılmaktadır.
 * <p>
 * 
 * @see UserRepositoryTest
 * @version 1.0
 * @author Emrullah KALKAN
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EntityScan(basePackages = { "com.emrkal.model" })
@EnableJpaRepositories(basePackages = { "com.emrkal.repository" })
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void addNewUserTest() {
		user = new User();
		user.setUsername("EMRKAL");
		user.setName("Emrullah");
		user.setLastName("KALKAN");
		user.setEmail("emrkal@emrkal.com");
		user.setPassword("123456");
		user = userRepository.save(user);
		assertThat(user.getId()).isNotNull();
		assertThat(user.getId() > 0);
	}

	@Ignore
	public void findByUsernameTest() {
		User findByUsernameTest = userRepository.findByUsername("EMRKAL");
		assertThat(findByUsernameTest).isNotNull();
		assertThat(findByUsernameTest.getId()).isEqualTo(user.getId());
	}

	@Test
	public void userLoginTest() {
		System.out.println("Logged");
	}

}
