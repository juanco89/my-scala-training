package juanco.rest

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.json._
import juanco.rest.MyJsonProtocol._

/*
 * Uso básico de spray-routing.
 */

object HelloWeb extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("HelloWebActorSystem")
  
  // Usando spray-routing para crear un servicio
  startServer(interface = "localhost", port = 8080) {
    path("saludo" / Segment) { (name) => 
      get {
        complete {
          Persona(name, 0).toJson.toString()
        }
      }
    }
  }
  
}
