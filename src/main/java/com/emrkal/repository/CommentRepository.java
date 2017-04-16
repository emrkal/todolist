package com.emrkal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emrkal.model.Comment;
import com.emrkal.model.User;

/**
 * Activity {@link com.emrkal.model.Comment} modeline ait "Persistence"
 * işlemlerinin yapıldığı Repository sınıftır.
 * <p>
 * 
 * @see CommentRepository
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	/**
	 * User parametresine göre {@link com.emrkal.model.User} tüm yorumlar
	 * döndürülür.
	 * <p>
	 * 
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.model.Comment}
	 * @author Emrullah KALKAN
	 */
	List<Comment> findAllByUserOrderByCommentDateDesc(User user);
}
