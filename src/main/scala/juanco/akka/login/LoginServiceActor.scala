package juanco.akka.login

import java.util.Date
import java.util.concurrent.Executors

import akka.actor.Actor
import akka.actor.ActorRef
// import akka.pattern._
import akka.actor.Props

import scala.collection.mutable.Map
import scala.concurrent.ExecutionContext

import juanco.services.mock.UserService
import juanco.akka.login._

/**
 * Servicio que gestiona las sesiones de autenticacion.
 * 
 * Se usa una estructura en memoria tipo mapa para almacenar las sessiones (actores)
 * creadas, específicamente una por usuario, invalidandola y volviéndola a activar, 
 * evitando así consumir tiempo en la creación de actores. 
 * 
 * Juan C. Orozco
 */
class LoginServiceActor extends Actor {
  
  private val executor = Executors.newFixedThreadPool(4)
  private implicit val _ = ExecutionContext.fromExecutorService(executor)
  
  private val service: UserService = new UserService
  
  private var activeSessions: Map[String, ActorRef] = Map()
  
  
  override def receive: Receive = {
    case m: LoginRequest =>
      val originalSender = sender()
      println("Intento de login de " + m.user)
      // Buscar las sesiones creadas previamente en la estructura en memoria.
      activeSessions.get(m.user) match {
	    case Some(actor) => {
	    	context.actorOf(Props(new Actor() {
	        actor ! TokenRequest(m.user)
	        
	        def receive: Receive = {
	          case NilToken() => 
	            val token: String = generarToken
 			    actor ! User(m.user, m.pass, token)
 			    println("Se actualiza el token de session: " + token)
	            context.stop(self)
	          case Token(token: String) => 
	            println( token )
	            context.stop(self)
	          case e =>
	            println("asdf" + e.getClass.getName())
	        }	        
	      }))
	    
	    }
	    case None => 
	      if(service.autenticar(m.user, m.pass)) crearSession(m)
	      else println("No existe el usuario " + m.user)
	  }
      ()
    case m: ActiveSessionsList =>
      println("Número de sesiones a listar " + activeSessions.size)
      activeSessions foreach (p => p._2 ! ActiveSessionsList()) 
      ()
  }
  
  def generarToken: String = new Date().getTime().toString
  
  def crearSession(u: LoginRequest) = {
    val token: String = generarToken
    val session: ActorRef = context.actorOf(Props[UserActor], u.user)
    session ! User(u.user, u.pass, token)
    activeSessions.put(u.user, session)
  }
}

