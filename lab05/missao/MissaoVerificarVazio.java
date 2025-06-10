package missao;

import ambiente.*;
import arquivos.*;
import robos.*;

//missao para ver se uma posicao do mapa esta vazia, se estiver dentro do raio de verificacao do robo
public class MissaoVerificarVazio implements Missao{
    protected int raioVerificacao;
    protected int x, y, z;

    //x, y, z representam a posicao que sera verificada pra ver se eh vazia
    public MissaoVerificarVazio(int x, int y, int z, int raioVerificacao){
        this.x = x;
        this.y = y;
        this.z = z;
        this.raioVerificacao = raioVerificacao;
    }

    //talvez seja melhor fazer um sensor pra isso, mas
    @Override
    public void executar(Robo r, Ambiente amb){
        String msgMissao = "Rodando Missao de Verificacao de Vazio com robo " + r.getNome() + ": \n";

        double dist = Math.sqrt(Math.pow(x - r.getX(), 2) + Math.pow(y - r.getY(), 2) + Math.pow(z - r.getZ(), 2));
        msgMissao += "A posicao (" + x + ", " + y + ", " + z + "), a uma distancia " + (int)(dist) + 
                    " do robo " + r.getNome() + ", esta ";

        if(dist < raioVerificacao){
            if(amb.estaOcupado(x, y, z) == false){
                msgMissao += "vazia.";
            }
            else{
                msgMissao += "ocupada.";
            }
        }

        else{
            msgMissao += "muito distante para ser verificada(raio de verificacao eh " + raioVerificacao + ")";
        }
        msgMissao += "\n";

        Salvar.escreverMissao(msgMissao);

        System.out.println("---Escreveu no Arquivo---");
        System.out.println(msgMissao);
        System.out.println("--------------------------");
    }

    @Override
    public void imprimirFormatoMissao() {
        System.out.println("MISSAOVF <identificador robo> x y z raio");
    }
}
