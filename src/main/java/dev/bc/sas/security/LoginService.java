package dev.bc.sas.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class LoginService implements UserDetailsService {

	private final LoginRepository loginRepository;

	LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return loginRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
