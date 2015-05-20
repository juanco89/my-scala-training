package juanco.akka.behavior

import akka.actor.ActorSystem
import akka.actor.Props
import juanco.akka.behavior.Counter._
import akka.testkit.TestKit
import org.scalatest.WordSpecLike
import org.scalatest.BeforeAndAfterAll
import akka.testkit.ImplicitSender
import akka.testkit.TestActorRef

class CounterActorTest extends TestKit(ActorSystem("TestSystem")) 
      with WordSpecLike with BeforeAndAfterAll with ImplicitSender {
  
  override def afterAll(): Unit = {
    system.shutdown()
  }
  
  
  "El actor contador" must {
    
    
    "incrementar su estado" in {
      val counter = system.actorOf(Props[CounterActor])
      
      counter ! Increment
      counter ! Get
      
      expectMsg(1)
    }
    
    "iniciar en cero (0)" in {
      val counter = system.actorOf(Props[CounterActor])
      
      counter ! Get
      
      expectMsg(0)
    }
    
    "mantener su estado tras varias operaciones" in {
      val counter = system.actorOf(Props[CounterActor])
      
      counter ! Increment
      counter ! Increment
      counter ! Increment
      counter ! Get
      
      expectMsg(3)
    }
    
    "reinicializar su estado a cero (0)" in {
      val counter = system.actorOf(Props[CounterActor])
      
      counter ! Increment
      counter ! Increment
      counter ! Get
      
      expectMsg(2) // cambio
      
      counter ! Reset
      counter ! Get
      
      expectMsg(0) // reinicializacion
    } 
  }
  
  "TestActorRef" must {
    
    "crear un actor listo para pruebas" in {
      val counter= TestActorRef[CounterActor]
      
      counter ! Increment
      counter ! Increment
      counter ! Get
      
      expectMsg(2)
    }
  }
}