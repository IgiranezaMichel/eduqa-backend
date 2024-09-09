package com.eduqa_backend.mapper;

import java.util.function.Function;

import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.modal.User;

public class UserMapper implements Function<User,UserDTO> {

    @Override
    public UserDTO apply(User t) {
     return new UserDTO(t);
    }

}
