package juanco.scala.reactivex

import java.util.concurrent.Executors

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import rx.lang.scala.Observable
import rx.lang.scala.Subscription

object ReaderObservable {
  
  implicit val executor = Executors.newFixedThreadPool(1000);

  
  def main(args: Array[String]): Unit = {
    
    val url = "http://reactivex.io/rxscala/scaladoc/index.html"
    
    /* Observable 1 */
    
    val webObservable = Observable.from(webPageContent(url).toList)
   
    webObservable.subscribe { 
      println(_) 
    }
    
    
    /* Observable 2 */
    
    val fileObservable = createFileObservable()
    
    val subscription = fileObservable.subscribe ( println(_), t => println("[*] Ocurrió un error en la lectura"), () => println("[*] Lectura del archivo terminada") )
    
    Thread.sleep(800)
    
    subscription.unsubscribe()
    
    
    
    
    print("Presione una tecla para terminar...")
    readLine()
  }
  
  
  def createFileObservable(): Observable[String] = Observable.create { o => 
    val subs = Subscription()
    
    val iterator = fileContent("large-file.txt")
    
    Future {
      try {
        while ( iterator.hasNext && !subs.isUnsubscribed ) {
          val line = iterator.next()
          if(line contains "error" ) o.onError(new IllegalArgumentException)
          else o.onNext(line)
          
          Thread.sleep(200) // este proceso lleva mucho tiempo
        }
      } finally { o.onCompleted() }
    }
    
    subs
  }
  
  def webPageContent(url: String): Iterator[String] = io.Source.fromURL(url).getLines()
  
  def fileContent(name: String): Iterator[String] = io.Source.fromFile(name).getLines()
  
}
