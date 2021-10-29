/**
 * La clase modela un sencillo pod�metro que registra informaci�n acerca de
 * los pasos, distancia... que una persona realiza durante una semana
 * 
 * @ Carlos Ar�valo
 * 27-10-21
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7; 
    private String marca;
    private char sexo;
    private double longitudZancada;
    private double altura;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int totalPasosLaborables;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int caminatasNoche;
    private int tiempo;
    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = MUJER;
        longitudZancada = 0;
        altura = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalPasosLaborables = 0;
        totalDistanciaFinSemana = 0;
        totalDistanciaSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;  
    }

    /**
     * Accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     */
    public void configurar(double queAltura, char queSexo) {
        sexo = queSexo;
        altura = queAltura;
        if (sexo == HOMBRE) {
            longitudZancada = Math.ceil(queAltura * 0.45);
        }
        else {
            longitudZancada = Math.floor(queAltura * 0.41);
        }
    }

    /**
     * Recibe cuatro par�metros que supondremos correctos:
     *  pasos - el n� de pasos caminados
     *  dia - n� de d�a de la semana en que se ha hecho la caminata 
     *      (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *  horaInicio � hora de inicio de la caminata
     *  horaFin � hora de fin de la caminata
     * A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     * y  actualizar� el pod�metro adecuadamente  
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) {
        int hora1;
        int hora2;
        hora1 = horaInicio;
        hora2 = horaFin;
        switch (dia){      
            case 1: 
                totalPasosLaborables += pasos;
                break;
            case 2: 
                totalPasosLaborables += pasos;
                break;
            case 3: 
                totalPasosLaborables += pasos;
                break;
            case 4: 
                totalPasosLaborables += pasos;
                break;
            case 5: 
                totalPasosLaborables += pasos;
                totalDistanciaSemana += pasos;
                break;
            case 6:
                totalPasosSabado += pasos;
                break;
            default:
                totalPasosDomingo += pasos;
                break;
        }
        totalDistanciaFinSemana = (totalPasosSabado + totalPasosDomingo) * longitudZancada;
        totalDistanciaSemana = totalPasosLaborables * longitudZancada;
        if (horaInicio >= 2100) {
            caminatasNoche++;
        }
        double hora;
        hora= Math.floor((hora2-hora1)/100);
        double minutos;
        minutos = (((hora2-hora1)/100)-hora)*100;
        if(minutos>60){
            minutos = (minutos - 60);
            hora = (hora+1);
        }
        tiempo = (hora2-hora1);
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado....
     */
    public void printConfiguracion() {
        System.out.println("Configuraci�n del pod�metro");
        System.out.println("*************************");
        System.out.println();
        System.out.println("Altura: " + altura/100 + "mtos");
        System.out.println("Sexo: " + sexo); 
        System.out.println("Longitud zancada: " + longitudZancada/100 + "mtos"); 
    }

    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada) 
     */
    public void printEstad�sticas() {
        System.out.println("Estad�sticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana / 1000 + "km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana / 1000 + "km");
        System.out.println();
        System.out.println("N� pasos d�as laborables: " + totalPasosLaborables);
        System.out.println("N� pasos S�BADO: " + totalPasosSabado);
        System.out.println("N� pasos DOMINGO: " + totalPasosDomingo);
        System.out.println();
        System.out.println("N� caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        System.out.println();
        double hora;
        hora= Math.floor((tiempo)/100);
        double minutos;
        minutos = (((tiempo)/100)-hora)*100;
        if(minutos>60){
            minutos = (minutos - 60);
            hora = (hora + 1);
        }
        System.out.println("Tiempo total caminado en la semana: " + tiempo);
        System.out.println("D�a/s con m�s pasos caminados: " + diaMayorNumeroPasos());
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        int diaMayorNumeroPasos;
        String dia = "";
        if (totalPasosLaborables > totalPasosSabado) {
            diaMayorNumeroPasos = totalPasosLaborables;
            dia = "Laborables";
        }
        else {
            diaMayorNumeroPasos = totalPasosSabado;
            dia = "Sabado";
        }

        if (totalPasosDomingo > diaMayorNumeroPasos) {
            diaMayorNumeroPasos = totalPasosDomingo;
            dia = "Domingo";
        }
        return dia;
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo que se establece a MUJER.
     * La marca no var�a
     */    
    public void reset() {
        sexo = MUJER;
        longitudZancada = 0;
        altura = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalPasosLaborables = 0;
        totalDistanciaFinSemana = 0;
        totalDistanciaSemana = 0;
        tiempo = 0;
        caminatasNoche = 0; 
    }
}