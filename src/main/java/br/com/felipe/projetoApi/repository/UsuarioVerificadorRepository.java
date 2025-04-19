package br.com.felipe.projetoApi.repository;

import br.com.felipe.projetoApi.entity.UsuarioEntity;
import br.com.felipe.projetoApi.entity.UsuarioVerificadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificadorEntity, Long> {

    public Optional<UsuarioVerificadorEntity> findByUuid(UUID uuid);
}
