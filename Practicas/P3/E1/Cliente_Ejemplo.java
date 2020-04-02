import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Cliente_Ejemplo {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String nombre_objeto_remoto = "Ejemplo_I";
            System.out.println("Buscando objeto remoto...");
            //Coge el servidor donde esta alojado el registry
            Registry registry = LocateRegistry.getRegistry(args[0]);
            //Busca la clase que esta en el registry segun el nombre del objeto
            Ejemplo_I instancia_local = (Ejemplo_I) registry.lookup(nombre_objeto_remoto);
            System.out.println("Invocando el objeto remoto");
            //Usamos la instancia local del objeto remoto con el numero de proceso pasado por linea de comandos
            instancia_local.escribir_mensaje(Integer.parseInt(args[1])); //Se escribe en el servidor
        } catch(Exception e) {
            System.err.println("Ejemplo_I exception:");
            e.printStackTrace();
        }
    }
}