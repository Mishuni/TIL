import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Prob7 {

	public static void main(String[] args) {

		List<Integer> timestamp = new LinkedList<Integer>();
		List<Integer> top = new LinkedList<Integer>();

		timestamp.add(2);
		timestamp.add(2);
		timestamp.add(4);
		timestamp.add(8);
		timestamp.add(11);
		timestamp.add(28);
		timestamp.add(30);
		
		top.add(0);
		top.add(5);
		top.add(5);
		top.add(15);
		
		System.out.println(requestsServed(timestamp,top));
		
	}
	
	public static int requestsServed(List<Integer> timestamp, 
			List<Integer> top) {
		
	  int served = 0;
	  Collections.sort(timestamp);
	  Collections.sort(top);
	  
	  while(!top.isEmpty() && !timestamp.isEmpty()) {
		  int pt = top.remove(0);
		  int done = 0 ;
		  for(int i=timestamp.size()-1; i>=0; --i) {
			  int time = timestamp.get(i);
			  if(time<=pt) {
				  timestamp.remove(i);
				  ++done;
			  }
			  if(done==5) {
				  break;
			  }
		  }
		  served+=done;
	  }
	  
	  
	  return served;
	
	}

}
