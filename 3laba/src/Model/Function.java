package Model;

public class Function {
    private int num;

    public int getNum() {
        return num;
    }

    public Function(int num) {
        this.num = num;
    }

    public double F(double x){
        if(num == 1){
            return x*x*x - x + 4;
        }else if(num == 2){
            return x*x + Math.sin((x-1));
        }else if(num==3){
            return Math.exp(x)-x-2;
        }else{
            return x-4;
        }
    }




    @Override
    public String toString() {
        if(num == 1){
            return "Y = x^3 - x + 4";
        }else if(num == 2){
            return "Y = x^2 + sin((x-1))";
        }else if(num==3){
            return "Y = e^x-x-2";
        }else{
            return "Y = x - 4";
        }
    }

    double D2F(double x) {
        if(num == 1){
            return 6*x;
        }else if(num == 2){
            return -Math.sin((x-1));
        }else if(num==3){
            return Math.exp(x);
        }else{
            return 0;
        }
    }
}
