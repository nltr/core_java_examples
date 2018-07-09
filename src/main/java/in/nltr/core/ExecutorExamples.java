/**
 * 
 */
package in.nltr.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Nishant
 *
 */
public class ExecutorExamples {

	/**
	 * 
	 */
	public ExecutorExamples() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService es=Executors.newFixedThreadPool(3);
		int i=2;
		Future<Integer> f1 = es.submit(() -> i+1);
		
		Future<Integer> f2=es.submit(() -> i+2);
		Future<Integer> f3 = es.submit(() -> {try{
			if(i==2) throw new NullPointerException("111");
			else
				return 3;
		}finally{
			//do nothing
		}});
//		f3.get()
		try {
			System.out.println(f1.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}

		try {
			System.out.println(f3.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			f2.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		es.shutdown();
	}
	

}
