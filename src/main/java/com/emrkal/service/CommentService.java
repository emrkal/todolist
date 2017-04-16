package com.emrkal.service;

import java.util.List;

import com.emrkal.model.Comment;

/**
 * Comment {@link com.emrkal.model.Comment} modeline ait interface
 * sınıftır.
 * <p>
 * 
 * @see CommentService
 * @version 1.0
 * @author Emrullah KALKAN
 */
public interface CommentService {

	/**
	 * Comment {@link com.emrkal.model.Comment} modelinin kaydedildiği
	 * metoddur.
	 * <p>
	 * 
	 * @param comment
	 * @version 1.0
	 * @return {@link com.emrkal.model.Comment}
	 * @author Emrullah KALKAN
	 */
	Comment saveComment(Comment comment);

	/**
	 * Tüm kayıtların {@link com.emrkal.model.Comment} döndürüldüğü
	 * metoddur.
	 * <p>
	 * 
	 * @version 1.0
	 * @return {@link com.emrkal.model.Comment}
	 * @author Emrullah KALKAN
	 */
	List<Comment> getAllComment();

}
