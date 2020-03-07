package com.pj.store.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long USER_ID;
    private String USER_USERNAME;
    private String USER_PASSWORD;

}
