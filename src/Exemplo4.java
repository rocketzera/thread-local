
public class Exemplo4 {

    public static ThreadLocal<Parametro> parametros = new ThreadLocal<>();

    public static void main(String[] args) {

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            Servico servico = new Servico();

            new Thread(() -> {

                servico.salvar(codigo);
                Long codigoRetorno = servico.buscar();

                if (codigo != codigoRetorno)
                    System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);

            }).start();
        }
    }

    public static class Servico {

        public void salvar(Long codigo) {
            parametros.set(new Parametro(codigo));
        }

        public Long buscar() {
            return parametros.get().getCodigo();
        }

    }
}
