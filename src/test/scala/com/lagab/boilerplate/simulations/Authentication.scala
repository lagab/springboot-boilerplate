package com.lagab.boilerplate.simulations


import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Authentication {

  val scn = scenario("Authentication ").exec(Login.chain_login)

}
