import java.util.LinkedHashMap;
import java.util.Optional;


public class StringProblemsJava {

	public static Optional<Character> firstNonRepeatingLinkedHashMap(String inputString) {

		if (null == inputString || inputString.isEmpty()) {
			return Optional.empty();
		}

		var frequency = new LinkedHashMap<Character, Integer>();

		inputString.chars().forEach(c -> frequency.compute((char) c, (k, v) -> (v == null) ? 1 : ++v));

		return Optional.empty();
	}


	public static void main(String[] args) {

	}
}
