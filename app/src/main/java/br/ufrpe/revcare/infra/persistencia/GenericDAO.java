package br.ufrpe.revcare.infra.persistencia;

import java.io.Closeable;
import java.io.IOException;

import br.ufrpe.revcare.infra.RevCareRunTimeException;

public class GenericDAO {

    protected void close(Closeable... args) throws RevCareRunTimeException {
        for (Closeable closable : args) {
            try {
                closable.close();
            } catch (IOException e) {
                throw new RevCareRunTimeException("Erro ao fechar as conexões",e);
            }
        }
    }
}






class AbstractDAO {

}