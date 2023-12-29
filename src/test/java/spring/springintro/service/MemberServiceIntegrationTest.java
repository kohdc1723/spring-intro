package spring.springintro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import spring.springintro.domain.Member;
import spring.springintro.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("Spring");
        // when
        Long resultId = memberService.join(member);
        // then
        Member resultMember = memberService.findOne(resultId).get();
        assertThat(resultMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void exceptDuplicateMember() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");
        Member member2 = new Member();
        member2.setName("Spring");
        // when
        memberService.join(member1);
        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("This name already exists.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}