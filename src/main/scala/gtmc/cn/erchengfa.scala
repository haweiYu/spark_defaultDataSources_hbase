package gtmc.cn

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object erchengfa {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("zuixiaoercheng")
    val sc = new SparkContext(conf)
    val sqc = new SQLContext(sc)
    val data = sc.textFile("E:\\tools\\学习资料\\spark\\spark03\\Spark第四天课前资料\\ml\\lritem.txt")
    val parseData=data.map(x => {
      val part = x.split("\\|")
      val features = part(1).split(" ")
      (part(0).toDouble,features(0).toDouble,features(1).toDouble)
    }
    )
    //--转成DF
    val df=sqc.createDataFrame(parseData)
    //定义列名
    val dataFrame: DataFrame = df.toDF("Y","X1","X2")
    dataFrame.foreach(print(_))
    //--定义features(自变量) 字段名
    val featureColum=Array("X1","X2")
    //--定义VectorAssemmbler格式，这是MLlib线性回归模型要求的数据形式
    //--此外，要指定特征向量是哪几列，以及定义特征向量对应的别称，一般用:features"
    val assembler=new VectorAssembler().setInputCols(featureColum).setOutputCol("features")

    //--将DF转成VectorAssemmbler格式，后面建模会用到
    val vecDF=assembler.transform(dataFrame)
    //--建模，设定 自变量(features)和 因变量(Y)。
    //--setFitIntercept(true) 表示是否需要计算截距项
    //--fit(数据)是训练生成模型
    val model=new LinearRegression().setFeaturesCol("features").setLabelCol("Y")
      .setFitIntercept(true).fit(vecDF)

    //--transform(数据) 预测数据，测试的数据的格式必须为:VectorAssemmbler
    val predictionTable=model.transform(vecDF)
    val result=predictionTable.selectExpr("features","Y","round(prediction,1)as prediction")

   // result.foreach { x => println(x) }
    val testData=assembler.transform(sqc.createDataFrame(sc.makeRDD(List((0.0,8,35)))).toDF("Y","X1","X2"))

    val testPrediction=model.transform(testData).selectExpr("features","Y","round(prediction,1)as prediction")

    testPrediction.foreach { x => println(x) }

    println(model.summary.r2)

    //parseData.foreach(print(_))
  }
}
