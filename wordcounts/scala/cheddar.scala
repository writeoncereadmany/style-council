import scala.io.Source

object words2 {
  val lowerCaseWords: List[String] =
    Source.fromFile("moby_dick_no_punctuation.txt")
      .getLines
      .flatMap(_.split(' '))
      .filter(_ != "")
      .map(_.toLowerCase)
      .toList

  def incrementCountForExistingWordOrAddNewCount(countsSoFar: Map[String,Int], word: String ): Map[String,Int] =
    countsSoFar
      .get(word)
      .map(count => (countsSoFar - word) + (word -> (count+1)))
      .getOrElse(countsSoFar + (word -> 1))

  val countOfWord: ((String,Int),(String,Int)) => Boolean
    = {case ((_,i),(_,j)) => i > j}

  val result = lowerCaseWords
              .foldLeft(Map(): Map[String,Int])(incrementCountForExistingWordOrAddNewCount)
              .toList
              .sortWith(countOfWord)

  result.take(5).foreach(println)

}
