package pl.dskrzyniarz;

public class CubicFunction implements Polynomial{
    private final int a;
    private final int b;
    private final int c;
    private final int d;


    public CubicFunction(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int calculate(int x){
        int result = 0;
        result += a*x*x*x;
        result += b*x*x;
        result += c*x;
        result += d;
        return  result;
    }


}
