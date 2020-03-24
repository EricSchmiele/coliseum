package br.com.rgb;

import java.util.*;

/**
 * Organiza todas as informações envolvidas no programa como um todo.
 * Implementa a interface Observavel para enviar informações aos observadores (interfaces gráficas para o usuário).
 * É responsável pelo funcionamento da lógica do programa, isto é, é ela que organiza os dados de acordo com a ação
 * do usuário e faz com que o jogador e o inimigo tenham uma reação correta.
 * Esta classe é abstrata pois o criador de classes para o jogador e para o inimigo pode ser diferenciado
 * dependendo da versão do jogo.
 * 
 * @author RGB 
 * @version 29/02/16
 */
public abstract class Engine implements Observavel
{
    private Personagem jogador;
    private Personagem inimigo;
    /**
     * Como a criação do inimigo depende da dificuldade, esta deve sempre ser "guardada" para posteriores
     * referências.
     */
    private int dificuldade;
    /**
     * Como o item deixado é utilizado em mais de um método (getNomeItemDeixado, itensDeixadosPeloInimigo, etc),
     * este deve ser "guardado" para posteriores referências.
     */
    private Item itemDeixado;
    /**
     * Como os stats com o item deixado são utilizados em mais de um método, estes devem ser "guardados" para
     * posteriores referências.
     */
    private Stats statsComNovoItem;
    private TodosOsItens todosOsItens;
    /**
     * Contador para verificar todos os itens do inimigo de maneira ordenada.
     */
    private int parteDoCorpoInimigo;
    private boolean moverJogador;
    private boolean jogadorErrou;
    private boolean moverInimigo;
    private boolean inimigoErrou;
    /**
     * Representa qual janela deve ser apresentada ao usuário dependendo do momento dentro da lógica do jogo.
     */
    private Janela janelaAtual;
    private ArrayList<Observador> listaDeObservadores;
    /**
     * Utilizada quando o jogador é criado, quando ele eleva o level ou quando ele equipa um novo item.
     * Deve informar todos os stats, o level, os itens e os ataques do jogador.
     */
    private boolean novoJogador;
    /**
     * Utilizado quando o inimigo é criado.
     * Deve informar o nome, a classe e os stats do inimigo.
     */
    private boolean novoInimigo;
    /**
     * Utilizado quando o inimigo ou o jogador ataca.
     * Deve informar os stats do jogador e do inimigo.
     */
    private boolean acaoDeLuta;
    /**
     * Utilizado quando um novo item pode ser equipado.
     * Deve informar o novo item, os stats do jogador e os stats com o novo item.
     */
    private boolean escolherItem;

    /**
     * Construtor para os objetos da classe Engine.
     * Inicia o jogador e o primeiro inimigo, além de enviar as primeiras mensagens para os observadores.
     * @param nome String com o nome do jogador.
     * @param classe Mensagem com o nome da classe que o personagem terá.
     * @param dificuldade int com o valor da dificuldade do jogo (0: fácil; 1: médio; 2: difícil).
     * @param observador Observador para o jogo, uma vez que o jogo não pode ser iniciado sem ter um
     * observador. Do contrário algumas informações seriam perdidas.
     */
    public Engine(String nome, Mensagem classe, int dificuldade, Observador observador)
    {
        listaDeObservadores = new ArrayList<Observador>();
        adicionarObservador(observador);
        itemDeixado = new Item("Nada",0,0,0,0,0,0,0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.CABECA,0,0);
        statsComNovoItem = new Stats(0,0,0,0,0,0);
        todosOsItens = new TodosOsItens();
        parteDoCorpoInimigo = -1;
        this.dificuldade = dificuldade;
        iniciaJogador(nome,classe);
        criarInimigo();
    }
    
    /**
     * Cria o jogador com o nome e classe especificados e informa os observadores que o jogador foi criado.
     * @param nome String com o nome do jogador.
     * @param classe Mensagem com o nome da classe que o personagem terá.
     */
    protected final void iniciaJogador(String nome, Mensagem classe)
    {
        jogador = new Personagem(nome,fabricarClasse(classe));
        setAcaoJogador(false,false);
        setJanelaAtual(Janela.MAXIMO);//nao deve atualizar qual janela mostrar pois o inimigo ainda será iniciado.
        flagsParaCenario(true,false,false,false);
        notificarObservadores();
    }

    /**
     * Cria um novo inimigo considerando a dificuldade escolhida e o nível atual do jogador.
     * A classe do inimigo é iniciada de maneira randômica utilizando a classe Dado.
     * Avisa os observadores que um novo inimigo foi criado.
     * Também verifica se o inimigo deve atacar primeiro ou não.
     */
    protected final void criarInimigo()
    {
        int numClasseInimigo = Dado.rolarD(3);
        String nomeInimigo = "Foe";
        inimigo = new Personagem(nomeInimigo,fabricarClasse(numClasseInimigo));
        for(int i = 0; i < (jogador.getLvl() - 1); i++)
        {
            inimigo.ganharExp(inimigo.getExpProximoLvl() - inimigo.getExpAtual());
        }
        //Eleva o level do inimigo até que ele seja um level a menos que o jogador.
        //No caso de o jogador estar no level 1 nada ocorre.
        //No nível de dificuldade:
        //Fácil: o inimigo tem um level a menos que o jogador (exceto se o jogador estiver no level um, neste
        //caso o inimigo tem o mesmo level que o jogador).
        //Médio: o inimigo tem o mesmo level que o jogador.
        //Difícil: o inimigo tem um level a mais que o jogador.
        switch(dificuldade)
        {
            case 0:
            {
                /*inimigo.equiparItem(new Item("Anel do poder",0,0,10,10,0,0,0,0,0,0,0,0,Mensagem.TODOS,
                ParteDoCorpo.ANEL,0,0));
                inimigo.equiparItem(new Item("Espada",10,0,0,0,0,10,0,0,0,0,0,0,Mensagem.TODOS,
                ParteDoCorpo.MAO1,0,0));
                inimigo.equiparItem(new Item("Escudo",0,10,0,0,10,0,0,0,0,0,0,0,Mensagem.TODOS,
                ParteDoCorpo.MAO2,0,0));*/
                for(ParteDoCorpo p: ParteDoCorpo.values())
                {
                    try
                    {
                        inimigo.equiparItem(todosOsItens.getItem((Dado.rolarD(4) - 1),p));
                    }
                    catch(Exception e)
                    {
                    }
                }
            }
            break;
            case 1:
            {
                if(jogador.getLvl() != 1)
                {
                    inimigo.ganharExp(inimigo.getExpProximoLvl() - inimigo.getExpAtual());
                }
                for(ParteDoCorpo p: ParteDoCorpo.values())
                {
                    try
                    {
                        inimigo.equiparItem(todosOsItens.getItem((Dado.rolarD(4) - 1),p));
                    }
                    catch(Exception e)
                    {
                    }
                }
            }
            break;
            case 2:
            {
                if(jogador.getLvl() != 1)
                {
                    inimigo.ganharExp(inimigo.getExpProximoLvl() - inimigo.getExpAtual());
                }
                inimigo.ganharExp(inimigo.getExpProximoLvl() - inimigo.getExpAtual());
                for(ParteDoCorpo p: ParteDoCorpo.values())
                {
                    try
                    {
                        inimigo.equiparItem(todosOsItens.getItem((Dado.rolarD(4) - 1),p));
                    }
                    catch(Exception e)
                    {
                        //Se houver algum problema, simplesmente não equipe o último item.
                    }
                }
            }
            break;
        }
        //atualizar toda a GUI pois o inimigo acabou de ser criado
        setAcaoInimigo(false,false);
        setJanelaAtual(Janela.CENARIO_PRINCIPAL);
        flagsParaCenario(false,true,false,false);
        notificarObservadores();
        if(inimigo.getStatAtual(PosStats.VELOCIDADE) > jogador.getStatAtual(PosStats.VELOCIDADE))
        {
            ataqueInimigo();
        }
    }
    
    /**
     * Executa um ataque específico do jogador e informa aos observadores que um ataque do jogador foi deferido e
     * se ele obteve sucesso ou não.
     * Também verifica se o inimigo foi derrotado após o ataque.
     * Se sim, os observadores são informados (sendo que a informação pode ser simplesmente que um inimigo foi
     * derrotado ou que o jogador venceu o jogo).
     * Se não um ataque do inimigo é executado.
     * @param int numeroAtaque com o número do ataque específico do jogador que será executado.
     */
    protected void ataqueJogador(int numeroAtaque)
    {
        Ataque ataque = jogador.getAtaque(numeroAtaque);
        if(ataque.getAtaque()[PosAtaque.ATACANTE.valor()] != PosStats.NADA)
        {
            if((jogador.getStatAtual(ataque.getAtaque(PosAtaque.ATACANTE)) + Dado.rolarD(10)) > //calculo_de_ataque
            (inimigo.getStatAtual(ataque.getAtaque(PosAtaque.DEFENSOR)) + Dado.rolarD(10))) //calculo_de_ataque
            {
                inimigo.receberDano(criarDano(ataque.getAtaque(PosAtaque.DANIFICADO),Dado.rolarD(jogador.getLvl() * 2))); //valor_de_ataque
                setAcaoJogador(true,false);
            }
            else
            {
                setAcaoJogador(true,true);
            }
        }
        else
        {
            setAcaoJogador(true,true);
        }
        setJanelaAtual(Janela.CENARIO_PRINCIPAL);
        flagsParaCenario(false,false,true,false);
        notificarObservadores();
        if(inimigoDerrotado())
        {
            elevarExperiencia();
            jogador.curarStatsTotalmente();
            if(jogador.getLvl() == 10) //condicao_de_vitoria
            {
                setJanelaAtual(Janela.VITORIA);
            }
            else
            {
                setJanelaAtual(Janela.INIMIGO_DERROTADO);
            }
            flagsParaCenario(true,false,false,false);
            notificarObservadores();
        }
        else
        {
            ataqueInimigo();
        }
    }
    
    /**
     * Executa um ataque específico do Inimigo e informa aos observadores que um ataque do inimigo foi deferido e
     * se ele obteve sucesso ou não.
     * Também verifica se o jogador foi derrotado .
     * Se sim, os observadores são informados (sendo que a informação pode ser simplesmente que o jogador foi
     * derrotado ou que o jogador não foi derrotado) chama-se a tela de derrota.
     * Se não um ataque a tela principal é chamada.
     */
    protected void ataqueInimigo()
    {
        int numeroAtaque = Dado.rolarD(3);
        Ataque ataque = inimigo.getAtaque(numeroAtaque);
        if(ataque.getAtaque()[PosAtaque.ATACANTE.valor()].valor() != -1)
        {
            if((inimigo.getStatAtual(ataque.getAtaque(PosAtaque.ATACANTE)) + Dado.rolarD(20)) > //calculo_de_ataque
            (jogador.getStatAtual(ataque.getAtaque(PosAtaque.DEFENSOR)) + Dado.rolarD(20))) //calculo_de_ataque
            {
                jogador.receberDano(criarDano(ataque.getAtaque(PosAtaque.DANIFICADO),Dado.rolarD(inimigo.getLvl() * 2))); //valor_de_ataque
                setAcaoInimigo(true,false);
            }
            else
            {
                setAcaoInimigo(true,true);
            }
        }
        else
        {
            setAcaoInimigo(true,true);
        }
        setJanelaAtual(Janela.CENARIO_PRINCIPAL);
        flagsParaCenario(false,false,true,false);
        notificarObservadores();
        if(jogadorDerrotado())
        {
            setJanelaAtual(Janela.DERROTA);
            flagsParaCenario(false,false,true,false);
            notificarObservadores();
        }
    }
    
    /**
     * Retorna um objeto da classe Stats com os novos stats após sofrerem dano de acordo com qual stat deve ser
     * danficado.
     * @param PosStats statDanificado que identifica qual stat deve ser danificado e o dano carrega quanto deste
     * stat deve ser danifcado.
     */
    protected Stats criarDano(PosStats statDanificado, int dano)
    {
        if(statDanificado == PosStats.FORCA)
        {
            return(new Stats(dano,0,0,0,0,0));
        }
        if(statDanificado == PosStats.DEFESA)
        {
            return(new Stats(0,dano,0,0,0,0));
        }
        if(statDanificado == PosStats.VELOCIDADE)
        {
            return(new Stats(0,0,dano,0,0,0));
        }
        if(statDanificado == PosStats.MAGIA)
        {
            return(new Stats(0,0,0,dano,0,0));
        }
        if(statDanificado == PosStats.SORTE)
        {
            return(new Stats(0,0,0,0,dano,0));
        }
        if(statDanificado == PosStats.VIDA)
        {
            return(new Stats(0,0,0,0,0,dano));
        }
        return(new Stats(0,0,0,0,0,0));
    }
    
    /**
     * Retorna uma variável do tipo boolean que indica de acordo com o valor do stat de Vida se o jogador foi
     * derrotado ou não de acordo com o dano deferido pelo inimigo no seu turno de jogada.
     */
    protected boolean jogadorDerrotado()
    {
        if(jogador.getStatAtual(PosStats.VIDA) <= 0)
            return true;
        else
            return false;
    }
    
    /**
     * Retorna uma variável do tipo boolean que indica de acordo com o valor do stat de Vida se o inimigo foi
     * derrotado ou não de acordo com o dano deferido pelo jogador no seu turno de jogada.
     */
    protected boolean inimigoDerrotado()
    {
        if(inimigo.getStatAtual(PosStats.VIDA) <= 0)
            return true;
        else
            return false;
    }
    
    /**
     * Retorna um boolean que indica se o jogador consegue fugir ou não do inimigo isto baseado no Stat sorte, se
     * sorte jogador maior que sorte inimigo então retorna-se flag verdadeira e o jogador pode fugir.
     */
    protected boolean fugir()
    {
        if(jogador.getStatAtual(PosStats.SORTE) > inimigo.getStatAtual(PosStats.SORTE))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Varre a lista de itens deixados pelo inimigo, a cada item varrido mostra-se o stats com novo item
     * notifica-se os observadores e espera se o usário irá aceitar ou não para continuar percorrendo os itens, a
     * cada item ocorre essas ações até chegar no valor máximo de ParteDoCorpo.
     */
    protected void itensDeixadosPeloInimigo()
    {
        boolean loop = true;
        parteDoCorpoInimigo++;
        while((parteDoCorpoInimigo < ParteDoCorpo.MAXIMO.valor()) && loop)
        {
            itemDeixado = inimigo.getItem(ParteDoCorpo.values()[parteDoCorpoInimigo]);
            try
            {
                if((jogador.getStat(PosStats.SORTE) + Dado.rolarD(10)) > //item_deixado
                (inimigo.getStat(PosStats.SORTE) + Dado.rolarD(10))) //item_deixado
                {
                    statsComNovoItem = jogador.getStatsTemporarios(itemDeixado);
                    loop = false;
                    setJanelaAtual(Janela.ESCOLHER_ITENS);
                    flagsParaCenario(false,false,false,true);
                    notificarObservadores();
                }
                else
                {
                    parteDoCorpoInimigo++;
                }
            }
            catch(Exception e)
            {
                loop = true;
                parteDoCorpoInimigo++;
            }
        }
    }
    
    /**
     * Calcula os pontos de experiência por ter derrotado o inimigo atual, envia estes dados para o objeto jogador
     * e mostra os novos stats atuais para o usuário no cenário.
     */
    protected void elevarExperiencia()
    {
        int valor = Dado.rolarD(20) + inimigo.getDificuldade();
        jogador.ganharExp(valor); //experiencia_adquirida
    }
    
    /**
     * Recebe as flags realacionados ao cenário, para que este seja atualizado de acordo com as ações do jogo
     * @param boolean novoJogador, boolean novoInimigo, boolean acaoDeLuta e boolean escolherItem são as flags
     * que indicam aos observadores quais foram as ações e mudanças ocorridas no jogo.
     */
    protected void flagsParaCenario(boolean novoJogador, boolean novoInimigo, boolean acaoDeLuta,boolean escolherItem)
    {
        this.novoJogador = novoJogador;
        this.novoInimigo = novoInimigo;
        this.acaoDeLuta = acaoDeLuta;
        this.escolherItem = escolherItem;
    }
    
    /**
     * Recebe as ações do usuário na interface e toma as decisões necessárias para que o jogo funcione (quais
     * métodos devem ser chamados e quais serão as informações a serem mostradas ao usuário).
     * @param recebe mensagem vinda do cenário Mensagem info.
     */
    public void mensagemDoCenario(Mensagem info)
    {
        if(info.valor().contains(Mensagem.ATAQUE.valor()))
        {
            String numeroDoAtaque = info.valor();
            numeroDoAtaque = numeroDoAtaque.replace(Mensagem.ATAQUE.valor(),"");
            ataqueJogador(Integer.valueOf(numeroDoAtaque));
        }
        if(info == Mensagem.FUGIR)
        {
            if(fugir())
            {
                setJanelaAtual(Janela.FUGA);
                flagsParaCenario(false,false,true,false);
                notificarObservadores();
            }
            else
            {
                ataqueInimigo();
            }
        }
        if(info == Mensagem.PROXIMO_INIMIGO_FUGA)
        {
            jogador.curarStatsTotalmente();
            flagsParaCenario(true,false,false,false);
            notificarObservadores();
            criarInimigo();
        }
        if(info == Mensagem.PROXIMO_INIMIGO)
        {
            itensDeixadosPeloInimigo();
            if(parteDoCorpoInimigo == ParteDoCorpo.MAXIMO.valor())
            {
                flagsParaCenario(true,false,false,false);
                notificarObservadores();
                parteDoCorpoInimigo = -1;
                criarInimigo();
            }
        }
        if(info == Mensagem.REJEITA)
        {
            itensDeixadosPeloInimigo();
            if(parteDoCorpoInimigo == ParteDoCorpo.MAXIMO.valor())
            {
                flagsParaCenario(true,false,false,false);
                notificarObservadores();
                parteDoCorpoInimigo = -1;
                criarInimigo();
            }
        }
        if(info == Mensagem.ACEITA)
        {
            try
            {
                jogador.equiparItem(itemDeixado);
            }
            catch(Exception e)
            {
            }
            itensDeixadosPeloInimigo();
            if(parteDoCorpoInimigo == ParteDoCorpo.MAXIMO.valor())
            {
                flagsParaCenario(true,false,false,false);
                notificarObservadores();
                parteDoCorpoInimigo = -1;
                criarInimigo();
            }
        }
        if(info == Mensagem.TENTAR_NOVAMENTE)
        {
            inimigo.curarStatsTotalmente();
            jogador.curarStatsTotalmente();
            setJanelaAtual(Janela.CENARIO_PRINCIPAL);
            flagsParaCenario(true,true,false,false);
            notificarObservadores();
            if(inimigo.getStatAtual(PosStats.VELOCIDADE) > jogador.getStatAtual(PosStats.VELOCIDADE))
            {
                ataqueInimigo();
            }
        }
    }
    
    /**
     * Retorna um vetor de duas posições que guarda o valor do Stats que o jogador  tem no momento do jogo
     * e a segunda posição indica quais são Stats iniciais, Stats antes de receberem qualquer dano.
     * @param PosStats pos para retorna o valor de um stats especifico, por exemplo, Vida.
     */
    public int[] getStatJogador(PosStats pos)
    {
        int[] v = new int[2];
        v[0] = jogador.getStatAtual(pos);
        v[1] = jogador.getStat(pos);
        return v;
    }
    
    /**
     * Retorna o level ao qual o jogador está na hora da chamado do método.
     */
    public int getLvlJogador()
    {
        return jogador.getLvl();
    }
    
    /**
     * Retorna um tipo String com o nome da parte do corpo a qual o item esta alocado.
     * @parte ParteDoCorpo parte indica qual parte do corpo o item esta alocado  
     */
    public String getNomeItem(ParteDoCorpo parte)
    {
        return jogador.getItem(parte).getNome();
    }
    
    /**
     * Retorna o nome do Ataque de acordo com a posição do Ataque.
     * @param int pos numero com a posição para se consultar o nome do ataque.
     */
    public String getNomeAtaque(int pos)
    {
        return jogador.getNomeAtaque(pos);
    }
    
    /**
     * Retorna um vetor boolean com duas posições com a ação do jogador, usado para sinalizar ao cenário se deve
     * ou não haver mudanças de cenário. Após gravar o valor no vetor zera-se os valores nas variáveis que
     * trabalham com as flags de cenário para evitar problemas futuros como, por exemplo, para que o cenário não
     * faça a mesma ação duas vezes.
     */
    public boolean[] getAcaoJogador()
    {
        boolean[] b = new boolean[2];
        b[0] = moverJogador;
        b[1] = jogadorErrou;
        moverJogador = false;
        jogadorErrou = false;
        return b;
    }
    
    /**
     * Retorna um tipo String com o nome do inimigo.
     */
    public String getNomeInimigo()
    {
        return inimigo.getNome();
    }
    
    /**
     * Retorna um tipo Mensagem que diz qual é a classe do inimigo.
     */
    public Mensagem getClasseInimigo()
    {
        return inimigo.getClasse();
    }
    
    /**
     * Retorna um vetor de duas posições que guarda o valor do Stats que o inimigo  tem no momento do jogo e a
     * segunda posição indica quais são Stats iniciais, Stats antes de receberem qualquer dano.
     * @param PosStats pos para retorna o valor de um stats especifico, por exemplo, Vida.
     */
    public int[] getStatInimigo(PosStats pos)
    {
        int[] v = new int[2];
        v[0] = inimigo.getStatAtual(pos);
        v[1] = inimigo.getStat(pos);
        return v;
    }
    
    /**
     * Retorna um vetor boolean com duas posições com a ação do inimigo, usado para sinalizar ao cenário se deve
     * ou não haver mudanças de cenário. Após gravar o valor no vetor zera-se os valores nas variáveis que trabalham
     * com as flags de cenário para evitar problemas futuros como, por exemplo, para que o cenário não faça a mesma
     * ação duas vezes. 
     */
    public boolean[] getAcaoInimigo()
    {
        boolean[] b = new boolean[2];
        b[0] = moverInimigo;
        b[1] = inimigoErrou;
        moverInimigo = false;
        inimigoErrou = false;
        return b;
    }
    
    /**
     * Retorna o valor de um Stat de acordo com a posição na enum Posstats com o valor caso se tenha um novo item
     * equipado.
     * @param PosStats pos que indicam a posição do stats na enum 
     */
    public int getStatComNovoItem(PosStats pos)
    {
        return statsComNovoItem.getUmStat(pos);
    }
    
    /**
     * Retorna o nome do Item deixado pelo inimigo
     */
    public String getNomeItemDeixado()
    {
        return itemDeixado.getNome();
    }
    
    /**
     * Retorna em qual janela o jogo atualmente está.
     */
    public Janela getJanelaAtual()
    {
        return janelaAtual;
    }
    
    /**
     * Guarda as flags que indicam que um inimigo ou um Jogador foi criado a ação de luta e se o usuario escolheu
     * ou não um item.
     * Após gravar o valor no vetor zera-se os valores nas variáveis que trabalham com as flags de cenário para
     * evitar problemas futuros como, por exemplo, para que o cenário não faça a mesma ação duas vezes. 
     */
    public boolean[] getFlagsParaCenario()
    {
        boolean[] b = new boolean[4];
        b[0] = novoJogador;
        b[1] = novoInimigo;
        b[2] = acaoDeLuta;
        b[3] = escolherItem;
        novoJogador = false;
        novoInimigo = false;
        acaoDeLuta = false;
        escolherItem = false;
        return b;
    }
    
    /**
     * Passa o valor contido em moveu e errou para os parâmetros de ação do jogador para que o cenário seja
     * informado de tal ação.
     * @param boolean moveu e boolean errou, flags que indicão ações do jogador
     */
    protected void setAcaoJogador(boolean moveu, boolean errou)
    {
        moverJogador = moveu;
        jogadorErrou = errou;
    }
    
    /** 
     * Passa o valor contido em moveu e errou para os parâmetros de ação do Inimigo para que o cenário seja
     * informado de tal ação.
     * @param boolean moveu e boolean errou, flags que indicão ações do Inimigo.
     */
    protected void setAcaoInimigo(boolean moveu, boolean errou)
    {
        moverInimigo = moveu;
        inimigoErrou = errou;
    }
    
    /**
     * Muda o valor da variável que informa qual deve ser a janela de informações apresentada ao jogador.
     * @param Janela novo contendo o novo valor para a variável janelaAtual.
     */
    protected void setJanelaAtual(Janela novo)
    {
        janelaAtual = novo;
    }
    
    /**
     * Adiciona um observador na lista de observadores. Isto ocorre para que o padrão Observer funcione.
     * @param Observador novoObservador é o novo Observador a ser adicionado a lista de observadores.
     */
    public void adicionarObservador(Observador novoObservador)
    {
        listaDeObservadores.add(novoObservador);
    }
    
    /**
     * Remove um observador na lista de observador. Isto ocorre para que o padrão Observer funcione.
     * @param Observador aSerRetirado  é o Observador que sera removido da lista de observadores.
     */
    public void removerObservador(Observador aSerRetirado)
    {
        listaDeObservadores.remove(aSerRetirado);
    }
    
    /**
     * Percorre a lista de Observadores e os notificam de todas as mudanças que ocorreram no programa.
     */
    public void notificarObservadores()
    {
        int i = 0;
        try
        {
            Observador atual = listaDeObservadores.get(i);
            while(atual != null)
            {
                atual.update(this);
                i++;
                atual = listaDeObservadores.get(i);
            }
        }
        catch(Exception e)
        {
        }
    }
    
    /**
     * Método a ser sobrescrito na EngineBasica e que faz com que o padrão factory funcione.
     * Cria a Classe desejada de acordo com a Mesagem recebida, usada para se criar Jogador.
     * @param Mensagem classe carrega a informação de qual classe deve ser criada (Mago, Arqueiro ou Cavaleiro).
     */
    public abstract ClassePura fabricarClasse(Mensagem classe);
   
    /**
     * Método a ser sobrescrito na EngineBasica e que faz com que o padrão factory funcione.
     * Cria a Classe desejada de acordo com o inteiro  recebido, usada para se criar inimigo.
     * @param int classe carrega a informação de qual classe deve ser criada (Mago, Arqueiro ou Cavaleiro).
     */
    public abstract ClassePura fabricarClasse(int classe);
}
