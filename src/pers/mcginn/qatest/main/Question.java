package pers.mcginn.qatest.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

	private String problem;
	private String answer;
	private List<String> options;

	public void setAnswer(int index) {
		if (index < 0 || index >= options.size())
			return ;
		answer = options.get(index);
	}
	
	public void addOption(String opt) {
		if (options == null) 
			options = new ArrayList<String>();
		options.add(opt);
	}
	
	public void shuffleOptions() {
		Collections.shuffle(options);
	}
	
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

}