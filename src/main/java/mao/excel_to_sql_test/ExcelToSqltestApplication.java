package mao.excel_to_sql_test;

import mao.excel_to_sql_test.service.RunService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExcelToSqltestApplication
{

    public static void main(String[] args) throws Exception
    {
        ConfigurableApplicationContext context = SpringApplication.run(ExcelToSqltestApplication.class, args);
        RunService runService = context.getBean(RunService.class);
        runService.run();
    }

}
