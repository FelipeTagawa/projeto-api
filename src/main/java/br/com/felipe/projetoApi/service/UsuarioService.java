package br.com.felipe.projetoApi.service;

import br.com.felipe.projetoApi.dto.UsuarioDTO;
import br.com.felipe.projetoApi.entity.UsuarioEntity;
import br.com.felipe.projetoApi.entity.UsuarioVerificadorEntity;
import br.com.felipe.projetoApi.entity.enums.TipoSituacaoUsuario;
import br.com.felipe.projetoApi.repository.UsuarioRepository;
import br.com.felipe.projetoApi.repository.UsuarioVerificadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioVerificadorRepository usuarioVerificadorRepository;

    public List<UsuarioDTO> listarTodos(){
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    //controller vai mandar um dto e aqui estamos convertendo em entity
    public void inserir(UsuarioDTO usuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuarioEntity);
    }

    public void inserirNovoUsuario(UsuarioDTO usuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
        usuarioEntity.setId(null);
        usuarioRepository.save(usuarioEntity);
        UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();
        verificador.setUsuario(usuarioEntity);
        verificador.setUuid(UUID.randomUUID());
        verificador.setDataexpiracao(Instant.now().plusMillis(900000));
        usuarioVerificadorRepository.save(verificador);
        //TODO - enviar email para verificar a conta
        emailService.enviarEmailTexto(usuario.getEmail(),
                  "Novo usuário cadastrado",
                "Você está recendo um email de cadastro o número para validação é " + verificador.getUuid());
    }

    public String verificarCadastro(String uuid){
        UsuarioVerificadorEntity usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid)).get();
        if(usuarioVerificacao != null){
           if (usuarioVerificacao.getDataexpiracao().compareTo(Instant.now()) >= 0){
               UsuarioEntity u = usuarioVerificacao.getUsuario();
               u.setSituacao(TipoSituacaoUsuario.ATIVO);
               usuarioRepository.save(u);
               return "Usuário verificado";
           }else {
               usuarioVerificadorRepository.delete(usuarioVerificacao);
               return "tempo de verificação expirado";
           }
        } else {
            return "Usuário não verificado";
        }

    }

    public UsuarioDTO alterar(UsuarioDTO usuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
    }

    public void excluir(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO buscarPorId(Long id){
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }


}
