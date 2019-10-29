package br.ufrpe.revcare.infra;

public class RevCareRunTimeException  extends RuntimeException {

    public RevCareRunTimeException(String message) {
        super(message);
    }
    public RevCareRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}


