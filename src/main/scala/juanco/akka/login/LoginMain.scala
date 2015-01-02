package juanco.akka.login

import scala.concurrent.ExecutionContext
import scala.annotation.tailrec
import scala.io.Source.stdin

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

import juanco.services.mock.Usuario

/**
 * 
 * Clase principal del ejercicio de Login y manejo de sesiones en memoria
 * realizadoen la capacitación de Akka y programacion reactiva.
 * 
 * Objetivo: Crear un sistema de autenticación y manejo de sesiones usando actores,
 * manteniendo los tokens de sesión en memoria.
 * 
 * Juan C. Orozco
 */
object LoginMain extends App {
  
  var system = ActorSystem("LoginActorSystem")
  implicit val _ : ExecutionContext = system.dispatcher

  val loginService: ActorRef = system.actorOf(Props[LoginServiceActor], "LoginService")
  
  var seguir: Boolean = true

  consola()

  @tailrec
  def consola(): Unit = {
    mostrarMenu()
    val accion = entrada()
	ejecutarAcccion(accion)
	if(seguir)
	  consola()
  }

  def mostrarMenu() = {
	  Console.println("1. Autenticar")
	  Console.println("2. Listar")
	  Console.println("3. Cerrar sesión")
	  Console.println("4. Salir")
  }

  def entrada(): Int = {
    readInt()
  }

  def ejecutarAcccion(opcion: Int) = {
    if(opcion == 1) {
      Console.print("Ingrese su usuario: ")
      val username = readLine()
      Console.print("Ingrese su contraseña: ")
      val password = readLine()
      loginService ! LoginRequest(username, password)
    }else if(opcion == 2) {
      loginService ! ActiveSessionsList()
    }else if(opcion == 3) {
      // En construcción
    }else if(opcion == 4) {
      seguir = false
    }
  }

  // Thread.sleep(5000)
  system.shutdown
}
