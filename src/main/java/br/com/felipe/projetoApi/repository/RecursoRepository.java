package br.com.felipe.projetoApi.repository;

import br.com.felipe.projetoApi.entity.RecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoRepository extends JpaRepository<RecursoEntity, Long> {
}
