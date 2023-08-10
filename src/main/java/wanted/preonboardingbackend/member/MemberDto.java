package wanted.preonboardingbackend.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;

public class MemberDto {

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class Post {
        @Pattern(regexp = ".*@.*", message = "반드시 @가 포함되어있어야합니다.")
        private String email;

        @Length(min = 8, message = "비밀번호의 길이는 최소 8자 이상이어야 합니다.")
        private String password;
    }

}
