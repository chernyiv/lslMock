package newSimpleRandomization;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class Person { 

EasyRandom g = new EasyRandom();

	static SimpleRandomization.Person internal;

	Person(SimpleRandomization.Person internal) { this.internal = internal; }

		public String toString() { 
			String s = internal.toString();
			return s;
		}


}