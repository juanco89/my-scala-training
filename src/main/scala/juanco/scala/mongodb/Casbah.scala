package juanco.scala.mongodb

import com.mongodb.casbah.Imports._
import shapeless.ToList


object Casbah extends App {
  
  val conn = new CasbahConnection("localhost", 27017, "scala")
  val coll = conn.getCollection("personas")
  
  // Inserting 
  for ( d <- PersonasHelper.generarPersonasAleatorias ) coll.insert(d)
  
  // Query
  println("Número de elementos iniciales: " + coll.count())
  
  println("Numero de personas mayores de edad => ")
  
  val mayores = coll.find( "edad" $gt 18 )
  for( d <- mayores ) {
    println(d)
    println(d.get("nombre"))
    println(d("nombre"))
  }
  
  // remove
  coll.remove("edad" $gt 10)
  
  conn.close
}


class CasbahConnection(val host: String, val port: Int, val dbName: String = "test") {
  
  val mongoClient = MongoClient(host, port)
  val db = mongoClient(dbName);
  
  def getCollection(name: String) = db(name)
  
  def close: Unit = mongoClient.close()
}

object PersonasHelper {
  
  def generarPersonasAleatorias = {
    val r = scala.util.Random
    val c = for ( i <- 1 to 10 ) 
      yield MongoDBObject("nombre" -> i.toString, "edad" -> r.nextInt(30))
    
    c.toList
  }
  
}