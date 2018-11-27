package com.lagab.boilerplate.repository;

import com.lagab.boilerplate.jpa.domain.dl.DLFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gabriel
 * @since 27/11/2018.
 */
public interface DLFileRepository extends JpaRepository<DLFile, Long> {

}
