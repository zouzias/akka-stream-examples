package org.zouzias.akka.stream

import akka.actor.ActorSystem
import akka.stream.{FlowMaterializer}
import akka.stream.scaladsl._

import scala.concurrent.duration._

/**
 * Created by zouzias on 03.10.15.
 */
object MergePerson extends App{


  implicit val sys = ActorSystem()
  implicit val mat = FlowMaterializer()

  import sys.dispatcher

  final case class Person(name: String, age: Int)


  val graph = FlowGraph { implicit b =>
    import FlowGraphImplicits._

    val zip = ZipWith {
      Person.apply _
    }

    Source(List("Tassos", "Costas")) ~> zip.left
    Source(List(31, 33)) ~> zip.right
    zip.out ~> Sink.foreach[Person]{
    p => println("Name: " + p.name + " age " + p.age)
    }
  }

  // Run the flow graph above
  graph.run()

  // Block for ever?
  System.in.read()
  sys.shutdown()
  sys.awaitTermination(1 second)

}
