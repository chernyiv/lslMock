package newSimpleRandomization;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class Address { 

	EasyRandom g = new EasyRandom();

	SimpleRandomization.Address internal;

	Address(SimpleRandomization.Address internal) {
		this.internal = internal;
	}

		public String toString() { 
			String s = internal.toString();
			return s;
		}

}