package com.managementsystem.poc.presidio_test.repository;

import com.managementsystem.poc.presidio_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByFirstName(String username);
    @Query("select u from User u where u.email=:email")
    public User findByEmailId(String email);
    @Query("select u from User u where u.id=:sellerId")
    public User findBySellerId(Integer sellerId);
}
