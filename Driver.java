public class Driver {
    public static void main(String [] args) throws Exception {
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));
        
        double[] c1 = {6, 0, 0, 5};
        int[] e1 = {0, 1, 2, 3};
        Polynomial p1 = new Polynomial(c1, e1);
        
        double[] c2 = {0, -2, 0, 0, -9};
        int[] e2 = {0, 1, 2, 3, 4};
        Polynomial p2 = new Polynomial(c2, e2);
        
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        
        if (s.hasRoot(1)){
            System.out.println("1 is a root of s");
		}
        
		else {
            System.out.println("1 is not a root of s");
        }
        Polynomial product = p1.multiply(p2);
        System.out.println("p1 * p2 evaluated at 2: " + product.evaluate(2));
        
        double[] c3 = {5, -3, 7};
        int[] e3 = {0, 2, 8};
        Polynomial p3 = new Polynomial(c3, e3);
        p3.saveToFile("test_poly.txt");
        System.out.println("Saved polynomial to test_poly.txt");
        
        Polynomial p4 = new Polynomial(new java.io.File("test_poly.txt"));
        System.out.println("Loaded polynomial evaluates to: " + p4.evaluate(1));
    }
}