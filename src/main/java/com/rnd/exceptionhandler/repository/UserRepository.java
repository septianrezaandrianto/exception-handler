package com.rnd.exceptionhandler.repository;

import com.rnd.exceptionhandler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value="SELECT * FROM users a WHERE a.name = :name", nativeQuery = true)
    User getUserByName(@Param("name")String name);

}
