package com.disneyapiproject.registration;

import com.disneyapiproject.appuser.AppUser;
import com.disneyapiproject.appuser.AppUserRole;
import com.disneyapiproject.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    public String register(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail){
           throw new IllegalStateException("Email not valid");
       }
        return appUserService.signUpUser(new AppUser(
                request.getFirstname(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
    }
}
