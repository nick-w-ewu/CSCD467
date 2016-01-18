/**
 * Created by nicho on 1/17/2016.
 */
public class ConditionalLock
{
    private boolean turn = true;
    private int messageNum = 1;

    public synchronized boolean isTurn()
    {
        return turn;
    }

    public synchronized void setTurn(boolean t)
    {
        this.turn = t;
    }

    public synchronized int getMessageNum()
    {
        return this.messageNum;
    }

    public synchronized void incrementMessageNum()
    {
        this.messageNum++;
    }
}
