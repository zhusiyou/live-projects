import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
/**
 * @author: zhudawei
 * @date: 2023/12/27
 * @description:
 */
public class MybatisGenerator {
    public static void main(String[] args) {

        DataSourceConfig dsc = new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/icode", "root", "").build();
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dsc);

        String[] tableNames = new String[]{
                "sys_permission","sys_role_permission"
        };
        String moduleName = "system";
        String projectPath = "D:\\developer\\live-projects\\backend\\code";

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig
                .Builder()
                .outputDir(projectPath + "/" + moduleName + "/src/main/java")
                .author("zhudawei")
                .openDir(false)
                .build();

        // 包配置
        PackageConfig packageConfig = new PackageConfig
                .Builder()
                .entity("model")
                .mapper("dao")
//                .xml("dao.xml")
//                .controller("controller")
                .parent("live.betterman")
                .moduleName(moduleName)
                .build();

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig
                .Builder()
                //.service(null,null)
//                .mapperXml(null)
                .controller(null)
                .build();

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig
                .Builder()
                //.addInclude("FLW_FORM")
                .addInclude(tableNames)
//                .likeTable(new LikeTable("NH_"))
                .entityBuilder().enableLombok().naming(NamingStrategy.underline_to_camel)
//                .superClass(BaseExtEntity.class)
                .serviceBuilder().formatServiceFileName("%sService")
                .build();

        mpg.global(globalConfig);
        mpg.packageInfo(packageConfig);
        mpg.template(templateConfig);
        mpg.strategy(strategyConfig);
        mpg.execute(new FreemarkerTemplateEngine());
    }
}
