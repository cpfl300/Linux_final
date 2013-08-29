import java.util.Random;


public class Pie implements Runnable {
	
	final static long MAX = 40000000;
	final static int THREAD_NUM = 100;
	final static long LOOP_NUM = MAX/THREAD_NUM;
	static int id = 0;
	static double totalDot = 0;
	static double innerDot = 0;
	
	static Random x = new Random();
	static Random y = new Random();
	
	@Override
	public void run() {
		long start, end;
		
		synchronized (this) {
			start = id * LOOP_NUM + 1;
			end = (id + 1) * LOOP_NUM;
			id++;
		}
		
		dot(start,end);
	}
	
	private void dot(long start, long end) {
		double subTotalDot = 0;
		double subInnerDot = 0;
		System.out.println("id: "+id+ ", start: "+start+", end: "+end);
		
		for(long i = start; i<= end; i++){
			subTotalDot++;
			
			double xPosition = x.nextDouble();
			double yPosition = y.nextDouble();
			
//			System.out.println(xPosition);
//			System.out.println(yPosition);

			if(calculate(xPosition, yPosition))
				subInnerDot++;
		}
		
		synchronized (this) {
			totalDot += subTotalDot;
			innerDot += subInnerDot;
		}
		
	}

	private boolean calculate(double x, double y) {

		double tmp = (x * x) + (y * y);
		double result = Math.sqrt(tmp);
		
		if(result<=1){
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) throws InterruptedException {
		Pie p = new Pie();
		Thread[] ta = new Thread[THREAD_NUM];
		
		for(int i = 0; i < THREAD_NUM; i++){
			ta[i] = new Thread(p);
			ta[i].start();
		}
		
		for(Thread tr : ta) tr.join();
		
		double PI = (innerDot*4)/totalDot;
	
		
		System.out.println(innerDot);
		System.out.println(totalDot);
//		System.out.println(PI);
		
		
		System.out.printf("PIE = %f", PI); // PI값을 계산합니다.
	}


}
