package juanco.scala.reactivex

import rx.lang.scala.subjects.PublishSubject

object ReaderSubject {

  def main(args: Array[String]): Unit = {
    
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
  
  def fileContent(name: String): Iterator[String] = io.Source.fromFile(name).getLines()
  
}
