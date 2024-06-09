package Exceptions;

public class DBException {
    private String msg;

    public Exception(String msg_da_excecao){
        this.msg = msg_da_excecao;
    }

    public String getMsg(){
        return this.msg;
    }
    
}
