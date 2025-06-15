package missao;

import ambiente.*;
import arquivos.*;
import main.MenuHelper;
import robos.*;

//missao para ver se uma posicao do mapa esta vazia, se estiver dentro do raio de verificacao do robo
public class MissaoVerificarVazio implements Missao{
    protected int x, y, z;
    String comandoMissao = "VERVAZIO";

    //x, y, z representam a posicao que sera verificada pra ver se eh vazia
    public MissaoVerificarVazio(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //raio de verificacao eh o minimo dos sensores (para usar o gerenciador de sensores)
    @Override
    public void executar(AgenteInteligente ai, Ambiente amb){
        String msgMissao = "Rodando Missao de Verificacao de Vazio com robo " + ai.getNome() + ": \n";
        
        double raioVerificacao = ai.getGerenciadorSensores().getRaioMinimo();

        msgMissao += "Raio de verificacao (minimo dos raios no gerenciador de sensores): " + raioVerificacao + "\n";

        double dist = Math.sqrt(Math.pow(x - ai.getX(), 2) + Math.pow(y - ai.getY(), 2) + Math.pow(z - ai.getZ(), 2));
        msgMissao += "A posicao (" + x + ", " + y + ", " + z + "), a uma distancia " + (int)(dist) + 
                    " do robo " + ai.getNome() + ", esta ";

        if(dist < raioVerificacao){
            if(amb.estaOcupado(x, y, z) == false){
                msgMissao += "vazia.";
            }
            else{
                msgMissao += "ocupada.";
            }
        }

        else{
            msgMissao += "muito distante para ser verificada";
        }
        msgMissao += "\n";

        Salvar.escreverMissao(msgMissao);

        System.out.println("---Escreveu no Arquivo---");
        System.out.println(msgMissao);
        System.out.println("--------------------------");
    }

    @Override
    public String getComando() {
        return comandoMissao;
    }

    //primeiros 2 valores sao comando de atribuicao e comando de missao, entao ve de 2 a 4
    @Override
    public Missao formatarParaMissao(String[] comDividido){
        MissaoVerificarVazio novaMissao = null;
        if(comDividido.length > 4){
            if(MenuHelper.ehInt(comDividido[2]) && MenuHelper.ehInt(comDividido[3]) && MenuHelper.ehInt(comDividido[4])){
                novaMissao = new MissaoVerificarVazio(Integer.parseInt(comDividido[2]), 
                                                        Integer.parseInt(comDividido[3]), 
                                                        Integer.parseInt(comDividido[4]));
                
            }
        }
        return novaMissao;
    }

    @Override
    public String formatoEntrada(){
        return comandoMissao + " x y z";
    }

    @Override
    public String getDescricao(){
        return "----Missao Verificar Vazio: ve se a posicao selecionada esta vazia, se estiver dentro do raio minimo dos sensores----";
    }

    @Override
    public String getExemplo(){
        return comandoMissao + " 10 20 10";
    }

}
