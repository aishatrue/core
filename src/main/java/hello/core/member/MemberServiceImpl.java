package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //오로지 인터페이스만 남게됨. 생성자를 통해 주입받고.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
