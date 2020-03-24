package br.com.rgb;

/**
 * Enum com os valores referentes às posiçoes dos seis stats que um personagem tem em seu vetor de stats.
 * 
 * @author RGB 
 * @version 21/02/16
 */
public enum PosStats
{
    FORCA(0),
    DEFESA(1),
    VELOCIDADE(2),
    MAGIA(3),
    SORTE(4),
    VIDA(5),
    /**
     * Enum dedicada para indicar que um stat não está sendo utilizado ou que o uso não é permetido atualmente.
     */
    NADA(-1);
    
    private int posicao;
    
    /**
     * Cria uma enum de PosStats com valor válido para o atributo.
     * @param posicao int com o valor da posição que o stat ocupa no vetor de stats do personagem.
     */
    private PosStats(int posicao)
    {
        this.posicao = posicao;
    }

    /**
     * Retorna o valor da posição do stat desejado.
     */
    public int valor()
    {
        return posicao;
    }
}
