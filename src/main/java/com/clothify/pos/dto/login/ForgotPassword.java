package com.clothify.pos.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPassword {
    private String email;
    private String otp;
    private String newPassword;
    private String confirmPassword;
}