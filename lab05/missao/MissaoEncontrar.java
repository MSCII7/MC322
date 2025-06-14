package missao;

import ambiente.*;
import arquivos.Salvar;
import exceptions.NaoAereoException;
import exceptions.RoboDesligadoException;
import java.util.ArrayList;
import robos.*;
import sensores.SensorLimites;

public class MissaoEncontrar implements Missao {
    TipoObstaculo tipo;
    String comandoMissao = "ENC";

    public MissaoEncontrar(TipoObstaculo t){
        this.tipo = t;
    }

    //exemplo: ME Bond ARVORE atribui a missao de encontrar arvore ao robo Bond



    @Override
    public void executar(AgenteInteligente ai, Ambiente a) {
        //Tentar criar uma lógica de exploração, ativando o sensor de Obstáculos para encontrar os obstáculos ao redor e ver se é do tipo desejado
        String msgMissao = "Rodando Missao de Encontrar Obstaculos com robo " + ai.getNome() + ": \n";
        //Mover para (0,0) ou o mais perto possivel
        try {
            moverPara(ai, a, 0, 0);  
            msgMissao=varrerAmbiente(msgMissao, a, ai);
        } catch (NaoAereoException | RoboDesligadoException e) {
        }
        

        Salvar.escreverMissao(msgMissao);

        System.out.println("---Escreveu no Arquivo---");
        System.out.println(msgMissao);
        System.out.println("--------------------------");
        
    }

    @Override
    public String getComando() {
        return comandoMissao;
    }

    @Override
    public Missao formatarParaMissao(String[] comDividido){
        MissaoEncontrar novaMissao = null;
        if(comDividido.length > 2){
            for(TipoObstaculo t : TipoObstaculo.values()){
                if(comDividido[2].equals(t.toString())){
                    novaMissao = new MissaoEncontrar(t);
                }
            }
        }
        return novaMissao;
    }

    @Override
    public String formatoEntrada(){
        return comandoMissao + " <TipoObstaculo>";
    }

    @Override
    public String getDescricao(){
        return "----Missao Encontrar : encontra todos os obstaculos de um determinado tipo no entorno----";
    }

    @Override
    public String getExemplo(){
        return comandoMissao + " ARVORE";
    }

    private void moverPara(AgenteInteligente ai, Ambiente amb, int fimX, int fimY) throws NaoAereoException, RoboDesligadoException {

        SensorLimites sensor = new SensorLimites(50);
        ai.getGerenciadorSensores().adicionarSensor(sensor);

        int atualX = ai.getX();
        int atualY = ai.getY();

        // Armazena a posição anterior para evitar loops de "vai e vem"
        int prevX = -1;
        int prevY = -1;

        while (atualX != fimX || atualY != fimY) {
            // Calcula o vetor direção para cada eixo (movimento ideal)
            int dirX = Integer.compare(fimX, atualX);
            int dirY = Integer.compare(fimY, atualY);
            
            // Armazena a posição atual antes de qualquer movimento nesta iteração
            // Isso se tornará a "posição anterior" na próxima iteração.
            int tempX = atualX;
            int tempY = atualY;

            // Tenta mover-se na direção X ideal
            if (dirX != 0 && ai.getGerenciadorSensores().estaLivre(atualX + dirX, atualY, 0, amb)) {
                ai.getControleMovimento().moverPara(atualX + dirX, atualY, 0, amb);
                atualX += dirX;
            }
            // Se o caminho X estiver bloqueado, tenta mover-se na direção Y ideal
            else if (dirY != 0 && ai.getGerenciadorSensores().estaLivre(atualX, atualY + dirY, 0, amb)) {
                ai.getControleMovimento().moverPara(atualX, atualY + dirY, 0, amb);
                atualY += dirY;
            }
            // Se ambos os caminhos ideais estiverem bloqueados, procura um caminho alternativo
            else {
                boolean moveu = false;
                // Itera sobre as quatro direções possíveis para encontrar um desvio
                for (int[] dir : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}) { // Direita, Baixo, Esquerda, Cima
                    int proxX = atualX + dir[0];
                    int proxY = atualY + dir[1];

                    if ((proxX != prevX || proxY != prevY) && ai.getGerenciadorSensores().estaLivre(proxX, proxY, 0, amb)) {
                        ai.getControleMovimento().moverPara(proxX, proxY, 0, amb);
                        atualX = proxX;
                        atualY = proxY;
                        moveu = true;
                        break; // Sai do loop de desvio assim que encontrar um caminho
                    }
                }

                if (!moveu) {
                    // Se não se moveu, o robô está preso.
                    // throw new RuntimeException("Robô preso! Não há caminho livre.");
                    return; // Fim do percurso, ponto mais próximo atingido
                }
            }
            
            // Atualiza a posição anterior com a posição em que o robô estava no início desta iteração
            prevX = tempX;
            prevY = tempY;
        }
    }
        


    private String varrerAmbiente(String msgMissao, Ambiente amb, AgenteInteligente ai) throws NaoAereoException, RoboDesligadoException {
        final int passo = 50; // Passo igual ao raio do sensor
        boolean direita = true;
        int xAtual = ai.getX(), yAtual = ai.getY();
        while (true) {
            // Verifica alvo na posição atual antes de mover
            msgMissao = verificarAlvo(msgMissao, ai, amb).mensagem();
            if (verificarAlvo(msgMissao, ai, amb).sucesso()) {
                return msgMissao;
            }

            // Calcula próxima posição em X
            int proximoX = direita ? xAtual + passo : xAtual - passo;

            // Verifica se pode mover em X
            if (!ai.getGerenciadorSensores().colisaoExtremidades(proximoX, yAtual, 0, amb)) {
                if (ai.getGerenciadorSensores().estaLivre(proximoX, yAtual, 0, amb)){
                    ai.getControleMovimento().moverPara(proximoX, yAtual,0, amb);
                    xAtual = proximoX;
                }else{
                    if (direita){
                        for (int i = proximoX; i > xAtual; i--){
                            if (ai.getGerenciadorSensores().estaLivre(i, yAtual, 0, amb)){
                                ai.getControleMovimento().moverPara(i, yAtual,0, amb);
                                xAtual = i;
                                break;
                            }
                        }
                    } else{
                        for (int i = proximoX; i< xAtual; i++){
                            if (ai.getGerenciadorSensores().estaLivre(i, yAtual, 0, amb)){
                                ai.getControleMovimento().moverPara(i, yAtual,0, amb);
                                xAtual = i;
                                break;
                            }
                        }
                    }
                }
                
            } 
            // Se não pode mover em X, tenta mover em Y
            else {
                int proximoY = yAtual + passo;
                if (!ai.getGerenciadorSensores().colisaoExtremidades(xAtual, proximoY, 0, amb)) {
                    if (ai.getGerenciadorSensores().estaLivre(xAtual, proximoY, passo, amb)){
                        ai.getControleMovimento().moverPara(xAtual, proximoY,0, amb);
                        yAtual = proximoY;
                    }else{  
                        for (int j = proximoY; j > yAtual; j--){
                            if (ai.getGerenciadorSensores().estaLivre(xAtual, j, passo, amb)){
                                ai.getControleMovimento().moverPara(xAtual, j,0, amb);
                                yAtual = j;
                                break;
                            }
                        }
                    }
                    direita = !direita; // Inverte direção
                } else {
                    // Não pode mover nem em X nem em Y - fim da varredura
                    break;
                }
            }
        }
        return msgMissao;
    }


    

    private record Resultado(boolean sucesso, String mensagem) {}
    private Resultado verificarAlvo(String msgMissao, AgenteInteligente ai, Ambiente a){
        try{
            //Mover para a posicao (0,0)
            ai.acionarSensores(a);
            ArrayList<Obstaculo> obstaculos = ai.getObstaculosDentro(a);

            msgMissao += "Ativou Sensor de Obstaculos do robo " + ai.getNome() + 
            ", de raio " + ai.getGerenciadorSensores().getSensorObstaculos().getRaio() + "\n";
            
            msgMissao += "Encontrou os seguintes obstaculos do tipo " + tipo.toString() + ": \n";
            boolean encontrou = false;
            for(Obstaculo ob : obstaculos){
                if(ob.getTipoObstaculo() == tipo){
                    msgMissao += ob.getDescricao() + "\n";
                    encontrou = true;
                }
            }
            if (encontrou)
                return new Resultado(true, msgMissao);
            msgMissao += "Nenhum obstáculo encontrado\n";
        }
        catch(RoboDesligadoException e){
            System.err.println(e.getMessage());
        }
        return new Resultado(false, msgMissao);
    }

    

}
