import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor_Principal {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        if(args.length != 2){
            System.out.println("Uso: Servidor nombre nombre_replica");
            System.exit(0);
        }


        try {
            String nombre_s = args[0];
            String replica = args[1];
            Registry reg;
        
            reg = LocateRegistry.getRegistry();
            
            Servidor servidor1 = new Servidor(nombre_s, replica);
            reg.rebind(nombre_s, servidor1);
            System.out.println(nombre_s + " bind: OK");
        } catch (Exception e) {
            System.out.println("Excepcion en Servidor :" + e.getMessage());
        }
    }
}