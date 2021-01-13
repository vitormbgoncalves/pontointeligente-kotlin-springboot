package com.github.vitormbgoncalves.pontointeligente.utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {
  private val SENHA = "123456"
  private val bCryptEncorder = BCryptPasswordEncoder()

  @Test
  fun testGerarHashSenha() {
    val hash = SenhaUtils().gerarBcrypt(SENHA)
    Assertions.assertTrue(bCryptEncorder.matches(SENHA, hash))
  }
}