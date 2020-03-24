package br.com.rgb;

import java.util.Random;

/**
 * Funciona como rolar um dado, gerando um número randômico de um até um valor requerido.
 * 
 * @author RGB 
 * @version 18/02/16
 */
public class Dado
{
    private static Random dado = new Random();
    
    /**
     * Gera um número randômico de 1 até o valor recebido como parâmetro (emula a rolagem de um dado).
     * Joga uma exceção caso o parâmetro enviado for zero ou menor que zero.
     * @param lados int com o número de lados do dado a ser emulado.
     */
    static public int rolarD(int lados)
    {
        if(lados > 0)
        {
            return dado.nextInt(lados) + 1;
        }
        else
        {
            throw new RuntimeException("Número inválido. Deve ser um inteiro maior que zero.");
        }
    }
}
