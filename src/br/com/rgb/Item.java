package br.com.rgb;

import java.io.*;

/**
 * Representa um item a ser utilizado pelo personagem. Define os valores dos stats adicionais que ele proporciona, o
 * conjunto ao qual ele pertence, assim como os stats bônus por ter um conjunto completo, entre outros detalhes.
 * Implementa a interface Serializable para poder ser escrita e lida em arquivo binário.
 * 
 * @author RGB 
 * @version 18/02/16
 */
public class Item implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
    /**
     * Stats proporcionados ao equipar o item.
     */
    private Stats statsAdicionais;
    /**
     * Stats proporcionados ao equipar quatro itens do mesmo conjunto.
     * Todos os itens do conjunto tem o mesmo valor de bônus e portanto ele é adicionado apenas uma vez.
     */
    private Stats statsBonus;
    private Mensagem classe;
    private ParteDoCorpo parteDoCorpo;
    private int conjunto;
    /**
     * Aplicado apenas em itens equipados nas mãos.
     */
    private int numeroDeMaos;

    /**
     * Cria um objeto Item com valores válidos para todos os atributos.
     * @param nome String com o nome do item.
     * @param forca int com força adicional do item.
     * @param defesa int com defesa adicional do item.
     * @param velocidade int com velocidade adicional do item.
     * @param magia int com magia adicional do item.
     * @param sorte int com sorte adicional do item.
     * @param vida int com vida adicional do item.
     * @param forcaBonus int com força bônus do item.
     * @param defesaBonus int com defesa bônus do item.
     * @param velocidadeBonus int com velocidade bônus do item.
     * @param magiaBonus int com magia bônus do item.
     * @param sorteBonus int com sorte bônus do item.
     * @param vidaBonus int com vida bônus do item.
     * @param classe Mensagem com a classe específica do item.
     * @param parteDoCorpo ParteDoCorpo com a parte do corpo na qual o item deve ser equipado.
     * @param conjunto int com o valor do conjunto do item.
     * @param numeroDeMaos int com o número de mãos que o item ocupa.
     */
    public Item(String nome, int forca, int defesa, int velocidade, int magia, int sorte, int vida, int forcaBonus,
    int defesaBonus, int velocidadeBonus, int magiaBonus, int sorteBonus, int vidaBonus, Mensagem classe,
    ParteDoCorpo parteDoCorpo, int conjunto, int numeroDeMaos)
    {
        this.nome = nome;
        statsAdicionais = new Stats(forca,defesa,velocidade,magia,sorte,vida);
        statsBonus = new Stats(forcaBonus,defesaBonus,velocidadeBonus,magiaBonus,sorteBonus,vidaBonus);
        this.classe = classe;
        this.parteDoCorpo = parteDoCorpo;
        this.conjunto = conjunto;
        this.numeroDeMaos = numeroDeMaos;
    }
    
    /**
     * Retorna o nome da do item.
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Retorna o valor de um stat adicional específico.
     * @param Posição do stat desejado.
     */
    public int getStatAdicional(PosStats stat)
    {
        return statsAdicionais.getUmStat(stat);
    }
    
    /**
     * Retorna os stats adicionais do item.
     */
    public Stats getStatsAdicionais()
    {
        return statsAdicionais.clone();
    }
    
    /**
     * Retorna o valor de um stat bônus específico.
     * @param Posição do stat bônus desejado.
     */
    public int getStatBonus(PosStats stat)
    {
        return statsBonus.getUmStat(stat);
    }
    
    /**
     * Retorna os stats bônus do item.
     */
    public Stats getStatsBonus()
    {
        return statsBonus.clone();
    }
    
    /**
     * Retorna o nome da classe que pode equipar o item.
     */
    public Mensagem getClasse()
    {
        return classe;
    }
    
    /**
     * Retorna qual a parte do corpo na qual o item deve ser equipado.
     */
    public ParteDoCorpo getParteDoCorpo()
    {
        return parteDoCorpo;
    }
    
    /**
     * Retorna o conjunto ao qual o item pertence.
     */
    public int getConjunto()
    {
        return conjunto;
    }
    
    /**
     * Retorna o número de mãos que o item ocupa.
     */
    public int getNumeroDeMaos()
    {
        return numeroDeMaos;
    }
    
    /**
     * Cria um novo objeto Item com os mesmos valores de atributo deste e o retorna como um clone.
     */
    public Item clone()
    {
        return new Item(nome,statsAdicionais.getUmStat(PosStats.FORCA),
        statsAdicionais.getUmStat(PosStats.DEFESA),statsAdicionais.getUmStat(PosStats.VELOCIDADE),
        statsAdicionais.getUmStat(PosStats.MAGIA),statsAdicionais.getUmStat(PosStats.SORTE),
        statsAdicionais.getUmStat(PosStats.VIDA),statsBonus.getUmStat(PosStats.FORCA),
        statsBonus.getUmStat(PosStats.DEFESA),statsBonus.getUmStat(PosStats.VELOCIDADE),
        statsBonus.getUmStat(PosStats.MAGIA),statsBonus.getUmStat(PosStats.SORTE),
        statsBonus.getUmStat(PosStats.VIDA),classe,parteDoCorpo,conjunto,numeroDeMaos);
    }
}
