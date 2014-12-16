package juanco.scala.notas

/**
 * Queens - Búsqueda combinatoria
 * Week 6.3
 * Curso Scala
 * 2014
 */
class Queens {
  
  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] =
      if(k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k -1)
          col <- 0 until n
          if(isSafe(col, queens))
        } yield col :: queens
    placeQueens(n)
  }
  
  private def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRow = (row -1 to 0 by -1) zip queens
    queensWithRow forall {
      case (r, c) => col != c && math.abs(col - c) != row -r
    }
  }
  
  def show(queens: List[Int]) = {
    val lines =
      for(col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" + (lines mkString "\n")
  }
}


object Queens {
  
  def main(args:Array[String]) : Unit = {
    
    val queen = new Queens
    
    val result = (queen.queens(8) map queen.show) mkString "\n"
    
    println(result)
  }
  
}