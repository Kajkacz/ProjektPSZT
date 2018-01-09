package minmax;
import java.util.*;

public class MinMax
{
    public static Stan minmax(Stan aktualny, int tree)
    {
        /*Stan[] s = aktualny.stanyPotomne();
        Random generator = new Random();
        int x = generator.nextInt(s.length);
        return s[x];*/
        return alfabeta(aktualny, tree, Integer.MIN_VALUE, Integer.MAX_VALUE).stan;
    }
    private static MinMax.pair alfabeta(Stan s, int d, int alfa, int beta)
    {
        Stan next = s;
        if (s.finalState() || d == 0) return MinMax.pair(s.funkcjaPrzystosowania(), s);
        if (s.player())
        {
            Stan[] sp = s.stanyPotomne();
            for (Stan i : sp)
            {
                MinMax.pair result = alfabeta(i, d - 1, alfa, beta);
                if (result.value > alfa)
                {
                    alfa = result.value;
                    next = result.stan;
                }
                if (alfa >= beta)
                {
                    return MinMax.pair(beta, next);
                }
            }
            return MinMax.pair(alfa, next);
        }
        else
        {
            Stan[] sp = s.stanyPotomne();
            for (Stan i : sp)
            {
                MinMax.pair result = alfabeta(i, d - 1, alfa, beta);
                if (result.value < beta)
                {
                    beta = result.value;
                    next = result.stan;
                }
                if (alfa >= beta)
                {
                    return MinMax.pair(alfa, next);
                }
            }
            return MinMax.pair(beta, next);
        }
    }
    private class pair
    {
        public int value;
        public Stan stan;
        public pair(int v, Stan s) {value = v; stan = s;}
    }
}
