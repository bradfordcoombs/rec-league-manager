package dev.bc.sas.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface LoginRepository extends JpaRepository<Login, Long> {
	Optional<Login> findByUsername(String username);
}
