package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericRepository<Category,Long>{
}
