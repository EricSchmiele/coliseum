package br.com.rgb;

/**
 * Um criador de classes para o Personagem. Como no futuro novas classes podem ser criadas esta interface deve ser
 * implementada para que as classes sejam criadas de maneira correta.
 * 
 * @author RGB
 * @version 29/02/16
 */
public interface ClassePuraFactory
{
    /**
     * Método para criar uma classe à partir de um objeto Mensagem (exemplo: Mensagem.ARQUEIRO cria um Arqueiro).
     */
    ClassePura criaClassePura(Mensagem classe);
    
    /**
     * Método para criar uma classe à partir de uma variável int (exemplo: 1 cria um Arqueiro).
     */
    ClassePura criaClassePura(int classe);
}
