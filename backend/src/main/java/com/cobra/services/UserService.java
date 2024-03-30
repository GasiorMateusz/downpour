package com.cobra.services;

import com.cobra.models.BasicMeasurements;
import com.cobra.models.User;
import com.cobra.models.UserDetailsImpl;
import com.cobra.repository.BasicMeasurementsDAO;
import com.cobra.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    BasicMeasurementsDAO basicMeasurementsDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public User saveUsersMeasurements(List<BasicMeasurements> measurements){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Optional<User> u = getUserById(userDetails.getId());
        List<BasicMeasurements> savedMeasurements = basicMeasurementsDAO.save(measurements.stream().toList());
        if (u.isPresent()){
            User user = u.get();
            user.setBasicMeasurements(savedMeasurements);
            userDao.save(user);
            return user;
        }
        else return null;
    }

    public Optional<User> getUserById(String id){
        return userDao.getById(id);
    }
}