package br.com.rgb;

import java.util.ArrayList;

/**
 * Classe abstrata que representa a classe do personagem (arqueiro, cavaleiro ou mago).
 * Ela possúi todas as informações necessárias da classe como nome da classe, level, ataques, stats, gets e sets,
 * método para elevar o level, etc.
 * Porém cada subclasse tem sua maneira específica de elever o level e tembém os seus ataques específicos.
 * 
 * @author RGB 
 * @version 15/02/16
 */
public abstract class ClassePura
{
    /**
     * Nome da classe (arqueiro, cavaleiro, mago).
     */
    private Mensagem nome;
    /**
     * Stats básicos da classe no level atual.
     */
    private Stats stats;
    private int level;
    /**
     * Lista de ataques possíveis da classe.
     */
    private ArrayList<Ataque> ataques;
    private int totalDeAtaques;
    
    /**
     * Cria um objeto da ClassePura com valores válidos para todos os atributos.
     * @param nome String com o nome da classe.
     * @param forca int com a força inicial da classe.
     * @param defesa int com a defesa inicial da classe.
     * @param velocidade int com a velocidade inicial da classe.
     * @param magia int com a magia inicial da classe.
     * @param sorte int com a sorte inicial da classe.
     */
    public ClassePura(Mensagem nome, int forca, int defesa,
    int velocidade, int magia, int sorte)
    {
        this.nome = nome;
        stats = new Stats(forca,defesa,velocidade,magia,sorte,15);
        level = 1;
        totalDeAtaques = 0;
        ataques = new ArrayList<Ataque>();
    }

    /**
     * Retorna o nome da classe.
     */
    public Mensagem getNome()
    {
        return nome;
    }
    
    /**
     * Retorna um clone dos stats básicos da classe
     */
    public Stats getStats()
    {
        return stats.clone();
    }
    
    /**
     * Retorna o level atual da classe.
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * Método para retornar apenas o nome de um ataque específico.
     * Se o ataque não existir o retorno será uma String sem caracteres.
     * @param Posição do ataque requerido na lista de ataques.
     */
    public String getNomeAtaque(int numeroAtaque)
    {
        if(numeroAtaque < totalDeAtaques)
        {
            return ataques.get(numeroAtaque).getNome();
        }
        else
        {
            return "";
        }
    }
    
    /**
     * Método para retornar um ataque específico.
     * Se o ataque não existir o retorno será um ataque com valores PosStats.NADA que devem ser interpretados como
     * ataques inválidos: um ataque que o personagem errou.
     * @param Posição do ataque requerido na lista de ataques.
     */
    public Ataque getAtaque(int numeroAtaque)
    {
        if(numeroAtaque < totalDeAtaques)
        {
            return ataques.get(numeroAtaque).clone();
        }
        else
        {
            return new Ataque("",PosStats.NADA,PosStats.NADA,PosStats.NADA);
        }
    }
    
    /**
     * Adiciona um objeto Ataque na lista de ataques.
     * @param novoAtaque Ataque novo a ser inserido.
     */
    protected void setAtaque(Ataque novoAtaque)
    {
        ataques.add(novoAtaque);
        totalDeAtaques++;
    }
    
    /**
     * Como a mudança de stats depende da subclasse a qual o personagem pertence, este método é abstrato e
     * determinará como os stats são modificados a cada level adquirido.
     */
    public abstract void levelUp();
    
    /**
     * Eleva o valor do atributo level de um em um.
     */
    protected void levelMais()
    {
        level++;
    }
    
    /**
     * Adiciona valores aos stats básicos da classe (Utilizado dentro do método levelUp nos subclasses).
     * @param adicionau Stats como os stats a serem somados aos stats básicos.
     */
    protected void adicionarStats(Stats adicionau)
    {
        stats.adicionar(adicionau);
    }
}
