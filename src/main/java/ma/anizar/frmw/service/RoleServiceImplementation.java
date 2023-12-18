package ma.anizar.frmw.service;

import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Role;
import ma.anizar.frmw.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
//        System.out.println("Found Role -> " + name + ": " + (roleRepository.findByName(name).isPresent() ? "FOUND" : "NOT FOUND"));
        return roleRepository.findByName(name);
    }

  /*  @Override
    public Optional<Role> findByName(String name) {
//        System.out.println("Found Role -> " + name + ": " + (roleRepository.findByName(name).isPresent() ? "FOUND" : "NOT FOUND"));
        return roleRepository.findByName(name);
    }*/

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
