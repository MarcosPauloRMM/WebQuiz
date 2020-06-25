package quizEnum;



import java.util.Arrays;
import java.util.List;

public enum AnswerEnum{
        //Em que ano aconteceu a revolução Francesa?
        ANSWER_1_QUESTION_1("1787", 1, false),
        ANSWER_2_QUESTION_1("1788", 1, false),
        ANSWER_3_QUESTION_1("1789", 1, true),
        ANSWER_4_QUESTION_1("1790", 1, false),
        
        //Descreve-se como Guerra do Peloponeso o conflito armado entre:
        ANSWER_1_QUESTION_2("Egito x Esparta", 1, false),
        ANSWER_2_QUESTION_2("Roma x Egito", 1, false),
        ANSWER_3_QUESTION_2("Esparta x Atena", 1, true),
        ANSWER_4_QUESTION_2("Atenas x Roma", 1, false),
        
        //Em que ano se iniciou a 2° Guerra Mundial
        ANSWER_1_QUESTION_3("1939", 1, true),
        ANSWER_2_QUESTION_3("1940", 1, false),
        ANSWER_3_QUESTION_3("1941", 1, false),
        ANSWER_4_QUESTION_3("1942", 1, false),
        
        //Em 1054 houve um evento chamado a Cisma do Oriente o qual dividiu o imperio romano em dois, quais são os nomes originais dessas duas partes?
        ANSWER_1_QUESTION_4("Sul e Norte", 1, false),
        ANSWER_2_QUESTION_4("Bizantino e Romano", 1, true),
        ANSWER_3_QUESTION_4("Equatorial e Polar", 1, false),
        ANSWER_4_QUESTION_4("Catolico e Ortodoxo", 1, false),
        
        //Quais desses não foi um imperador de Roma?
        ANSWER_1_QUESTION_5("Vespasiano", 1, false),
        ANSWER_2_QUESTION_5("Trajano", 1, false),
        ANSWER_3_QUESTION_5("Cômodo", 1, false),
        ANSWER_4_QUESTION_5("Marco", 1, true),

        //Quantos anos durou a guerra dos cem anos?
        ANSWER_1_QUESTION_6("103", 1, false),
        ANSWER_2_QUESTION_6("116", 1, true),
        ANSWER_3_QUESTION_6("98", 1, false),
        ANSWER_4_QUESTION_6("32", 1, false),
        
        //A pré-história está dividida em três periodos, ordenados cronologicamente são:
        ANSWER_1_QUESTION_7("Paleolítico, Neolítico e Idade da Pedra Polida", 1, true),
        ANSWER_2_QUESTION_7("Neolítico, Palolítico e Idade da Pedra Polida", 1, false),
        ANSWER_3_QUESTION_7("Idade da Pedra Polida, Neolitico e Paleolitico", 1, false),
        ANSWER_4_QUESTION_7("Idade da Pedra Polida, Paleolitico e Neolitico", 1, false),
        
        //O que significa URRS?
        ANSWER_1_QUESTION_8("União das Republicas Socialistas Soviéticas", 1, false),
        ANSWER_2_QUESTION_8("União Republicana Socialista e Soviética", 1, false),
        ANSWER_3_QUESTION_8("União Republicana Soviética e Socialista", 1, false),
        ANSWER_4_QUESTION_8("União das Republicas Soviéticas Socialistas", 1, true),

        //Quando foi criado o primeiro computador?
        ANSWER_1_QUESTION_9("1945", 1, false),
        ANSWER_2_QUESTION_9("1946", 1, true),
        ANSWER_3_QUESTION_9("1947", 1, false),
        ANSWER_4_QUESTION_9("1947", 1, false),
        
        //Quantos presidentes tivemso até 2020?
        ANSWER_1_QUESTION_10("39", 1, false),
        ANSWER_2_QUESTION_10("40", 1, false),
        ANSWER_3_QUESTION_10("38", 1, true),
        ANSWER_4_QUESTION_10("66", 1, false),;
        

        private final String answer;
        private final int codeQuestion;
        private final boolean answerTrueOrFalse;
        
        public int getCodeQuestion() {
            return codeQuestion;
        }

        public String getAnswer() {
            return answer;
        }
        
        public boolean getAnswerTrueOrFalse(){
            return answerTrueOrFalse;
        }
        
        
        AnswerEnum(String answer, int codeAnswer, boolean answerTrueOrFalse){
            this.answer = answer;
            this.codeQuestion = codeAnswer;
            this.answerTrueOrFalse = answerTrueOrFalse;
        }
        
        public static List<AnswerEnum> getListAnswers(){
            List<AnswerEnum> listAnswers = Arrays.asList(AnswerEnum.values());
            return listAnswers;
        }
        
    }