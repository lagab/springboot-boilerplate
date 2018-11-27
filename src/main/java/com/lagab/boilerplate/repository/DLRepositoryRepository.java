package com.lagab.boilerplate.repository;

import com.lagab.boilerplate.jpa.domain.dl.DLRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gabriel
 * @since 27/11/2018.
 */
public interface DLRepositoryRepository extends JpaRepository<DLRepository, Long> {
}
