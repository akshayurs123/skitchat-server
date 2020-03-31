package org.akshay.skitchat.repository;

import java.util.List;

import org.akshay.skitchat.entity.SoulEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SoulRepository extends JpaRepository<SoulEntity, Integer> {
	
	
	@Query(value = "SELECT s FROM SoulEntity s WHERE s.username = :username AND s.password = :password")
	SoulEntity authenticateSoul(@Param (value="username") String username, @Param (value="password") String password);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM SoulEntity u WHERE u.username = :username")
	boolean userNamePresent(@Param (value="username") String username);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM SoulEntity u WHERE u.email = :email")
	boolean emailPresent(@Param (value="email")String email);
	
	
	
	
	/*
	 * @Query("SELECT * s); String userAuth(@Param (value="emailId") String emailId
	 * , @Param(value="password") String password) ;
	 */
}