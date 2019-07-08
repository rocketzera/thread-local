
public class Exemplo4 {

    public static ThreadLocal<Parametro> parametros = new ThreadLocal<>();

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
            parametros.set(new Parametro(codigo));
            Long codigoRetorno = parametros.get().getCodigo();
            if (codigo != codigoRetorno)
                System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);
        }

    }
}
