package hbase.org.custom.spark.sql.hbase

/**
  * @author piter.yu
  * @date 2020/10/26 16:11
  * @Description
  */
object constans {
  val hbase_zookeeper_quorum = "qfwa0600:2181,qfwa0601:2181,qfwa0603:2181"
  val sparksql_table_schema="sparksql_table_schema"
  val hbase_table_name="hbase_table_name"
  val hbase_table_schema="hbase_table_schema"

  //spark sql schema
  //val order_sparksql_table_schema="(id String, create_time String ,pay_all String ,open_lng String , open_lat String , begin_address_code String , charge_mileage String , city_name String , vehicle_license String , driver_id String ,driver_name String, cancel String , close_gps_time String)"
  val order_sparksql_table_schema="(name String)"
  //匹配同步表(需要查的hbase表)
  val syn_table_order_info="test1"

  //hbase schema
 // val order_hbase_table_schema="(MM:id , MM:create_time ,MM:pay_all, MM:open_lng , MM:open_lat , MM:begin_address_code , MM:charge_mileage , MM:city_name  , MM:vehicle_license , MM:driver_id ,MM:driver_name, MM:cancel , MM:close_gps_time)"
  val order_hbase_table_schema="(f1:name )"

}
