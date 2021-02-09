package com.theater.app.security;

import com.theater.app.model.Viewer;
import com.theater.app.repositories.ViewerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyViewerDetails implements UserDetailsService {

    private ViewerRepository viewerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Viewer viewer = viewerRepository.findByUsername(username);
        if(viewer ==  null){
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(viewer);
    }


}
