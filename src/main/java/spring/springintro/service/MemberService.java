package spring.springintro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.springintro.domain.Member;
import spring.springintro.repository.MemberRepository;
import spring.springintro.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Register a member
     */
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("This name already exists.");
                });
    }

    /**
     * Retrieve all members
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * Retrieve a member by id
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
