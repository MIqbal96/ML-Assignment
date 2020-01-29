package APRIORI
import java.io._
import scala._
import scala.util.Random
import scala.io.Source
object main {
  def main(args:Array[String])
  {
    var file = new PrintWriter("/home/iqbal/"+new File("Myresult"+".txt" ))
     var s="...................k=1 itemsets.....................\n"
    println("Enter Number of Transactions:")
    var transactionsNum=scala.Console.readInt
    println("Enter Support (between 0 and 1):")
    var Support=transactionsNum*scala.Console.readDouble
    var alphabets=List.range(65,91).map(x=>x.toChar)
   var v= List.fill(transactionsNum)(List.fill(26)(scala.util.Random.nextInt(2 )))
   var transactions= v.map(x=>(x.zip(alphabets)).filter(_._1==1).map(_._2))
    var count=transactions.flatten.groupBy(identity).map(x=>(x._1,x._2.size)).toList
   println("count")
    count.foreach(println)
    
    println("1 Frequent ItemSet")
   var sorted=count.sortBy(_._1).filter(_._2>=Support).map(x=>x._1).sorted//.foreach(println)
   //var v1=List[List[Char]]()
   
   println("...................k=1 itemsets.....................")
   sorted.foreach(println)
   s=s+sorted.mkString+"\n...................k=2 itemsets.....................\n"
 // var v1=sorted.map(x=>sorted.takeRight(sorted.length-(sorted.indexOf(x)+1)).map(y=>x:::y)).flatten
  // sorted.map(x=>sorted.takeRight(n)) 
   var tt=sorted.combinations(2).toList.map(_.sorted)//.toList.flatten
    //println("v")
 //tt.foreach(println)
   var v1=tt.map(x=>transactions.map(y=>if(x.intersect(y)==x) 1 else 0)).map(x=>x.count(_==1)).zip(tt).filter(_._1>=Support).map(_._2)
  println("...................k=2 itemsets.....................")
 // var t=v1.flatten
  v1.foreach(println)
  s=s+v1.mkString+"\n...................k=3 itemsets.....................\n"
   tt= v1.map(x=>v1.dropWhile(_==x).map(y=>if(x(x.length-1)==y(1))(x:::y).distinct.sorted else x )).map(x=>x.filter(_.length==3)).flatten
  v1=tt.map(x=>transactions.map(y=>if(x.intersect(y)==x) 1 else 0)).map(x=>x.count(_==1)).zip(tt).filter(_._1>=Support).map(_._2)
  println("...................k=3 itemsets.....................")
   v1.foreach(println)
   s=s+v1.mkString+"\n...................k=4 itemsets.....................\n"
  var i=2
  var k=4
  while(v1.length>1)
  {
  tt=v1.map(x=>v1.dropWhile(_==x).map(y=>if(x.take(i)==y.take(i)) (x:::y).distinct.sorted else x)).map(x=>x.filter(_.length==k)).flatten  
    k=k+1
    i=i+1
    s=s+v1.mkString+"\n...................k="+(k-1)+" itemsets.....................\n"
    v1=tt.map(x=>transactions.map(y=>if(x.intersect(y)==x) 1 else 0)).map(x=>x.count(_==1)).zip(tt).filter(_._1>=Support).map(_._2)
  println("...................k="+(k-1)+" itemsets.....................")
   v1.foreach(println)
  }
     file.write(s)
     file.close
   
}
}
