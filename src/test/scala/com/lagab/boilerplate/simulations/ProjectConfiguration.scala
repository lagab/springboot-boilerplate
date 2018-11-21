package com.lagab.boilerplate.simulations

import io.gatling.core.Predef._

object ProjectConfiguration {

  /**
    * Data set
    */
  val userCredentialsFile = "gatling/feeders/user_credentials.csv"

  val userCredentials = csv(ProjectConfiguration.userCredentialsFile).circular

  // -- HTTP Configuration HTTP
  val baseURL = "http://localhost:8081";

}
