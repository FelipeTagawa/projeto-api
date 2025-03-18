package br.com.felipe.projetoApi.repository;

import br.com.felipe.projetoApi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
