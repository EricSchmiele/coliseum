package br.com.rgb;

import java.io.*;

/**
 * Enum com textos de mesnagens utilizadas entre várias classes para facilitar o envio de parâmetros de String,
 * diminuindo a necessidade de checar se o parâmetro enviado é inválido ou não.
 * Também é utilizada principalmente para comunicar à engine qual foi a ação do usuário na GUI.
 * Implementa a interface Serializable para poder ser escrita e lida em arquivo binário.
 * 
 * @author RGB 
 * @version 28/02/16
 */
public enum Mensagem implements Serializable
{
    NOME("NOME"),
    ARQUEIRO("Arqueiro"),
    CAVALEIRO("Cavaleiro"),
    MAGO("Mago"),
    /**
     * Enum dedicada para mostrar que algum item pode ser utilizado por todas as classes (arqueiro, cavaleiro e mago).
     */
    TODOS("Todos"),
    DIFICULDADE_ESCOLHIDA("DIFICULDADE_ESCOLHIDA"),
    FACIL("FACIL"),
    MEDIO("MEDIO"),
    DIFICIL("DIFICIL"),
    ATAQUE("ATAQUE"),
    ATAQUE0("ATAQUE0"),
    ATAQUE1("ATAQUE1"),
    ATAQUE2("ATAQUE2"),
    ATAQUE3("ATAQUE3"),
    ATAQUE4("ATAQUE4"),
    ATAQUE5("ATAQUE5"),
    ATAQUE6("ATAQUE6"),
    ATAQUE7("ATAQUE7"),
    ATAQUE8("ATAQUE8"),
    ATAQUE9("ATAQUE9"),
    ATAQUE10("ATAQUE10"),
    FUGIR("FUGIR"),
    MENU("MENU"),
    OK("OK"),
    PROXIMO_INIMIGO("PROXIMO_INIMIGO"),
    PROXIMO_INIMIGO_FUGA("PROXIMO_INIMIGO_FUGA"),
    REJEITA("REJEITA"),
    ACEITA("ACEITA"),
    TENTAR_NOVAMENTE("TENTAR_NOVAMENTE"),
    NOVO_JOGO("NOVO_JOGO"),
    /**
     * Enum dedicada para mostrar que nenhuma mensagem foi enviada, ou uma mensagem inválida foi utilizada.
     */
    NADA("NADA");
    
    private final String texto;
    
    /**
     * Cria uma enum de Mensagem com valor válido para o atributo.
     * @param texto String com o texto da mensagem a ser transmitida entre classes.
     */
    private Mensagem(String texto)
    {
        this.texto = texto;
    }
    
    /**
     * Retorna o valor do texto da mensagem a ser usada entre as classes.
     */
    public String valor()
    {
        return texto;
    }
}
