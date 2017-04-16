package com.emrkal.service;

import com.emrkal.model.User;

/**
 * User {@link com.emrkal.model.User} modeline ait interface sınıfıdır.
 * <p>
 * 
 * @see UserService
 * @version 1.0
 * @author Emrullah KALKAN
 */
public interface UserService {

	/**
	 * Id bilgisine göre user {@link com.emrkal.model.User} bilgisinin
	 * döndürülüğü metoddur.
	 * <p>
	 * 
	 * @param id
	 * @version 1.0
	 * @return {@link com.emrkal.model.User}
	 * @author Emrullah KALKAN
	 */
	User getUserById(long id);

	/**
	 * Kullanıcı adına göre user {@link com.emrkal.model.User} bilgisinin
	 * döndürülüğü metoddur.
	 * <p>
	 * 
	 * @param username
	 * @version 1.0
	 * @return {@link com.emrkal.model.User}
	 * @author Emrullah KALKAN
	 */
	User getUserByUsername(String username);

	/**
	 * Yeni bir kullanıcının {@link com.emrkal.model.User} kaydedildiği
	 * metoddur.
	 * <p>
	 * 
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.model.Comment}
	 * @author Emrullah KALKAN
	 */
	User addNewUser(User user);

}
