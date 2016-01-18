public class Alternation
{

    final ConditionalLock lock = new ConditionalLock();
    Thread t1;
    Thread t2;

    public Alternation()
    {

        t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(lock.getMessageNum() < 50)
                {
                    while (!lock.isTurn())
                    {
                        ;  //guarded block
                    }
                    System.out.printf("Message %d from Thread T1\n", lock.getMessageNum());
                    lock.incrementMessageNum();
                    lock.setTurn(false);
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                    }
                }//end of for
            }
        });
        t2 = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while(lock.getMessageNum() < 50)
                {
                    while (lock.isTurn())
                    {
                        ;   //guarded block
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                    }
                    System.out.printf("Message %d from Thread T2\n", lock.getMessageNum());
                    lock.incrementMessageNum();
                    lock.setTurn(true);
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


