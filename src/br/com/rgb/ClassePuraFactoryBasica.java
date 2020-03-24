package br.com.rgb;

/**
 * Classe que implementa o criador de classes para Personagem. Sobrescreve os métodos da interface para que a versão
 * básica do jogo funcione.
 * 
 * @author RGB
 * @version 29/02/16
 */
public class ClassePuraFactoryBasica implements ClassePuraFactory
{
    /**
     * Método para criar uma classe à partir de um objeto Mensagem (exemplo: Mensagem.ARQUEIRO cria um Arqueiro).
     * Caso a Mensagem não informe uma classe, uma exceção é jogada.
     */
    public ClassePura criaClassePura(Mensagem classe)
    {
        if(classe == Mensagem.ARQUEIRO)
        {
            return new Arqueiro();
        }
        if(classe == Mensagem.CAVALEIRO)
        {
            return new Cavaleiro();
        }
        if(classe == Mensagem.MAGO)
        {
            return new Mago();
        }
        else
        {
            throw new RuntimeException("Mensagem de classe inválida.");
        }
    }
    
    /**
     * Método para criar uma classe à partir de uma variável int (exemplo: 0 cria um Arqueiro).
     * Caso a variável int seja menor que 1 ou maior que 3, uma exceção é jogada.
     */
    public ClassePura criaClassePura(int classe)
    {
        if(classe == 1)
        {
            return new Arqueiro();
        }
        if(classe == 2)
        {
            return new Cavaleiro();
        }
        if(classe == 3)
        {
            return new Mago();
        }
        else
        {
            throw new RuntimeException("Número de classe inválido.");
        }
    }
}
