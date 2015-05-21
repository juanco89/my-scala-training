package juanco.scala

import rx.lang.scala.subjects.PublishSubject
import rx.lang.scala.Observable


object ReaderObservable {

  def main(args: Array[String]): Unit = {
    
    val url = "http://reactivex.io/rxscala/scaladoc/index.html"
    
    /* With Observable */
      
    val webObservable = Observable.from(webPageContent(url).toList)
    
    webObservable.subscribe { 
      println(_) 
    }   
    
    /* With subject */
    
    val subject = PublishSubject[String]()
    
    subject.subscribe (
      println(_),
      t => println("Ocurrió un error leyendo el archivo"), 
      () => println("Se completó la lectura del archivo") 
    )
    
    try {
      for(l <- fileContent("large-file.txt")) {
        if(l contains "error" ) subject.onError(new IllegalArgumentException)
        else subject.onNext(l)
        
        Thread.sleep(200) // un proceso largo
      }
    } finally {
      subject.onCompleted()
    }
    
    
    
    print("Presione una tecla para terminar...")
    readLine()
  }
  
  
  def webPageContent(url: String): Iterator[String] = io.Source.fromURL(url).getLines()
  
  def fileContent(name: String): Iterator[String] = io.Source.fromFile(name).getLines()
  
}
