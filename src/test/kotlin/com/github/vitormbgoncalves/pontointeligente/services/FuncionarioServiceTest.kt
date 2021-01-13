package com.github.vitormbgoncalves.pontointeligente.services

import com.github.vitormbgoncalves.pontointeligente.documents.Funcionario
import com.github.vitormbgoncalves.pontointeligente.enums.PerfilEnum
import com.github.vitormbgoncalves.pontointeligente.repositories.FuncionarioRepository
import com.github.vitormbgoncalves.pontointeligente.utils.SenhaUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
class FuncionarioServiceTest {

  @MockBean
  private val funcionarioRepository: FuncionarioRepository? = null

  @Autowired
  private val funcionarioService: FuncionarioService? = null

  private val email: String = "email@email.com"
  private val cpf: String = "3434323434"
  private val id: String = "1"

  @BeforeEach
  @Throws(Exception::class)
  fun setUp() {
    BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java)))
      .willReturn(funcionario())
    BDDMockito.given(funcionarioRepository?.findById(id)).willReturn(Optional.of(funcionario()))
    BDDMockito.given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
    BDDMockito.given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())
  }

  @Test
  fun testPersistirFuncionario() {
    val funcionario: Funcionario? = this.funcionarioService?.persistir(funcionario())
    Assertions.assertNotNull(funcionario)
  }

  @Test
  fun testBuscarFuncionarioPorId() {
    val funcionario: Funcionario? = this.funcionarioService?.buscarPorId(id)
    Assertions.assertNotNull(funcionario)
  }

  @Test
  fun testBuscarFuncionarioPorEmail() {
    val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(email)
    Assertions.assertNotNull(funcionario)
  }

  @Test
  fun testBuscarFuncionarioPorCpf() {
    val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(cpf)
    Assertions.assertNotNull(funcionario)
  }

  private fun funcionario(): Funcionario =
    Funcionario("Nome", email, SenhaUtils().gerarBcrypt("123456"), cpf, PerfilEnum.ROLE_USUARIO, id)
}
