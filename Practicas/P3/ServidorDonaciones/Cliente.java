import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
            
        System.out.println("Probando la funcionalidad del servidor de donaciones...");
        System.out.println("Servidor 1: ");

        String host = "localhost";
        String nombre_s = "servidor1";

        try{
            Registry reg = LocateRegistry.getRegistry(host, 1099);
            Interfaz_cliente donaciones = (Interfaz_cliente)reg.lookup(nombre_s);

            //Probamos a registrarnos con el nombre Roman
            System.out.println("Probamos a registrarnos con el nombre: Roman...");
            if(donaciones.registrar("Roman")){
                //Registro satisfactorio
                System.out.println("OK");
            }
            else{
                System.out.println("Error en el registro");
            }

            //Probamos a ver el total de donaciones, debe dar error en un primer momento
            System.out.println("Probamos a ver el total de donaciones, sin haber donado nada aun...");
            double totalDonaciones = donaciones.getTotalDonaciones("Roman");
            if( totalDonaciones != -1.0){
                System.out.print("El total de donaciones es " + totalDonaciones);
            }
            else{
                System.out.println("Error, no registrado o no has donado aún");
            }

            //Realizamos donacion
            System.out.println("Donamos 250 euros...");
            if(donaciones.donar("Roman", 250.0)){
                System.out.println("Has donado con exito");
            }
            else{
                System.out.println("Error al donar");
            }

            //Intentamos registrarnos en el otro servidor
            Interfaz_cliente donaciones2 = (Interfaz_cliente)reg.lookup("servidor2");

            System.out.println("Probamos a registrarnos con el nombre: Roman... EN LA REPLICA");
            if(donaciones2.registrar("Roman")){
                //Registro satisfactorio
                System.out.println("OK");
            }
            else{
                System.out.println("Error en el registro");
            }

            System.out.println("Probamos a ver el total de donaciones llamando a la replica...");
            totalDonaciones = donaciones2.getTotalDonaciones("Roman");
            if( totalDonaciones != -1.0){
                System.out.println("El total de donaciones es " + totalDonaciones);
            }
            else{
                System.out.println("Error, no registrado o no has donado aún");
            }

            System.out.println("Probamos a registrarnos en el principal con el nombre: Maria...");
            if(donaciones.registrar("Maria")){
                //Registro satisfactorio
                System.out.println("Registro realizado con exito (Mirar terminales de servidores para ver donde se ha realizado)");
            }
            else{
                System.out.println("Error en el registro");
            }

            System.out.println("Probamos a ver el total de donaciones como Maria llamando a la replica...");
            totalDonaciones = donaciones2.getTotalDonaciones("Maria");
            if( totalDonaciones != -1.0){
                System.out.println("El total de donaciones es " + totalDonaciones);
            }
            else{
                System.out.println("Error, no registrado o no ha donado aún");
            }

            System.out.println("Maria realiza una donacion de 300 al servidor principal");
            if(donaciones2.donar("Maria", 300.0)){
                System.out.println("Has donado con exito");
            }
            else{
                System.out.println("Error al donar");
            }

            System.out.println("Probamos a ver el total de donaciones como Maria llamando a la replica...");
            totalDonaciones = donaciones2.getTotalDonaciones("Maria");
            if( totalDonaciones != -1.0){
                System.out.println("El total de donaciones es " + totalDonaciones);
            }
            else{
                System.out.println("Error, no registrado o no ha donado aún");
            }



        } catch(Exception e){
            System.err.println("Excepcion del sistema: " + e);
        }

        System.exit(0);
    }
}