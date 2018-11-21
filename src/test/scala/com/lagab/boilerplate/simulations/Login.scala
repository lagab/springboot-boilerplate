package com.lagab.boilerplate.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}

import scala.concurrent.duration._

object Login {

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


  val headers_7 = Map(
    """Accept""" -> """text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8""",
    """Content-Type""" -> """application/x-www-form-urlencoded""",
    """Origin""" -> ProjectConfiguration.baseURL)

  val chain_login =
    repeat(1) {
      exec(http("First unauthenticated request")
        .get("/account/account")
        .headers(headers_http)
        .check(status.is(200))).exitHereIfFailed
        .pause(1 seconds)
        .exec(http("Authentication")
          .post("/auth/authenticate")
          .headers(headers_http_authentication)
          .body(StringBody("""{"username": "admin", "password":"admin" }"""))//.asJSON
          .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
          .get("/account/account")
          .headers(headers_http_authenticated)
          .check(status.is(200)))
        .pause(5 seconds)
    }

}
