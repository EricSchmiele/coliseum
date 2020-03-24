package br.com.rgb;

/**
 * Representa um personagem deste jogo (jogador ou inimigo) com todos os atributos necessários incluindo stats,
 * itens, classe, etc. Também possui métodos para requisição e alteração destes atributos de maneira correta.
 * 
 * @author RGB 
 * @version 28/02/16
 */
public class Personagem
{
    private String nome;
    /**
     * Classe de luta do personagem.
     */
    private ClassePura classe;
    /**
     * Stats do personagem somando os stats base da classe e os adicionais e bônus dos itens equipados.
     */
    private Stats stats;
    /**
     * Stats do personagem atualmente, cosinderando danos sofridos durante uma luta (valor de stats com os danos
     * subtraidos).
     */
    private Stats statsAtuais;
    /**
     * Stats considerando a possibilidade de equipar um novo item. Apresenta a previsão dos valores dos stats deste
     * personagem considerando desiquipar um item e equipar outro no lugar dele.
     */
    private Stats statsTemporarios;
    private int expAtual;
    private int expProximoLvl;
    /**
     * Vetor de itens equipados no personagem.
     */
    private Item[] itens;
    /**
     * Indicador de que o personagem possúi um conjunto de itens completo, possibilitando o ganho dos stats bônus
     * deste conjunto de itens.
     */
    private boolean conjuntoCompleto;
    /**
     * Itens iniciais do personagem. Não possuem stat adicional algum.
     */
    private Item cabecaVazia;
    private Item troncoVazio;
    private Item bracosVazios;
    private Item pesVazios;
    private Item anelVazio;
    /**
     * O item mao1Vazia é utilizado também para o caso de o jogador ter um item de duas mãos equipado e resolve
     * equipar um novo item na mão2, desta maneira o item de duas mãos será desequipado e a mao1Vazia é equipada no
     * lugar.
     */
    private Item mao1Vazia;
    /**
     * O item mao2Vazia é utilizado também para o caso de o jogador equipar um item de duas mãos, desta maneira o 
     * item da mão 2 será desequipado e a mao2Vazia é equipada no lugar.
     */
    private Item mao2Vazia;

    /**
     * Cria um objeto de Personagem com valores válidos para todos os atributos.
     * Inicia os itens "vazios" como itens sem stats adicionais.
     * @param nome String com o nome do personagem.
     * @param classe Classe com a classe de luta a qual o personagem terá.
     */
    public Personagem(String nome, ClassePura classe)
    {
        this.nome = nome;
        this.classe = classe;
        conjuntoCompleto = false;
        cabecaVazia = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.CABECA,0,0);
        troncoVazio = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.TRONCO,0,0);
        bracosVazios = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.BRACOS,0,0);
        pesVazios = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.PES,0,0);
        anelVazio = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.ANEL,0,0);
        mao1Vazia = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.MAO1,0,0);
        mao2Vazia = new Item("",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.MAO2,0,0);
        itens = new Item[ParteDoCorpo.MAXIMO.valor()];
        itens[0] = cabecaVazia.clone();
        itens[1] = troncoVazio.clone();
        itens[2] = bracosVazios.clone();
        itens[3] = pesVazios.clone();
        itens[4] = anelVazio.clone();
        itens[5] = mao1Vazia.clone();
        itens[6] = mao2Vazia.clone();
        atualizarStats();
        expAtual = 0;
        expProximoLvl = 300;
    }
    
    /**
     * Retorna o nome do personagem.
     */
    public String getNome()
    {
        return nome;
    }
    
    /**
     * Retorna a classe de luta do personagem.
     */
    public Mensagem getClasse()
    {
        return classe.getNome();
    }
    
    /**
     * Retorna o valor total de um stat específico.
     * @param Posição do stat desejado.
     */
    public int getStat(PosStats stat)
    {
        return stats.getUmStat(stat);
    }
    
    /**
     * Retorna o valor atual de um stat específico.
     * @param Posição do stat desejado.
     */
    public int getStatAtual(PosStats stat)
    {
        return statsAtuais.getUmStat(stat);
    }
    
    /**
     * Retorna os stats totais do personagem.
     */
    public Stats getStatsTotais()
    {
        return stats.clone();
    }
    
    /**
     * Retorna a experiência atual do personagem.
     */
    public int getExpAtual()
    {
        return expAtual;
    }
    
    /**
     * Retorna o valor de experiência necessária para o próximo level.
     */
    public int getExpProximoLvl()
    {
        return expProximoLvl;
    }
    
    /**
     * Retorna o valor do level atual da classe do personagem.
     */
    public int getLvl()
    {
        return classe.getLevel();
    }
    
    /**
     * Retorna o item equipado em uma parte do corpo específica.
     * @param Posição no corpo do item desejado.
     */
    public Item getItem(ParteDoCorpo parteDoCorpo)
    {
        return itens[parteDoCorpo.valor()].clone();
    }
    
    /**
     * Equipa um novo item no personagem, levando em consideração a classe do personagem.
     * Se for um item de mão o tratamento é diferenciado: se um item de duas mãos o item da mão2 será desequipado
     * automaticamente. E se um item de duas mãos estiver equipado e um item da mão2 for equipado, o item de duas
     * mãos será desequipado automaticamente.
     * Joga uma exceção caso o item a ser equipado pertencer a outra classe.
     * @param Item a ser equipado.
     */
    public void equiparItem(Item item)
    {
        if((item.getClasse() == Mensagem.TODOS) || (item.getClasse() == classe.getNome()))
        {
            if((item.getNumeroDeMaos() == 2) && (item.getParteDoCorpo() == ParteDoCorpo.MAO1))
            //verificação se o novo item é de duas mãos.
            {
                itens[ParteDoCorpo.MAO2.valor()] = mao2Vazia.clone();
                itens[ParteDoCorpo.MAO1.valor()] = item;
            }
            else if((itens[ParteDoCorpo.MAO1.valor()].getNumeroDeMaos() == 2) && (item.getParteDoCorpo()
            == ParteDoCorpo.MAO2))
            //verificação se o item equipado na mão1 é de duas mãos e se o novo item será equipado na mão2.
            {
                itens[ParteDoCorpo.MAO1.valor()] = mao1Vazia.clone();
                itens[ParteDoCorpo.MAO2.valor()] = item;
            }
            else
            {
                itens[item.getParteDoCorpo().valor()] = item;
                verificaConjunto();
            }
            atualizarStats();
        }
        else
        {
            throw new RuntimeException("Item incompatível com a classe do personagem.");
        }
    }
    
    /**
     * Calcula os valores do atributo statsTemporarios considerando um novo item a ser equipado, simulando os valores
     * que dos stats totais do personagem caso este item seja realmente equipado e retorna estes valores.
     * Joga uma exceção caso o item a ser equipado pertencer a outra classe.
     * @param O item com o qual se deseja simular a mudança dos stats.
     * @return Um objeto Stats com todos os valores calculados pela simulação de equipar o novo item.
     */
    public Stats getStatsTemporarios(Item item)
    {
        if((item.getClasse() == Mensagem.TODOS) || (item.getClasse() == classe.getNome()))
        {
            statsTemporarios = stats.clone();
            statsTemporarios.danificar(itens[item.getParteDoCorpo().valor()].getStatsAdicionais());
            //retirar o adicional do item anteriormente equipado.
            statsTemporarios.adicionar(item.getStatsAdicionais());
            if(item.getParteDoCorpo().valor() != ParteDoCorpo.ANEL.valor())
            //se o item for um anel, não há necessidade de um tratamento posterior sendo que o anel não interfere
            //no número de mãos disponíveis e nem no completamento do conjunto.
            {
                if((item.getParteDoCorpo().valor() >= ParteDoCorpo.CABECA.valor())
                && (item.getParteDoCorpo().valor() <= ParteDoCorpo.PES.valor()))
                //se o item for do corpo (não for uma arma ou item equipado na mão) é necessário verificar se o
                //conjunto atual fica descompleto ou se um novo conjunto é completado.
                {
                    if(conjuntoCompleto)
                    {
                        statsTemporarios.danificar(itens[ParteDoCorpo.CABECA.valor()].getStatsBonus());
                    }
                    boolean aux = true;
                    int i = ParteDoCorpo.CABECA.valor();
                    while(aux && i < ParteDoCorpo.PES.valor())
                    //utilização de uma variável auxiliar para verificar se o novo item pertence ao mesmo conjunto
                    //dos outros itens, sendo que se um item de conjunto diferente for encontrado as comparações já
                    //param.
                    {
                        if((item.getParteDoCorpo().valor() != i) && (itens[i].getConjunto() != item.getConjunto()))
                        {
                            aux = false;
                        }
                        i++;
                    }
                    if(aux)
                    {
                        statsTemporarios.adicionar(item.getStatsBonus());
                    }
                }
                else
                //se o item for equipado em uma mão, é necessário verificar se ele é um item de duas mãos ou se já
                //há um item de duas mãos equipado (mesmo esquema para um processo de equipar normal).
                {
                    if((item.getNumeroDeMaos() == 2) && (item.getParteDoCorpo() == ParteDoCorpo.MAO1))
                    {
                        statsTemporarios.adicionar(itens[ParteDoCorpo.MAO2.valor()].getStatsAdicionais());
                    }
                    else if((itens[ParteDoCorpo.MAO1.valor()].getNumeroDeMaos() == 2) && (item.getParteDoCorpo()
                    == ParteDoCorpo.MAO2))
                    {
                        statsTemporarios.adicionar(itens[ParteDoCorpo.MAO1.valor()].getStatsAdicionais());
                    }
                }
            }
            return statsTemporarios;
        }
        else
        {
            throw new RuntimeException("Item incompatível com a classe do personagem.");
        }
    }
    
    /**
     * Verifica se os itens utilizados pelo personagem pertencem ao mesmo conjunto ou não e atualiza a variável
     * indicadora de conjunto completo, possibilitando o uso dos stats bônus do conjunto.
     */
    private void verificaConjunto()
    {
        conjuntoCompleto = true;
        int i = ParteDoCorpo.CABECA.valor();
        do
        {
            if((itens[i].getConjunto() != itens[i + 1].getConjunto()) || (itens[i].getConjunto() == 0))
            {
                conjuntoCompleto = false;
            }
            i++;
        }while(conjuntoCompleto && (i < ParteDoCorpo.PES.valor()));
        //verificação de todos os itens da cabeça aos pés, desconsiderando o anel e as mãos.
    }
    
    /**
     * Eleva o valor atual de experiência do personagem e verifica se ele elevou o level de sua classe de luta. Se
     * realmente elevou de level os stats são atualizados automaticamente.
     * @param O valor adicional de experiência adquirido.
     * @return Verdadeiro se elevou de level, falso caso contrário.
     */
    public boolean ganharExp(int experiencia)
    {
        expAtual += experiencia;
        if(expAtual >= expProximoLvl)
        {
            expProximoLvl = (int)(1.5 * expProximoLvl);
            classe.levelUp();
            atualizarStats();
            return true;
        }
        else
        {
            atualizarStats();
            return false;
        }
    }
    
    /**
     * Danifica o personagem conforme o dano recebido atualizando o valor dos stats atuais.
     * @param O dano como um tipo Stats (pois o dano pode ter sido em qualquer um dos seis stats).
     */
    public void receberDano(Stats dano)
    {
        statsAtuais.danificar(dano);
    }
    
    /**
     * Retorna o nome de um ataque específico.
     * @param Posição do ataque desejado.
     */
    public String getNomeAtaque(int numeroAtaque)
    {
        return classe.getNomeAtaque(numeroAtaque);
    }
    
    /**
     * Retorna um ataque (tipo Ataque) específico.
     * @param Posição do ataque desejado.
     */
    public Ataque getAtaque(int numeroAtaque)
    {
        return classe.getAtaque(numeroAtaque);
    }
    
    /**
     * Atualiza o valor dos stats totais do personagem: soma dos stats base da classe de luta do personagem com os
     * stats adicionais e de bônus dos itens equipados.
     * Também atualiza o valor dos stats Atuais para serem iguais aos totais menos o dano que o personagem já tinha
     * recebido até o presente momento.
     */
    public final void atualizarStats()
    {
        if(stats == null)
        //se o personagem acabou de ser criado os atributos stats e statsAtuais devem ser iniciados com valores
        //nulos para que eles possam ser iniciados corretamente.
        {
            stats = new Stats(0,0,0,0,0,0);
            statsAtuais = new Stats(0,0,0,0,0,0);
        }
        stats.danificar(statsAtuais);
        Stats danosAtuais = stats.clone();
        stats = classe.getStats();
        for(int i = 0;i < 7;i++)
        {
            stats.adicionar(itens[i].getStatsAdicionais());
        }
        if(conjuntoCompleto)
        {
            stats.adicionar(itens[ParteDoCorpo.CABECA.valor()].getStatsBonus());
        }
        statsAtuais = stats.clone();
        statsAtuais.danificar(danosAtuais);
        statsTemporarios = stats.clone();
    }
    
    /**
     * Recupera os valores dos stats atuais para serem iguais aos totais (cura totalmente o personagem).
     */
    public void curarStatsTotalmente()
    {
        statsAtuais = stats.clone();
    }
    
    /**
     * Retorna a dificuldade que o personagem impõe.
     */
    public int getDificuldade()
    {
        return statsAtuais.getSoma();
    }
}
