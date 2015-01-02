package juanco.akka.login

import java.util.Date
import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

import akka.actor.Actor

import juanco.akka.login._

/**
 * Actor que representa una sesion de autenticación del sistema.
 * 
 * Se usa un un system.scheduler para invalidar la sesión cada minuto.
 * Otro mecanismo es usar un timestamp del último movimiento en la sesión
 * y validar en cada operación el tiempo que ha transcurrido.
 * 
 * Juan C. Orozco
 */
class UserActor extends Actor {
  
  private implicit val _ : ExecutionContext = context.dispatcher
  
  private var u: User = User("", "", "")
  
  private var lastAction = new Date
  
  override def receive: Receive = {
    case m: User =>
      // validarTimeout
      u = m
      context.system.scheduler.scheduleOnce(Duration.create(60, TimeUnit.SECONDS), self, InvalidateSession() )
      println("Se crea session para el usuario " + m.user)
      ()
    case m: InvalidateSession =>
      u = User(u.user , u.pass, "")
      println("Se invalida la session de " + u.user)
      ()
    case m: TokenRequest =>
      // validarTimeout
      if(u.token.equals("")) sender() ! NilToken()
      else sender() ! Token(u.token)
      ()
    case m: ActiveSessionsList =>
      // validarTimeout
      if(u.token != "")
    	  println("* Session activa, usuario: " + u.user + " token: " + u.token)
      ()
    case _ =>
      // validarTimeout
      println("Mensaje no identificado")
  }
  
  
  /**
   * Verifica el tiempo transcurrido desde el último movimiento en la sesión,
   * invalidandola o renovando la marca de tiempo.
   */
  def validarTimeout() = {
    val newTimeout = new Date
    if( (newTimeout.getTime() - lastAction.getTime()) > (60 * 1000) ) {
      // Invalidar la actual sesión.
      // self ! Kill
      u = User(u.user , u.pass, "")
    } else {
      lastAction = new Date
    }
  }
  
}
