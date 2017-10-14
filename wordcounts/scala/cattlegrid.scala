package mobydick

import scala.io.Source

object MobyDickScala {

  def main(args: Array[String]) {
    val filename = args(0)
    val text = readTextFile(filename)

    wordCounts(text).foreach(wordAndCount =>
      println(wordAndCount._1 + ": " + wordAndCount._2)
    )
  }

  private def readTextFile(filename: String) = Source.fromFile(filename).getLines.mkString

  def wordCounts(text: String): List[(String,Int)] = {
    val delimiters = Array(' ', '\n')
    val words = text
      .split(delimiters)
      .map(_.toLowerCase())
    words
      .groupBy(word => word)
      .mapValues(_.length)
      .toList
      .sortBy(_._2).reverse
  }
}
