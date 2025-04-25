import java.util.Scanner;
public class Ex2_2_6 {
	public static void pt1(Scanner scanner) {
	    System.out.println("ax + b = 0");
	    System.out.print("a: ");
	    double a = scanner.nextDouble();
	    System.out.print("b: ");
	    double b = scanner.nextDouble();
	    if (a == 0) {
	        if (b == 0) {
	            System.out.println("Phương trình có vô số nghiệm");}
            else {
	            System.out.println("Phương trình vô nghiệm");
	        }
	    } else {
	        double x = -b / a;
	        System.out.println("Phương trình có nghiệm là: x = " + x);}
	}
	   public static void pt2(Scanner scanner) {
	        System.out.println("a11 * x1 + a12 * x2 = b1");
	        System.out.println("a21 * x1 + a22 * x2 = b2");
	        System.out.print("a11: ");
	        double a11 = scanner.nextDouble();
	        System.out.print("a12: ");
	        double a12 = scanner.nextDouble();
	        System.out.print("b1: ");
	        double b1 = scanner.nextDouble();
	        System.out.print("a21: ");
	        double a21 = scanner.nextDouble();
	        System.out.print("a22: ");
	        double a22 = scanner.nextDouble();
	        System.out.print("b2: ");
	        double b2 = scanner.nextDouble();
	        double D = a11 * a22 - a12 * a21;
	        double Dx = b1 * a22 - b2 * a12;
	        double Dy = a11 * b2 - a21 * b1;
	        if (D == 0) {
	            if (Dx == 0 && Dy == 0) {
	                System.out.println("Phương trình có vô số nghiệm");} 
          else {
	                System.out.println("Phương trình vô nghiệm");
	            }
	        } else {
	            double x1 = Dx / D;
	            double x2 = Dy / D;
	            System.out.println("Phương trình có nghiệm là x1 = " + x1 + ", x2 = " + x2);}
	    }
	   public static void pt3(Scanner scanner) {
	        System.out.println("ax^2 + bx + c = 0");
	        System.out.print("a: ");
	        double a = scanner.nextDouble();
	        System.out.print("b: ");
	        double b = scanner.nextDouble();
	        System.out.print("c: ");
	        double c = scanner.nextDouble();
	        if (a == 0) {
	            System.out.println("Không phải phương trình bậc 2");
	            return;}
	        double delta = b * b - 4 * a * c;
	        if (delta > 0) {
	            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
	            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
	            System.out.println("Phương trình có 2 nghiệm phân biệt x1 = " + x1 + ", x2 = " + x2);
	        } else if (delta == 0) {
	            double x = -b / (2 * a);
	            System.out.println("Phương trình có nghiệm kép x = " + x);
	        } else {
	            System.out.println("Phương trình vô nghiệm");}
	    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Giải phương trình bậc nhất 1 ẩn");
        System.out.println("2.Giải hệ phương trình bậc nhất 2 ẩn");
        System.out.println("3.Giải hệ phương trình bậc 2 1 ẩn");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                solve_1(sc);
                break;
            case 2:
                solve_2(sc);
                break;
            case 3:
                solve_3(sc);
                break;
            default:
                System.out.println("Hãy chọn 1 trong 3 loại giải phương trình!");}
        sc.close();}
}
