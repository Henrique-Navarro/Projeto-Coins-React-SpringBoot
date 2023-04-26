package com.example.backend.repositories;

import com.example.backend.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    @Query("SELECT s FROM services s WHERE s.id_projeto = :projectId")
    List<Services> findAllByProjectId(Long projectId);
}
