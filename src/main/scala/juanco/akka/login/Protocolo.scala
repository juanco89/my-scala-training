package juanco.akka.login


// Case classes para implementar protocolo de mensajería entre actores.

case class LoginRequest(user: String, pass: String)
case class TokenRequest(user: String)
case class ActiveSessionsList()
case class InvalidateSession()

case class User(user: String, pass: String, token: String)
case class NilToken()
case class Token(token: String)
