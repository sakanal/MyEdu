import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewCodeGenerator {
    public static void main(String[] args) {

        String host = "localhost";
        String port = "3306";
        //数据库名
        String database = "guli";
        String username = "root";
        String password = "123456";
        //作者
        String author = "sakanal";
        //服务目录
        String serviceDir = "D:\\Program Files (x86)\\Developer_tools\\IDEAworkspace\\guli_edu\\guli_edu_api\\service\\service_statistics";
        //包名
        String parent = "com.sakanal";
        //父模块名
        String moduleName = "statistics";
        //要去除的表前缀
        String tablePrefix = "statistics_";

        List<String> tables = new ArrayList<>();
        //数据库表名
        tables.add("statistics_daily");

        FastAutoGenerator.create("jdbc:mysql://" + host + ":" + port + "/" + database, username, password)
                .globalConfig(builder -> {
                    builder.author(author)//
                            .enableSwagger()
                            .outputDir(serviceDir + "\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent(parent)
                            .moduleName(moduleName)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, serviceDir + "\\src\\main\\resources\\mapper"))
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper");
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix(tablePrefix)

                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()

                            .controllerBuilder()
                            // 映射路径使用连字符格式，而不是驼峰
                            .enableHyphenStyle()
                            .formatFileName("%sController")
                            .enableRestStyle()

                            .mapperBuilder()
                            //生成通用的resultMap
                            .enableBaseResultMap()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
