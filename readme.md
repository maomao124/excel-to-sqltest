# excel to sql text

##  介绍




本程序用于将Excel文件里的数据转换成sql脚本，并选择执行!
通过自定义模板来生成想要的sql语句

提示：

1. 模板使用freemacker模板引擎实现，变量使用'${表头列名称}'格式来替换
2. freemacker支持加减乘除运算、条件语句、类型转换等，使用方法自行百度
3. 如果表头包含空格，写法'${.vars['CPU Temp']'
4. 数值类型会有逗号，可以使用类型转换，示例：'${(_index?number*2+100000)?c}'
5. 程序包含内置参数'_index'，可以直接使用，从0开始
6. excel的日期类型统一转换成时间戳，想要格式化的时间，可以写成'${Time?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}'
7. 对于excel中性别字段为男或女，但是数据库为0或1的，可以使用if else解决，示例：'insert into student values(${年龄},<#if 性别=='男'>0</#if><#if 性别=='女'>1</#if>)'
8. 数据库配置位于 druid.properties 文件里
9. 如果直接想要使用默认的模板，可以直接按回车即可，使用默认模板时，excel里的表头列名称要满足数据库规范
10. 程序通过配置属性类读取配置，配置文件示例如下，可以通过修改application.yml来修改配置，也可以通过参数传递来让配置临时生效






## 配置示例

配置位于`application.yml`文件

```yaml
ets:
  inputPath: './in5.xlsx'    # excel文件的路径
  outputPath: './out.sql'    # sql文件的输出路径
  isWriteToMysql: false      # 是否直接执行输出的sql文件
  wrapNum: 1                 # sql文件两条sql之间换行的数量，也就是\n的个数
  distinct: ''               # 要去重的字段
  orderBy: 'FPS,desc'        # 要排序的列，列为表头名称，升序为asc，降序为desc，按姓名降序：'姓名,desc'，按成绩升序：'成绩,asc'
  excel:
    sheetAt: 0               # 读取sheet的索引，从0开始，第一个sheet就是0，第二个sheet就是1
    startRow: 0              # 开始行，选择要从第几行开始读，从0开始，开始行必须为表头，从第4行开始就是3
    endRow: -1               # 结束行，值为-1默认读到最后一行
    startCell: 0             # 开始列，默认从第一列开始读
    endCell: -1              # 结束列，值为-1默认读到最后一列
  filter:                    # 过滤器，过滤掉满足以下条件的行，比如过滤掉‘所属班级’这一列中值为100001的行、过滤‘编码’字段值为‘Noinfo’、‘NotOnly’或者‘N000004-446-1151’的行
    "[编码]": ['Noinfo','NotOnly','N000004-446-1151']
    "[所属班级]": ['100001']
    "[联系电话]": ['']        # 过滤联系电话为空的行
```


## 使用

使用maven打包项目，生成jar包：
```shell
mvn package 
```

可选择跳过测试


生成的文件位于target目录


运行jar包：
```shell
java -jar xxx.jar
```

