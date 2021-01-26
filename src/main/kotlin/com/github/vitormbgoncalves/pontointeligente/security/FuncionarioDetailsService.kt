package com.github.vitormbgoncalves.pontointeligente.security

import com.github.vitormbgoncalves.pontointeligente.documents.Funcionario
import com.github.vitormbgoncalves.pontointeligente.services.FuncionarioService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Service
class FuncionarioDetailsService(val funcionarioService: FuncionarioService) : UserDetailsService {

  override fun loadUserByUsername(username: String?): UserDetails {
    if(username != null) {
      val funcionario: Funcionario? = funcionarioService.buscarPorEmail(username)
      if (funcionario != null) {
        return FuncionarioPrincipal(funcionario)
      }
    }
    throw UsernameNotFoundException(username)
  }

}