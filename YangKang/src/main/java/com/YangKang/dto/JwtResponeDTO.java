package com.YangKang.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtResponeDTO {
    @NotNull
    private String token;
    @NotNull
    private String username;
    @NotNull
    private String role;


}
