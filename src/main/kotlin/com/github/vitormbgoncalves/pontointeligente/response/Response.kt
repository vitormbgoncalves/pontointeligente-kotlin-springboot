package com.github.vitormbgoncalves.pontointeligente.response

class Response<T> (
  val erros: ArrayList<String> = arrayListOf(),
  val data: T? = null
)