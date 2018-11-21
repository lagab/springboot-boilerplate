package com.lagab.boilerplate.simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._


/**
  * @author gabriel
  * @since 21/11/2018.
  */
class Scenario extends Simulation {

  val httpProtocol = http
    .baseUrl(ProjectConfiguration.baseURL)
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
    .disableCaching

  //Run Scenario
  setUp(
    Test1.scn.inject(rampUsers(5) during(1 minutes)),

  ).protocols(httpProtocol)
}