package mao.excel_to_sql_test.config;

import mao.excel_to_sql_test.entity.Excel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.config
 * Class(类名): BaseConfigurationProperties
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 16:53
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Component
@ConfigurationProperties(prefix = "ets")
public class BaseConfigurationProperties
{
    /**
     * 输入的excel文件路径，包含文件
     */
    private String inputPath = "./in.xlsx";

    /**
     * 输出的sql文件路径，包含文件
     */
    private String outputPath = "./out.sql";

    /**
     * 模版日志
     */
    private String templateLogPath = "./template.log";

    /**
     * 是否生成文件后，还要将数据更新到mysql数据库里（执行sql语句）
     */
    private boolean isWriteToMysql = false;

    /**
     * sql语句每个sql之间\n的数量
     */
    private int wrapNum = 2;

    /**
     * 要去重的列
     */
    private String distinct = null;

    /**
     * 要排序的列，列为表头名称，升序为asc，降序为desc，按姓名降序：'姓名,desc'，按成绩升序：'成绩,asc'
     */
    private String orderBy = null;

    /**
     * 过滤器，如果不想要条件为xxx的行数据，则可以通过此字段过滤
     * 比如学生表中，不想要性别为男的行数据(只要性别为女的)，假设表头名称为'sex'，那么结构应该为
     * <pre>
     *     List<String> list = new ArrayList<>();
     *     list.add("男");
     *     filter.put("sex", list);
     * </pre>
     * <br>
     * 比如学生表中，不想要班级id为10001和10003的行数据，假设表头名称为'class_id'，那么结构应该为
     * <pre>
     *     List<String> list = new ArrayList<>();
     *     list.add("10001");
     *     list.add("10003");
     *     filter.put("class_id", list);
     * </pre>
     */
    private Map<String, List<String>> filter = new HashMap<>();

    /**
     * excel配置
     */
    private Excel excel = new Excel();


    public String getInputPath()
    {
        return inputPath;
    }

    public BaseConfigurationProperties setInputPath(String inputPath)
    {
        this.inputPath = inputPath;
        return this;
    }

    public String getOutputPath()
    {
        return outputPath;
    }

    public BaseConfigurationProperties setOutputPath(String outputPath)
    {
        this.outputPath = outputPath;
        return this;
    }

    public boolean isWriteToMysql()
    {
        return isWriteToMysql;
    }

    public BaseConfigurationProperties setWriteToMysql(boolean writeToMysql)
    {
        isWriteToMysql = writeToMysql;
        return this;
    }

    public Map<String, List<String>> getFilter()
    {
        return filter;
    }

    public BaseConfigurationProperties setFilter(Map<String, List<String>> filter)
    {
        this.filter = filter;
        return this;
    }

    public int getWrapNum()
    {
        return wrapNum;
    }

    public BaseConfigurationProperties setWrapNum(int wrapNum)
    {
        this.wrapNum = wrapNum;
        return this;
    }

    public String getTemplateLogPath()
    {
        return templateLogPath;
    }

    public BaseConfigurationProperties setTemplateLogPath(String templateLogPath)
    {
        this.templateLogPath = templateLogPath;
        return this;
    }

    public Excel getExcel()
    {
        return excel;
    }

    public BaseConfigurationProperties setExcel(Excel excel)
    {
        this.excel = excel;
        return this;
    }

    public String getDistinct()
    {
        return distinct;
    }

    public BaseConfigurationProperties setDistinct(String distinct)
    {
        this.distinct = distinct;
        return this;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public BaseConfigurationProperties setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
        return this;
    }
}

