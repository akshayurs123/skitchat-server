package hello.DbInterface;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hello.Entity.SoulEntity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SoulRepository extends CrudRepository<SoulEntity, Integer> {
	
	
	@Query(value = "SELECT s FROM SoulEntity s WHERE s.username = :username AND s.password = :password")
	SoulEntity authenticateSoul(@Param (value="username") String username, @Param (value="password") String password);
	
	/*
	 * @Query("SELECT * s); String userAuth(@Param (value="emailId") String emailId
	 * , @Param(value="password") String password) ;
	 */
}