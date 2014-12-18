
package juanco.akka.login

import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import akka.actor.Props
import scala.annotation.tailrec

object LoginMain extends App {
  var system = ActorSystem("LoginActorSystem")
  implicit val _ : ExecutionContext = system.dispatcher
  
  // TODO: Crear actores
  
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
    scala.io.StdIn.readInt
  }
  
  def ejecutarAcccion(opcion: Int) = {
    if(opcion == 1) {
      Console.print("Ingrese su usuario: ")
      val username = scala.io.StdIn.readLine
      Console.print("Ingrese su contraseña: ")
      val password = scala.io.StdIn.readLine
      
    }else if(opcion == 2) {
      
    }else if(opcion == 3) {
      
    }else if(opcion == 4) {
      seguir = false
    }
  }
  
  // Thread.sleep(20000)
  system.shutdown
}
