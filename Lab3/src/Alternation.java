public class Alternation
{

    final ConditionalLock monitor = new ConditionalLock();
    Thread t1;
    Thread t2;

    public Alternation()
    {
        t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(monitor.getMessageNum() < 50)
                {
                    while (!monitor.isTurn())
                    {
                          //guarded block
                    }

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                    }
                    System.out.printf("Message %d from Thread T1\n", monitor.getMessageNum());
                    monitor.incrementMessageNum();
                    monitor.setTurn(false);
                }//end of for
            }
        });

        t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(monitor.getMessageNum() < 50)
                {
                    while (monitor.isTurn())
                    {
                           //guarded block
                    }
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                    }
                    System.out.printf("Message %d from Thread T2\n", monitor.getMessageNum());
                    monitor.incrementMessageNum();
                    monitor.setTurn(true);
                }
            }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args)
    {
        new Alternation();
    }
}


