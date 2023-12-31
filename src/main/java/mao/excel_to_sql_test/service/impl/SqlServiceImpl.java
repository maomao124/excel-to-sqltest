package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
import mao.excel_to_sql_test.handler.SqlDataHandler;
import mao.excel_to_sql_test.service.ExcelService;
import mao.excel_to_sql_test.service.SqlService;
import mao.excel_to_sql_test.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.IOException;
import java.util.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl.impl
 * Class(类名): SqlServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/11
 * Time(创建时间)： 10:18
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class SqlServiceImpl implements SqlService
{
    private static final Logger log = LoggerFactory.getLogger(SqlServiceImpl.class);

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ExcelService excelService;

    @Autowired(required = false)
    private List<SqlDataHandler> sqlDataHandlerList;

    @Override
    public List<String> excelToSql(String template, ExcelData excelData) throws Exception
    {
        List<String> sqlList = new ArrayList<>();
        log.debug("模板：" + template);
        List<String> titles = excelData.getTitles();
        List<Map<String, String>> content = excelData.getContent();
        /*content.forEach(rowMap ->
        {
            String result = templateService.parseTemplate(template, rowMap);
            sqlList.add(result);
            log.debug("记录：" + result);
        });*/
        int index = 1;
        for (Map<String, String> rowMap : content)
        {
            rowMap.put("_index", String.valueOf(index));
            String result = templateService.parseTemplate(template, rowMap);
            sqlList.add(result);
            log.debug("记录：" + result);
            index++;
        }
        return executeSqlDataHandler(template, excelData, sqlList);
    }

    @Override
    public List<String> excelToSql(String template) throws Exception
    {
        ExcelData excelData = excelService.loadExcel();
        return excelToSql(template, excelData);
    }

    /**
     * 执行sql数据处理器
     *
     * @param template  模板
     * @param excelData excel数据
     * @param sqlList   sql语句列表
     * @return {@link List}<{@link String}>
     */
    private List<String> executeSqlDataHandler(String template, ExcelData excelData, List<String> sqlList)
    {
        if (sqlDataHandlerList != null && sqlDataHandlerList.size() > 0)
        {
            sqlDataHandlerList.sort(Comparator.comparingInt(SqlDataHandler::getOrder));
            StringBuilder str = new StringBuilder("sql数据处理器执行顺序：");
            for (SqlDataHandler sqlDataHandler : sqlDataHandlerList)
            {
                if (!sqlDataHandler.enabled())
                {
                    continue;
                }
                str.append("-->").append(sqlDataHandler.getName());
            }
            log.info(str.toString());
            for (SqlDataHandler sqlDataHandler : sqlDataHandlerList)
            {
                if (!sqlDataHandler.enabled())
                {
                    log.info("sql数据处理器\"" + sqlDataHandler.getName() + "\"已关闭");
                    continue;
                }
                log.info("开始进入\"" + sqlDataHandler.getName() + "\"");
                sqlDataHandler.handler(excelData, template, sqlList);
                log.info("退出\"" + sqlDataHandler.getName() + "\"");
            }
        }
        return sqlList;
    }
}
