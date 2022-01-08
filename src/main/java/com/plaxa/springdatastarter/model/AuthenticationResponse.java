package com.plaxa.springdatastarter.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class AuthenticationResponse implements Serializable {

    @Getter
    private final String jwt;
}
