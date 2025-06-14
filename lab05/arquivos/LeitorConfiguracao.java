package arquivos;
import ambiente.*;
import java.io.*;
import java.util.Scanner;
import missao.*;
import robos.*;

 
public class LeitorConfiguracao {
    Scanner in = null;
    public LeitorConfiguracao(){

    }
    public Ambiente ler(String arquivo) throws IOException{
        try {
            Ambiente amb=null;
            File arq = new File(arquivo);
            if (!arq.exists())
                throw new FileNotFoundException();
            in = new Scanner(arq);
            while (in.hasNextLine()){
                String linha = in.nextLine();
                String[] palavras = linha.split(" ");
                switch (palavras[0]){
                    case "AMBIENTE" -> amb = construirAmbiente(palavras);
                    case "ROBO" -> geraRobo(palavras, amb);
                    case "OBSTACULO" -> geraObstaculo(palavras, amb);
                    case "MISSAO" -> atribuiMissao(palavras, amb);
                    default -> throw new IOException();
                }
            }
            return amb;
        } catch (Exception e) {
            throw new IOException();
        }
    }
    private Ambiente construirAmbiente(String[] dimensoes) throws IOException{
        try {
            if (dimensoes.length != 4)
                throw new IOException();
            int largura = Integer.parseInt(dimensoes[1]);
            int comprimento = Integer.parseInt(dimensoes[2]);
            int altura = Integer.parseInt(dimensoes[3]);
            Ambiente amb = new Ambiente(largura, comprimento, altura);
            return amb;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private void geraRobo(String[] caracteristicas, Ambiente a) throws Exception{
        try {
            if (a == null || caracteristicas.length < 5)
                throw new IOException();
            String tipo = caracteristicas[1];
            String id = caracteristicas[2];
            int x = Integer.parseInt(caracteristicas[3]);
            int y = Integer.parseInt(caracteristicas[4]);
            int vmax, altMax, z;
            switch (tipo){
                case "Eletrico" -> {
                    if (caracteristicas.length < 6)
                        throw new IOException();
                    vmax = Integer.parseInt(caracteristicas[5]);
                    RoboTerrestreEletrico el = new RoboTerrestreEletrico(id, x, y, vmax);
                    a.adicionarEntidade(el);
                }
                case "Terrestre" -> {
                    if (caracteristicas.length < 6)
                        throw new IOException();
                    vmax = Integer.parseInt(caracteristicas[5]);
                    RoboTerrestre t = new RoboTerrestre(id, x, y, vmax);
                    a.adicionarEntidade(t);
                }
                case "Ambientalista" -> {
                    if (caracteristicas.length < 6)
                        throw new IOException();
                    vmax = Integer.parseInt(caracteristicas[5]);
                    RoboTerrestreAmbientalista am = new RoboTerrestreAmbientalista(id, x, y, vmax);
                    a.adicionarEntidade(am);
                }
                case "Teletransporte" -> {
                    if (caracteristicas.length < 6)
                        throw new IOException();
                    vmax = Integer.parseInt(caracteristicas[5]);
                    RoboTerrestreTeletransporte tp = new RoboTerrestreTeletransporte(id, x, y, vmax);
                    a.adicionarEntidade(tp);
                }
                case "Morador" -> {
                    if (caracteristicas.length < 6)
                        throw new IOException();
                    vmax = Integer.parseInt(caracteristicas[5]);
                    RoboTerrestreMorador mor = new RoboTerrestreMorador(id, x, y, vmax);
                    a.adicionarEntidade(mor);
                }
                case "Panfletario" -> {
                    if (caracteristicas.length < 6)
                        throw new IOException();
                    vmax = Integer.parseInt(caracteristicas[5]);
                    RoboTerrestrePanfletario p = new RoboTerrestrePanfletario(id, x, y, vmax);
                    a.adicionarEntidade(p);
                }
                case "Aereo" -> {
                    if (caracteristicas.length < 7)
                        throw new IOException();
                    z = Integer.parseInt(caracteristicas[5]);
                    altMax = Integer.parseInt(caracteristicas[6]);
                    RoboAereo ae = new RoboAereo(id, x, y, z,altMax);
                    a.adicionarEntidade(ae);
                }
                case "Refletor" -> {
                    if (caracteristicas.length < 7)
                        throw new IOException();
                    z = Integer.parseInt(caracteristicas[5]);
                    altMax =Integer.parseInt(caracteristicas[6]);
                    int altMin =Integer.parseInt(caracteristicas[7]);
                    RoboAereoRefletor ar = new RoboAereoRefletor(id, x, y, z, altMax, altMin);
                    a.adicionarEntidade(ar);
                }
                case "Consciente" -> {
                    if (caracteristicas.length < 7)
                        throw new IOException();
                    z = Integer.parseInt(caracteristicas[5]);
                    altMax =Integer.parseInt(caracteristicas[6]);
                    int distMin =Integer.parseInt(caracteristicas[7]);
                    RoboAereoConsciente ac = new RoboAereoConsciente(id, x, y, z,altMax, distMin);
                    a.adicionarEntidade(ac);
                }
                case "Agente" -> {
                    RoboAgente rg = new RoboAgente(id, x, y);
                    a.adicionarEntidade(rg);
                }
                default -> throw new IOException();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void geraObstaculo(String[] info, Ambiente a) throws Exception{
        try {
            if (info.length < 4)
                throw new IOException();
            String tipo = info[1];
            int x = Integer.parseInt(info[2]);
            int y = Integer.parseInt(info[3]);
            switch (tipo){
                case "Arvore" -> {
                    Obstaculo arv = new Obstaculo(x, y, TipoObstaculo.ARVORE);
                    a.adicionarEntidade(arv);
                }
                case "Arbusto" -> {
                    Obstaculo arb = new Obstaculo(x, y,TipoObstaculo.ARBUSTO);
                    a.adicionarEntidade(arb);
                }
                case "Predio" -> {
                    Obstaculo pr =new Obstaculo(x, y, TipoObstaculo.PREDIO);
                    a.adicionarEntidade(pr);
                }
                case "Casa" -> {
                    Obstaculo ca = new Obstaculo(x, y, TipoObstaculo.CASA);
                    a.adicionarEntidade(ca);
                }
                case "Muro" -> {
                    Obstaculo m = new Obstaculo(x, y, TipoObstaculo.MURO);
                    a.adicionarEntidade(m);
                }
                case "Megamuro" -> {
                    Obstaculo mm = new Obstaculo(x, y, TipoObstaculo.MEGAMURO);
                    a.adicionarEntidade(mm);
                }
                default -> throw new IOException();
            }
            a.visualizarAmbiente(null);
        } catch (Exception e) {
            throw e;

        }

    }
    private void atribuiMissao(String[] info, Ambiente a) throws IOException{
        try {
            if (info.length < 3)
                throw new IOException();
            int id = Integer.parseInt(info[1]);
            if (!(a.getRobos().get(id) instanceof AgenteInteligente ai))
                throw new IOException();
            String tipo = info[2];
            switch (tipo){
                case "EMP" -> {
                    Missao m = new MissaoEMP();
                    ai.definirMissao(m);
                }
                case "Encontrar" -> {
                    if (info.length < 4)
                        throw new IOException();
                    String tipoObs = info[3];
                    TipoObstaculo obs = null;
                    switch (tipoObs){
                        case "Arvore" -> obs = TipoObstaculo.ARVORE;
                        case "Arbusto" -> obs = TipoObstaculo.ARBUSTO;
                        case "Predio" -> obs = TipoObstaculo.PREDIO;
                        case "Casa" -> obs = TipoObstaculo.CASA;
                        case "Muro" -> obs = TipoObstaculo.MURO;
                        case "Megamuro" -> obs =TipoObstaculo.MEGAMURO;
                        
                    }
                    if (obs == null)
                        throw new IOException();
                    Missao me= new MissaoEncontrar(obs);
                    ai.definirMissao(me);
                    ai.executarMissao(a);
                }

                case "VerificarVazio" -> {
                    if (info.length < 6)
                        throw new IOException();
                    int x = Integer.parseInt(info[3]);
                    int y = Integer.parseInt(info[4]);
                    int z = Integer.parseInt(info[5]);
                    Missao mvv = new MissaoVerificarVazio(x, y, z);
                    ai.definirMissao(mvv);
                }
                default -> throw new IOException();

            }
        } catch (Exception e) {
            throw new IOException();
        }
    }
    

}
    