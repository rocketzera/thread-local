import java.util.HashMap;

public class Exemplo2 {

    public static HashMap<Long, Parametro> concurrentHashMap = new HashMap();

    public static void main(String[] args) {

        Servico servico = new Servico();

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            new Thread(() -> {
                servico.salvar(codigo);

            }).start();
        }
    }

    public static class Servico {

        public void salvar(Long codigo) {
            concurrentHashMap.put(codigo, new Parametro(codigo));
            Long codigoRetorno = concurrentHashMap.get(codigo).getCodigo();

            if (codigo != codigoRetorno)
                System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);
        }
    }
}
