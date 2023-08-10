package wanted.preonboardingbackend.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wanted.preonboardingbackend.exception.BusinessLogicException;
import wanted.preonboardingbackend.exception.ExceptionCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member createMember(Member member) {
        verifyExistEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        return memberRepository.save(member);
    }

    public Member signIn(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        Member signInMember = findMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_INVALID));
        if(!passwordEncoder.matches(member.getPassword(), signInMember.getPassword())) throw new BusinessLogicException(ExceptionCode.MEMBER_INVALID);
        return signInMember;
    }

    private void verifyExistEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

}
