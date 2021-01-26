package com.github.vitormbgoncalves.pontointeligente

import com.github.vitormbgoncalves.pontointeligente.documents.Empresa
import com.github.vitormbgoncalves.pontointeligente.documents.Funcionario
import com.github.vitormbgoncalves.pontointeligente.enums.PerfilEnum
import com.github.vitormbgoncalves.pontointeligente.repositories.EmpresaRepository
import com.github.vitormbgoncalves.pontointeligente.repositories.FuncionarioRepository
import com.github.vitormbgoncalves.pontointeligente.repositories.LancamentoRepository
import com.github.vitormbgoncalves.pontointeligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PontointeligenteApplication(val empresaRepository: EmpresaRepository,
                                  val funcionarioRepository: FuncionarioRepository,
                                  val lancamentoRepository: LancamentoRepository
) : CommandLineRunner {

  override fun run(vararg args: String?) {
    empresaRepository.deleteAll()
    funcionarioRepository.deleteAll()
    lancamentoRepository.deleteAll()

    var empresa: Empresa = Empresa("Empresa", "10443887000146")
    empresa = empresaRepository.save(empresa)

    var admin: Funcionario = Funcionario("Admin", "admin@empresa.com",
      SenhaUtils().gerarBcrypt("123456"), "2570317000",
      PerfilEnum.ROLE_ADMIN, empresa.id!!)
    admin = funcionarioRepository.save(admin)

    var funcionario: Funcionario = Funcionario("Funcionario",
      "funcionario@empresa.com", SenhaUtils().gerarBcrypt("123456"),
      "4432544155s7", PerfilEnum.ROLE_USUARIO, empresa.id!!)
    funcionario = funcionarioRepository.save(funcionario)

    System.out.println("Empresa ID: " + empresa.id)
    System.out.println("Admin ID: " + admin.id)
    System.out.println("Funcionario ID: " + funcionario.id)
  }
}


fun main(args: Array<String>) {
  SpringApplication.run(PontointeligenteApplication::class.java, *args)
}