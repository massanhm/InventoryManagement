package com.example.demo.Form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupForm {

    @NotNull(message = "名前を入力してください")
    @Size(min = 2, max = 50, message = "2文字40文字以下で入力してください")
    private String  username;

    @NotNull(message = "メールアドレスを入力してください")
    @Size(max = 100, message = "2文字100文字以下で入力してください")
    @Pattern(regexp = "[\\w\\-._]+@[\\w\\-._]+\\.[A-Za-z]+",
            message = "正しい形式で入力して下さい")
    private String  email;

    @NotNull(message = "パスワードを入力してください")
    @Size(min = 8, max = 20, message = "8文字20文字以下で入力してください")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$)",
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
