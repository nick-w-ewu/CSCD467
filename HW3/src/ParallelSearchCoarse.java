import java.io.FileNotFoundException;

public class ParallelSearchCoarse
{
    public static void main(String args[]) throws InterruptedException
    {
        if (args.length < 2)
        {
            System.out.println("Usage: Java ParallelSearchCoarse FileName Pattern");
            System.exit(0);
        }

        String fname = args[0];         // fileName = files/wikipedia2text-extracted.txt
        String pattern = args[1];       // pattern = "(John) (.+?) ";
//        String fname = "files/testfile2.txt";
//        String pattern = "(John) (.+?) ";
        long start = System.currentTimeMillis();

        // Create your thread reader and searcher here
        SharedQueue q = new SharedQueue();
        Reader r = null;
        try
        {
            r = new Reader(q, fname);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Invalid file name");
            System.exit(0);
        }
        Thread reader = new Thread(r);
        Searcher searcher = new Searcher(q, pattern);

        reader.start();
        searcher.start();

        reader.join();
        searcher.join();

        long end = System.currentTimeMillis();
        System.out.println("Total number of lines searched is " + (r.getCount()-1));
        System.out.printf("Total occurance of patter '%s' is %d\n", pattern, searcher.getCount());
        System.out.println("Time cost for concurrent solution is " + (end - start) + "ms");
    }
}
