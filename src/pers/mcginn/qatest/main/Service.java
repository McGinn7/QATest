package pers.mcginn.qatest.main;

import java.util.ArrayList;
import java.util.List;

public class Service {
	
	public static List<Question> getListQuestion(String probFilename, String ansFilename) throws Exception {
		List<Question> probs = getProblem(probFilename);
		String ans = getAnswer(ansFilename);
		if (probs.size() != ans.length()) {
			throw new Exception("Problems doesn't match answers, " + probs.size() + " problems and"
					+ " " + ans.length() + " answers.");
		}
		for (int i = 0; i < probs.size(); ++i) {
			int index = ans.charAt(i) - 'a';
			probs.get(i).setAnswer(index);
		}
		return probs;
	}
	
	/*
	 * Problem format:
	 * 	Description
	 * 	option1
	 * 	option2
	 * 	option3
	 * 	option4
	 */
	private static List<Question> getProblem(String probFilename) {
		List<Question> list = new ArrayList<Question>();
		List<String> fileLines = FileUtil.readFileByLine(probFilename);
		int index = 0;
		Question question = null;
		for (String line : fileLines) {
			if (line == null || "".equals(line))
				continue;
			if (index == 0) {
				question = new Question();
				question.setProblem(line);
			} else {
				question.addOption(line);
				if (index == 4) {
					list.add(question);
				}
			}
			index = (index + 1) % 5;
		}		
		return list;
	}
	
	private static String getAnswer(String ansFilename) {
		String str = FileUtil.readFileByCharacter(ansFilename);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z'))
				sb.append(ch);
		}
		return sb.toString().toLowerCase();
	}

}
