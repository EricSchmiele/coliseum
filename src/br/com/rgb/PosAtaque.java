package br.com.rgb;

/**
 * Enum com os valores referentes aos três tipos de stats envolvidos em um ataque (atacante, defensor e danificado).
 * 
 * @author RGB
 * @version 21/02/16
 */
public enum PosAtaque
{
    ATACANTE(0),
    DEFENSOR(1),
    DANIFICADO(2);
    
    private int posicao;
    
    /**
     * Cria uma enum de PosAtaque com valor válido para o atributo.
     * @param posicao int com o valor da posição que o stat ocupa no vetor de stats da classe Ataque.
     */
    private PosAtaque(int posicao)
    {
        this.posicao = posicao;
    }

    /**
     * Retorna o valor da posição do tipo de stat desejado.
     */
    public int valor()
    {
        return posicao;
    }
}
