package com.ramonmr95.tiky.olc.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.repositories.IUserDao;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private IUserDao userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			 throw new UsernameNotFoundException("User Not Found with email: " + email);
		}
		return UserDetailsImpl.build(user);
	}

}