class Function {
    private int num;

    Function(int num){
        this.num = num;
    }

    double y(double x){
        if(num == 1){
            return x*x*x+x*x+x+1;
        }else if(num == 2){
            return Math.log(x) + x;
        }else if(num == 3){
            return Math.sin(x)+x;
        }else{
            return 1/(x*x);
        }
    }

}
