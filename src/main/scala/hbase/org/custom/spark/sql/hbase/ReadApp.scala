package hbase.org.custom.spark.sql.hbase


import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import utils.{GlobalConfigUtils, KerberosAuthenticator}

/**
  * Created by angel
  */
object ReadApp {
  def main(args: Array[String]): Unit = {

    KerberosAuthenticator.authentication("kerberos", "D:/tools/krb5.conf", "hive@GTMC.COM", "D:/tools/etl.keytab")
    val sparkConf = SparkEngine.getSparkConf()
    val session = SparkEngine.getSparkSession(sparkConf)
    val sqlContext = session.sqlContext
    /*val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)*/
    //查询hbase
    val load: DataFrame = session.read
      .format("hbase.org.custom.spark.sql.hbase")
      .options(Map(
        constans.sparksql_table_schema ->constans.order_sparksql_table_schema,
        constans.hbase_table_name -> constans.syn_table_order_info,
        constans.hbase_table_schema -> constans.order_hbase_table_schema,
        "hbase.table.rowkey"->"1->4"
      )).load()
    val test1 = load.registerTempTable("test1")

    val rows: DataFrame = sqlContext.sql("select * from test1")
    rows.show()
   // load.foreach(println(_))

/*//写入hbase
    load.write.format(GlobalConfigUtils.getProp("custom.hbase.path"))
      .options(Map(
        "hbase.table.name" -> "test22" ,
        "hbase.table.rowkey" -> "id" ,
        "hbase.table.columnFamily" -> "MM" ,
        "hbase.table.regionNum" -> s"${1}" ,
        "hbase.engable.bulkload" -> "true"
      )).save()*/
  }
}
