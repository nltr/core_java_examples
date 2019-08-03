/**
 * 
 */
package in.nltr.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
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
//		example1();
		example2();
	}

	private static void example2() {
		ExecutorService es=Executors.newFixedThreadPool(3);
		int i=2;
		Callable<Integer> f1 =() -> i+1;
		
		Callable<Integer> f2=() -> i+2;
		Callable<Integer> f3 = () -> {try{
			if(i==2) throw new NullPointerException("111");
			else
				return 3;
		}finally{
			//do nothing
		}};
		List<Callable<Integer>> list=new ArrayList<>();
		list.add(f1);
		list.add(f2);
		list.add(f3);
		try {
			List<Future<Integer>> invokeAll = es.invokeAll(list);
			invokeAll.get(0).get();
			invokeAll.get(1);
			invokeAll.get(2).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdown();
	}

	private static void example1() {
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
