package juanco.akka.helloweb

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

/*
 * Uso básico de spray-routing.
 */

object HelloWeb extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("HelloWebActorSystem")
  
  // Usando spray-routing para crear un servicio
  startServer(interface = "localhost", port = 8080) {
    path("saludo") {
      get {
        complete {
          <h1>Hola mundo!</h1>
        }
      }
    }
  }
  
}
