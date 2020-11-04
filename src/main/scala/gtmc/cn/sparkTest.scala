package gtmc.cn

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object sparkTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sum").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val sparkConf = new SparkConf().setAppName("StatefulNetworkWordCount").setMaster("local")
    // Create the context with a 1 second batch size
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    ssc.checkpoint(".")

    // Initial RDD input to updateStateByKey
    val initialRDD = ssc.sparkContext.parallelize(List(("hello", 1), ("world", 1)))

  }
}
