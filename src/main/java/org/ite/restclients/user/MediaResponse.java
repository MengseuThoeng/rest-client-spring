package org.ite.restclients.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(

//        String name, //unique file name
//
//        String contentType, //PNG Extension
//
//        String extension,
//
//        String uri, // https://asd/dad.png
//
//        @JsonInclude(JsonInclude.Include.NON_NULL)
//        Long size

        String response

) {
}

