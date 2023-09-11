package mao.excel_to_sql_test.service;

import mao.excel_to_sql_test.entity.ExcelData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): ExcelService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/10
 * Time(创建时间)： 16:45
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface ExcelService
{
    /**
     * 从excel里加载数据，默认从in.xls或者in.xlsx里导入
     *
     * @return {@link List}<{@link Map}<{@link String}, {@link String}>>
     */
    ExcelData loadExcel() throws IOException;
}
