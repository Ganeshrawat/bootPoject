package com.api.gateway.models;

import lombok.*;

import java.util.Collection;
import java.util.Collections;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {

    private String userId;
    private String AccessToken;
    private String RefreshToken;
    private long expireAt;
    private Collection<String> authorities;
}
