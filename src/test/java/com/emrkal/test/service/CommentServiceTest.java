/**
 * 
 */
package com.emrkal.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emrkal.model.Comment;
import com.emrkal.model.User;
import com.emrkal.repository.CommentRepository;
import com.emrkal.repository.UserRepository;

/**
 * CommentService {@link com.emrkal.service.CommentService} sınıfındaki
 * metodların testi yapılmaktadır.
 * <p>
 * 
 * @see CommentServiceTest
 * @version 1.0
 * @author Emrullah KALKAN
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EntityScan(basePackages = { "com.emrkal.model" })
@EnableJpaRepositories(basePackages = { "com.emrkal.repository" })
public class CommentServiceTest {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	private User user;

	private Comment comment;

	@Before
	public void createCommentData() {
		user = userRepository.findByUsername("EMRKAL");
		assertThat(user.getId()).isNotNull();
		comment = new Comment();
		comment.setActive(true);
		comment.setCommentDate(new Date());
		comment.setDescription("Test Data");
		comment.setUser(user);

		comment = commentRepository.save(comment);
		assertThat(comment.getId()).isNotNull();
		assertThat(comment.getId() > 0);
	}

	@Test
	public void findByCommentTest() {
		Comment findByCommentTest = commentRepository.findOne(comment.getId());
		assertThat(findByCommentTest).isNotNull();
		assertThat(findByCommentTest.getId()).isEqualTo(comment.getId());
	}

	@Test
	public void findAllByUserOrderByCommentDateDesc() {
		List<Comment> findByCommentTest = commentRepository.findAllByUserOrderByCommentDateDesc(user);
		assertThat(findByCommentTest).isNotNull();
	}

	@After
	public void removeCommentTest() {
		commentRepository.delete(comment.getId());
		Comment removeCommentTest = commentRepository.findOne(comment.getId());
		assertThat(removeCommentTest).isNull();

	}

}
