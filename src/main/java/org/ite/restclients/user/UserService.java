package org.ite.restclients.user;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface UserService {

    List<UserResponse> getUsers();

    UserResponse getUserById(Integer id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Integer id, UserRequest userRequest);

    UserResponse patchUser(Integer id, UserRequest userRequest);

    void deleteUser(Integer id);

    String errorUri();

//    MediaResponse uploadFile();

    void uploadFile(Path filePath);

}
