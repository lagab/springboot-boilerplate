package com.lagab.boilerplate.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lagab.boilerplate.common.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}