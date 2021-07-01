package com.example.TestServer16.repositories;

import com.example.TestServer16.models.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TagRepo extends CrudRepository<Tag, Integer> {

    Iterable<Tag> findByUserToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tag SET is_active = :isActive WHERE  id = :id",
            nativeQuery = true)
    int setIsActive(@Param("id") Integer id, @Param("isActive") boolean isActive);

}
