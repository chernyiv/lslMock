package newSimpleRandomization;
import org.jeasy.random.EasyRandom;

public class Execution { 

	static EasyRandom g = new EasyRandom();

	static SimpleRandomization.Execution internal;

	public Execution(SimpleRandomization.Execution internal) { Execution.internal = internal; }

	public static Data setAndReturnData() {
		SimpleRandomization.Data d = internal.setAndReturnData();
		d = g.nextObject(SimpleRandomization.Data.class);
		return new Data(d);
	}

}