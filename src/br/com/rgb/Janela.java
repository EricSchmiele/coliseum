package br.com.rgb;

/**
 * Enum com os valores referentes às posiçoes dos paineis possíveis da janela da GUI para que a enum possa comunicar
 * qual deve ser o próximo painel a ser exibido ao usuário dependendo das ações dele.
 * 
 * @author RGB
 * @version 21/02/16
 */
public enum Janela
{
    NOME_PERSONAGEM(0),
    CLASSE_PERSONAGEM(1),
    DIFICULDADE(2),
    CENARIO_PRINCIPAL(3),
    MENU_JOGADOR(4),
    ESCOLHER_ITENS(5),
    INIMIGO_DERROTADO(6),
    VITORIA(7),
    DERROTA(8),
    FUGA(9),
    MAXIMO(10);
    
    private int posicao;
    
    /**
     * Cria uma enum de Janela com valor válido para o atributo.
     * @param posicao int com o valor da posição que o painel ocupa no vetor de paineis da janela da GUI.
     */
    private Janela(int posicao)
    {
        this.posicao = posicao;
    }
    
    /**
     * Retorna o valor da posição do painel desejado.
     */
    public int valor()
    {
        return posicao;
    }
}
