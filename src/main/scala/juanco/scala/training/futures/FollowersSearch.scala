package juanco.scala.training.futures

import java.util.concurrent.Executors
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.util.Success
import scala.util.Failure
import collection.JavaConversions._
import juanco.services.mock.TwitterService


object FollowersSearch extends App {
  
  // Necesario para la ejecución de futuros
  private val executorService = Executors.newFixedThreadPool(4)
  private implicit val _ = ExecutionContext.fromExecutorService(executorService)
  
  
  def queryFollowers(): Future[List[String]] = {
    val service: TwitterService = new TwitterService
    val result: Future[List[String]] = Future { service.twitterFollowers().toList }
    result
  }
  
  
  val future: Future[List[String]] = queryFollowers;
  
  future onComplete {
    case Success(l) => println("Seguidores encontrados: " + l.mkString(", "))
    case Failure(ex) => println("Opps! \n\n" + ex.getLocalizedMessage())
  }
  
  Await.ready(future, 3 seconds)
}
