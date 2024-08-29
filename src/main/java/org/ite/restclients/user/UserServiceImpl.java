package org.ite.restclients.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpHeaders;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final RestClient restClient;

    public UserServiceImpl() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Override
    public List<UserResponse> getUsers() {

        return restClient.get()
                .uri("/api/v1/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public UserResponse getUserById(Integer id) {
        return restClient.get()
                .uri("/api/v1/users/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<UserResponse>() {
                });
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return restClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRequest)
                .retrieve()
                .body(UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        return restClient.put()
                .uri("/api/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRequest)
                .retrieve()
                .body(UserResponse.class);
    }

    @Override
    public UserResponse patchUser(Integer id, UserRequest userRequest) {
        return restClient.patch()
                .uri("/api/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRequest)
                .retrieve()
                .body(UserResponse.class);
    }

    @Override
    public void deleteUser(Integer id) {
        restClient.delete()
                .uri("/api/v1/users/{id}", id)
                .retrieve()
                .body(Void.class);
    }

    @Override
    public String errorUri() {
        return restClient.get()
                .uri("/api/v1/users/error")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ResponseStatusException(
                            response.getStatusCode(), request.getURI() + " not found. " + response.getStatusCode());
                })
                .body(String.class);
    }

//    @Override
//    public MediaResponse uploadFile() {
//        // Create a MultiValueMap to hold the parts
//        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
//
//        // Add a simple field part (e.g., form field)
//        parts.add("fieldPart", "fieldValue");
//
//        // Add a file part using FileSystemResource
//        parts.add("filePart", new FileSystemResource("C:\\Users\\mengs\\Downloads\\SQLQuery1.sql"));
//
//        // If you need to send any plain text data (no object needed)
//        parts.add("plainTextPart", "This is a plain text part");
//
//        // Send the multipart request using RestClient
//        String response = restClient.post()
//                .uri("/api/v1/users/uploadFile")
//                .body(parts)
//                .retrieve()
//                .body(String.class);
//
//        return new MediaResponse(response);
//    }

    //    @Override
//    public MediaResponse uploadFile() {
//        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
//
//        // Add the file part
//        parts.add("filePart", new FileSystemResource("C:\\Users\\mengs\\Downloads\\SQLQuery1.sql"));
//
//        // Add any additional parts if needed
//        parts.add("plainTextPart", "This is a plain text part");
//
//        // Ensure the URI matches the one in the controller
//        String response = restClient.post()
//                .uri("/users/uploadFile") // Ensure this path matches the controller's path
//                .body(parts)
//                .retrieve()
//                .body(String.class);
//
//        return new MediaResponse(response);
//    }
//    @Override
//    public void uploadFile(Path filePath) {
//        String url = "http://localhost:8080/api/v1/users/uploadFile";
//
//        // Create the request body and headers
//        FileSystemResource fileResource = new FileSystemResource(filePath);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        // Create the multipart request body
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("files", fileResource);
//
//        // Send the request and block to get the response
//        ResponseEntity<Void> responseEntity = restClient
//                .post()
//                .uri(url)
//                .headers(h -> h.putAll(headers))
//                .body(BodyInserters.fromMultipartData(body))
//                .retrieve()
//                .toBodilessEntity();
//
//        // Get the status code from the response entity
//        HttpStatus responseStatus = (HttpStatus) responseEntity.getStatusCode();
//
//        // Check the response status
//        if (responseStatus.is2xxSuccessful()) {
//            System.out.println("File uploaded successfully!");
//        } else {
//            System.err.println("Failed to upload file. Status: " + responseStatus);
//        }
//    }

    @Override
    public void uploadFile(Path filePath) {
        MultiValueMap<String,Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", new FileSystemResource(filePath));
        restClient.post()
                .uri("/api/v1/users/seu")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(parts)
                .retrieve()
                .body(Void.class);
    }




}

