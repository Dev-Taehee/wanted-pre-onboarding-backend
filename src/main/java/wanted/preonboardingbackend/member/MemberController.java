package wanted.preonboardingbackend.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wanted.preonboardingbackend.security.JwtTokenProvider;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-up")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = new Member(requestBody.getEmail(), requestBody.getPassword());
        member = memberService.createMember(member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sign-in")
    public ResponseEntity getMember(@Valid @RequestBody MemberDto.Get requestBody) {
        Member member = new Member(requestBody.getEmail(), requestBody.getPassword());
        memberService.signIn(member);
        return new ResponseEntity(jwtTokenProvider.createToken(member.getEmail()), HttpStatus.OK);
    }

}
