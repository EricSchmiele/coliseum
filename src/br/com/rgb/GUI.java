package br.com.rgb;

import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.File;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe responsável por mostrar as informações necessárias para o usuário através de uma interface gráfica. Também requisita e
 * recebe informações do usuário. Ela implementa a interface ActionListener para monitorar as ações produzidas pelos
 * eventos gerados por cada botão da interface e também implementa Observador para conseguir se comunicar com a Engine.
 * 
 * @author RGB 
 * @version 28/01/16
 */
public class GUI implements ActionListener, Observador
{

    private JFrame frame;
    

    private JPanel painelNomePersonagem;
    private JPanel painelClassePersonagem;
    private JPanel painelDificuldade;
    private JPanel painelCenarioPrincipal;
    private JPanel painelMenuJogador;
    private JPanel painelEscolherItens;
    private JPanel painelInimigoDerrotado;
    private JPanel painelVitoria;
    private JPanel painelDerrota;
    private JPanel painelFuga;
    private JPanel[] paineis;

    private JPanel painelCabecalho1;
    private JPanel painelCabecalho2;
    private JPanel painelCabecalho3;
    

    private JLabel escolhaNome;
    private JLabel escolhaClasse;
    private JLabel arqueiro;
    private JLabel cavaleiro;
    private JLabel mago;
    private JLabel escolhaDificuldade;
    private JLabel nomeCabecalho1;
    private JLabel nomeCabecalho2;
    private JLabel nomeCabecalho3;
    private JLabel vidaCabecalho1;
    private JLabel vidaCabecalho2;
    private JLabel vidaCabecalho3;
    private JLabel levelCabecalho1;
    private JLabel levelCabecalho2;
    private JLabel levelCabecalho3;
    private JLabel classeCabecalho1;
    private JLabel classeCabecalho2;
    private JLabel classeCabecalho3;
    private JLabel infoInimigo;
    private JLabel jogador;
    private JLabel jogadorErrou;
    private JLabel inimigo;
    private JLabel inimigoErrou;
    private JLabel forcaAtual;
    private JLabel defesaAtual;
    private JLabel velocidadeAtual;
    private JLabel magiaAtual;
    private JLabel sorteAtual;
    private JLabel vidaAtual;
    private JLabel cabecaAtual;
    private JLabel troncoAtual;
    private JLabel bracosAtual;
    private JLabel pesAtual;
    private JLabel mao1Atual;
    private JLabel mao2Atual;
    private JLabel anelAtual;
    private JLabel forca;
    private JLabel defesa;
    private JLabel velocidade;
    private JLabel magia;
    private JLabel sorte;
    private JLabel vida;
    private JLabel textoStatsAtuais;
    private JLabel textoStatsComNovoItem;
    private JLabel forcaComNovoItem;
    private JLabel defesaComNovoItem;
    private JLabel velocidadeComNovoItem;
    private JLabel magiaComNovoItem;
    private JLabel sorteComNovoItem;
    private JLabel vidaComNovoItem;
    private JLabel textoNovoItem;
    private JLabel novoItem;
    private JLabel mensagemInimigoDerrotado;
    private JLabel jogadorContinua;
    private JLabel inimigoDerrotado;
    private JLabel mensagemVitoria;
    private JLabel vitoria;
    private JLabel mensagemDerrota;
    private JLabel derrota;
    private JLabel mensagemFuga;
    private JLabel fuga;

    private JTextField campoTextoNome;
    

    private JButton botaoNomeOK;
    private JButton botaoArqueiro;
    private JButton botaoCavaleiro;
    private JButton botaoMago;
    private JButton botaoDificuldadeOK;
    private JButton botaoAtaque0;
    private JButton botaoAtaque1;
    private JButton botaoAtaque2;
    private JButton botaoFugir;
    private JButton botaoMenu;
    private JButton botaoOK;
    private JButton botaoRejeitaItem;
    private JButton botaoAceitaItem;
    private JButton botaoInimigoDerrotadoOK;
    private JButton botaoVitoriaNovoJogo;
    private JButton botaoTentarNovamente;
    private JButton botaoNovoJogo;
    private JButton botaoFugaOK;
    
    private JComboBox<String> caixaDificuldade;

    private BufferedImage imagemArqueiro;
    private BufferedImage imagemCavaleiro;
    private BufferedImage imagemMago;
    private BufferedImage imagemArqueiroDireita;
    private BufferedImage imagemCavaleiroDireita;
    private BufferedImage imagemMagoDireita;
    private BufferedImage imagemArqueiroEsquerda;
    private BufferedImage imagemCavaleiroEsquerda;
    private BufferedImage imagemMagoEsquerda;
    private BufferedImage imagemArqueiroVitoria;
    private BufferedImage imagemCavaleiroVitoria;
    private BufferedImage imagemMagoVitoria;
    private BufferedImage imagemArqueiroDerrota;
    private BufferedImage imagemCavaleiroDerrota;
    private BufferedImage imagemMagoDerrota;
    private BufferedImage imagemArqueiroInimigoDerrotado;
    private BufferedImage imagemCavaleiroInimigoDerrotado;
    private BufferedImage imagemMagoInimigoDerrotado;
    private BufferedImage imagemArqueiroJogadorContinua;
    private BufferedImage imagemCavaleiroJogadorContinua;
    private BufferedImage imagemMagoJogadorContinua;
    private BufferedImage imagemArqueiroFuga;
    private BufferedImage imagemCavaleiroFuga;
    private BufferedImage imagemMagoFuga;
    
	
    private Engine jogo;
	
	 /**
     *Nome da classe (arqueiro, cavaleiro, mago).
     */
    private Mensagem classe;
	
	
    private String nomeInimigo;
	
	/**
	*String utilizada para a comunicação do usúario com o jogo, quando necessária uma string
	*/
    private String opcaoDoUsuario;
    
      /**
     * Construtor da GUI, cria um objeto da GUI, chamando os inicializadores necessários e iniciando os demais atributos com valores válidos 
     */
    public GUI()
    {
        opcaoDoUsuario = Mensagem.NADA.valor();
        classe = Mensagem.TODOS;
        nomeInimigo = "Foe";
        initImagens();
        initBotoesEOutros();
        initLabels();
        initPaineisSecundarios();
        initPaineisPrincipais();
        initFrames();
    }
    
    /**
     * Método o qual é responsável por carregar todas as imagens necessárias para posterior utilização nos paíneis, inicializando-as.
     */
    private final void initImagens()
    {
        try
        {
        	imagemArqueiro = ImageIO.read(new File("images/red-mageEsquerda.png"));
            imagemCavaleiro = ImageIO.read(new File("images/fighterEsquerda.png"));
            imagemMago = ImageIO.read(new File("images/black-mageEsquerda.png"));
            imagemArqueiroDireita = ImageIO.read(new File("images/red-mageDireita.png"));
            imagemCavaleiroDireita = ImageIO.read(new File("images/fighterDireita.png"));
            imagemMagoDireita = ImageIO.read(new File("images/black-mageDireita.png"));
            imagemArqueiroEsquerda = ImageIO.read(new File("images/red-mageEsquerda.png"));
            imagemCavaleiroEsquerda = ImageIO.read(new File("images/fighterEsquerda.png"));
            imagemMagoEsquerda = ImageIO.read(new File("images/black-mageEsquerda.png"));
            imagemArqueiroVitoria = ImageIO.read(new File("images/red-mageEsquerda.png"));
            imagemCavaleiroVitoria = ImageIO.read(new File("images/fighterEsquerda.png"));
            imagemMagoVitoria = ImageIO.read(new File("images/black-mageEsquerda.png"));
            imagemArqueiroDerrota = ImageIO.read(new File("images/red-mageEsquerda.png"));
            imagemCavaleiroDerrota = ImageIO.read(new File("images/fighterEsquerda.png"));
            imagemMagoDerrota = ImageIO.read(new File("images/black-mageEsquerda.png"));
            imagemArqueiroInimigoDerrotado = ImageIO.read(new File("images/red-mageDireita.png"));
            imagemCavaleiroInimigoDerrotado = ImageIO.read(new File("images/fighterDireita.png"));
            imagemMagoInimigoDerrotado = ImageIO.read(new File("images/black-mageDireita.png"));
            imagemArqueiroJogadorContinua = ImageIO.read(new File("images/red-mageEsquerda.png"));
            imagemCavaleiroJogadorContinua = ImageIO.read(new File("images/fighterEsquerda.png"));
            imagemMagoJogadorContinua = ImageIO.read(new File("images/black-mageEsquerda.png"));
            imagemArqueiroFuga = ImageIO.read(new File("images/red-mageEsquerda.png"));
            imagemCavaleiroFuga = ImageIO.read(new File("images/fighterEsquerda.png"));
            imagemMagoFuga = ImageIO.read(new File("images/black-mageEsquerda.png"));
        }
        catch(Exception e)
        {
        	System.out.println("Imagens nao carregadas");
        }
    }
    
    /**
     * Método responsável por inicializar os botões, a caixa de texto e o campo de opções com as devidas imagens e textos para posterior utilização nos paíneis.
     */
    private final void initBotoesEOutros()
    {
        campoTextoNome = new JTextField();
        campoTextoNome.setText("Jogador_1");
        campoTextoNome.setBounds(350,500,300,50);
        campoTextoNome.setColumns(10);
        campoTextoNome.setFont(new Font(campoTextoNome.getFont().getName(),Font.PLAIN,30));
    
        botaoNomeOK = new JButton("OK");
        botaoNomeOK.setBounds(410,740,180,50);
        botaoNomeOK.setBackground(Color.WHITE);
        botaoNomeOK.setFont(new Font(botaoNomeOK.getFont().getName(),Font.PLAIN,30));
        botaoNomeOK.setActionCommand(Mensagem.NOME.valor());
        botaoNomeOK.addActionListener(this);
        
        botaoArqueiro = new JButton(new ImageIcon(imagemArqueiro));
        botaoArqueiro.setBounds(100,550,250,260);
        botaoArqueiro.setBackground(Color.WHITE);
        botaoArqueiro.setActionCommand(Mensagem.ARQUEIRO.valor());
        botaoArqueiro.addActionListener(this);
        
        botaoCavaleiro = new JButton(new ImageIcon(imagemCavaleiro));
        botaoCavaleiro.setBounds(375,550,250,260);
        botaoCavaleiro.setBackground(Color.WHITE);
        botaoCavaleiro.setActionCommand(Mensagem.CAVALEIRO.valor());
        botaoCavaleiro.addActionListener(this);
        
        botaoMago = new JButton(new ImageIcon(imagemMago));
        botaoMago.setBounds(650,550,250,260);
        botaoMago.setBackground(Color.WHITE);
        botaoMago.setActionCommand(Mensagem.MAGO.valor());
        botaoMago.addActionListener(this);
        
        botaoDificuldadeOK = new JButton("OK");
        botaoDificuldadeOK.setBounds(410,740,180,50);
        botaoDificuldadeOK.setBackground(Color.WHITE);
        botaoDificuldadeOK.setFont(new Font(botaoDificuldadeOK.getFont().getName(),Font.PLAIN,30));
        botaoDificuldadeOK.setActionCommand(Mensagem.DIFICULDADE_ESCOLHIDA.valor());
        botaoDificuldadeOK.addActionListener(this);
        
        botaoAtaque0 = new JButton("Soco");
        botaoAtaque0.setBounds(50,760,375,50);
        botaoAtaque0.setBackground(Color.WHITE);
        botaoAtaque0.setFont(new Font(botaoAtaque0.getFont().getName(),Font.PLAIN,30));
        botaoAtaque0.setActionCommand(Mensagem.ATAQUE0.valor());
        botaoAtaque0.addActionListener(this);
        
        botaoAtaque1 = new JButton("Chute");
        botaoAtaque1.setBounds(525,760,375,50);
        botaoAtaque1.setBackground(Color.WHITE);
        botaoAtaque1.setFont(new Font(botaoAtaque1.getFont().getName(),Font.PLAIN,30));
        botaoAtaque1.setActionCommand(Mensagem.ATAQUE1.valor());
        botaoAtaque1.addActionListener(this);
        
        botaoAtaque2 = new JButton("Jogar Kunai");
        botaoAtaque2.setBounds(50,830,375,50);
        botaoAtaque2.setBackground(Color.WHITE);
        botaoAtaque2.setFont(new Font(botaoAtaque2.getFont().getName(),Font.PLAIN,30));
        botaoAtaque2.setActionCommand(Mensagem.ATAQUE2.valor());
        botaoAtaque2.addActionListener(this);
        
        botaoFugir = new JButton("Fugir");
        botaoFugir.setBounds(525,830,375,50);
        botaoFugir.setBackground(Color.WHITE);
        botaoFugir.setFont(new Font(botaoFugir.getFont().getName(),Font.PLAIN,30));
        botaoFugir.setActionCommand(Mensagem.FUGIR.valor());
        botaoFugir.addActionListener(this);
        
        botaoMenu = new JButton("MENU");
        botaoMenu.setBounds(375,900,250,50);
        botaoMenu.setBackground(Color.WHITE);
        botaoMenu.setFont(new Font(botaoMenu.getFont().getName(),Font.PLAIN,30));
        botaoMenu.setActionCommand(Mensagem.MENU.valor());
        botaoMenu.addActionListener(this);
        
        botaoOK = new JButton("OK");
        botaoOK.setBounds(375,900,250,50);
        botaoOK.setBackground(Color.WHITE);
        botaoOK.setFont(new Font(botaoOK.getFont().getName(),Font.PLAIN,30));
        botaoOK.setActionCommand(Mensagem.OK.valor());
        botaoOK.addActionListener(this);
        
        botaoRejeitaItem = new JButton("Rejeita");
        botaoRejeitaItem.setBounds(50,900,150,50);
        botaoRejeitaItem.setBackground(Color.WHITE);
        botaoRejeitaItem.setFont(new Font(botaoRejeitaItem.getFont().getName(),Font.PLAIN,30));
        botaoRejeitaItem.setActionCommand(Mensagem.REJEITA.valor());
        botaoRejeitaItem.addActionListener(this);
        
        botaoAceitaItem = new JButton("Aceita");
        botaoAceitaItem.setBounds(800,900,150,50);
        botaoAceitaItem.setBackground(Color.WHITE);
        botaoAceitaItem.setFont(new Font(botaoAceitaItem.getFont().getName(),Font.PLAIN,30));
        botaoAceitaItem.setActionCommand(Mensagem.ACEITA.valor());
        botaoAceitaItem.addActionListener(this);
        
        botaoInimigoDerrotadoOK = new JButton("OK");
        botaoInimigoDerrotadoOK.setBounds(375,900,250,50);
        botaoInimigoDerrotadoOK.setBackground(Color.WHITE);
        botaoInimigoDerrotadoOK.setFont(new Font(botaoInimigoDerrotadoOK.getFont().getName(),Font.PLAIN,30));
        botaoInimigoDerrotadoOK.setActionCommand(Mensagem.PROXIMO_INIMIGO.valor());
        botaoInimigoDerrotadoOK.addActionListener(this);
        
        botaoVitoriaNovoJogo = new JButton("Novo Jogo");
        botaoVitoriaNovoJogo.setBounds(375,900,250,50);
        botaoVitoriaNovoJogo.setBackground(Color.WHITE);
        botaoVitoriaNovoJogo.setFont(new Font(botaoVitoriaNovoJogo.getFont().getName(),Font.PLAIN,30));
        botaoVitoriaNovoJogo.setActionCommand(Mensagem.NOVO_JOGO.valor());
        botaoVitoriaNovoJogo.addActionListener(this);
        
        botaoTentarNovamente = new JButton("Tentar Novamente");
        botaoTentarNovamente.setBounds(100,900,300,50);
        botaoTentarNovamente.setBackground(Color.WHITE);
        botaoTentarNovamente.setFont(new Font(botaoTentarNovamente.getFont().getName(),Font.PLAIN,30));
        botaoTentarNovamente.setActionCommand(Mensagem.TENTAR_NOVAMENTE.valor());
        botaoTentarNovamente.addActionListener(this);
        
        botaoNovoJogo = new JButton("Novo Jogo");
        botaoNovoJogo.setBounds(600,900,300,50);
        botaoNovoJogo.setBackground(Color.WHITE);
        botaoNovoJogo.setFont(new Font(botaoNovoJogo.getFont().getName(),Font.PLAIN,30));
        botaoNovoJogo.setActionCommand(Mensagem.NOVO_JOGO.valor());
        botaoNovoJogo.addActionListener(this);
        
        botaoFugaOK = new JButton("OK");
        botaoFugaOK.setBounds(375,900,250,50);
        botaoFugaOK.setBackground(Color.WHITE);
        botaoFugaOK.setFont(new Font(botaoInimigoDerrotadoOK.getFont().getName(),Font.PLAIN,30));
        botaoFugaOK.setActionCommand(Mensagem.PROXIMO_INIMIGO_FUGA.valor());
        botaoFugaOK.addActionListener(this);
        
        caixaDificuldade = new JComboBox<>(new String[]{"Fácil","Médio","Difícil"});
        caixaDificuldade.setBounds(350,500,300,50);
        caixaDificuldade.setBackground(Color.WHITE);
        caixaDificuldade.setFont(new Font(caixaDificuldade.getFont().getName(),Font.PLAIN,30));
    }
    
    /**
     * Método responsável por inicializar todos os textos da forma necessária para posterior utilização nos paíneis.
     */
    private final void initLabels()
    {
        escolhaNome = new JLabel("Digite seu nome: ",SwingConstants.CENTER);
        escolhaNome.setBounds(382,250,236,35);
        escolhaNome.setFont(new Font(escolhaNome.getFont().getName(),Font.PLAIN,30));
        
        escolhaClasse = new JLabel("Escolha sua classe: ",SwingConstants.CENTER);
        escolhaClasse.setBounds(350,250,300,35);
        escolhaClasse.setFont(new Font(escolhaClasse.getFont().getName(),Font.PLAIN,30));
        
        arqueiro = new JLabel(Mensagem.ARQUEIRO.valor());
        arqueiro.setBounds(170,500,250,35);
        arqueiro.setFont(new Font(arqueiro.getFont().getName(),Font.PLAIN,30));
        
        cavaleiro = new JLabel(Mensagem.CAVALEIRO.valor());
        cavaleiro.setBounds(435,500,250,35);
        cavaleiro.setFont(new Font(cavaleiro.getFont().getName(),Font.PLAIN,30));
        
        mago = new JLabel(Mensagem.MAGO.valor());
        mago.setBounds(740,500,250,35);
        mago.setFont(new Font(mago.getFont().getName(),Font.PLAIN,30));
        
        escolhaDificuldade = new JLabel("Escolha a dificuldade: ",SwingConstants.CENTER);
        escolhaDificuldade.setBounds(350,250,300,35);
        escolhaDificuldade.setFont(new Font(escolhaDificuldade.getFont().getName(),Font.PLAIN,30));
        
        nomeCabecalho1 = new JLabel("Maito Guy");
        nomeCabecalho1.setFont(new Font(nomeCabecalho1.getFont().getName(),Font.PLAIN,30));
        
        nomeCabecalho2 = new JLabel("Maito Guy");
        nomeCabecalho2.setFont(new Font(nomeCabecalho2.getFont().getName(),Font.PLAIN,30));
        
        nomeCabecalho3 = new JLabel("Maito Guy");
        nomeCabecalho3.setFont(new Font(nomeCabecalho3.getFont().getName(),Font.PLAIN,30));
        
        vidaCabecalho1 = new JLabel("Vida: 100/100");
        vidaCabecalho1.setFont(new Font(vidaCabecalho1.getFont().getName(),Font.PLAIN,30));
        
        vidaCabecalho2 = new JLabel("Vida: 100/100");
        vidaCabecalho2.setFont(new Font(vidaCabecalho2.getFont().getName(),Font.PLAIN,30));
        
        vidaCabecalho3 = new JLabel("Vida: 100/100");
        vidaCabecalho3.setFont(new Font(vidaCabecalho3.getFont().getName(),Font.PLAIN,30));
        
        levelCabecalho1 = new JLabel("Level: 10");
        levelCabecalho1.setFont(new Font(levelCabecalho1.getFont().getName(),Font.PLAIN,30));
        
        levelCabecalho2 = new JLabel("Level: 10");
        levelCabecalho2.setFont(new Font(levelCabecalho2.getFont().getName(),Font.PLAIN,30));
        
        levelCabecalho3 = new JLabel("Level: 10");
        levelCabecalho3.setFont(new Font(levelCabecalho3.getFont().getName(),Font.PLAIN,30));
        
        classeCabecalho1 = new JLabel("Classe: Cavaleiro");
        classeCabecalho1.setFont(new Font(classeCabecalho1.getFont().getName(),Font.PLAIN,30));
        
        classeCabecalho2 = new JLabel("Classe: Cavaleiro");
        classeCabecalho2.setFont(new Font(classeCabecalho2.getFont().getName(),Font.PLAIN,30));
        
        classeCabecalho3 = new JLabel("Classe: Cavaleiro");
        classeCabecalho3.setFont(new Font(classeCabecalho3.getFont().getName(),Font.PLAIN,30));
        
        infoInimigo = new JLabel("Kakashi: **********",SwingConstants.RIGHT);
        infoInimigo.setBounds(0,40,850,35);
        infoInimigo.setFont(new Font(infoInimigo.getFont().getName(),Font.PLAIN,30));
        
        jogador = new JLabel(new ImageIcon(imagemCavaleiroEsquerda));
        jogador.setBounds(200,280,250,260);
        
        jogadorErrou = new JLabel("",SwingConstants.CENTER);
        jogadorErrou.setBounds(800,280,100,50);
        jogadorErrou.setFont(new Font(jogadorErrou.getFont().getName(),Font.PLAIN,30));
        
        inimigo = new JLabel(new ImageIcon(imagemCavaleiroDireita));
        inimigo.setBounds(550,280,250,260);
        
        inimigoErrou = new JLabel("",SwingConstants.CENTER);
        inimigoErrou.setBounds(100,280,100,50);
        inimigoErrou.setFont(new Font(inimigoErrou.getFont().getName(),Font.PLAIN,30));
                
        forcaAtual = new JLabel("For: 100/100");
        forcaAtual.setBounds(50,150,200,50);
        forcaAtual.setFont(new Font(forcaAtual.getFont().getName(),Font.PLAIN,30));
        
        defesaAtual = new JLabel("Def: 100/100");
        defesaAtual.setBounds(50,250,200,50);
        defesaAtual.setFont(new Font(defesaAtual.getFont().getName(),Font.PLAIN,30));
        
        velocidadeAtual = new JLabel("Vel: 100/100");
        velocidadeAtual.setBounds(50,350,200,50);
        velocidadeAtual.setFont(new Font(velocidadeAtual.getFont().getName(),Font.PLAIN,30));
        
        magiaAtual = new JLabel("Mag: 0/0");
        magiaAtual.setBounds(50,450,200,50);
        magiaAtual.setFont(new Font(magiaAtual.getFont().getName(),Font.PLAIN,30));
        
        sorteAtual = new JLabel("Sor: 50/50");
        sorteAtual.setBounds(50,550,200,50);
        sorteAtual.setFont(new Font(sorteAtual.getFont().getName(),Font.PLAIN,30));
        
        vidaAtual = new JLabel("Vid: 100/100");
        vidaAtual.setBounds(50,650,200,50);
        vidaAtual.setFont(new Font(vidaAtual.getFont().getName(),Font.PLAIN,30));
        
        cabecaAtual = new JLabel("Cabeça: Cabelo Tijela");
        cabecaAtual.setBounds(400,150,550,50);
        cabecaAtual.setFont(new Font(cabecaAtual.getFont().getName(),Font.PLAIN,30));
        
        troncoAtual = new JLabel("Tronco: Camiseta");
        troncoAtual.setBounds(400,250,550,50);
        troncoAtual.setFont(new Font(troncoAtual.getFont().getName(),Font.PLAIN,30));
        
        bracosAtual = new JLabel("Braços: Ataduras");
        bracosAtual.setBounds(400,350,550,50);
        bracosAtual.setFont(new Font(bracosAtual.getFont().getName(),Font.PLAIN,30));
        
        pesAtual = new JLabel("Pés: Chinelo");
        pesAtual.setBounds(400,450,550,50);
        pesAtual.setFont(new Font(pesAtual.getFont().getName(),Font.PLAIN,30));
        
        anelAtual = new JLabel("Anel: Nada");
        anelAtual.setBounds(400,550,550,50);
        anelAtual.setFont(new Font(anelAtual.getFont().getName(),Font.PLAIN,30));
        
        mao1Atual = new JLabel("Mão Direita: Kunai");
        mao1Atual.setBounds(400,650,550,50);
        mao1Atual.setFont(new Font(mao1Atual.getFont().getName(),Font.PLAIN,30));
        
        mao2Atual = new JLabel("Mão Esquerda: Nada");
        mao2Atual.setBounds(400,750,550,50);
        mao2Atual.setFont(new Font(mao2Atual.getFont().getName(),Font.PLAIN,30));
        
        textoStatsAtuais = new JLabel("Stats atuais",SwingConstants.CENTER);
        textoStatsAtuais.setBounds(200,100,200,50);
        textoStatsAtuais.setFont(new Font(textoStatsAtuais.getFont().getName(),Font.PLAIN,30));
        
        textoStatsComNovoItem = new JLabel("Stats com novo item",SwingConstants.CENTER);
        textoStatsComNovoItem.setBounds(550,100,300,50);
        textoStatsComNovoItem.setFont(new Font(textoStatsComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        forca = new JLabel("For: 100",SwingConstants.CENTER);
        forca.setBounds(200,200,200,50);
        forca.setFont(new Font(forca.getFont().getName(),Font.PLAIN,30));
        
        defesa = new JLabel("Def: 100",SwingConstants.CENTER);
        defesa.setBounds(200,300,200,50);
        defesa.setFont(new Font(defesa.getFont().getName(),Font.PLAIN,30));
        
        velocidade = new JLabel("Vel: 100",SwingConstants.CENTER);
        velocidade.setBounds(200,400,200,50);
        velocidade.setFont(new Font(velocidade.getFont().getName(),Font.PLAIN,30));
        
        magia = new JLabel("Mag: 0",SwingConstants.CENTER);
        magia.setBounds(200,500,200,50);
        magia.setFont(new Font(magia.getFont().getName(),Font.PLAIN,30));
        
        sorte = new JLabel("Sor: 50",SwingConstants.CENTER);
        sorte.setBounds(200,600,200,50);
        sorte.setFont(new Font(sorte.getFont().getName(),Font.PLAIN,30));
        
        vida = new JLabel("Vid: 100",SwingConstants.CENTER);
        vida.setBounds(200,700,200,50);
        vida.setFont(new Font(vida.getFont().getName(),Font.PLAIN,30));
        
        forcaComNovoItem = new JLabel("For: 100",SwingConstants.CENTER);
        forcaComNovoItem.setBounds(600,200,200,50);
        forcaComNovoItem.setFont(new Font(forcaComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        defesaComNovoItem = new JLabel("Def: 100",SwingConstants.CENTER);
        defesaComNovoItem.setBounds(600,300,200,50);
        defesaComNovoItem.setFont(new Font(defesaComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        velocidadeComNovoItem = new JLabel("Vel: 100",SwingConstants.CENTER);
        velocidadeComNovoItem.setBounds(600,400,200,50);
        velocidadeComNovoItem.setFont(new Font(velocidadeComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        magiaComNovoItem = new JLabel("Mag: 0",SwingConstants.CENTER);
        magiaComNovoItem.setBounds(600,500,200,50);
        magiaComNovoItem.setFont(new Font(magiaComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        sorteComNovoItem = new JLabel("Sor: 100",SwingConstants.CENTER);
        sorteComNovoItem.setBounds(600,600,200,50);
        sorteComNovoItem.setFont(new Font(sorteComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        vidaComNovoItem = new JLabel("Vid: 100",SwingConstants.CENTER);
        vidaComNovoItem.setBounds(600,700,200,50);
        vidaComNovoItem.setFont(new Font(vidaComNovoItem.getFont().getName(),Font.PLAIN,30));
        
        textoNovoItem = new JLabel("Novo item",SwingConstants.CENTER);
        textoNovoItem.setBounds(400,800,200,50);
        textoNovoItem.setFont(new Font(textoNovoItem.getFont().getName(),Font.PLAIN,30));
        
        novoItem = new JLabel("Trevo de quatro folhas",SwingConstants.CENTER);
        novoItem.setBounds(200,850,600,50);
        novoItem.setFont(new Font(novoItem.getFont().getName(),Font.PLAIN,30));
        
        mensagemInimigoDerrotado = new JLabel("Inimigo Derrotado",SwingConstants.CENTER);
        mensagemInimigoDerrotado.setBounds(250,80,500,100);
        mensagemInimigoDerrotado.setFont(new Font(mensagemInimigoDerrotado.getFont().getName(),Font.PLAIN,50));
        
        jogadorContinua = new JLabel(new ImageIcon(imagemCavaleiroJogadorContinua));
        jogadorContinua.setBounds(200,280,250,260);
        
        inimigoDerrotado = new JLabel(new ImageIcon(imagemCavaleiroInimigoDerrotado));
        inimigoDerrotado.setBounds(550,280,250,260);
        
        mensagemVitoria = new JLabel("Vitória!",SwingConstants.CENTER);
        mensagemVitoria.setBounds(250,80,500,100);
        mensagemVitoria.setFont(new Font(mensagemVitoria.getFont().getName(),Font.PLAIN,50));
        
        vitoria = new JLabel(new ImageIcon(imagemCavaleiroVitoria));
        vitoria.setBounds(375,280,250,260);
        
        mensagemDerrota = new JLabel("Você foi derrotado!",SwingConstants.CENTER);
        mensagemDerrota.setBounds(250,80,500,100);
        mensagemDerrota.setFont(new Font(mensagemDerrota.getFont().getName(),Font.PLAIN,50));
        
        derrota = new JLabel(new ImageIcon(imagemCavaleiroDerrota));
        derrota.setBounds(375,280,250,260);
        
        mensagemFuga = new JLabel("Você fugiu!",SwingConstants.CENTER);
        mensagemFuga.setBounds(250,80,500,100);
        mensagemFuga.setFont(new Font(mensagemVitoria.getFont().getName(),Font.PLAIN,50));
        
        fuga = new JLabel(new ImageIcon(imagemCavaleiroFuga));
        fuga.setBounds(375,280,250,260);
        
    }
    
    /**
     * Método responsável por inicializar os paíneis secundários para organização de botões e textos, os quais são utilizados pelos paíneis principais posteriormente.
     */
    private final void initPaineisSecundarios()
    {
        painelCabecalho1 = new JPanel();
        painelCabecalho1.setLayout(new GridLayout(1,4));
        painelCabecalho1.setBounds(0,0,1000,35);
        painelCabecalho1.add(nomeCabecalho1);
        painelCabecalho1.add(vidaCabecalho1);
        painelCabecalho1.add(levelCabecalho1);
        painelCabecalho1.add(classeCabecalho1);
        
        painelCabecalho2 = new JPanel();
        painelCabecalho2.setLayout(new GridLayout(1,3));
        painelCabecalho2.setBounds(0,0,1000,35);
        painelCabecalho2.add(nomeCabecalho2);
        painelCabecalho2.add(vidaCabecalho2);
        painelCabecalho2.add(levelCabecalho2);
        painelCabecalho2.add(classeCabecalho2);
        
        painelCabecalho3 = new JPanel();
        painelCabecalho3.setLayout(new GridLayout(1,3));
        painelCabecalho3.setBounds(0,0,1000,35);
        painelCabecalho3.add(nomeCabecalho3);
        painelCabecalho3.add(vidaCabecalho3);
        painelCabecalho3.add(levelCabecalho3);
        painelCabecalho3.add(classeCabecalho3);
    }
    
    /**
     * Método responsável por inicializar os paíneis principais , os quais serão utilizados em momentos oportunos pela janela, para com isto organizar todo conteúdo do jogo seguindo a lógica necessária.
     */
    private final void initPaineisPrincipais()
    {
        painelNomePersonagem = new JPanel();
        painelNomePersonagem.setLayout(null);
        painelNomePersonagem.setBounds(0,0,1000,1000);
        painelNomePersonagem.add(escolhaNome);
        painelNomePersonagem.add(campoTextoNome);
        painelNomePersonagem.add(botaoNomeOK);
        painelNomePersonagem.setVisible(false);
        
        painelClassePersonagem = new JPanel();
        painelClassePersonagem.setLayout(null);
        painelClassePersonagem.setBounds(0,0,1000,1000);
        painelClassePersonagem.add(escolhaClasse);
        painelClassePersonagem.add(arqueiro);
        painelClassePersonagem.add(cavaleiro);
        painelClassePersonagem.add(mago);
        painelClassePersonagem.add(botaoArqueiro);
        painelClassePersonagem.add(botaoCavaleiro);
        painelClassePersonagem.add(botaoMago);
        painelClassePersonagem.setVisible(false);
        
        painelDificuldade = new JPanel();
        painelDificuldade.setLayout(null);
        painelDificuldade.setBounds(0,0,1000,1000);
        painelDificuldade.add(escolhaDificuldade);
        painelDificuldade.add(caixaDificuldade);
        painelDificuldade.add(botaoDificuldadeOK);
        painelDificuldade.setVisible(false);
        
        painelCenarioPrincipal = new JPanel();
        painelCenarioPrincipal.setLayout(null);
        painelCenarioPrincipal.setBounds(0,0,1000,1000);
        painelCenarioPrincipal.add(painelCabecalho1);
        painelCenarioPrincipal.add(infoInimigo);
        painelCenarioPrincipal.add(jogador);
        painelCenarioPrincipal.add(jogadorErrou);
        painelCenarioPrincipal.add(inimigo);
        painelCenarioPrincipal.add(inimigoErrou);
        painelCenarioPrincipal.add(botaoAtaque0);
        painelCenarioPrincipal.add(botaoAtaque1);
        painelCenarioPrincipal.add(botaoAtaque2);
        painelCenarioPrincipal.add(botaoFugir);
        painelCenarioPrincipal.add(botaoMenu);
        painelCenarioPrincipal.setVisible(false);
        
        painelMenuJogador = new JPanel();
        painelMenuJogador.setLayout(null);
        painelMenuJogador.setBounds(0,0,1000,1000);
        painelMenuJogador.add(painelCabecalho2);
        painelMenuJogador.add(forcaAtual);
        painelMenuJogador.add(defesaAtual);
        painelMenuJogador.add(velocidadeAtual);
        painelMenuJogador.add(magiaAtual);
        painelMenuJogador.add(sorteAtual);
        painelMenuJogador.add(vidaAtual);
        painelMenuJogador.add(cabecaAtual);
        painelMenuJogador.add(troncoAtual);
        painelMenuJogador.add(bracosAtual);
        painelMenuJogador.add(pesAtual);
        painelMenuJogador.add(anelAtual);
        painelMenuJogador.add(mao1Atual);
        painelMenuJogador.add(mao2Atual);
        painelMenuJogador.add(botaoOK);
        painelMenuJogador.setVisible(false);
        
        painelEscolherItens = new JPanel();
        painelEscolherItens.setLayout(null);
        painelEscolherItens.setBounds(0,0,1000,1000);
        painelEscolherItens.add(painelCabecalho3);
        painelEscolherItens.add(textoStatsAtuais);
        painelEscolherItens.add(textoStatsComNovoItem);
        painelEscolherItens.add(forca);
        painelEscolherItens.add(defesa);
        painelEscolherItens.add(velocidade);
        painelEscolherItens.add(magia);
        painelEscolherItens.add(sorte);
        painelEscolherItens.add(vida);
        painelEscolherItens.add(forcaComNovoItem);
        painelEscolherItens.add(defesaComNovoItem);
        painelEscolherItens.add(velocidadeComNovoItem);
        painelEscolherItens.add(magiaComNovoItem);
        painelEscolherItens.add(sorteComNovoItem);
        painelEscolherItens.add(vidaComNovoItem);
        painelEscolherItens.add(botaoRejeitaItem);
        painelEscolherItens.add(textoNovoItem);
        painelEscolherItens.add(novoItem);
        painelEscolherItens.add(botaoAceitaItem);
        painelEscolherItens.setVisible(false);
        
        painelInimigoDerrotado = new JPanel();
        painelInimigoDerrotado.setLayout(null);
        painelInimigoDerrotado.setBounds(0,0,1000,1000);
        painelInimigoDerrotado.add(mensagemInimigoDerrotado);
        painelInimigoDerrotado.add(jogadorContinua);
        painelInimigoDerrotado.add(inimigoDerrotado);
        painelInimigoDerrotado.add(botaoInimigoDerrotadoOK);
        painelInimigoDerrotado.setVisible(false);
        
        painelVitoria = new JPanel();
        painelVitoria.setLayout(null);
        painelVitoria.setBounds(0,0,1000,1000);
        painelVitoria.add(mensagemVitoria);
        painelVitoria.add(vitoria);
        painelVitoria.add(botaoVitoriaNovoJogo);
        painelVitoria.setVisible(false);
        
        painelDerrota = new JPanel();
        painelDerrota.setLayout(null);
        painelDerrota.setBounds(0,0,1000,1000);
        painelDerrota.add(mensagemDerrota);
        painelDerrota.add(derrota);
        painelDerrota.add(botaoTentarNovamente);
        painelDerrota.add(botaoNovoJogo);
        painelDerrota.setVisible(false);
        
        painelFuga = new JPanel();
        painelFuga.setLayout(null);
        painelFuga.setBounds(0,0,1000,1000);
        painelFuga.add(mensagemFuga);
        painelFuga.add(fuga);
        painelFuga.add(botaoFugaOK);
        painelFuga.setVisible(false);
        
        paineis = new JPanel[10];
        paineis[0] = painelNomePersonagem;
        paineis[1] = painelClassePersonagem;
        paineis[2] = painelDificuldade;
        paineis[3] = painelCenarioPrincipal;
        paineis[4] = painelMenuJogador;
        paineis[5] = painelEscolherItens;
        paineis[6] = painelInimigoDerrotado;
        paineis[7] = painelVitoria;
        paineis[8] = painelDerrota;
        paineis[9] = painelFuga;
    }
    
    /**
     * Método responsável por inicializar a janela com seus paíneis , especificando sua visibilidade e tamanho.
     */
    private final void initFrames()
    {
        frame = new JFrame("Coliseu");
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        for(int i = 0; i < Janela.MAXIMO.valor(); i++)
        {
            frame.getContentPane().add(paineis[i]);
        }
        frame.setVisible(true);
        
        setPainel(Janela.DIFICULDADE);
    }
    
    /**
     * Método o qual recebe como parâmetro um Objeto da enum Janela para determinar qual painel estará visível (deixando invisíveis os demais).
	 *@param painel Janela (Enum), determina a janela atual necessária.
     */
    public void setPainel(Janela painel)
    {
        if(painel != Janela.MAXIMO)
        {
            paineis[painel.valor()].setVisible(true);
            for(Janela j: Janela.values())
            {
                if(j != Janela.MAXIMO)
                {
                    if(j != painel)
                    {
                        paineis[j.valor()].setVisible(false);
                    }
                }
            }
        }
    }
    
    /**
     *Método responsável  por determinar o nome que será mostrado no campo Nome do Jogador, baseando-se para isso no texto inserido pelo usuário na caixa de texto "campoTextoNome"
     */
    public void setNome()
    {
        String texto = "Nome: " + campoTextoNome.getText();
        nomeCabecalho1.setText(texto);
        nomeCabecalho2.setText(texto);
        nomeCabecalho3.setText(texto);
    }
    
    /**
     *Método responsável por apresentar os valores de vida atual e total (nos diversos campos de vida existentes nos paíneis), utilizando-se para isso de dois parâmetros inteiros, um contendo a vida total e outro a vida atual.
	 *@param  novoValor Int o qual contém o valor atual de vida
	 *@param valorTotal Int o qual contém o valor total de vida
     */
    public void setVidaAtual(int novoValor, int valorTotal)
    {
        String texto = "Vida: " + novoValor + "/" + valorTotal;
        vidaCabecalho1.setText(texto);
        vidaCabecalho2.setText(texto);
        vidaCabecalho3.setText(texto);
        texto = "Vid: " + novoValor + "/" + valorTotal;
        vidaAtual.setText(texto);
        texto = "Vid: " + valorTotal;
        vida.setText(texto);
    }
    
    /**
     *Método responsável por apresentar o valor do level do jogador (nos diversos campos de level existentes nos paíneis), utilizando-se para isso de um parâmetro inteiro , contendo o level atual.
     @param novo Int contendo o level atual do jogador
	 */
    public void setLevel(int novo)
    {
        String texto = "Level: " + novo;
        levelCabecalho1.setText(texto);
        levelCabecalho2.setText(texto);
        levelCabecalho3.setText(texto);
    }
    
    /**
     * Método responsável por apresentar a classe do usuário (nos diversos campos de classe existentes nos paíneis), utilizando-se para isso de um parâmetro do tipo Mensagem (Enum), contendo o nome da classe escolhida.
	 *@param novo Mensagem (Enum) que contém o nome da classe do usuário
     */
    public void setClasse(Mensagem novo)
    {
        String texto = "Classe: " + novo.valor();
        classe = novo;
        classeCabecalho1.setText(texto);
        classeCabecalho2.setText(texto);
        classeCabecalho3.setText(texto);
        if(classe == Mensagem.ARQUEIRO)
        {
            jogador.setIcon(new ImageIcon(imagemArqueiroEsquerda));
            jogadorContinua.setIcon(new ImageIcon(imagemArqueiroJogadorContinua));
            vitoria.setIcon(new ImageIcon(imagemArqueiroVitoria));
            derrota.setIcon(new ImageIcon(imagemArqueiroDerrota));
            fuga.setIcon(new ImageIcon(imagemArqueiroFuga));
        }
        else if(classe == Mensagem.CAVALEIRO)
        {
            jogador.setIcon(new ImageIcon(imagemCavaleiroEsquerda));
            jogadorContinua.setIcon(new ImageIcon(imagemCavaleiroJogadorContinua));
            vitoria.setIcon(new ImageIcon(imagemCavaleiroVitoria));
            derrota.setIcon(new ImageIcon(imagemCavaleiroDerrota));
            fuga.setIcon(new ImageIcon(imagemCavaleiroFuga));
        }
        else if(classe == Mensagem.MAGO)
        {
            jogador.setIcon(new ImageIcon(imagemMagoEsquerda));
            jogadorContinua.setIcon(new ImageIcon(imagemMagoJogadorContinua));
            vitoria.setIcon(new ImageIcon(imagemMagoVitoria));
            derrota.setIcon(new ImageIcon(imagemMagoDerrota));
            fuga.setIcon(new ImageIcon(imagemMagoFuga));
        }
    }
        
    /**
     * Método responsável por atualizar as informações sobre o inimigo (imagens e dados), para isto se utiliza de dois parâmetros, sendo um Objeto do tipo Mensagem (Enum) que contém a classe do inimigo e uma String que contém o nome do inimigo.
	 *@param novoNome String que contém o nome do inimigo.
	 *@param novoClasse Mensagem (Enum) que contém a classe do Inimigo
     */
    public void setInfoInimigo(String novoNome, Mensagem novoClasse)
    {
        nomeInimigo = novoNome;
        if(novoClasse == Mensagem.ARQUEIRO)
        {
            inimigo.setIcon(new ImageIcon(imagemArqueiroDireita));
            inimigoDerrotado.setIcon(new ImageIcon(imagemArqueiroInimigoDerrotado));
        }
        else if(novoClasse == Mensagem.CAVALEIRO)
        {
            inimigo.setIcon(new ImageIcon(imagemCavaleiroDireita));
            inimigoDerrotado.setIcon(new ImageIcon(imagemCavaleiroInimigoDerrotado));
        }
        else if(novoClasse == Mensagem.MAGO)
        {
            inimigo.setIcon(new ImageIcon(imagemMagoDireita));
            inimigoDerrotado.setIcon(new ImageIcon(imagemMagoInimigoDerrotado));
        }
    }
    
    /**
     * Método responável por atualizar a barra de vida do inimigo para isto se utiliza de dois parâmetros inteiros, um com a vida total do inimigo e outra com a vida atual, fazendo a divisão entre estes valores chega-se a proporção demonstrada na barra
     *@param novoValor Int que contém o valor atual de vida do inimigo
	 *@param valorTotal Int que contém o valor total de vida do inimigo
	 */
    public void setVidaInimigo(int novoValor, int valorTotal)
    {
        int vidaInimigo = novoValor * 10;
        vidaInimigo /= valorTotal;
        String texto = " " + nomeInimigo;
        for(int i = 0; i < vidaInimigo; i++)
        {
            texto = "*" + texto;
        }
        infoInimigo.setText(texto);
    }
    
    /**
     * Método responsável por Determinar o texto do botão de ataque 1 , através de um parâmetro do tipo string, contendo o nome do ataque 0.
     @param novo String a qual contém o nome do ataque 0
	 */
    public void setAtaque0(String novo)
    {
        botaoAtaque0.setText(novo);
    }
    
     /**
     * Método responsável por Determinar o texto do botão de ataque 2 , através de um parâmetro do tipo string, contendo o nome do ataque 1.
     @param novo String a qual contém o nome do ataque 1
	 */
    public void setAtaque1(String novo)
    {
        botaoAtaque1.setText(novo);
    }
    
    /**
     * Método responsável por Determinar o texto do botão de ataque 3 , através de um parâmetro do tipo string, contendo o nome do ataque 2.
     @param novo String a qual contém o nome do ataque 2
	 */
    public void setAtaque2(String novo)
    {
        botaoAtaque2.setText(novo);
    }
    
    /**
     * Método responsável por apresentar os valores atuais e total de força (nos diversos campos de força existente nos paíneis), utilizando-se para isso de dois parâmetros do tipo int , um contendo a força atual e outro a força total.
     *@param novoValor Int que contém o valor atual de força
	 *@param valorTotal Int que contém o valor total de força
	 */
    public void setForcaAtual(int novoValor, int valorTotal)
    {
        String texto = "For: " + novoValor + "/" + valorTotal;
        forcaAtual.setText(texto);
        texto = "For: " + valorTotal;
        forca.setText(texto);
    }
    
    /**
     * Método responsável por apresentar os valores atuais e total de defesa (nos diversos campos de defesa existente nos paíneis), utilizando-se para isso de dois parâmetros do tipo int , um contendo a defesa atual e outro a defesa total.
     *@param novoValor Int que contém o valor atual de defesa
	 *@param valorTotal Int que contém o valor total de defesa
	 */
    public void setDefesaAtual(int novoValor, int valorTotal)
    {
        String texto = "Def: " + novoValor + "/" + valorTotal;
        defesaAtual.setText(texto);
        texto = "Def: " + valorTotal;
        defesa.setText(texto);
    }
    
     /**
     * Método responsável por apresentar os valores atuais e total de velocidade (nos diversos campos de velocidade existente nos paíneis), utilizando-se para isso de dois parâmetros do tipo int , um contendo a velocidade atual e outro a velocidade total.
     *@param novoValor Int que contém o valor atual de velocidade
	 *@param valorTotal Int que contém o valor total de velocidade
	 */
    public void setVelocidadeAtual(int novoValor, int valorTotal)
    {
        String texto = "Vel: " + novoValor + "/" + valorTotal;
        velocidadeAtual.setText(texto);
        texto = "Vel: " + valorTotal;
        velocidade.setText(texto);
    }
    
     /**
     * Método responsável por apresentar os valores atuais e total de magia (nos diversos campos de magia existente nos paíneis), utilizando-se para isso de dois parâmetros do tipo int , um contendo a magia atual e outro a magia total.
     *@param novoValor Int que contém o valor atual de magia
	 *@param valorTotal Int que contém o valor total de magia
	 */
    public void setMagiaAtual(int novoValor, int valorTotal)
    {
        String texto = "Mag: " + novoValor + "/" + valorTotal;
        magiaAtual.setText(texto);
        texto = "Mag: " + valorTotal;
        magia.setText(texto);
    }
    
     /**
     * Método responsável por apresentar os valores atuais e total de sorte (nos diversos campos de sorte existente nos paíneis), utilizando-se para isso de dois parâmetros do tipo int , um contendo a sorte atual e outro a sorte total.
     *@param novoValor Int que contém o valor atual de sorte
	 *@param valorTotal Int que contém o valor total de sorte
	 */
    public void setSorteAtual(int novoValor, int valorTotal)
    {
        String texto = "Sor: " + novoValor + "/" + valorTotal;
        sorteAtual.setText(texto);
        texto = "Sor: " + valorTotal;
        sorte.setText(texto);
    }
    
    /**
     * Método responsável por apresentar o nome de equipamento equipado na cabeça do personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do equipamento.
     @param novo String que contém o nome do equipamento equipado na cabeça do personagem
	 */
    public void setCabecaAtual(String novo)
    {
        String texto = "Cabeca: " + novo;
        cabecaAtual.setText(texto);
    }
    
    /**
     * Método responsável por apresentar o nome de equipamento equipado no tronco do personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do equipamento.
     @param novo String que contém o nome do equipamento equipado no tronco do personagem
	 */
    public void setTroncoAtual(String novo)
    {
        String texto = "Tronco: " + novo;
        troncoAtual.setText(texto);
    }
    
    /**
     * Método responsável por apresentar o nome de equipamento equipado nos braços do personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do equipamento.
     @param novo String que contém o nome do equipamento equipado nos braços do personagem
	 */
    public void setBracosAtual(String novo)
    {
        String texto = "Braços: " + novo;
        bracosAtual.setText(texto);
    }
    
     /**
     * Método responsável por apresentar o nome de equipamento equipado nos pés do personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do equipamento.
     @param novo String que contém o nome do equipamento equipado nos pés do personagem
	 */
    public void setPesAtual(String novo)
    {
        String texto = "Pés: " + novo;
        pesAtual.setText(texto);
    }
    
    /**
     * Método responsável por apresentar o nome do anel equipado no personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do anel.
     @param novo String que contém o nome do anel equipado no personagem
	 */
    public void setAnelAtual(String novo)
    {
        String texto = "Anel: " + novo;
        anelAtual.setText(texto);
    }
    
    /**
     * Método responsável por apresentar o nome de equipamento equipado na mão direita do personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do equipamento.
     @param novo String que contém o nome do equipamento equipado na mão direita do personagem
	 */
    public void setMao1Atual(String novo)
    {
        String texto = "Mão Direita: " + novo;
        mao1Atual.setText(texto);
    }
    
    /**
     * Método responsável por apresentar o nome de equipamento equipado na mão esquerda do personagem e escrevê-lo no campo especifico do menu, recebendo para isso uma String com o nome do equipamento.
     @param novo String que contém o nome do equipamento equipado na mão esquerda do personagem
	 */
    public void setMao2Atual(String novo)
    {
        String texto = "Mão Esquerda: " + novo;
        mao2Atual.setText(texto);
    }
    
    /**
     * Método responsável por exibir o valor de força com o novo item equipado no painel "frameEscolherItens", utilizando um parâmetro inteiro com o valor para este atributo do novo item.
     @param novo Int com o valor de força adicionado pelo novo item
	 */
    public void setForcaComNovoItem(int novo)
    {
        String texto = "For: " + novo;
        forcaComNovoItem.setText(texto);
    }
    
    /**
     * Método responsável por exibir o valor de defesa com o novo item equipado no painel "frameEscolherItens", utilizando um parâmetro inteiro com o valor para este atributo do novo item.
     @param novo Int com o valor de defesa adicionado pelo novo item
	 */
    public void setDefesaComNovoItem(int novo)
    {
        String texto = "Def: " + novo;
        defesaComNovoItem.setText(texto);
    }
    
     /**
     * Método responsável por exibir o valor de velocidade com o novo item equipado no painel "frameEscolherItens", utilizando um parâmetro inteiro com o valor para este atributo do novo item.
     @param novo Int com o valor de velocidade adicionado pelo novo item
	 */
    public void setVelocidadeComNovoItem(int novo)
    {
        String texto = "Vel: " + novo;
        velocidadeComNovoItem.setText(texto);
    }
    
    /**
     * Método responsável por exibir o valor de magia com o novo item equipado no painel "frameEscolherItens", utilizando um parâmetro inteiro com o valor para este atributo do novo item.
     @param novo Int com o valor de magia adicionado pelo novo item
	 */
    public void setMagiaComNovoItem(int novo)
    {
        String texto = "Mag: " + novo;
        magiaComNovoItem.setText(texto);
    }
    
    /**
     * Método responsável por exibir o valor de sorte com o novo item equipado no painel "frameEscolherItens", utilizando um parâmetro inteiro com o valor para este atributo do novo item.
     @param novo Int com o valor de sorte adicionado pelo novo item
	 */
    public void setSorteComNovoItem(int novo)
    {
        String texto = "Sor: " + novo;
        sorteComNovoItem.setText(texto);
    }
    
    /**
     * Método responsável por exibir o valor de vida com o novo item equipado no painel "frameEscolherItens", utilizando um parâmetro inteiro com o valor para este atributo do novo item.
     @param novo Int com o valor de vida adicionado pelo novo item
	 */
    public void setVidaComNovoItem(int novo)
    {
        String texto = "Vid: " + novo;
        vidaComNovoItem.setText(texto);
    }
    
    /**
     * Método responsável por exibir o nome do novo item a ser equipado no painel "frameEscolherItens".
	 @param novo String a qual contém o nome do novo item a ser equipado
     */
    public void setNovoItem(String novo)
    {
        novoItem.setText(novo);
    }
    
    /**
     * Método responsável por dar um get no nome escolhido para o personagem pelo jogador.
     */
    public String getNome()
    {
        return campoTextoNome.getText();
    }
    
    /**
     * Método responsável por dar um get na classe escolhida para o personagem pelo jogador.
     */
    public Mensagem getClasse()
    {
        return classe;
    }
    
    /**
     * Método responsável por dar um get na dificuldade escolhida para o jogo pelo jogador.
     */
    public int getDificuldade()
    {
        return caixaDificuldade.getSelectedIndex();
    }
    
	/**
	* Método responsável por mover o jogador para simular graficamente a realização de um ataque
	*/
    public void moverJogador()
    {
        jogador.setBounds(250,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        jogador.setBounds(300,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        jogador.setBounds(350,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        jogador.setBounds(300,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        jogador.setBounds(250,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        jogador.setBounds(200,280,250,260);
    }
    
	/**
	* Método responsável por mostrar ao jogador que este realizou um ataque que falhou em acertar o inimigo.
	*/
    public void missJogador()
    {
        jogadorErrou.setText("Errou");
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
        }
        jogadorErrou.setText("");
    }
    
	/**
	* Método responsável por mover o inimigo para simular graficamente a realização de um ataque
	*/
    public void moverInimigo()
    {
        inimigo.setBounds(500,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        inimigo.setBounds(450,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        inimigo.setBounds(400,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        inimigo.setBounds(450,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        inimigo.setBounds(500,280,250,260);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
        }
        inimigo.setBounds(550,280,250,260);
    }
    
	/**
	* Método responsável por mostrar ao jogador que o inimigo realizou um ataque que falhou em acertar o jogador.
	*/
    public void missInimigo()
    {
        inimigoErrou.setText("Errou");
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
        }
        inimigoErrou.setText("");
    }
    
    /**
     * Método responsável por Ler as ações causadas pelos eventos dos botões (em tempo real) , através de um parâmetro do tipo ActionEvent, determinando qual será o valor da variável opcaoDoUsuario(através do método getActionComand).
     @param e ActionEvent que contém o evento ocorrido
	 */
    public void actionPerformed(ActionEvent e)
    {
        opcaoDoUsuario = e.getActionCommand();
        fazerAcaoDoUsuario();
    }
    
    /**
     * Método responsável por realizar as ações do usuário e chamar os métodos necessários para tratar cada ação possível, através do valor da opcaoDoUsuario, setada pelo método actionPerformed, além de resetar o valor da opcaoDoUsuario para um valor nulo (devido a este evento já ter sido tratado).
     */
    public void fazerAcaoDoUsuario()
    {
        String acao = opcaoDoUsuario;
        opcaoDoUsuario = Mensagem.NADA.valor();
        if(acao.equals(Mensagem.DIFICULDADE_ESCOLHIDA.valor()))
        {
            setPainel(Janela.NOME_PERSONAGEM);
        }
        if(acao.equals(Mensagem.NOME.valor()))
        {
            setNome();
            setPainel(Janela.CLASSE_PERSONAGEM);
        }
        if(acao.equals(Mensagem.ARQUEIRO.valor()))
        {
            setClasse(Mensagem.ARQUEIRO);
            jogo = new EngineBasica(getNome(),getClasse(),getDificuldade(),this);
        }
        if(acao.equals(Mensagem.CAVALEIRO.valor()))
        {
            setClasse(Mensagem.CAVALEIRO);
            jogo = new EngineBasica(getNome(),getClasse(),getDificuldade(),this);
        }
        if(acao.equals(Mensagem.MAGO.valor()))
        {
            setClasse(Mensagem.MAGO);
            jogo = new EngineBasica(getNome(),getClasse(),getDificuldade(),this);
        }
        if(acao.equals(Mensagem.ATAQUE0.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.ATAQUE0);
        }
        if(acao.equals(Mensagem.ATAQUE1.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.ATAQUE1);
        }
        if(acao.equals(Mensagem.ATAQUE2.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.ATAQUE2);
        }
        if(acao.equals(Mensagem.FUGIR.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.FUGIR);
        }
        if(acao.equals(Mensagem.MENU.valor()))
        {
            setPainel(Janela.MENU_JOGADOR);
        }
        if(acao.equals(Mensagem.OK.valor()))
        {
            setPainel(Janela.CENARIO_PRINCIPAL);
        }
        if(acao.equals(Mensagem.PROXIMO_INIMIGO.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.PROXIMO_INIMIGO);
        }
        if(acao.equals(Mensagem.PROXIMO_INIMIGO_FUGA.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.PROXIMO_INIMIGO_FUGA);
        }
        if(acao.equals(Mensagem.REJEITA.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.REJEITA);
        }
        if(acao.equals(Mensagem.ACEITA.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.ACEITA);
        }
        if(acao.equals(Mensagem.TENTAR_NOVAMENTE.valor()))
        {
            jogo.mensagemDoCenario(Mensagem.TENTAR_NOVAMENTE);
        }
        if(acao.equals(Mensagem.NOVO_JOGO.valor()))
        {
            setPainel(Janela.DIFICULDADE);
        }
    }
    
    /**
     * Método responsável por perceber que houve mudança na Engine que requererá mudanças na GUI, recebendo como parâmetro um objeto do tipo Observavel.
	 @param observavelMudado Observavel Engine com os valores alterados para ser utilizada nas alterações necessárias da GUI.
     */
    public void update(Observavel observavelMudado)
    {
        Engine jogoMudado = (Engine)observavelMudado;
        boolean baux[] = new boolean[4];
        baux = jogoMudado.getFlagsParaCenario();
        if(baux[0])
        {
            jogadorCriado(jogoMudado);
        }
        if(baux[1])
        {
            inimigoCriado(jogoMudado);
        }
        if(baux[2])
        {
            acaoDeJogo(jogoMudado);
        }
        if(baux[3])
        {
            escolherItem(jogoMudado);
        }
    }
    
    /**
     * Método responsável por "criar" o jogador, com os atributos , equipamentos , level e ataques existentes para o mesmo, utilizando para isto um parâmetro do tipo Engine.
	 @param jogoMudado Engine , com as informações necessárias para a GUI setar os atributos, equipamentos, level e ataques do personagem.
     */
    public void jogadorCriado(Engine jogoMudado)
    {
        //Deve informar todos os stats, o level, os itens e os ataques do jogador.
        int vaux[] = new int[2];
        vaux = jogoMudado.getStatJogador(PosStats.FORCA);
        setForcaAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.DEFESA);
        setDefesaAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.VELOCIDADE);
        setVelocidadeAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.MAGIA);
        setMagiaAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.SORTE);
        setSorteAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.VIDA);
        setVidaAtual(vaux[0],vaux[1]);
        setLevel(jogoMudado.getLvlJogador());
        setCabecaAtual(jogoMudado.getNomeItem(ParteDoCorpo.CABECA));
        setTroncoAtual(jogoMudado.getNomeItem(ParteDoCorpo.TRONCO));
        setBracosAtual(jogoMudado.getNomeItem(ParteDoCorpo.BRACOS));
        setPesAtual(jogoMudado.getNomeItem(ParteDoCorpo.PES));
        setAnelAtual(jogoMudado.getNomeItem(ParteDoCorpo.ANEL));
        setMao1Atual(jogoMudado.getNomeItem(ParteDoCorpo.MAO1));
        setMao2Atual(jogoMudado.getNomeItem(ParteDoCorpo.MAO2));
        setAtaque0(jogoMudado.getNomeAtaque(0));
        setAtaque1(jogoMudado.getNomeAtaque(1));
        setAtaque2(jogoMudado.getNomeAtaque(2));
        setPainel(jogoMudado.getJanelaAtual());
    }
    
      /**
     * Método responsável por "criar" o inimigo, com os atributos , classe e nome , utilizando para isto um parâmetro do tipo Engine.
	 @param jogoMudado Engine , com as informações necessárias para a GUI setar os atributos, classe e nome do inimigo.
     */
    public void inimigoCriado(Engine jogoMudado)
    {
        //Deve informar o nome, a classe e os stats do inimigo.
        int vaux[] = new int[2];
        setInfoInimigo(jogoMudado.getNomeInimigo(),jogoMudado.getClasseInimigo());
        vaux = jogoMudado.getStatInimigo(PosStats.VIDA);
        setVidaInimigo(vaux[0],vaux[1]);
        setPainel(jogoMudado.getJanelaAtual());
    }
    
    /**
     * Método responsável por lidar com as ações de jogo (ataques) tanto do inimigo quando do personagem, para isso utilizando-se um parâmetro do tipo Engine.
     *@param jogoMudado Engine com as informações sobre os status atuais do jogador e do inimigo após um ataque com ou sem sucesso.
	 */
    public void acaoDeJogo(Engine jogoMudado)
    {
        //Deve informar os stats do jogador e do inimigo.
        int vaux[] = new int[2];
        boolean baux[] = new boolean[2];
        baux = jogoMudado.getAcaoJogador();
        if(baux[0])
        {
            moverJogador();
            if(baux[1])
            {
                missJogador();
            }
            vaux = jogoMudado.getStatInimigo(PosStats.VIDA);
            setVidaInimigo(vaux[0],vaux[1]);
        }
        baux = jogoMudado.getAcaoInimigo();
        if(baux[0])
        {
            moverInimigo();
            if(baux[1])
            {
                missInimigo();
            }
            vaux = jogoMudado.getStatJogador(PosStats.FORCA);
            setForcaAtual(vaux[0],vaux[1]);
            vaux = jogoMudado.getStatJogador(PosStats.DEFESA);
            setDefesaAtual(vaux[0],vaux[1]);
            vaux = jogoMudado.getStatJogador(PosStats.VELOCIDADE);
            setVelocidadeAtual(vaux[0],vaux[1]);
            vaux = jogoMudado.getStatJogador(PosStats.MAGIA);
            setMagiaAtual(vaux[0],vaux[1]);
            vaux = jogoMudado.getStatJogador(PosStats.SORTE);
            setSorteAtual(vaux[0],vaux[1]);
            vaux = jogoMudado.getStatJogador(PosStats.VIDA);
            setVidaAtual(vaux[0],vaux[1]);
        }
        setPainel(jogoMudado.getJanelaAtual());
    }
    
    /**
     * Método responsável por lidar com o equipamento de itens, ou seja , atualizar os valores do atributo do jogador e os atributos do novo item, utilizando-se de um parâmetro do tipo Engine.
     *@param jogoMudado Engine com as informações sobre os atributos do jogador e os atributos do novo item equipado
	 */
    public void escolherItem(Engine jogoMudado)
    {
        //Deve informar o novo item, os stats do jogador e os stats com o novo item.
        int vaux[] = new int[2];
        setNovoItem(jogoMudado.getNomeItemDeixado());
        vaux = jogoMudado.getStatJogador(PosStats.FORCA);
        setForcaAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.DEFESA);
        setDefesaAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.VELOCIDADE);
        setVelocidadeAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.MAGIA);
        setMagiaAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.SORTE);
        setSorteAtual(vaux[0],vaux[1]);
        vaux = jogoMudado.getStatJogador(PosStats.VIDA);
        setVidaAtual(vaux[0],vaux[1]);
        setForcaComNovoItem(jogoMudado.getStatComNovoItem(PosStats.FORCA));
        setDefesaComNovoItem(jogoMudado.getStatComNovoItem(PosStats.DEFESA));
        setVelocidadeComNovoItem(jogoMudado.getStatComNovoItem(PosStats.VELOCIDADE));
        setMagiaComNovoItem(jogoMudado.getStatComNovoItem(PosStats.MAGIA));
        setSorteComNovoItem(jogoMudado.getStatComNovoItem(PosStats.SORTE));
        setVidaComNovoItem(jogoMudado.getStatComNovoItem(PosStats.VIDA));
        setPainel(jogoMudado.getJanelaAtual());
    }
    
    /**
     * 
     */
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        while(true)
        {
        }
    }
}
