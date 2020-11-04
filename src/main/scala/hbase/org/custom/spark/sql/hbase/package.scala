package hbase.org.custom.spark.sql

/**
  * @author piter.yu
  * @date 2020/10/26 14:55
  * @Description
  */
package object hbase {
  //类型的封装(spark和hbase)
  abstract  class  SchemaField extends  Serializable
  //spark的schema封装
  case class  RegisterSchemaField(fieldName:String,fieldType:String) extends SchemaField with  Serializable
  //hbase的schema封装
  case class  HbaseSchemaField(fieldName:String,fieldType:String) extends SchemaField with  Serializable

}
