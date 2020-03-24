package br.com.rgb;
/**
 * Define um tipo arqueiro, sendo uma subclasse de ClassePura. Especifica o método levelUp e a inicialização dos 
 * valores dos atributos para ser diferente das outras subclasses.
 * 
 * @author RGB 
 * @version 16/02/16
 * @TODO javadoc
 */
public class Arqueiro extends ClassePura
{
    /**
     * Cria um objeto Arqueiro com os valores iniciais específicos dos atributos para esta classe.
     */
    public Arqueiro()
    {
        super(Mensagem.ARQUEIRO,3,1,3,0,1);
        setAtaque(new Ataque("Tiro com arco",PosStats.FORCA,PosStats.VELOCIDADE,PosStats.VIDA));
    }

    /**
     * Determina como a classe Arqueiro deve mudar os stats a cada level. E especifica em qual level um novo ataque
     * será liberado.
     */
    public void levelUp()
    {
        Stats adicionau = new Stats(Dado.rolarD(4),Dado.rolarD(3),Dado.rolarD(3),Dado.rolarD(5),Dado.rolarD(2),
        Dado.rolarD(5));//soma é 22
        adicionarStats(adicionau);
        levelMais();
        if(getLevel() == 3)
        {
            setAtaque(new Ataque("Flecha venenosa",PosStats.VELOCIDADE,PosStats.FORCA,PosStats.FORCA));
        }
        if(getLevel() == 7)
        {
            setAtaque(new Ataque("Chuva de flechas",PosStats.VELOCIDADE,PosStats.VIDA,PosStats.VIDA));
        }
    }
}