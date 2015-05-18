package juanco.rest

import spray.json.DefaultJsonProtocol

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val personaFormat = jsonFormat2(Persona)
}
