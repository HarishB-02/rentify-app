package com.managementsystem.poc.presidio_test.repository;

import com.managementsystem.poc.presidio_test.model.LikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<LikeCount,Integer> {
    @Query(value = "select l from LikeCount l where l.propertyId=:propertyId and l.userId=:userId")
    public LikeCount findLikedUser(Integer propertyId,Integer userId);
    @Query(value = "select count(l) from LikeCount l where l.propertyId=:propertyId")
    public Integer findCount(Integer propertyId);
}
