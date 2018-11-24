package org.hut.user.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hutwanghui on 2018/11/24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
public class MyGenerator {
    public static void main(String[] args) {
        String outputDir = "f://tmp";
        final String viewOutputDir = outputDir + "/view/";
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor("hutwanghui");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("dubbo");
        dsc.setPassword("dubbo123");
        dsc.setUrl("jdbc:mysql://localhost:3306/whdubbo?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setSuperControllerClass("org.hut.common.web.BaseController");
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.hut.user");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        // 生成的模版路径，不存在时需要先新建
        File viewDir = new File(viewOutputDir);
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
//        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
//        focList.add(new FileOutConfig("/templates/listvue.vue.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return getGeneratorViewPath(viewOutputDir, tableInfo, ".vue");
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //生成controller相关
        mpg.execute();
    }
}
