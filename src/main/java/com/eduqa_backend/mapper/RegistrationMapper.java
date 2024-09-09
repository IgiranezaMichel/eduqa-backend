package com.eduqa_backend.mapper;
import java.util.function.Function;
import com.eduqa_backend.dto.RegistrationDTO;
import com.eduqa_backend.modal.Registration;
public class RegistrationMapper implements Function<Registration, RegistrationDTO> {

    @Override
    public RegistrationDTO apply(Registration t) {
       return new RegistrationDTO(t.getUser(), t.getSemester());
    }
 
}
