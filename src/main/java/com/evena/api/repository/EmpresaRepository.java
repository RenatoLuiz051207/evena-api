package com.evena.api.repository;

import com.evena.api.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    List<Empresa> findByPerfilId(Integer perfilId);
}
