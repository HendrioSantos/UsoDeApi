//package repositorios.hendrio.java.filmes.model;
//
//public enum EstaNoPlano {
//    BASICO("Basico", "Basic"),
//    PREMIUM("Premiado", "Premium");
//
//    private final String estaNoPlano;
//    private final String estaNoPlanoIngles;
//
//    EstaNoPlano(String estaNoPlano, String estaNoPlanoIngles) {
//        this.estaNoPlano = estaNoPlano;
//        this.estaNoPlanoIngles = estaNoPlanoIngles;
//    }
//
//    public static EstaNoPlano verificarPlano(String tipoPlano){
//        for (EstaNoPlano estaNoPlano1 : EstaNoPlano.values()){
//            if (estaNoPlano1.estaNoPlano.equalsIgnoreCase(tipoPlano)){
//                return estaNoPlano1;
//            }
//        }
//        throw new IllegalArgumentException("Não foi encontrado " +
//                "nenhuma categoria para o tipo de plano: " + tipoPlano +
//                " Somente temos Basico e Premiado, com as " +
//                "variações em inglês");
//    }
//
//    public static EstaNoPlano verifyPlan(String typePlan){
//        for (EstaNoPlano estaNoPlano1 : EstaNoPlano.values()){
//            if (estaNoPlano1.estaNoPlanoIngles.equalsIgnoreCase(typePlan)){
//                return estaNoPlano1;
//            }
//        }
//        throw new IllegalArgumentException("Não foi encontrado " +
//                "nenhuma categoria para o tipo de plano: " + typePlan +
//                " Somente temos Basic e Premium, com as " +
//                "variações em português");
//    }
//}
