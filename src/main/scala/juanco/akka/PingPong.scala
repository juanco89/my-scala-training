
package juanco.akka


import scala.concurrent.ExecutionContext

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.ActorSelection

/**
 * Comunicación básica entre actores con clases Ping-Pong.
 * 
 * Ejercicio realizado en la capacitación "principios de programación reactiva con Akka"
 * 2014
 */
object ActorPingPongObject extends App {
  var system = ActorSystem("ActorSystem")
  implicit val _ : ExecutionContext = system.dispatcher
  
  val ping: ActorRef = system.actorOf(Props[Ping], "Ping")
  val pong: ActorRef = system.actorOf(Props[Pong], "Pong")
  
  // Cada actor está identificado por una ruta en el sistema (grafo) de actores.
  println(ping.path)
  
  // Se envía el primer mensaje al actor Ping
  ping ! PingPongMessage("Ping ")
  
  Thread.sleep(500)
  system.shutdown
}


case class PingPongMessage(msg: String)

class Ping extends Actor {
  private implicit val _: ExecutionContext = context.dispatcher 
  
  // Referencia a otro actor, es necesaria para poder enviar mensajes al actor.
  // Se debe conocer la ruta (dirección) dentro del grafo.
  private val pong: ActorSelection = context.actorSelection("akka://ActorSystem/user/Pong")
  
  override def receive: Receive = {
    case ppm: PingPongMessage =>
      print(ppm.msg)
      pong ! PingPongMessage("Pong!")
    case _ =>
      println("Se recibe mensaje no identificado en Ping")
  }
}

class Pong extends Actor {
  private implicit val _: ExecutionContext = context.dispatcher 
  
  private val ping: ActorSelection = context.actorSelection("akka://ActorSystem/user/Ping")
  
  override def receive: Receive = {
    case ppm: PingPongMessage =>
      println(ppm.msg)
      ping ! PingPongMessage("Ping ")
    case _ =>
      println("Se recibe mensaje no identificado en Pong")
  }  
}
