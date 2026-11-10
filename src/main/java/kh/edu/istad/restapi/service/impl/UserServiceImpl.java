package kh.edu.istad.restapi.service.impl;

import kh.edu.istad.restapi.dto.UserAddDto;
import kh.edu.istad.restapi.dto.UserDto;

import kh.edu.istad.restapi.dto.UserUpadateDto;
import kh.edu.istad.restapi.model.User;
import kh.edu.istad.restapi.repository.UserRepository;
import kh.edu.istad.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void deleteById(UUID id){
        boolean isFound = userRepository.select()
                .stream()
                .anyMatch(user -> user.id().equals(id));

        IO.println("User with ID" + id + "is found" + isFound);

        if (isFound)
            userRepository.select().removeIf( user -> user.id().equals(id));
        else
            throw new RuntimeException("User not found");
    }

    @Override
    public UserDto updateById(UUID id, UserUpadateDto userUpadateDto) {
        List<User> users = userRepository.select();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.id().equals(id)) {
                User updatedUser = new User(
                        user.id(),
                        user.username(),
                        user.password(),
                        userUpadateDto.fullname()
                );
                users.set(i, updatedUser);
                return new UserDto(updatedUser.id(), updatedUser.fullName());
            }
        }

        throw new RuntimeException("User not found");
    }

    @Override
    public UserDto getById(UUID id) {
        return userRepository.select()
                .stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .map(user -> new UserDto(user.id(), user.fullName()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto addNew(UserAddDto userAddDto) {
        User user = new User(
                UUID.randomUUID(),
                userAddDto.username(),
                userAddDto.password(),
                userAddDto.fullname()
        );

        userRepository.select().add(user);
        return new UserDto(user.id(), user.fullName());
    }

    @Override
    public List<UserDto> getAll(String keyword) {
        return userRepository.select()
                .stream()
                .filter(user -> user.fullName().toLowerCase()
                        .contains(keyword.toLowerCase())
                )
                .map(user -> new UserDto(user.id(), user.fullName()))
                .toList();
    }

}
