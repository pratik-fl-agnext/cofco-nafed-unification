import java.util.Arrays;
import java.util.HashMap;

public class Test {
	/** The Constant OPERATION_AND. */
	private static final String OPERATION_AND = "AND";
	
	/** The Constant OPERATION_OR. */
	private static final String OPERATION_OR = "OR";
	
	/** The Constant OPERATOR_EQUALS. */
	private static final String OPERATOR_EQUALS = "=";
	
	/** The Constant OPERATOR_LIKE. */
	private static final String OPERATOR_LIKE = "LIKE";
	
	/** The Constant OPEN_BRACKET. */
	private static final String OPEN_BRACKET = "(";
	
	/** The Constant CLOSE_BRACKET. */
	private static final String CLOSE_BRACKET = ")";

	public static void main(String args[]) {
		HashMap<String, String> data=new HashMap<>();
		data.put("BL", "20");
		data.put("temp", "20");
		String query="[{\r\n" + 
				"\"mainOperator\": \"none\",\r\n" + 
				"\"bracketAtStart\": \"(\",\r\n" + 
				"\"BL\": \"20\",\r\n" + 
				"\"operator\": \"And\",\r\n" + 
				" \"Temp\": \"20\",\"bracketAtEnd\": \")\"\r\n" + 
				"}]";
		String[] queryList = query.split(" ");
		

		boolean isBracketOpen = false;
		boolean ruleFound = false;
		boolean withInBracketRuleFound = false;
		String logicalOperator = null;
		String outerLogicalOperator = null;
		for (int i = 0; i < queryList.length; i++) {

			if (OPERATION_AND.equalsIgnoreCase(queryList[i]) || OPERATION_OR.equalsIgnoreCase(queryList[i])) {
				logicalOperator = queryList[i].toUpperCase();
				String nextVal = queryList[i + 1];
				if (nextVal.equals(OPEN_BRACKET)) {
					i++;
					outerLogicalOperator = logicalOperator;
					isBracketOpen = true;
					logicalOperator = null;
				}
			} else if (queryList[i].equals(OPEN_BRACKET)) {
				isBracketOpen = true;
				logicalOperator = null;
			} else if (queryList[i].equals(CLOSE_BRACKET)) {
				isBracketOpen = false;
				logicalOperator = null;

				if (OPERATION_AND.equalsIgnoreCase(outerLogicalOperator))
					ruleFound = ruleFound && withInBracketRuleFound;
				else if (OPERATION_OR.equalsIgnoreCase(outerLogicalOperator))
					ruleFound = ruleFound || withInBracketRuleFound;
				else
					ruleFound = withInBracketRuleFound;
				withInBracketRuleFound = false;

				if (i + 2 > queryList.length)
					return;
				continue;
			} else {
				i--;
			}

			if (isBracketOpen) {
				withInBracketRuleFound = parse(logicalOperator, queryList[++i], queryList[++i], queryList[++i], data,
						withInBracketRuleFound);
			} else {
				ruleFound = parse(logicalOperator, queryList[++i], queryList[++i], queryList[++i], data, ruleFound);
			}
		}
	}
	
	private static  boolean parse(String logicalOperator, String key, String operator, String value,
			HashMap<String, String> data, boolean previousFound) {

		if (!previousFound && OPERATION_AND.equalsIgnoreCase(logicalOperator))
			return false;

		if (operator.equalsIgnoreCase(OPERATOR_EQUALS)) {
			boolean currentFound = data.get(key) != null && data.get(key).equalsIgnoreCase(value);
			return logicalOperator == null ? currentFound
					: logicalOperator.equalsIgnoreCase(OPERATION_AND) ? previousFound && currentFound
							: logicalOperator.equalsIgnoreCase(OPERATION_OR) ? previousFound || currentFound : false;
		}

		if (operator.equalsIgnoreCase(OPERATOR_LIKE)) {
			if (data.get(key) != null) {
				return matchLike(data.get(key),value);
			}
		}

		return false;

	}
	
	private static  boolean matchLike(String data, String pattern) {
		if (data == null || data.trim().length() == 0)
			return false;
		if (pattern == null || pattern.trim().length() == 0)
			return false;

		int dataLength = data.length();
		int patternLength = pattern.length();

		boolean[][] lookup = new boolean[dataLength + 1][patternLength + 1];

		for (int i = 0; i < dataLength + 1; i++)
			Arrays.fill(lookup[i], false);

		lookup[0][0] = true;

		for (int j = 1; j <= patternLength; j++)
			if (pattern.charAt(j - 1) == '*')
				lookup[0][j] = lookup[0][j - 1];

		for (int i = 1; i <= dataLength; i++) {
			for (int j = 1; j <= patternLength; j++) {
				if (pattern.charAt(j - 1) == '*')
					lookup[i][j] = lookup[i][j - 1] || lookup[i - 1][j];

				else if (pattern.charAt(j - 1) == '?' || data.charAt(i - 1) == pattern.charAt(j - 1))
					lookup[i][j] = lookup[i - 1][j - 1];

				else
					lookup[i][j] = false;
			}
		}

		return lookup[dataLength][patternLength];
	}
}
