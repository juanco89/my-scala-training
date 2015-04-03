package juanco.rest

import spray.json.DefaultJsonProtocol

case class Persona (val nombre: String)

object PersonaJsonProtocol extends DefaultJsonProtocol {
  implicit val personaFormat = jsonFormat1(Persona)
}