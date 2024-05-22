package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.bc.sas.exception.EntityNotFoundException;
import dev.bc.sas.web.CoachModel;

@Service
class CoachManager implements CoachService {

	private final CoachRepository coachRepository;

	CoachManager(CoachRepository coachRepository) {
		this.coachRepository = coachRepository;
	}

	@Override
	public List<Coach> getCoaches() {
		return coachRepository.findAll();
	}

	@Override
	public Optional<Coach> getCoach(Long id) {
		return coachRepository.findById(id);
	}

	@Override
	public Coach createCoach(CoachModel coachModel) {
		return coachRepository.save(Coach.init(coachModel));
	}

	@Override
	public Coach updateCoach(Long id, CoachModel coachModel) {
		var coach = coachRepository.findById(id).map(coachEntity -> coachEntity.update(coachModel))
				.orElseThrow(() -> new EntityNotFoundException(id.toString()));
		return coachRepository.save(coach);
	}

}
