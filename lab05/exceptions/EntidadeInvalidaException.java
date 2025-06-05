//Para quando uma entidade e criada onde ja ha outra. Utilizado apenas para a inicializacao, util principalmente para 
//ver se nao ha interseccao de obstaculos
package exceptions;

public class EntidadeInvalidaException extends Exception{
    public EntidadeInvalidaException(){
        super("Entidade criada em posicao onde ja ha entidade");
    }
}