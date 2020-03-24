package br.com.rgb;

import java.io.*;

/**
 * Enum com os valores referentes às posiçoes dos sete itens que um personagem pode ter em seu vetor de itens.
 * Implementa a interface Serializable para poder ser escrita e lida em arquivo binário.
 * 
 * @author RGB 
 * @version 21/02/16
 */
public enum ParteDoCorpo implements Serializable
{
    CABECA(0),
    TRONCO(1),
    BRACOS(2),
    PES(3),
    ANEL(4),
    MAO1(5),
    MAO2(6),
    /**
     * Enum dedicada para mostrar o valor máximo de itens que um personagem pode ter.
     */
    MAXIMO(7);
    
    private int posicao;
    
    /**
     * Cria uma enum de ParteDoCorpo com valor válido para o atributo.
     * @param posicao int com o valor da posição que o item ocupa no vetor de itens do personagem.
     */
    private ParteDoCorpo(int posicao)
    {
        this.posicao = posicao;
    }

    /**
     * Retorna o valor da posição do item desejado.
     */
    public int valor()
    {
        return posicao;
    }
}
