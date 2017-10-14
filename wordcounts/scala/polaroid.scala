import scala.io.Source

object Words {

  def count: Unit = {

    val lowerCaseWords: List[String] =
          Source.fromFile("/home/bruce/scratch/moby_dick_no_punctuation.txt")
          .getLines
          .flatMap(_.split(' '))
          .filter(_ != "")
          .map(_.toLowerCase)
          .toList

    val wordsGrouped: Map[String, List[String]] =
          lowerCaseWords.groupBy(identity)

    val wordsAndTheirCounts: List[(String, Int)] =
          wordsGrouped
          .map({case (word, elementForEachOccurence) => (word, elementForEachOccurence.length)})
          .toList

    val sortedForOutput: List[(String,Int)] =
          wordsAndTheirCounts
          .sortBy(_._2)
          .reverse

     sortedForOutput.take(5).foreach(println)
  }

}

