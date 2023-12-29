package spring.springintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.springintro.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JPQL: "select m from Member m where m.name = ?"
    @Override
    Optional<Member> findByName(String name);
}
