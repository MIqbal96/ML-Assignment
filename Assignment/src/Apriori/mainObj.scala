package Apriori
import java.io._
import scala.io.Source
object mainObj {
  def main(args:Array[String])
  {
    val f = new PrintWriter("/home/Iqbal/"+new File("output"+".txt" ))
  println("Enter Number of Transactions:")
    var transactionsNum=scala.Console.readInt
    println("Enter Support (between 0 and 1):")
    var Support=transactionsNum*scala.Console.readDouble
    var alphabets=List.range(65,91).map(x=>x.toChar)
    var v= List.fill(transactionsNum)(List.fill(26)(0+ scala.util.Random.nextInt( (1- 0) + 1 )))
    var transactions= v.map(x=>(x.zip(alphabets)).filter(_._1==1).map(_._2))
     var count=transactions.flatten.groupBy(identity).map(x=>(x._1,x._2.size)).toList
     println("count of each element:")
    count.foreach(println)
    println("1 Frequent ItemSet with count:")
   var sorted=count.sortBy(_._1).filter(_._2>=Support).mkString
   f.write(sorted)
   f.close//.foreach(println)
   
}
}