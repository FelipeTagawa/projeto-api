package br.com.felipe.projetoApi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "USUARIO_VERIFICADOR")
public class UsuarioVerificadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private Instant dataexpiracao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", unique = true)
    private UsuarioEntity usuario;
}
