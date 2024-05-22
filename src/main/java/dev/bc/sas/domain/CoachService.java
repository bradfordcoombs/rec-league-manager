package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import dev.bc.sas.web.CoachModel;

public interface CoachService {

	List<Coach> getCoaches();

	Optional<Coach> getCoach(Long id);

	Coach createCoach(CoachModel coachModel);

	Coach updateCoach(Long id, CoachModel coachModel);
}
