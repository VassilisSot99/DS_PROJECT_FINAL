package gr.hua.huaproject.repository;

import gr.hua.huaproject.entity.AuthPK;
import gr.hua.huaproject.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, AuthPK> {

}
