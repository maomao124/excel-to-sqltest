package mao.excel_to_sql_test.entity;

import java.util.StringJoiner;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.entity
 * Class(类名): Excel
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 17:26
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Excel
{
    private int sheetAt = 0;
    private int startRow = 0;
    private int endRow = -1;
    private int startCell = 0;
    private int endCell = -1;

    public int getSheetAt()
    {
        return sheetAt;
    }

    public Excel setSheetAt(int sheetAt)
    {
        this.sheetAt = sheetAt;
        return this;
    }

    public int getStartRow()
    {
        return startRow;
    }

    public Excel setStartRow(int startRow)
    {
        this.startRow = startRow;
        return this;
    }

    public int getEndRow()
    {
        return endRow;
    }

    public Excel setEndRow(int endRow)
    {
        this.endRow = endRow;
        return this;
    }

    public int getStartCell()
    {
        return startCell;
    }

    public Excel setStartCell(int startCell)
    {
        this.startCell = startCell;
        return this;
    }

    public int getEndCell()
    {
        return endCell;
    }

    public Excel setEndCell(int endCell)
    {
        this.endCell = endCell;
        return this;
    }

    @Override
    public String toString()
    {
        return new StringJoiner(", ", Excel.class.getSimpleName() + "[", "]")
                .add("sheetAt=" + sheetAt)
                .add("startRow=" + startRow)
                .add("endRow=" + endRow)
                .add("startCell=" + startCell)
                .add("endCell=" + endCell)
                .toString();
    }
}
