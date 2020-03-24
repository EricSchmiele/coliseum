package br.com.rgb;

/**
 * Classe que representa um ataque e todos os elementos envolvidos nele. É construido considerando um ataque que
 * utiliza um valor de ataque e outro de defesa, se o de ataque for maior que o de defesa então o dano será efetuado.
 * Para tal esta classe informa quais stats serão utilizados para fazer os cálculos do ataque:
 * Stat atacante: stat utilizado pelo personagem atacante para determinar se o ataque obteve sucesso ou não.
 * Stat defensor: stat utilizado pelo personagem atacado para determinar se o ataque obteve sucesso ou não.
 * Stat danificado: stat do personagem atacado que será danificado com o golpe.
 * 
 * @author RGB 
 * @version 21/02/16
 */
public class Ataque
{
    private String nome;
    /**
     * Vetor com os PosStats que mostram quais serão os stats atacante defensor e danificado.
     */
    private PosStats[] tudo;

    /**
     * Cria um objeto de Ataque com valores válidos para todos os atributos.
     * @param nome String com o nome do ataque.
     * @param statAtacante PosStats com a posição do stat a ser usado para calcular o ataque.
     * @param statDefensor PosStats com a posição do stat a ser usado para calcular a defesa.
     * @param statDanificado PosStats com a posição do stat a ser danificado se o ataque for deferido.
     */
    public Ataque(String nome, PosStats statAtacante,
    PosStats statDefensor, PosStats statDanificado)
    {
        this.nome = nome;
        tudo = new PosStats[3];
        tudo[0] = statAtacante;
        tudo[1] = statDefensor;
        tudo[2] = statDanificado;
    }

    /**
     * Retorna o vetor com os stats atacante, defensor e danificado.
     */
    public PosStats[] getAtaque()
    {
        return tudo;
    }
    
    /**
     * Retorna um stat específico do ataque.
     * @param PosAtaque representando qual dos stats é desejado (atacante, defensor ou danificado).
     */
    public PosStats getAtaque(PosAtaque pos)
    {
        return tudo[pos.valor()];
    }
    
    /**
     * Retorna o nome do ataque.
     */
    public String getNome()
    {
        return nome;
    }
    
    /**
     * Retorna um clone deste objeto Ataque com os mesmos valores de atributos.
     */
    public Ataque clone()
    {
        return new Ataque(nome,tudo[0],tudo[1],tudo[2]);
    }
}
