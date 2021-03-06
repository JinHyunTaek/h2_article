package com.example.article.jparepository;

import com.example.article.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Member findById(Long memberId) {
        return em.find(Member.class,memberId);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return findAll().stream()
                .filter(member -> member.getNickname().equals(nickname))
                .findFirst();
    }

    private void validateDuplicateField(){
    }

}
