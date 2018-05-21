package pers.mcginn.qatest.main;

import java.util.ArrayList;
import java.util.List;

public class Service {
	
	public static List<Problem> getListProblem(String probFilename, String ansFilename) throws Exception {
		List<Problem> probs = getProblem(probFilename);
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
	private static List<Problem> getProblem(String probFilename) {
		List<Problem> list = new ArrayList<Problem>();
		List<String> fileLines = FileUtil.readFileByLine(probFilename);
		int index = 0;
		Problem problem = null;
		for (String line : fileLines) {
			if (line == null || "".equals(line))
				continue;
			if (index == 0) {
				problem = new Problem();
				problem.setQuestion(line);
			} else {
				problem.addOption(line);
				if (index == 4) {
					list.add(problem);
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
