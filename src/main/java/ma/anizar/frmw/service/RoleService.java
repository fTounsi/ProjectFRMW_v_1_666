package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    //Optional<Role> findByName(String name);
    Role findByName(String name);
    Role findById(Long id);
    List<Role> findAll();
    void save(Role role);
}
