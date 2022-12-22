package telran.util.test;

import java.util.function.Predicate;

public class SubStrPredicate implements Predicate<String> {
	String subStr;

	public SubStrPredicate(String subStr) {
		this.subStr = subStr;
	}
	
	@Override
	public boolean test(String t) {
		return t.contains(subStr);//String contains
	}

}
