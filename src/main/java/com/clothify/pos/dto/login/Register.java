package com.clothify.pos.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
