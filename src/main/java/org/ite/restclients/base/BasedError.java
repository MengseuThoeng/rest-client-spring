package org.ite.restclients.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BasedError<T> {
    private String code;
    private T description;
}
