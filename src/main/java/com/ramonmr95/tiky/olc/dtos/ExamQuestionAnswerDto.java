package com.ramonmr95.tiky.olc.dtos;

import java.io.Serializable;
import java.util.List;

import com.ramonmr95.tiky.olc.entities.Answer;

public class ExamQuestionAnswerDto implements Serializable {

	private static final long serialVersionUID = 2991826557837917756L;

	private QuestionDto question;

	private List<Answer> answers;

	public ExamQuestionAnswerDto(QuestionDto question, List<Answer> answer) {
		this.question = question;
		this.answers = answer;
	}

	public QuestionDto getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDto question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
