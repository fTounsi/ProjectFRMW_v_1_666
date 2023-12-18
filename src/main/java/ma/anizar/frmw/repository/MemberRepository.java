package ma.anizar.frmw.repository;

import ma.anizar.frmw.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    boolean existsByCin(String cin);
}
