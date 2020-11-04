package utils;



import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * Kerberos认证工具类
 */
public class KerberosAuthenticator {

    public static void authentication(String authenticationType, String krb5FilePath, String principal, String keytab) {

        // 判断认证类型，如果不需要认证直接返回
        if (authenticationType != null && "kerberos".equalsIgnoreCase(authenticationType.trim())) {
            System.out.println("开始设置kerberos身份验证.");
        } else {
            System.out.println("未设置kerberos身份验证.");
            return;
        }

        // 设置认证服务器连接配置文件
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            // 默认：这里不设置的话，win默认会到 C盘下读取krb5.init
            System.setProperty("java.security.krb5.conf", krb5FilePath);
            System.out.println(krb5FilePath);
        } else {
            // linux 会默认到 /etc/krb5.conf
            // 中读取krb5.conf,本文笔者已将该文件放到/etc/目录下，因而这里便不用再设置了
            System.setProperty("java.security.krb5.conf", krb5FilePath);
            System.out.println(krb5FilePath);
        }

        // 使用Hadoop安全登录
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("hadoop.security.authentication", authenticationType);
        conf.addResource(new Path("hdfs-site.xml"));
        conf.addResource(new Path("core-site.xml"));
        conf.addResource(new Path("hbase-site.xml"));
//        conf.addResource(new Path("hive-site.xml"));
        try {
            // 用户认证
            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab(principal, keytab);
            System.out.println(
                    "Kerberos身份认证完成, krb5FilePath：" + krb5FilePath + ", principal:" + principal + ", keytab:" + keytab);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
