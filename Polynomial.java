public class Polynomial{
    double[] coefficients;
    
    public Polynomial(){
        this.coefficients= new double[1];
        coefficients[0] = 0;
        
    }
    
    public Polynomial(double[] coefficients) {
        
        this.coefficients = new double[coefficients.length];

        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }
    
    public Polynomial add(Polynomial second) {
        
        
        int max_len;
        
        if (this.coefficients.length >= second.coefficients.length) {
            max_len = this.coefficients.length;
        }
        
        else {
            max_len = second.coefficients.length;
     	}
        
        
        double[] result = new double[max_len];
            
        for (int i = 0; i < second.coefficients.length; i++) {
                result[i] = second.coefficients[i];
        }
            
        for (int i = 0; i < this.coefficients.length; i++) {
                result[i] += this.coefficients[i];
        }
        
        return new Polynomial(result);
    }
    
    public double evaluate(double x) {
        
        double result = 0;
        double power = 1;
        
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * power;
            power *= x;
        }
        
        return result;
    }
    
    public boolean hasRoot(double x) {
        
        if (evaluate(x) == 0)
        {
            return true;
        }
        
        return false;
    }
            
}
