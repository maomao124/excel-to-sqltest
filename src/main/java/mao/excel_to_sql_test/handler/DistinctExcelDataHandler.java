package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;
import org.springframework.stereotype.Component;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): DistinctExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 13:28
 * Version(版本): 1.0
 * Description(描述)： 字段去重处理器
 */

@Component
public class DistinctExcelDataHandler implements ExcelDataHandler
{

    @Override
    public int getOrder()
    {
        return 1;
    }

    @Override
    public String getName()
    {
        return "字段去重处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {

    }
}
