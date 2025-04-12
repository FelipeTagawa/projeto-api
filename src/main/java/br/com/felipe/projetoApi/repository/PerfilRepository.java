package br.com.felipe.projetoApi.repository;

import br.com.felipe.projetoApi.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
}
