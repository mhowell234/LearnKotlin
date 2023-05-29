import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class StringProblemsJava {

	public static Optional<Character> firstNonRepeatingLinkedHashMap(String inputString) {

		if (null == inputString || inputString.isEmpty()) {
			return Optional.empty();
		}

		var frequency = new LinkedHashMap<Character, Integer>();

		inputString.chars().forEach(c -> frequency.compute((char) c, (k, v) -> (v == null) ? 1 : ++v));

		return Optional.empty();
	}

	public static Map<Character, Long> countDuplicateCharsStream(String inputString) {
		return inputString.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
	}

	public boolean hasOnlyDigits(final String inputString) {
		if (null == inputString) {
			return false;
		}

		return inputString.chars().allMatch(Character::isDigit);
	}

	public boolean isPalindrome(final String inputString) {
		return IntStream.range(0, inputString.length() / 2).allMatch(p ->
				inputString.charAt(p) == inputString.charAt(inputString.length() - p - 1));
	}

	public static void main(String[] args) {

	}
}
