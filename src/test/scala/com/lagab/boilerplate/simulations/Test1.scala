package com.lagab.boilerplate.simulations


import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Test1 {
  val headers_0 = Map(
    "Accept" -> "text/html, application/xhtml+xml, */*",
    "Accept-Language" -> "fr-FR")

  val headers_http = Map(
    "Accept" -> """application/json"""
  )

  val headers_http_authentication = Map(
    "Content-Type" -> """application/json""",
    "Accept" -> """application/json"""
  )

  val headers_http_authenticated = Map(
    "Accept" -> """application/json""",
    "Authorization" -> "${access_token}"
  )

  // si login OK
  val chain_read_article =
    exec(http("1.2 Lecture communauté")
      .get("/web/systemes-d-information/accueil")
      .headers(headers_0)
      .check(status.is(200))
    )


  val scn = scenario("Users ").exec(Login.chain_login)

}
