package juanco.akka.behavior


object Counter {
  
  case class Increment()
  case class Reset()
  case class Get()
  
  /**
   * Actor contador con cambio de comportamiento.
   * 
   * Mantiene estado en la pila cambiando de estado y actualizando  
   * los parámetros del método receive. 
   */
  class CounterActor extends akka.actor.Actor {
    
    /**
     * Método que define el comportamiento del actor.
     * 
     * Se actualiza el comportamiento con context.become() 
     * actualizando el estado en el parámetro de este método.
     */
    def contador(n: Int): Receive = {
      case Increment => context.become(contador(n + 1))
      case Reset => context.become(contador(0))
      case Get => sender ! n
    }
    
    /**
     * Implementación del método receive necesario por la clase base Actor.
     * 
     * Establece el estado inicial en 0.
     */
    def receive = contador(0)
  }
  
}
