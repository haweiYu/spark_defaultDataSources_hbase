package gtmc.cn

import org.apache.spark.ml.linalg.Vectors

object testML {
  def main(args: Array[String]): Unit = {
    /*val vd=Vectors.dense(2,0,6)//
    println(vd)*/
    val vs=Vectors.sparse(7,Array(0,1,3,6),Array(9,5,2,7))
    println(vs(5))
  }
}
