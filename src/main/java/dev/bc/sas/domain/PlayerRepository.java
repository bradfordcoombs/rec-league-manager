package dev.bc.sas.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findByTeam_id(Long teamId);
}
