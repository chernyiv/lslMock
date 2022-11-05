package newSimpleRandomization;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class Job { 

EasyRandom g = new EasyRandom();

	static SimpleRandomization.Job internal;

	Job(SimpleRandomization.Job internal) { this.internal = internal; }

		public String toString() { 
			String s = internal.toString();
			return s;
		}


}