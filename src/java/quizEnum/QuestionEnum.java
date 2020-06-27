package quizEnum;




import java.util.Arrays;
import java.util.List;

public enum QuestionEnum {
    
    //ENUMERANDO PERGUNTAS PARA FACILITAR A RANDOMIZAÇÂO E A CHAMADA.
    QUESTION_1("Em que ano aconteceu a revolução Francesa?"),
    QUESTION_2("Descreve-se como Guerra do Peloponeso o conflito armado entre:"),
    QUESTION_3("Em que ano se iniciou a 2° Guerra Mundial"),
    QUESTION_4("Em 1054 houve um evento chamado a Cisma do Oriente o qual dividiu o imperio romano em dois, quais são os nomes originais dessas duas partes?"),
    QUESTION_5("Quais desses não foi um imperador de Roma?"),
    QUESTION_6("Quantos anos durou a guerra dos cem anos?"),
    QUESTION_7("A pré-história está dividida em três periodos, ordenados cronologicamente são:"),
    QUESTION_8("O que significa URRS?"),
    QUESTION_9("Quando foi criado o primeiro computador?"),
    QUESTION_10("Quantos presidentes tivemso até 2020?");

    private final String question;
    
    //CONSTRUTOR DA ENUMERAÇÃO
    QuestionEnum(String question){
        this.question = question;
    }
    //METODO PARA RECUPERA A LISTA DE QUESTÔES
    public static List<QuestionEnum> getListQuestions(){
        List<QuestionEnum> listQuestions = Arrays.asList(QuestionEnum.values());
        return listQuestions;
    }
    //METODO PARA RETORNAR A QUESTÂO
    public String getQuestion() {
        return question;
    }
}
