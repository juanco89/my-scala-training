package juanco.rest

import spray.json.DefaultJsonProtocol

case class Persona (val nombre: String, val edad: Int)

object PersonaJsonProtocol extends DefaultJsonProtocol {
  implicit val personaFormat = jsonFormat2(Persona)
}