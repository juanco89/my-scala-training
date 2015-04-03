package juanco.rest

// imports necesarios para uso básico de json.
import spray.json._
import DefaultJsonProtocol._ // Si no se crea un protocolo propio, sirve para los tipos básicos.

// Importante para serializar las clases propias.
import juanco.rest.PersonaJsonProtocol._

/** 
 *  Json parsing using spray-json.
 */
object JsonTest extends App {
  
  val jsonAst = Persona("Juan Carlos").toJson
  
  println("Cool, Json!! ~~> " + jsonAst)
}
