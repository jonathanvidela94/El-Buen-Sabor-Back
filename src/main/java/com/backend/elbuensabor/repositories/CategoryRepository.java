package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends GenericRepository<Category,Long>{
    @Query(value = "SELECT * FROM category WHERE category.is_banned = false", nativeQuery = true)
    List<Category> findAllByIsBanned();

    @Query(value = "SELECT * FROM category WHERE category.id = :id AND category.is_banned = false", nativeQuery = true)
    Optional<Category> findByIdAndIsBanned(@Param("id") Long id);
}
