package org.zouzias.akka.stream

import akka.actor.ActorSystem
import akka.stream.{FlowMaterializer}
import akka.stream.scaladsl.{Sink, Source}
import concurrent.duration._
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
 * Created by zouzias on 03.10.15.
 */
object HelloWorld extends App {

  implicit val sys = ActorSystem()
  implicit val mat = FlowMaterializer()

  import sys.dispatcher

  // Define a pipeline from Source to Sink
  val pipeline : Future[String] = Source("Hello world".toList)
  .map(c => c.toUpper)
  .concat(Source("!!!"))
  .runWith(Sink.fold(""){
    case (acc, c) =>{
      acc + c
    }
  })

  // On completion
  pipeline.onComplete{
    case Success(text) =>
      println(text)
      sys.shutdown()
      sys.awaitTermination(1 second)
    case Failure(text) =>
      sys.shutdown()
  }

}
