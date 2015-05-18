package juanco.scala.slick

import scala.slick.driver.PostgresDriver.simple._


object PersonasDbMapping {
  
  /*
    Column   |  Type   |                       Modifiers                       
  -----------+---------+-------------------------------------------------------
   id        | integer | not null default nextval('personas_id_seq'::regclass)
   nombre    | text    | not null
   edad      | integer | not null
   telefono  | text    | 
   direccion | text    | 
  */
  class Personas(tag: Tag) extends Table[(Int, String, Int, String, String)](tag, "personas") {
    def id  = column[Int]("id", O.AutoInc, O.PrimaryKey)
    def nombre = column[String]("nombre", O.NotNull)
    def edad = column[Int]("edad", O.NotNull)
    def telefono = column[String]("telefono",O.Nullable)
    def direccion = column[String]("direccion", O.Nullable)
    
    def * = (id, nombre, edad, telefono, direccion)
  }
  
  def main(args: Array[String]): Unit = {
    
    val conexUrl = "jdbc:postgresql://localhost/personas?user=postgres&password=postgres"
    
    Database.forURL(conexUrl, driver = "org.postgresql.Driver") withSession { 
      implicit session => 
        
        val personas = TableQuery[Personas]
        
        val query = for(p <- personas; if p.edad > 18) yield p.nombre
        
        query.list foreach { println(_) }
    }
  }
  
}
