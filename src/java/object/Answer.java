
package object;


public class Answer {

    private String codeAnswer;
    private String codeQuestion;
    private String answer;
    private boolean resultTrueOrFalse;
    
  
    
    public String getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(String codeQuestion) {
        this.codeQuestion = codeQuestion;
    }
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public boolean isResultTrueOrFalse() {
        return resultTrueOrFalse;
    }

    public void setResultTrueOrFalse(boolean resultTrueOrFalse) {
        this.resultTrueOrFalse = resultTrueOrFalse;
    }

    public String getCodeAnswer() {
        return codeAnswer;
    }

    public void setCodeAnswer(String codeAnswer) {
        this.codeAnswer = codeAnswer;
    }
    
}
