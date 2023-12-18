package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Member;
import ma.anizar.frmw.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String addMember(Member member) {
        if (memberRepository.existsByCin(member.getCin())) {
            return "Error: Member avec CIN " + member.getCin() + " exist deja.";
        }
        memberRepository.save(member);
        return "Opération, effectuée avec succès.";
    }

    public String updateMember(Member member) {
        memberRepository.save(member);
        return "Opération, effectuée avec succès.";
    }
}
