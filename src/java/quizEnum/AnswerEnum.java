package quizEnum;



import java.util.Arrays;
import java.util.List;

public enum AnswerEnum{
    
        //ENUMERANDO QUESTÔES PARA FACILITAR A RANDOMIZAÇÂO E A CHAMADA.
        //Em que ano aconteceu a revolução Francesa?
        ANSWER_1_QUESTION_1("1787", 1, false),
        ANSWER_2_QUESTION_1("1788", 1, false),
        ANSWER_3_QUESTION_1("1789", 1, true),
        ANSWER_4_QUESTION_1("1790", 1, false),
        
        //Descreve-se como Guerra do Peloponeso o conflito armado entre:
        ANSWER_1_QUESTION_2("Egito x Esparta", 2, false),
        ANSWER_2_QUESTION_2("Roma x Egito", 2, false),
        ANSWER_3_QUESTION_2("Esparta x Atena", 2, true),
        ANSWER_4_QUESTION_2("Atenas x Roma", 2, false),
        
        //Em que ano se iniciou a 2° Guerra Mundial
        ANSWER_1_QUESTION_3("1939", 3, true),
        ANSWER_2_QUESTION_3("1940", 3, false),
        ANSWER_3_QUESTION_3("1941", 3, false),
        ANSWER_4_QUESTION_3("1942", 3, false),
        
        //Em 1054 houve um evento chamado a Cisma do Oriente o qual dividiu o imperio romano em dois, quais são os nomes originais dessas duas partes?
        ANSWER_1_QUESTION_4("Sul e Norte", 4, false),
        ANSWER_2_QUESTION_4("Bizantino e Romano", 4, true),
        ANSWER_3_QUESTION_4("Equatorial e Polar", 4, false),
        ANSWER_4_QUESTION_4("Catolico e Ortodoxo", 4, false),
        
        //Quais desses não foi um imperador de Roma?
        ANSWER_1_QUESTION_5("Vespasiano", 5, false),
        ANSWER_2_QUESTION_5("Trajano", 5, false),
        ANSWER_3_QUESTION_5("Cômodo", 5, false),
        ANSWER_4_QUESTION_5("Marco", 5, true),

        //Quantos anos durou a guerra dos cem anos?
        ANSWER_1_QUESTION_6("103", 6, false),
        ANSWER_2_QUESTION_6("116", 6, true),
        ANSWER_3_QUESTION_6("98", 6, false),
        ANSWER_4_QUESTION_6("32", 6, false),
        
        //A pré-história está dividida em três periodos, ordenados cronologicamente são:
        ANSWER_1_QUESTION_7("Paleolítico, Neolítico e Idade da Pedra Polida", 7, true),
        ANSWER_2_QUESTION_7("Neolítico, Palolítico e Idade da Pedra Polida", 7, false),
        ANSWER_3_QUESTION_7("Idade da Pedra Polida, Neolitico e Paleolitico", 7, false),
        ANSWER_4_QUESTION_7("Idade da Pedra Polida, Paleolitico e Neolitico", 7, false),
        
        //O que significa URRS?
        ANSWER_1_QUESTION_8("União das Republicas Socialistas Soviéticas", 8, false),
        ANSWER_2_QUESTION_8("União Republicana Socialista e Soviética", 8, false),
        ANSWER_3_QUESTION_8("União Republicana Soviética e Socialista", 8, false),
        ANSWER_4_QUESTION_8("União das Republicas Soviéticas Socialistas", 8, true),

        //Quando foi criado o primeiro computador?
        ANSWER_1_QUESTION_9("1945", 9, false),
        ANSWER_2_QUESTION_9("1946", 9, true),
        ANSWER_3_QUESTION_9("1947", 9, false),
        ANSWER_4_QUESTION_9("1948", 9, false),
        
        //Quantos presidentes tivemso até 2020?
        ANSWER_1_QUESTION_10("39", 10, false),
        ANSWER_2_QUESTION_10("40", 10, false),
        ANSWER_3_QUESTION_10("38", 10, true),
        ANSWER_4_QUESTION_10("66", 10, false),;
        

        private final String answer;
        private final int codeQuestion;
        private final boolean answerTrueOrFalse;
        
        //METODO PARA RECUPERA CODIGO DA RESPOSTAS
        public int getCodeQuestion() {
            return codeQuestion;
        }
        //METODO PARA RECUPERA A RESPOSTAS
        public String getAnswer() {
            return answer;
        }
        //METODO PARA SABER SE A RESPOSTAS ESTÁ CERTA OU ERRADA
        public boolean getAnswerTrueOrFalse(){
            return answerTrueOrFalse;
        }
        
        //CONSTRUTOR DA ENUMERAÇÂO
        AnswerEnum(String answer, int codeAnswer, boolean answerTrueOrFalse){
            this.answer = answer;
            this.codeQuestion = codeAnswer;
            this.answerTrueOrFalse = answerTrueOrFalse;
        }
        //METODO PARA RECUPERA A LISTA DE RESPOSTAS
        public static List<AnswerEnum> getListAnswers(){
            List<AnswerEnum> listAnswers = Arrays.asList(AnswerEnum.values());
            return listAnswers;
        }
        
    }