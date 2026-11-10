package kh.edu.istad.restapi.service;

import kh.edu.istad.restapi.dto.UserAddDto;
import kh.edu.istad.restapi.dto.UserDto;
import kh.edu.istad.restapi.dto.UserUpadateDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void deleteById(UUID id);

    UserDto getById(UUID id);

    UserDto addNew(UserAddDto userAddDto);

    UserDto updateById(UUID id, UserUpadateDto userUpadateDto);

    // Define business logic
    List<UserDto> getAll(String keyword);

}
