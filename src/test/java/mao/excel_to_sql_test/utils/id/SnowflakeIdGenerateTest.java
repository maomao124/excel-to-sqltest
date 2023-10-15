package mao.excel_to_sql_test.utils.id;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class SnowflakeIdGenerateTest
{
    /**
     * Method under test: {@link SnowflakeIdGenerate#generate()}
     */
    @Test
    void testGenerate()
    {
        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1L);
        System.out.println(snowflakeIdGenerate.generate());
    }

    /**
     * Method under test: {@link SnowflakeIdGenerate#generate()}
     */
    @Test
    void testGenerate2()
    {
        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1L);
        for (int i = 0; i < 50000; i++)
        {
            System.out.println(snowflakeIdGenerate.generate());
        }
    }

    /**
     * Method under test: {@link SnowflakeIdGenerate#generate()}
     */
    @Test
    void testGenerate3() throws InterruptedException
    {
        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1L);
        CountDownLatch countDownLatch=new CountDownLatch(64);
        for (int i = 0; i < 64; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int i = 0; i < 500000; i++)
                    {
                        //System.out.println(snowflakeIdGenerate.generate());
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }
}

