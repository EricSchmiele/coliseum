package br.com.rgb;

import java.io.*;

/**
 * Esta classe representa os valores utilizados para batalhar durante o jogo, estes são chamados de stats.
 * Os stats são: força, defesa, velocidade, magia, sorte e vida.
 * Implementa a interface Serializable para poder ser escrita e lida em arquivo binário.
 * 
 * @author RGB 
 * @version 15/02/16
 */
public class Stats implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Vetor com os valores dos stats.
     */
    private int[] todosStats;
    
    /**
     * Cria um objeto de Stats com valores válidos para seu atributo.
     * @param forca int com o valor da força.
     * @param defesa int com o valor da defesa.
     * @param velocidade int com o valor da velocidade.
     * @param magia int com o valor da magia.
     * @param sorte int com o valor da sorte.
     * @param vida int com o valor da vida.
     */
    public Stats(int forca, int defesa, int velocidade, int magia,
    int sorte, int vida)
    {
        todosStats = new int[6];
        todosStats[0] = forca;
        todosStats[1] = defesa;
        todosStats[2] = velocidade;
        todosStats[3] = magia;
        todosStats[4] = sorte;
        todosStats[5] = vida;
    }
    
    /**
     * Método para retornar o valor de apenas um stat específico.
     * @param statDesejado PosStats do stat desejado.
     */
    public int getUmStat(PosStats statDesejado)
    {
        return todosStats[statDesejado.valor()];
    }
    
    /**
     * Método para subtrair (danificar) os valores de um stat.
     * Como podem haver um ou mais valores sendo danificados ao mesmo tempo, um objeto Stats com os valores a serem
     * subtraídos de cada stat específico é enviado (sendo que os valores dos stats que não mudarão serão todos
     * zero).
     * Obs: os valores do dano devem ser positivos.
     * @param dano Stats com os valores a serem subtraídos de cada stat.
     */
    public void danificar(Stats dano)
    {
        for(PosStats p: PosStats.values())
        {
            if(p != PosStats.NADA)
            {
                todosStats[p.valor()] -= dano.getUmStat(p);
                if(todosStats[p.valor()] < 0)
                {
                    todosStats[p.valor()] = 0;
                }
            }
        }
    }
    
    /**
     * Método para somar (adicionar) os valores de um stat.
     * Como podem haver um ou mais valore sendo adicionados ao mesmo tempo, um objeto Stats com os valores a serem
     * somados de cada stat específico é enviado (sendo que os valores dos stats que não mudarão serão todos zero).
     * @param statsAdd Stats com os valores a serem somados de cada stat.
     */
    public void adicionar(Stats statsAdd)
    {
        for(PosStats p: PosStats.values())
        {
            if(p != PosStats.NADA)
            {
                todosStats[p.valor()] += statsAdd.getUmStat(p);
                if(todosStats[p.valor()] < 0)
                {
                    todosStats[p.valor()] = 0;
                }
            }
        }
    }
    
    /**
     * Retorna um clone deste objeto Stats.
     */
    public Stats clone()
    {
        return new Stats(todosStats[0],todosStats[1],todosStats[2],todosStats[3],todosStats[4],todosStats[5]);
    }
    
    /**
     * Retorna a soma dos valores de todos os stats.
     */
    public int getSoma()
    {
        int soma = 0;
        for(int i = 0; i < 6; i++)
        {
            soma += todosStats[i];
        }
        return soma;
    }
}
