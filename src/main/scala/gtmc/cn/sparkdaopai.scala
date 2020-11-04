package gtmc.cn

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sparkdaopai {
  def main(args: Array[String]): Unit = {
    //System.setProperty("hadoop.home.dir","C:\hadoop\")
    val conf = new SparkConf().setAppName("test").setMaster("local[1]")
    val sc =new SparkContext(conf);
    var value: RDD[String] = sc.textFile("D:\\tools\\1.txt")
    value.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(print(_))
   // value.map(line =>{ line.split(" ") }).foreach(print(_))
    /*var r1=value.map { case(filpath, filetext) =>
          var filename=filpath.split("/").last
      (filename,filetext)
    }
    r1.foreach(print(_))*/
    /*
    val r2=r1.flatMap{case(fileName,fileText)=>
      fileText.split("\r\n").flatMap { line => line.split(" ")}
        .map { word =>(word,fileName) }
    }
    val r3=r2.groupByKey.map { case(word,buffer) =>(word,buffer.toList.distinct.mkString(",")) }
    r3.foreach{println}
     */
  }
}
