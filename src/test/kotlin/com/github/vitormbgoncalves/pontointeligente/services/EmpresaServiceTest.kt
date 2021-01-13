package com.github.vitormbgoncalves.pontointeligente.services

import com.github.vitormbgoncalves.pontointeligente.documents.Empresa
import com.github.vitormbgoncalves.pontointeligente.repositories.EmpresaRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class EmpresaServiceTest {

  @Autowired
  val empresaService: EmpresaService? = null

  @MockBean
  private val empresaRepository: EmpresaRepository? = null

  private val CNPJ = "51463645000100"

  @BeforeEach
  @Throws(Exception::class)
  fun setUp() {
    BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(empresa())
    BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
  }

  @Test
  fun testBuscarEmpresaPorCnpj() {
    val empresa: Empresa? = empresaService?.buscarPorCNPJ(CNPJ)
    Assertions.assertNotNull(empresa)
  }

  @Test
  fun testPersistirEmpresa() {
    val empresa: Empresa? = empresaService?.persistir(empresa())
    Assertions.assertNotNull(empresa)
  }

  private fun empresa(): Empresa = Empresa("Razão Social", "5146364500010", "1")
}