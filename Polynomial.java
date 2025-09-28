import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
    double[] coefficients;
	int[] exponents;
    
    public Polynomial(){
        this.coefficients = new double[1];
		this.exponents = new int[1];
        coefficients[0] = 0;
		exponents[0] = 0;
        
    }
    
    public Polynomial(double[] coefficients, int[] exponents) {
        
        this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];

        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
		
		for (int i = 0; i < exponents.length; i++) {
            this.exponents[i] = exponents[i];
        }
		
    }
    
    public Polynomial add(Polynomial second) {
        
        double[] result_c = new double[this.exponents.length + second.exponents.length];
		int[] result_e = new int[this.exponents.length + second.exponents.length];
		int ctr = 1;
		
		for (int i = 0; i < this.exponents.length; i++) {
			boolean inside = false;
				
				for (int x = 0; x < result_e.length; x++) {
					
					if (result_e[x] == this.exponents[i]) {
							result_c[x] += this.coefficients[i];
							inside = true;
					}
				}
				
				if (!inside) {
					ctr = 1;
					
					while (result_e[ctr] != 0) {
						ctr++;
					}
					
					result_e[ctr] = this.exponents[i];
					result_c[ctr] = this.coefficients[i];
				}					
				
			
        }
            
        for (int i = 0; i < second.exponents.length; i++) {
				boolean inside = false;
				
				for (int x = 0; x < result_e.length; x++) {
					
					if (result_e[x] == second.exponents[i]) {
							result_c[x] += second.coefficients[i];
							inside = true;
					}
				}
				
				if (!inside) {
					ctr = 1;
					
					while (result_e[ctr] != 0) {
						ctr++;
					}
					
					result_e[ctr] = second.exponents[i];
					result_c[ctr] = second.coefficients[i];
				}					
				
			
        }
		
		ctr = 1;
		while (result_e[ctr] != 0)
		{
			ctr ++;
		}
		
		double[] final_c = new double[ctr];
		int[] final_e = new int[ctr];
		
		for (int i = 0; i < ctr; i++) {
			final_c[i] = result_c[i];
			final_e[i] = result_e[i];
		}
			
            
        
        return new Polynomial(final_c, final_e);
    }
    
    public double evaluate(double x) {
        
        double result = 0;
		double multi = 1.0;
		
		
        for (int i = 0; i < this.coefficients.length; i++) {
			multi = 1.0;
			
			for (int m = 0; m < this.exponents[i]; m++) {
				multi = multi * x; 
			}
			
			result += this.coefficients[i] * multi;
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
	
	private Polynomial multiplyterm(double coeff, int exp, Polynomial second) {
		
		double[] resultCoeffs = new double[second.coefficients.length];
		int[] resultExps = new int[second.exponents.length];
    
		for (int i = 0; i < second.coefficients.length; i++) {
			resultCoeffs[i] = coeff * second.coefficients[i];
			resultExps[i] = exp + second.exponents[i];
		}
    
		return new Polynomial(resultCoeffs, resultExps);
	}
	
	
	public Polynomial multiply(Polynomial second) {
		
		Polynomial result = new Polynomial();
		
		for (int i = 0; i < this.coefficients.length; i++) {
			
			Polynomial temp = multiplyterm(this.coefficients[i], this.exponents[i], second);
			
			result = result.add(temp);
		}
		
		return result;
            
	}
	
	public Polynomial(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String line = scanner.nextLine().replaceAll("\\s", "");
		scanner.close();

		String[] terms = line.split("(?=[+-])");
		int count = 0;
		for (String t : terms) {
			if (!t.isEmpty()){
				count++;
			}
		}


		this.coefficients = new double[count];
		this.exponents = new int[count];
		int idx = 0;

		for (String term : terms) {
			if (term.isEmpty()) continue;

			double coef;
			int exp;

			if (term.contains("x")) {
				int xPos = term.indexOf('x');
				String coefStr = term.substring(0, xPos);
				String expStr = term.substring(xPos + 1);

				if (coefStr.isEmpty() || coefStr.equals("+")) {
					coef = 1.0;
				} 
				else if (coefStr.equals("-")) {
					coef = -1.0;
				} 
				else {
					coef = Double.parseDouble(coefStr);
				}

				if (expStr.isEmpty()) {
					exp = 1;
				} 
				else {
					exp = Integer.parseInt(expStr);
				}
			} 
			
			else {
				coef = Double.parseDouble(term);
				exp = 0;
			}

			coefficients[idx] = coef;
			exponents[idx] = exp;
			idx++;
		}
	}
	
	public void saveToFile(String filename) throws IOException {
		FileWriter writer = new FileWriter(filename);
    
		if (coefficients.length == 0) {
			writer.write("0");
			writer.close();
			return;
		}
    
		StringBuilder result = new StringBuilder();
    
		for (int i = 0; i < coefficients.length; i++) {
			double coeff = coefficients[i];
			int exp = exponents[i];
        
			if (coeff == 0) continue;
        
			if (i > 0) {
				if (coeff > 0) {
					result.append("+");
				}
			}	
        
			if (exp == 0) {
				result.append(coeff);
			} 
			
			else if (exp == 1) {
				if (coeff == 1) {
					result.append("x");
				} 
				else if (coeff == -1) {
                result.append("-x");
				} 
				else {
                result.append(coeff).append("x");
				}
			} 
			
			else {
				if (coeff == 1) {
					result.append("x").append(exp);
				} 
				else if (coeff == -1) {
					result.append("-x").append(exp);
				} 
				else {
					result.append(coeff).append("x").append(exp);
				}
			}
		}
    
		if (result.length() == 0) {
			result.append("0");
		}
    
		writer.write(result.toString());
		writer.close();
	}
		
}
