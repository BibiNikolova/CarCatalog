package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.Model;
import com.example.carcatalog.model.enums.ModelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepo extends JpaRepository<Model, Long> {

    Optional<Model> findByModelName(ModelName modelName);
}
