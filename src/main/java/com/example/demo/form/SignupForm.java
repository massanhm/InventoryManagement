package com.example.demo.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.validation.UniqueUsername;

import lombok.Data;

@Data
public class SignupForm {

    @NotNull(message = "名前を入力してください")
    @Size(min = 2, max = 50, message = "2文字40文字以下で入力してください")
    @UniqueUsername
    private String  username;

    @NotNull(message = "パスワードを入力してください")
    @Size(min = 8, max = 20, message = "8文字20文字以下で入力してください")
    @Pattern(regexp =
            "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,20}$",
    message = "正しい形式で入力して下さい")
    private String password;

    @NotNull(message = "パスワードを入力してください")
    private String passwordConfirmation;

    @AssertTrue(message = "パスワードは同一にしてください。")
    public boolean isPasswordValid() {
        if (password == null || password.isEmpty()) {
            return true;
        }

        return password.equals(passwordConfirmation);
    }
}
