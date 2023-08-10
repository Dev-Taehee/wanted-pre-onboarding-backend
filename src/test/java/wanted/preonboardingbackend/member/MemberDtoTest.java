package wanted.preonboardingbackend.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MemberDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("올바른 입력이 들어온 경우")
    void memberDto_validation() {
        MemberDto.Post memberPostDto = MemberDto.Post.builder()
                .email("wth0086@gmail.com")
                .password("tmp123456")
                .build();
        Set<ConstraintViolation<MemberDto.Post>> violations = validator.validate(memberPostDto);
        Assertions.assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("이메일이 올바른 형태가 아닌 경우")
    void memberDto_email_not_valid() {
        MemberDto.Post memberPostDto = MemberDto.Post.builder()
                .email("wth0086gmail.com")
                .password("tmp123456")
                .build();
        Set<ConstraintViolation<MemberDto.Post>> violations = validator.validate(memberPostDto);

        List<String> messages = new LinkedList<>();
        Iterator<ConstraintViolation<MemberDto.Post>> iterator = violations.iterator();
        while(iterator.hasNext()) {
            messages.add(iterator.next().getMessage());
        }

        Assertions.assertThat(violations.size()).isEqualTo(1);
        Assertions.assertThat(messages).contains("반드시 @가 포함되어있어야합니다.");
    }

    @Test
    @DisplayName("비밀번호가 올바른 형태가 아닌 경우")
    void memberDto_password_not_valid() {
        MemberDto.Post memberPostDto = MemberDto.Post.builder()
                .email("wth0086@gmail.com")
                .password("tmp1234")
                .build();
        Set<ConstraintViolation<MemberDto.Post>> violations = validator.validate(memberPostDto);

        List<String> messages = new LinkedList<>();
        Iterator<ConstraintViolation<MemberDto.Post>> iterator = violations.iterator();
        while(iterator.hasNext()) {
            messages.add(iterator.next().getMessage());
        }

        Assertions.assertThat(violations.size()).isEqualTo(1);
        Assertions.assertThat(messages).contains("비밀번호의 길이는 최소 8자 이상이어야 합니다.");
    }

}
